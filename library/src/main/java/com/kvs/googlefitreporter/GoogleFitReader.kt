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
import java.time.*
import java.time.temporal.TemporalAccessor
import java.util.*
import java.util.concurrent.TimeUnit

class GoogleFitReader(
    private val activity: Activity,
    private val account: GoogleSignInAccount,
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun accessGoogleFit() {
        val end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX)
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
                Log.i("TAG", "OnSuccess()")
                for (dataSet in response.buckets.flatMap { it.dataSets }) {
                    dumpDataSet(dataSet)
                }
            }
            .addOnFailureListener { e -> Log.d("TAG", "OnFailure()", e) }

        Fitness.getHistoryClient(activity, account)
            .readDailyTotal(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .addOnSuccessListener { dataSet ->
                // Use response data here
                Log.i("TAG", "OnSuccess()")
                dumpDataSet(dataSet)
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dumpDataSet(dataSet: DataSet) {
        Log.i("TAG", "Data returned for Data type: ${dataSet.dataType.name}")
        for (dp in dataSet.dataPoints) {
            Log.i("TAG","Data point:")
            Log.i("TAG","\tType: ${dp.dataType.name}")
            Log.i("TAG","\tStart: ${dp.getStartTimeString()}")
            Log.i("TAG","\tEnd: ${dp.getEndTimeString()}")
            for (field in dp.dataType.fields) {
                Log.i("TAG","\tField: ${field.name.toString()} Value: ${dp.getValue(field)}")
            }
        }
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