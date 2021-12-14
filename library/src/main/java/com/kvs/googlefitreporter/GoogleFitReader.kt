package com.kvs.googlefitreporter

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.HistoryClient
import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.DataReadRequest
import com.kvs.googlefitreporter.model.AggregateResult
import java.lang.Exception
import java.time.*
import java.time.temporal.TemporalAccessor
import java.util.*
import java.util.concurrent.TimeUnit

class GoogleFitReader(
    private val activity: Activity,
    private val account: GoogleSignInAccount,
) {
    // TODO: not clear for what read is
    @RequiresApi(Build.VERSION_CODES.O)
    fun read() {
        val end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX)
        val start = end.minusYears(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()

        val readRequest = DataReadRequest.Builder()
            .read(DataType.TYPE_STEP_COUNT_DELTA)
            .setTimeRange(startSeconds, endSeconds, TimeUnit.SECONDS)
            .enableServerQueries()
            .build()
        Fitness.getHistoryClient(activity, account)
            .readData(readRequest)
            .addOnSuccessListener { response ->
                // Use response data here
                Log.i("READ", "READ OnSuccess(), ${response.dataSets.count()}")
                for (dataSet in response.dataSets) {
                    dumpDataSet(dataSet)
                }
            }
            .addOnFailureListener { e -> Log.d("READ", "OnFailure()", e) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun aggregate() {
        val end = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT)
        val start = end.minusYears(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()

        val readRequest = DataReadRequest.Builder()
            .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .setTimeRange(startSeconds, endSeconds, TimeUnit.SECONDS)
            .enableServerQueries()
            .bucketByTime(1, TimeUnit.DAYS)
            .build()
        Fitness.getHistoryClient(activity, account)
            .readData(readRequest)
            .addOnSuccessListener { response ->
                // Use response data here
                Log.i("AGGREGATE", "AGGREGATE OnSuccess()")
                for (dataSet in response.buckets.flatMap { it.dataSets }) {
                    //dumpDataSet(dataSet)
                    val aggregateResult = AggregateResult.createFrom(dataSet)
                    Log.i("AGGREGATE JSON", aggregateResult.json)
                }
            }
            .addOnFailureListener { e -> Log.d("AGGREGATE", "OnFailure()", e) }

        Fitness.getHistoryClient(activity, account)
            .readDailyTotal(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .addOnSuccessListener { dataSet ->
                // Use response data here
                Log.i("AGGREGATE", "AGGREGATE OnSuccess()")
                dumpDataSet(dataSet)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dumpDataSet(dataSet: DataSet) {
        var resultString = "Result:\n"
        resultString += "Data returned for Data source: ${dataSet.dataSource.appPackageName}, ${dataSet.dataSource.streamIdentifier}, ${dataSet.dataSource.streamName}, ${dataSet.dataSource.type}\n"
        resultString += "type: ${dataSet.dataType.name}\n"
        resultString += "device: ${dataSet.dataSource.device?.manufacturer}, ${dataSet.dataSource.device?.model}, ${dataSet.dataSource.device?.type}, ${dataSet.dataSource.device?.uid}\n"
        for (dp in dataSet.dataPoints) {
            var dataPointString = "Data point:\n"
            dataPointString += "\tOriginal Data Source: ${dp.originalDataSource.streamName}, ${dp.originalDataSource.streamIdentifier}, ${dp.originalDataSource.appPackageName}, ${dp.originalDataSource.type}\n"
            dataPointString += "\tDevice: ${dp.originalDataSource.device?.manufacturer}, ${dp.originalDataSource.device?.model}, ${dp.originalDataSource.device?.type}, ${dp.originalDataSource.device?.uid}\n"
            dataPointString += "\tType: ${dp.dataType.name}\n"
            dataPointString += "\tAggregate Type: ${dp.dataType.aggregateType?.name}\n"
            dataPointString += "\tStart: ${dp.getStartTimeString()}\n"
            dataPointString += "\tEnd: ${dp.getEndTimeString()}\n"
            for (field in dp.dataType.fields) {
                dataPointString += "\tField: ${field.name} Format: ${field.format} IsOptional: ${field.isOptional}\n"
                dataPointString += "Value: ${dp.getValue(field)}\n"
                dataPointString += "Format: ${dp.getValue(field).format}\n"
                dataPointString += "isSet: ${dp.getValue(field).isSet}\n"
                try {
                    dataPointString += "asActivity: ${dp.getValue(field).asActivity()}\n"

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    dataPointString += "asFloat: ${dp.getValue(field).asFloat()}\n"

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    dataPointString += "asInt: ${dp.getValue(field).asInt()}\n"

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                try {
                    dataPointString += "asString: ${dp.getValue(field).asString()}\n"

                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
            resultString += dataPointString
        }
        Log.i("TAG", resultString)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun DataPoint.getStartTimeString() = Instant.ofEpochSecond(this.getStartTime(TimeUnit.SECONDS))
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime().toString()

    @RequiresApi(Build.VERSION_CODES.O)
    fun DataPoint.getEndTimeString() = Instant.ofEpochSecond(this.getEndTime(TimeUnit.SECONDS))
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime().toString()

}