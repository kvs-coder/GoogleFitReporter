package com.kvs.googlefitreporter

import android.app.Activity

class GoogleFitReporter(private val activity: Activity) {

    val manager = GoogleFitManager(activity)

    val reader: GoogleFitReader get() = GoogleFitReader(activity, manager.account)
    val writer: GoogleFitWriter get() = GoogleFitWriter(activity, manager.account)

}