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
        val type: Type
    ) {

        @Serializable
        data class Type(val id: Int, val description: String) {

            companion object {
                internal fun createFrom(type: Int) =
                    when (type) {
                        com.google.android.gms.fitness.data.DataSource.TYPE_RAW -> Type(0, "raw")
                        com.google.android.gms.fitness.data.DataSource.TYPE_DERIVED -> Type(
                            1,
                            "derived"
                        )
                        else -> Type(
                            -1,
                            "unknown"
                        )
                    }

            }

        }

        @Serializable
        data class Device(
            val manufacturer: String,
            val model: String,
            val type: Type,
            val uid: String
        ) {
            @Serializable
            data class Type(val id: Int, val description: String) {

                companion object {
                    internal fun createFrom(id: Int) =
                        when (id) {
                            com.google.android.gms.fitness.data.Device.TYPE_UNKNOWN -> Type(
                                0,
                                "unknown"
                            )
                            com.google.android.gms.fitness.data.Device.TYPE_PHONE -> Type(
                                1,
                                "phone"
                            )
                            com.google.android.gms.fitness.data.Device.TYPE_TABLET -> Type(
                                2,
                                "tablet"
                            )
                            com.google.android.gms.fitness.data.Device.TYPE_WATCH -> Type(
                                3,
                                "watch"
                            )
                            com.google.android.gms.fitness.data.Device.TYPE_CHEST_STRAP -> Type(
                                4,
                                "chest_strap"
                            )
                            com.google.android.gms.fitness.data.Device.TYPE_SCALE -> Type(
                                5,
                                "scale"
                            )
                            com.google.android.gms.fitness.data.Device.TYPE_HEAD_MOUNTED -> Type(
                                6,
                                "head_mounted"
                            )
                            else -> Type(-1, "invalid")
                        }
                }

            }

            companion object {
                internal fun createFrom(device: com.google.android.gms.fitness.data.Device) =
                    Device(
                        device.manufacturer,
                        device.model,
                        Type.createFrom(device.type),
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
                    Type.createFrom(dataSource.type)
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
            data class Format(val id: Int, val description: String) {

                companion object {
                    internal fun createFrom(format: Int): Format {
                        val propertyFormat = Property.Format.createFrom(format)
                        return Format(propertyFormat.id, propertyFormat.name.lowercase())
                    }
                }

            }

            @Serializable
            data class Field(val name: String, val format: Format, val isOptional: Boolean?) {

                companion object {
                    internal fun createFrom(field: com.google.android.gms.fitness.data.Field) =
                        Field(
                            field.name,
                            Format.createFrom(field.format),
                            field.isOptional,
                        )
                }

                val json: String
                    get() = Json.encodeToString(this)

            }

            @Serializable
            data class Value(
                val format: Format,
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
                            Format.createFrom(value.format),
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

