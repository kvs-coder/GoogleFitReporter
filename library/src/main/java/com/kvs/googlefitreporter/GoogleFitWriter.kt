package com.kvs.googlefitreporter

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.tasks.Tasks
import com.kvs.googlefitreporter.model.DetailType
import com.kvs.googlefitreporter.model.HealthType
import com.kvs.googlefitreporter.model.InsertResult
import com.kvs.googlefitreporter.resolver.ResolverFactory
import java.util.concurrent.ExecutionException

class GoogleFitWriter(
    private val activity: Activity,
    private val account: GoogleSignInAccount,
) {

    @Throws(ExecutionException::class, InterruptedException::class)
    fun insert(insertResult: InsertResult): Boolean {
        val dataSet = ResolverFactory.create().createInsertDataSet(insertResult)
        val task = Fitness.getHistoryClient(activity, account).insertData(dataSet)
        Tasks.await(task)
        return task.isSuccessful
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun update(
        insertResult: InsertResult,
        startTime: Long,
        endTime: Long
    ): Boolean {
        val request = ResolverFactory.create().createDataUpdateRequest(insertResult, startTime, endTime)
        val task = Fitness.getHistoryClient(activity, account).updateData(request)
        Tasks.await(task)
        return task.isSuccessful
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun delete(
        detailType: DetailType,
        startTime: Long,
        endTime: Long
    ): Boolean {
        val request = ResolverFactory.create().createDataDeleteRequest(detailType, startTime, endTime)
        val task = Fitness.getHistoryClient(activity, account).deleteData(request)
        Tasks.await(task)
        return task.isSuccessful
    }

}
