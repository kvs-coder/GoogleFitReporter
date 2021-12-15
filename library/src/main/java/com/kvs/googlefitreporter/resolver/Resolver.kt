package com.kvs.googlefitreporter.resolver

import com.google.android.gms.fitness.data.*
import com.google.android.gms.fitness.request.DataDeleteRequest
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.request.DataUpdateRequest
import com.kvs.googlefitreporter.model.AggregateResult
import com.kvs.googlefitreporter.model.ActivityType
import com.kvs.googlefitreporter.model.HealthType
import com.kvs.googlefitreporter.model.InsertResult
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
        .setTimeInterval(startTime, endTime, TimeUnit.SECONDS)
        .build()

    override fun createDataDeleteRequest(healthType: HealthType, startTime: Long, endTime: Long): DataDeleteRequest =
        DataDeleteRequest.Builder()
            .setTimeInterval(startTime, endTime, TimeUnit.SECONDS)
            .addDataType(healthType.asOriginal())
            .build()

    override fun createAggregateRequest(healthType: HealthType, startTime: Long, endTime: Long): DataReadRequest {
        val datasource = DataSource.Builder()
            .setAppPackageName(AggregateResult.APP_PACKAGE_NAME)
            .setDataType(healthType.asOriginal())
            .setType(DataSource.TYPE_DERIVED)
            .apply {
                if (healthType == ActivityType.STEP_COUNT_DELTA) {
                    setStreamName(AggregateResult.STEPS_STREAM_NAME)
                }
            }
            .build()
        return DataReadRequest.Builder()
            .aggregate(datasource)
            .setTimeRange(startTime, endTime, TimeUnit.SECONDS)
            .bucketByTime(1, TimeUnit.DAYS)
            .build()
    }

}