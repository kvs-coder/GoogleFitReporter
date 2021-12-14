package com.kvs.googlefitreporter

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.tasks.Tasks
import com.kvs.googlefitreporter.model.HealthType
import com.kvs.googlefitreporter.model.InsertResult
import com.kvs.googlefitreporter.resolver.ResolverFactory

class GoogleFitWriter(
    private val activity: Activity,
    private val account: GoogleSignInAccount,
) {

    @Throws(IllegalStateException::class)
    fun insertData(
        type: HealthType,
        insertResult: InsertResult
    ): Boolean {
        val dataSet = ResolverFactory.createFrom(type).createInsertDataSet(insertResult)
        val task = Fitness.getHistoryClient(activity, account).insertData(dataSet)
        Tasks.await(task)
        return task.isSuccessful
    }

    @Throws(IllegalStateException::class)
    fun updateData(
        type: HealthType,
        insertResult: InsertResult,
        startTime: Long,
        endTime: Long
    ): Boolean {
        val request = ResolverFactory.createFrom(type).createDataUpdateRequest(insertResult, startTime, endTime)
        val task = Fitness.getHistoryClient(activity, account).updateData(request)
        Tasks.await(task)
        return task.isSuccessful
    }

}
