package com.kvs.googlefitreporter.resolver

import com.google.android.gms.fitness.data.*
import com.google.android.gms.fitness.request.DataDeleteRequest
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.request.DataUpdateRequest
import com.kvs.googlefitreporter.model.*
import java.util.concurrent.TimeUnit

internal class Resolver : CommonResolver {

    override fun createInsertDataSet(insertResult: InsertResult): DataSet =
        insertResult.asOriginal()

    override fun createDataUpdateRequest(
        insertResult: InsertResult,
        startTime: Long,
        endTime: Long
    ): DataUpdateRequest = DataUpdateRequest.Builder()
        .setDataSet(insertResult.asOriginal())
        .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS)
        .build()

    override fun createDataDeleteRequest(
        detailType: DetailType,
        startTime: Long,
        endTime: Long
    ): DataDeleteRequest =
        DataDeleteRequest.Builder()
            .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS)
            .addDataType(detailType.asOriginal())
            .build()

    override fun createReadRequest(
        healthType: HealthType,
        startTime: Long,
        endTime: Long
    ): DataReadRequest = DataReadRequest.Builder()
        .read(healthType.asOriginal())
        .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
        .build()

    override fun createAggregateRequest(
        aggregateType: AggregateType,
        startTime: Long,
        endTime: Long
    ): DataReadRequest = DataReadRequest.Builder()
        .apply {
            if (aggregateType == AggregateType.STEP_COUNT_DELTA) {
                val datasource = DataSource.Builder()
                    .setAppPackageName(HistoryResult.APP_PACKAGE_NAME)
                    .setDataType(aggregateType.asOriginal())
                    .setType(DataSource.TYPE_DERIVED)
                    .setStreamName(HistoryResult.STEPS_STREAM_NAME)
                    .build()
                aggregate(datasource)
            } else {
                aggregate(aggregateType.asOriginal())
            }
        }
        .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
        .bucketByTime(1, TimeUnit.DAYS)
        .build()
}