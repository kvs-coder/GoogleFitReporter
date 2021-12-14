package com.kvs.googlefitreporter.example

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.gms.fitness.data.DataType
import com.kvs.googlefitreporter.GoogleFitManager.Companion.GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE
import com.kvs.googlefitreporter.GoogleFitReporter
import com.kvs.googlefitreporter.model.HealthType
import com.kvs.googlefitreporter.model.InsertResult
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "GFR-MainActivity"
    }

    private lateinit var reporter: GoogleFitReporter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        reporter = GoogleFitReporter(this)
        reporter.manager.authorize(
            toReadTypes = setOf(
                DataType.AGGREGATE_STEP_COUNT_DELTA,
                DataType.TYPE_STEP_COUNT_DELTA
            ),
            toWriteTypes = setOf(DataType.AGGREGATE_STEP_COUNT_DELTA)
        )
        if (reporter.manager.hasPermissions()) {
            //getGFitData()
            //saveGFitData()
            deleteGFitData()
        } else {
            reporter.manager.requestPermissions()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> when (requestCode) {
                GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE -> getGFitData()
                else -> {
                    // Result wasn't from Google Fit
                }
            }
            else -> {
                // Permission not granted
            }
        }
    }

    private fun saveGFitData() {
        thread {
            try {
                insertDataSteps()
                updateDataSteps()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteGFitData() {
        thread {
            try {
                deleteDataSteps()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getGFitData() {
        thread {
            try {
                readDailyTotalSteps()
                aggregateSteps()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun aggregateSteps() {
        val end = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT)
        val start = end.minusYears(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
        val results = reporter.reader.aggregate(HealthType.STEPS, startSeconds, endSeconds)
        results.forEach {
            Log.i(TAG, "Aggregate ${it.json}")
        }
    }

    private fun readDailyTotalSteps() {
        val results = reporter.reader.readTotalDaily(HealthType.STEPS)
        Log.i(TAG, "Daily total ${results.json}")
    }

    private fun insertDataSteps() {
        val end = LocalDateTime.now()
        val start = end.minusHours(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
        val insertResult = InsertResult(
            applicationContext.packageName,
            "steps_on_fly",
            999,
            startSeconds,
            endSeconds
        )
        val isSuccessful = reporter.writer.insertData(HealthType.STEPS, insertResult)
        Log.i(TAG, "Insert $isSuccessful")
    }

    private fun updateDataSteps() {
        val end = LocalDateTime.now()
        val start = end.minusHours(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
        val insertResult = InsertResult(
            applicationContext.packageName,
            "steps_on_fly",
            100,
            startSeconds,
            endSeconds
        )
        val endOfTheDay = LocalDateTime.now().plusHours(1)
        val startOfTheDay = endOfTheDay.minusHours(3)
        val endOfTheDaySeconds = endOfTheDay.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startOfTheDaySeconds = startOfTheDay.atZone(ZoneId.systemDefault()).toEpochSecond()
        val isSuccessful = reporter.writer.updateData(HealthType.STEPS, insertResult, startOfTheDaySeconds, endOfTheDaySeconds)
        Log.i(TAG, "Update $isSuccessful")
    }

    private fun deleteDataSteps() {
        val endOfTheDay = LocalDateTime.now().plusHours(2)
        val startOfTheDay = endOfTheDay.minusHours(4)
        val endOfTheDaySeconds = endOfTheDay.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startOfTheDaySeconds = startOfTheDay.atZone(ZoneId.systemDefault()).toEpochSecond()
        val isSuccessful = reporter.writer.deleteData(HealthType.STEPS, startOfTheDaySeconds, endOfTheDaySeconds)
        Log.i(TAG, "Delete $isSuccessful")
    }

}