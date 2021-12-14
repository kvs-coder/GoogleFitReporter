package com.kvs.googlefitreporter.resolver

import com.google.android.gms.fitness.data.*
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.request.DataUpdateRequest
import com.kvs.googlefitreporter.model.InsertResult
import java.util.concurrent.TimeUnit

internal class StepCountResolver : CommonResolver {

    companion object {
        private const val APP_PACKAGE_NAME = "com.google.android.gms"
        private const val STREAM_NAME = "estimated_steps"
    }

    @Throws(ClassCastException::class)
    override fun createInsertDataSet(insertResult: InsertResult) = createDataSet(insertResult)

    override fun createDataUpdateRequest(
        insertResult: InsertResult,
        startTime: Long,
        endTime: Long
    ): DataUpdateRequest {
        val dataSet = createDataSet(insertResult)
        return DataUpdateRequest.Builder()
            .setDataSet(dataSet)
            .setTimeInterval(startTime, endTime, TimeUnit.SECONDS)
            .build()
    }

    override fun createAggregateRequest(startTime: Long, endTime: Long): DataReadRequest {
        val datasource = DataSource.Builder()
            .setAppPackageName(APP_PACKAGE_NAME)
            .setDataType(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .setType(DataSource.TYPE_DERIVED)
            .setStreamName(STREAM_NAME)
            .build()
        return DataReadRequest.Builder()
            .aggregate(datasource)
            .setTimeRange(startTime, endTime, TimeUnit.SECONDS)
            .bucketByTime(1, TimeUnit.DAYS)
            .build()
    }

    private fun createDataSet(insertResult: InsertResult): DataSet {
        val dataSource = DataSource.Builder()
            .setAppPackageName(insertResult.appPackageName)
            .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
            .setStreamName(insertResult.streamName)
            .setType(DataSource.TYPE_RAW)
            .build()
        val dataPoint =
            DataPoint.builder(dataSource)
                .setField(Field.FIELD_STEPS, insertResult.value as Int)
                .setTimeInterval(insertResult.startTime, insertResult.endTime, TimeUnit.SECONDS)
                .build()
        return DataSet.builder(dataSource)
            .add(dataPoint)
            .build()
    }
}