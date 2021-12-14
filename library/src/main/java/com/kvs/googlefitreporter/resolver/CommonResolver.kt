package com.kvs.googlefitreporter.resolver

import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.request.DataDeleteRequest
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.request.DataUpdateRequest
import com.kvs.googlefitreporter.model.InsertResult

interface CommonResolver {

    fun createInsertDataSet(insertResult: InsertResult): DataSet

    fun createDataUpdateRequest(
        insertResult: InsertResult,
        startTime: Long,
        endTime: Long
    ): DataUpdateRequest

    fun createAggregateRequest(startTime: Long, endTime: Long): DataReadRequest

    fun createDataDeleteRequest(startTime: Long, endTime: Long): DataDeleteRequest

}