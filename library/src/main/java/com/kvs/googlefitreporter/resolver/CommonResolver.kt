package com.kvs.googlefitreporter.resolver

import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.request.DataReadRequest
import com.kvs.googlefitreporter.model.InsertResult

interface CommonResolver {
    fun createInsertDataSet(insertResult: InsertResult): DataSet
    fun createAggregateRequest(startTime: Long, endTime: Long): DataReadRequest
}