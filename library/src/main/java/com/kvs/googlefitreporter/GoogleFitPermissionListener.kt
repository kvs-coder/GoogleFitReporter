package com.kvs.googlefitreporter

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.fitness.FitnessOptions

interface GoogleFitPermissionListener {
    fun onAcquired(account: GoogleSignInAccount, fitnessOptions: FitnessOptions, success: Boolean)
}