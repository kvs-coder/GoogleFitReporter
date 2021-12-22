package com.kvs.googlefitreporter.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.lang.Exception
import java.util.concurrent.TimeUnit

@Serializable
data class HistoryResult(
    val dataSource: DataSource,
    val dataType: DataType,
    val dataPoints: List<DataPoint>,
) {

    @Serializable
    data class DataSource(
        val appPackageName: String?,
        val device: Device?,
        val streamIdentifier: String,
        val streamName: String,
        val type: Int
    ) {

        @Serializable
        data class Device(
            val manufacturer: String,
            val model: String,
            val type: Int,
            val uid: String
        ) {
            companion object {
                internal fun createFrom(device: com.google.android.gms.fitness.data.Device) =
                    Device(
                        device.manufacturer,
                        device.model,
                        device.type,
                        device.uid
                    )
            }

            val json: String
                get() = Json.encodeToString(this)
        }

        companion object {
            internal fun createFrom(dataSource: com.google.android.gms.fitness.data.DataSource) =
                DataSource(
                    dataSource.appPackageName,
                    dataSource.device?.let { Device.createFrom(it) },
                    dataSource.streamIdentifier,
                    dataSource.streamName,
                    dataSource.type
                )
        }

        val json: String
            get() = Json.encodeToString(this)

    }

    @Serializable
    data class DataType(val name: String) {

        companion object {
            internal fun createFrom(dataType: com.google.android.gms.fitness.data.DataType) =
                DataType(dataType.name)
        }

        val json: String
            get() = Json.encodeToString(this)

    }

    @Serializable
    data class DataPoint(
        val startTime: Long,
        val endTime: Long,
        val originalDataSource: DataSource,
        val entries: List<Entry>
    ) {

        @Serializable
        data class Entry(val field: Field, val value: Value) {

            @Serializable
            data class Field(val name: String, val format: Int, val isOptional: Boolean?) {

                companion object {
                    internal fun createFrom(field: com.google.android.gms.fitness.data.Field) =
                        Field(
                            field.name,
                            field.format,
                            field.isOptional,
                        )
                }

                val json: String
                    get() = Json.encodeToString(this)

            }

            @Serializable
            data class Value(
                val format: Int,
                val isSet: Boolean,
                val activity: String?,
                val integer: Int?,
                val float: Float?,
                val string: String?
            ) {

                companion object {
                    internal fun createFrom(value: com.google.android.gms.fitness.data.Value): Value {
                        val activity: String? = try {
                            value.asActivity()
                        } catch (e: Exception) {
                            null
                        }
                        val integer: Int? = try {
                            value.asInt()
                        } catch (e: Exception) {
                            null
                        }
                        val float: Float? = try {
                            value.asFloat()
                        } catch (e: Exception) {
                            null
                        }
                        val string: String? = try {
                            value.asString()
                        } catch (e: Exception) {
                            null
                        }
                        return Value(
                            value.format,
                            value.isSet,
                            activity,
                            integer,
                            float,
                            string
                        )
                    }
                }

                val json: String
                    get() = Json.encodeToString(this)

            }

            companion object {
                internal fun createFrom(
                    field: com.google.android.gms.fitness.data.Field,
                    value: com.google.android.gms.fitness.data.Value,
                ) = Entry(
                    Field.createFrom(field),
                    Value.createFrom(value)
                )
            }

            val json: String
                get() = Json.encodeToString(this)

        }

        companion object {
            internal fun createFrom(
                dataPoint: com.google.android.gms.fitness.data.DataPoint,
                timeUnit: TimeUnit = TimeUnit.MILLISECONDS
            ) = DataPoint(
                dataPoint.getStartTime(timeUnit),
                dataPoint.getEndTime(timeUnit),
                DataSource.createFrom(dataPoint.originalDataSource),
                dataPoint.dataType.fields.map { Entry.createFrom(it, dataPoint.getValue(it)) }
            )
        }

        val json: String
            get() = Json.encodeToString(this)

    }

    companion object {
        const val APP_PACKAGE_NAME = "com.google.android.gms"
        const val STEPS_STREAM_NAME = "estimated_steps"

        internal fun createFrom(
            dataSet: com.google.android.gms.fitness.data.DataSet,
        ) = HistoryResult(
            DataSource.createFrom(dataSet.dataSource),
            DataType.createFrom(dataSet.dataType),
            dataSet.dataPoints.map { DataPoint.createFrom(it) }
        )
    }

    val json: String
        get() = Json.encodeToString(this)

}

