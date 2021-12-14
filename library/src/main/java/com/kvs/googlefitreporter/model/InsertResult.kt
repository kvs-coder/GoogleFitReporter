package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataSource
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.concurrent.TimeUnit

data class InsertResult(
    val appPackageName: String,
    val streamName: String,
    val healthType: HealthType,
    val startTime: Long,
    val endTime: Long,
    val entries: List<Entry>
) {

    @Serializable
    data class Entry(val field: String, @Contextual val value: Any)

    private val originalDataType: com.google.android.gms.fitness.data.DataType
        get() = healthType.originalType
    private val originalDataSource: DataSource
        get() = DataSource.Builder()
            .setAppPackageName(appPackageName)
            .setDataType(originalDataType)
            .setStreamName(streamName)
            .setType(DataSource.TYPE_RAW)
            .build()
    private val originalDataPoint: DataPoint
        get() =
            DataPoint.builder(originalDataSource)
                .apply {
                    originalDataType.fields.forEach { field ->
                        val value = entries.first { it.field == field.name }.value
                        if (value is Int) setField(field, value)
                        if (value is Float ) setField(field, value)
                        if (value is String ) setField(field, value)
                    }
                }
                .setTimeInterval(startTime, endTime, TimeUnit.SECONDS)
                .build()

    val originalDataSet: DataSet
        get() = DataSet.builder(originalDataSource)
            .add(originalDataPoint)
            .build()
}