package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataSource
import java.util.concurrent.TimeUnit

data class InsertResult(
    val appPackageName: String,
    val streamName: String,
    val healthType: HealthType,
    val startTime: Long,
    val endTime: Long,
    val entries: List<Entry>
) {

    data class Entry(val property: Property, val value: Any) {
        val integer: Int
            get() = value as Int
        val float: Float
            get() = value as Float
        val string: String
            get() = value as String
        val map: Map<String, Float>
            get() = value as Map<String, Float>
    }

    @Throws(ClassCastException::class)
    fun asOriginal(): DataSet {
        val dataType = healthType.asOriginal()
        val dataSource = DataSource.Builder()
            .setAppPackageName(appPackageName)
            .setDataType(dataType)
            .setStreamName(streamName)
            .setType(DataSource.TYPE_RAW)
            .build()
        val dataPointBuilder = DataPoint.builder(dataSource)
        dataPointBuilder.apply {
            dataType.fields.forEach { field ->
                val entry = entries.first { it.property.string == field.name }
                when (entry.property.format) {
                    Property.Format.INTEGER -> setField(field, entry.integer)
                    Property.Format.FLOAT -> setField(field, entry.float)
                    Property.Format.STRING -> setField(field, entry.string)
                    Property.Format.MAP -> setField(field, entry.map)
                }
            }
            setTimeInterval(startTime, endTime, TimeUnit.SECONDS)
        }
        val dataPoint = dataPointBuilder.build()
        return DataSet.builder(dataSource)
            .add(dataPoint)
            .build()
    }
}