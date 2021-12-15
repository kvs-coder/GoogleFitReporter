package com.kvs.googlefitreporter

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.tasks.Tasks
import com.kvs.googlefitreporter.model.AggregateResult
import com.kvs.googlefitreporter.model.HealthType
import com.kvs.googlefitreporter.resolver.ResolverFactory

class GoogleFitReader(
    private val activity: Activity,
    private val account: GoogleSignInAccount,
) {

    @Throws(IllegalStateException::class)
    fun aggregate(
        healthType: HealthType,
        startTime: Long,
        endTime: Long
    ): List<AggregateResult> {
        val readRequest = ResolverFactory.create().createAggregateRequest(healthType, startTime, endTime)
        val task = Fitness.getHistoryClient(activity, account).readData(readRequest)
        val response = Tasks.await(task).buckets
        return response
            .flatMap { it.dataSets }
            .map { AggregateResult.createFrom(it) }
    }

    @Throws(IllegalStateException::class)
    fun readTotalDaily(healthType: HealthType): AggregateResult {
        val task = Fitness.getHistoryClient(activity, account).readDailyTotal(healthType.asOriginal())
        val response = Tasks.await(task)
        return AggregateResult.createFrom(response)
    }

}