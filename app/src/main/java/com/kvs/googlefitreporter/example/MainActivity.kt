package com.kvs.googlefitreporter.example

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.gms.fitness.data.DataType
import com.kvs.googlefitreporter.GoogleFitManager.Companion.GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE
import com.kvs.googlefitreporter.GoogleFitReporter

class MainActivity : AppCompatActivity() {
    private lateinit var reporter: GoogleFitReporter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reporter = GoogleFitReporter(this)
        reporter.manager.authorize(
            toReadTypes = setOf(DataType.AGGREGATE_STEP_COUNT_DELTA),
            toWriteTypes = setOf(DataType.AGGREGATE_STEP_COUNT_DELTA)
        )
        if (reporter.manager.hasPermissions()) {
            reporter.reader.accessGoogleFit()
        } else {
            reporter.manager.requestPermissions()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> when (requestCode) {
                GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE -> reporter.reader.accessGoogleFit()
                else -> {
                    // Result wasn't from Google Fit
                }
            }
            else -> {
                // Permission not granted
            }
        }
    }
}