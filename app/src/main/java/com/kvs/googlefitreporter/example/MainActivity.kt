package com.kvs.googlefitreporter.example

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.kvs.googlefitreporter.GoogleFitManager.Companion.GOOGLE_FIT_REPORTER_PERMISSIONS_REQUEST_CODE
import com.kvs.googlefitreporter.GoogleFitReporter
import com.kvs.googlefitreporter.model.ActivityProperty
import com.kvs.googlefitreporter.model.AggregateType
import com.kvs.googlefitreporter.model.DetailType
import com.kvs.googlefitreporter.model.InsertResult
import java.lang.Exception
import java.time.*
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
            toReadTypes = AggregateType.values().toSet(),
            toWriteTypes = DetailType.values().toSet()
        )
        if (reporter.manager.hasPermissions()) {
            getGFitData()
            saveGFitData()
            deleteGFitData()
        } else {
            reporter.manager.requestPermissions()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun aggregateAll() {
        val end = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT)
        val start = end.minusYears(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        AggregateType.values().toSet().forEach { healthType ->
            val results = reporter.reader.aggregate(healthType, startSeconds, endSeconds)
            results.forEach {

                if (it.dataPoints.count() > 0) {
                    Log.i(TAG, "Aggregate ${healthType.string}}: ${it.json}")
                    Log.i(TAG, "Aggregate ${healthType.string}}: ${it.dataPoints.count()}")
                }

            }
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
                aggregateAll()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDataSteps() {
        val end = LocalDateTime.now()
        val start = end.minusHours(1)
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val insertResult = InsertResult(
            applicationContext.packageName,
            "steps_on_fly",
            DetailType.STEP_COUNT_DELTA,
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
        val endSeconds = end.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val insertResult = InsertResult(
            applicationContext.packageName,
            "steps_on_fly",
            DetailType.STEP_COUNT_DELTA,
            startSeconds,
            endSeconds,
            listOf(InsertResult.Entry(ActivityProperty.STEPS, 999))
        )
        val endOfTheDay = LocalDateTime.now().plusHours(1)
        val startOfTheDay = endOfTheDay.minusHours(3)
        val endOfTheDaySeconds = endOfTheDay.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val startOfTheDaySeconds = startOfTheDay.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val isSuccessful = reporter.writer.updateData(insertResult, startOfTheDaySeconds, endOfTheDaySeconds)
        Log.i(TAG, "Update $isSuccessful")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun deleteDataSteps() {
        val endOfTheDay = LocalDateTime.now().plusHours(2)
        val startOfTheDay = endOfTheDay.minusHours(4)
        val endOfTheDaySeconds = endOfTheDay.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val startOfTheDaySeconds = startOfTheDay.atZone(ZoneId.systemDefault()).toEpochMillisecond()
        val isSuccessful = reporter.writer.deleteData(DetailType.STEP_COUNT_DELTA, startOfTheDaySeconds, endOfTheDaySeconds)
        Log.i(TAG, "Delete $isSuccessful")
    }

}

@RequiresApi(Build.VERSION_CODES.O)
fun ZonedDateTime.toEpochMillisecond() = toEpochSecond() * 1000