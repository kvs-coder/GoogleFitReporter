package com.kvs.googlefitreporter

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.FitnessOptions
import com.kvs.googlefitreporter.model.HealthType

class GoogleFitManager(private val activity: Activity) {

    companion object {
        const val GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE = 1000
    }

    private lateinit var fitnessOptions: FitnessOptions

    lateinit var account: GoogleSignInAccount

    @Throws(GoogleFitRequestPermissionsException::class)
    fun hasPermissions(
        toReadTypes: Set<HealthType>,
        toWriteTypes: Set<HealthType>
    ): Boolean {
        fitnessOptions = FitnessOptions.builder().apply {
            toReadTypes.forEach { addDataType(it.asOriginal(), FitnessOptions.ACCESS_READ) }
            toWriteTypes.forEach { addDataType(it.asOriginal(), FitnessOptions.ACCESS_WRITE) }
        }.build()
        account = GoogleSignIn.getAccountForExtension(activity, fitnessOptions)
        if (!this::account.isInitialized || !this::fitnessOptions.isInitialized) {
            throw GoogleFitRequestPermissionsException("Authorize first")
        }
        return GoogleSignIn.hasPermissions(account, fitnessOptions)
    }

    @Throws(GoogleFitRequestPermissionsException::class)
    fun requestPermissions() {
        if (!this::account.isInitialized || !this::fitnessOptions.isInitialized) {
            throw GoogleFitRequestPermissionsException("Authorize first")
        }
        GoogleSignIn.requestPermissions(
            activity,
            GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE,
            account,
            fitnessOptions
        )
    }
}

