package com.kvs.googlefitreporter

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.tasks.Tasks
import com.kvs.googlefitreporter.model.AggregateType
import com.kvs.googlefitreporter.model.HistoryResult
import com.kvs.googlefitreporter.model.HealthType
import com.kvs.googlefitreporter.resolver.ResolverFactory

class GoogleFitReader(
    private val activity: Activity,
    private val account: GoogleSignInAccount,
) {

    /**
     * Aggregates data points in a single result
     */
    @Throws(IllegalStateException::class)
    fun aggregate(
        aggregateType: AggregateType,
        startTime: Long,
        endTime: Long
    ): List<HistoryResult> {
        val readRequest = ResolverFactory.create().createAggregateRequest(aggregateType, startTime, endTime)
        val task = Fitness.getHistoryClient(activity, account).readData(readRequest)
        val response = Tasks.await(task).buckets
        return response
            .flatMap { it.dataSets }
            .map { HistoryResult.createFrom(it) }
    }

    /**
     * Read every data point separately
     */
    @Throws(IllegalStateException::class)
    fun read(
        healthType: HealthType,
        startTime: Long,
        endTime: Long
    ): List<HistoryResult> {
        val readRequest = ResolverFactory.create().createReadRequest(healthType, startTime, endTime)
        val task = Fitness.getHistoryClient(activity, account).readData(readRequest)
        val response = Tasks.await(task)
        return response.dataSets.map { HistoryResult.createFrom(it) }
    }

    @Throws(IllegalStateException::class)
    fun readTotalDaily(healthType: HealthType): HistoryResult {
        val task = Fitness.getHistoryClient(activity, account).readDailyTotal(healthType.asOriginal())
        val response = Tasks.await(task)
        return HistoryResult.createFrom(response)
    }

}