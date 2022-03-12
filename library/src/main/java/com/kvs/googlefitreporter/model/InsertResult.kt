package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.DataPoint
import com.google.android.gms.fitness.data.DataSet
import com.google.android.gms.fitness.data.DataSource
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit

@Serializable
data class InsertResult(
    val appPackageName: String,
    val streamName: String,
    val detailType: DetailType,
    val startTime: Long,
    val endTime: Long,
    val entries: List<Entry>
) {

    @Serializable
    data class Entry(val property: Property, @Contextual val value: Any) {

        companion object {
            fun createFrom(json: String) = Json.decodeFromString<Entry>(json)
        }

        val integer: Int
            get() = value as Int
        val float: Float
            get() = value as Float
        val string: String
            get() = value as String
        val map: Map<String, Float>
            get() = value as Map<String, Float>

        val json: String
            get() = Json.encodeToString(this)
    }

    companion object {
        fun createFrom(json: String) = Json.decodeFromString<InsertResult>(json)
    }

    val json: String
        get() = Json.encodeToString(this)

    @Throws(ClassCastException::class)
    fun asOriginal(): DataSet {
        val dataType = detailType.asOriginal()
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