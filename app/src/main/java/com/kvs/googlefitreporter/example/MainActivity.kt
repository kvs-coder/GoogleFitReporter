package com.kvs.googlefitreporter.example

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.kvs.googlefitreporter.GoogleFitManager.Companion.GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE
import com.kvs.googlefitreporter.GoogleFitReporter
import com.kvs.googlefitreporter.model.ActivityProperty
import com.kvs.googlefitreporter.model.ActivityType
import com.kvs.googlefitreporter.model.InsertResult
import com.kvs.googlefitreporter.model.Property
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
                ActivityType.STEP_COUNT_DELTA,
                ActivityType.HEART_RATE_BPM,
                ActivityType.HEART_POINTS,
                ActivityType.HEART_POINTS_SUMMARY,
                ActivityType.HEART_RATE_SUMMARY,
            ),
            toWriteTypes = setOf(ActivityType.STEP_COUNT_DELTA, ActivityType.HEART_RATE_BPM)
        )
        if (reporter.manager.hasPermissions()) {
            getGFitData()
            //saveGFitData()
            //deleteGFitData()
        } else {
            reporter.manager.requestPermissions()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> when (requestCode) {
                GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE -> return
                else -> {
                    // Result wasn't from Google Fit
                }
            }
            else -> {
                // Permission not granted
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun deleteGFitData() {
        thread {
            try {
                deleteDataSteps()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getGFitData() {
        thread {
            try {
                //readDailyTotalSteps()
                //aggregateSteps()
                readDailyTotalHeartRateBPM()
                aggregateHeartRateBPM()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun aggregateSteps() {
        val end = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT)
        val start = end.minusYears(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
        val results = reporter.reader.aggregate(ActivityType.STEP_COUNT_DELTA, startSeconds, endSeconds)
        results.forEach {
            Log.i(TAG, "Aggregate STEPS: ${it.json}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun aggregateHeartRateBPM() {
        val end = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT)
        val start = end.minusYears(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
        val results = reporter.reader.aggregate(ActivityType.HEART_POINTS_SUMMARY, startSeconds, endSeconds)
        results.forEach {
            Log.i(TAG, "Aggregate HR_BPM: ${it.json}")
        }
    }

    private fun readDailyTotalSteps() {
        val results = reporter.reader.readTotalDaily(ActivityType.STEP_COUNT_DELTA)
        Log.i(TAG, "Daily total STEPS: ${results.json}")
    }

    private fun readDailyTotalHeartRateBPM() {
        val results = reporter.reader.readTotalDaily(ActivityType.HEART_RATE_BPM)
        Log.i(TAG, "Daily total HR_BPM: ${results.json}")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDataSteps() {
        val end = LocalDateTime.now()
        val start = end.minusHours(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
        val insertResult = InsertResult(
            applicationContext.packageName,
            "steps_on_fly",
            ActivityType.STEP_COUNT_DELTA,
            startSeconds,
            endSeconds,
            listOf(InsertResult.Entry(ActivityProperty.STEPS, 999))
        )
        val isSuccessful = reporter.writer.insertData(insertResult)
        Log.i(TAG, "Insert $isSuccessful")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateDataSteps() {
        val end = LocalDateTime.now()
        val start = end.minusHours(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
        val insertResult = InsertResult(
            applicationContext.packageName,
            "steps_on_fly",
            ActivityType.STEP_COUNT_DELTA,
            startSeconds,
            endSeconds,
            listOf(InsertResult.Entry(ActivityProperty.STEPS, 999))
        )
        val endOfTheDay = LocalDateTime.now().plusHours(1)
        val startOfTheDay = endOfTheDay.minusHours(3)
        val endOfTheDaySeconds = endOfTheDay.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startOfTheDaySeconds = startOfTheDay.atZone(ZoneId.systemDefault()).toEpochSecond()
        val isSuccessful = reporter.writer.updateData(insertResult, startOfTheDaySeconds, endOfTheDaySeconds)
        Log.i(TAG, "Update $isSuccessful")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun deleteDataSteps() {
        val endOfTheDay = LocalDateTime.now().plusHours(2)
        val startOfTheDay = endOfTheDay.minusHours(4)
        val endOfTheDaySeconds = endOfTheDay.atZone(ZoneId.systemDefault()).toEpochSecond()
        val startOfTheDaySeconds = startOfTheDay.atZone(ZoneId.systemDefault()).toEpochSecond()
        val isSuccessful = reporter.writer.deleteData(ActivityType.STEP_COUNT_DELTA, startOfTheDaySeconds, endOfTheDaySeconds)
        Log.i(TAG, "Delete $isSuccessful")
    }

}