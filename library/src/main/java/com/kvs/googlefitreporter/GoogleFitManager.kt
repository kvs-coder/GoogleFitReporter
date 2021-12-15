package com.kvs.googlefitreporter

import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType

class GoogleFitManager(private val activity: Activity) {

    companion object {
        const val GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE = 1000
    }

    private lateinit var fitnessOptions: FitnessOptions

    lateinit var account: GoogleSignInAccount

    fun authorize(
        toReadTypes: Set<DataType>,
        toWriteTypes: Set<DataType>
    ) {
        fitnessOptions = FitnessOptions.builder().apply {
            toReadTypes.forEach { addDataType(it, FitnessOptions.ACCESS_READ) }
            toWriteTypes.forEach { addDataType(it, FitnessOptions.ACCESS_WRITE) }
        }.build()
        account = GoogleSignIn.getAccountForExtension(activity, fitnessOptions)
    }

    @Throws(GoogleFitRequestPermissionsException::class)
    fun hasPermissions(): Boolean {
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

