package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.HealthDataTypes
import com.kvs.googlefitreporter.GoogleFitDataTypeException

interface HealthType {

    val string: String
    val properties: List<Property>

    companion object {
        @Throws(GoogleFitDataTypeException::class)
        fun createFrom(string: String): HealthType {
            return when (string) {
                AggregateType.STEP_COUNT_DELTA.string -> AggregateType.STEP_COUNT_DELTA
                AggregateType.ACTIVITY_SEGMENT.string -> AggregateType.ACTIVITY_SEGMENT
                AggregateType.CALORIES_EXPENDED.string -> AggregateType.CALORIES_EXPENDED
                AggregateType.BASAL_METABOLIC_RATE.string -> AggregateType.BASAL_METABOLIC_RATE
                AggregateType.POWER_SAMPLE.string -> AggregateType.POWER_SAMPLE
                AggregateType.HEART_RATE_BPM.string -> AggregateType.HEART_RATE_BPM
                AggregateType.LOCATION_SAMPLE.string -> AggregateType.LOCATION_SAMPLE
                AggregateType.DISTANCE_DELTA.string -> AggregateType.DISTANCE_DELTA
                AggregateType.SPEED.string -> AggregateType.SPEED
                AggregateType.HEIGHT.string -> AggregateType.HEIGHT
                AggregateType.WEIGHT.string -> AggregateType.WEIGHT
                AggregateType.BODY_FAT_PERCENTAGE.string -> AggregateType.BODY_FAT_PERCENTAGE
                AggregateType.NUTRITION.string -> AggregateType.NUTRITION
                AggregateType.HYDRATION.string -> AggregateType.HYDRATION
                AggregateType.MOVE_MINUTES.string -> AggregateType.MOVE_MINUTES
                AggregateType.HEART_POINTS.string -> AggregateType.HEART_POINTS
                AggregateType.BLOOD_PRESSURE.string -> AggregateType.BLOOD_PRESSURE
                AggregateType.BLOOD_GLUCOSE.string -> AggregateType.BLOOD_GLUCOSE
                AggregateType.OXYGEN_SATURATION.string -> AggregateType.OXYGEN_SATURATION
                AggregateType.BODY_TEMPERATURE.string -> AggregateType.BODY_TEMPERATURE
                AggregateType.CERVICAL_MUCUS.string -> AggregateType.CERVICAL_MUCUS
                AggregateType.CERVICAL_POSITION.string -> AggregateType.CERVICAL_POSITION
                AggregateType.MENSTRUATION.string -> AggregateType.MENSTRUATION
                AggregateType.OVULATION_TEST.string -> AggregateType.OVULATION_TEST
                AggregateType.VAGINAL_SPOTTING.string -> AggregateType.VAGINAL_SPOTTING
                DetailType.STEP_COUNT_DELTA.string -> DetailType.STEP_COUNT_DELTA
                DetailType.STEP_COUNT_CUMULATIVE.string -> DetailType.STEP_COUNT_CUMULATIVE
                DetailType.STEP_COUNT_CADENCE.string -> DetailType.STEP_COUNT_CADENCE
                DetailType.ACTIVITY_SEGMENT.string -> DetailType.ACTIVITY_SEGMENT
                DetailType.SLEEP_SEGMENT.string -> DetailType.SLEEP_SEGMENT
                DetailType.CALORIES_EXPENDED.string -> DetailType.CALORIES_EXPENDED
                DetailType.BASAL_METABOLIC_RATE.string -> DetailType.BASAL_METABOLIC_RATE
                DetailType.POWER_SAMPLE.string -> DetailType.POWER_SAMPLE
                DetailType.HEART_RATE_BPM.string -> DetailType.HEART_RATE_BPM
                DetailType.LOCATION_SAMPLE.string -> DetailType.LOCATION_SAMPLE
                DetailType.DISTANCE_DELTA.string -> DetailType.DISTANCE_DELTA
                DetailType.SPEED.string -> DetailType.SPEED
                DetailType.CYCLING_WHEEL_REVOLUTION.string -> DetailType.CYCLING_WHEEL_REVOLUTION
                DetailType.CYCLING_WHEEL_RPM.string -> DetailType.CYCLING_WHEEL_RPM
                DetailType.CYCLING_PEDALING_CUMULATIVE.string -> DetailType.CYCLING_PEDALING_CUMULATIVE
                DetailType.CYCLING_PEDALING_CADENCE.string -> DetailType.CYCLING_PEDALING_CADENCE
                DetailType.HEIGHT.string -> DetailType.HEIGHT
                DetailType.WEIGHT.string -> DetailType.WEIGHT
                DetailType.BODY_FAT_PERCENTAGE.string -> DetailType.BODY_FAT_PERCENTAGE
                DetailType.NUTRITION.string -> DetailType.NUTRITION
                DetailType.HYDRATION.string -> DetailType.HYDRATION
                DetailType.WORKOUT_EXERCISE.string -> DetailType.WORKOUT_EXERCISE
                DetailType.MOVE_MINUTES.string -> DetailType.MOVE_MINUTES
                DetailType.HEART_POINTS.string -> DetailType.HEART_POINTS
                DetailType.BLOOD_PRESSURE.string -> DetailType.BLOOD_PRESSURE
                DetailType.BLOOD_GLUCOSE.string -> DetailType.BLOOD_GLUCOSE
                DetailType.OXYGEN_SATURATION.string -> DetailType.OXYGEN_SATURATION
                DetailType.BODY_TEMPERATURE.string -> DetailType.BODY_TEMPERATURE
                DetailType.CERVICAL_MUCUS.string -> DetailType.CERVICAL_MUCUS
                DetailType.CERVICAL_POSITION.string -> DetailType.CERVICAL_POSITION
                DetailType.MENSTRUATION.string -> DetailType.MENSTRUATION
                DetailType.OVULATION_TEST.string -> DetailType.OVULATION_TEST
                DetailType.VAGINAL_SPOTTING.string -> DetailType.VAGINAL_SPOTTING
                else -> throw GoogleFitDataTypeException("Unknown health type: $string")
            }
        }
    }

    @Throws(GoogleFitDataTypeException::class)
    fun asOriginal(): DataType = when (string) {
        AggregateType.STEP_COUNT_DELTA.string -> DataType.TYPE_STEP_COUNT_DELTA
        AggregateType.ACTIVITY_SEGMENT.string -> DataType.TYPE_ACTIVITY_SEGMENT
        AggregateType.CALORIES_EXPENDED.string -> DataType.TYPE_CALORIES_EXPENDED
        AggregateType.BASAL_METABOLIC_RATE.string -> DataType.TYPE_BASAL_METABOLIC_RATE
        AggregateType.POWER_SAMPLE.string -> DataType.TYPE_POWER_SAMPLE
        AggregateType.HEART_RATE_BPM.string -> DataType.TYPE_HEART_RATE_BPM
        AggregateType.LOCATION_SAMPLE.string -> DataType.TYPE_LOCATION_SAMPLE
        AggregateType.DISTANCE_DELTA.string -> DataType.TYPE_DISTANCE_DELTA
        AggregateType.SPEED.string -> DataType.TYPE_SPEED
        AggregateType.HEIGHT.string -> DataType.TYPE_HEIGHT
        AggregateType.WEIGHT.string -> DataType.TYPE_WEIGHT
        AggregateType.BODY_FAT_PERCENTAGE.string -> DataType.TYPE_BODY_FAT_PERCENTAGE
        AggregateType.NUTRITION.string -> DataType.TYPE_NUTRITION
        AggregateType.HYDRATION.string -> DataType.TYPE_HYDRATION
        AggregateType.MOVE_MINUTES.string -> DataType.TYPE_MOVE_MINUTES
        AggregateType.HEART_POINTS.string -> DataType.TYPE_HEART_POINTS
        AggregateType.BLOOD_PRESSURE.string -> HealthDataTypes.TYPE_BLOOD_PRESSURE
        AggregateType.BLOOD_GLUCOSE.string -> HealthDataTypes.TYPE_BLOOD_GLUCOSE
        AggregateType.OXYGEN_SATURATION.string -> HealthDataTypes.TYPE_OXYGEN_SATURATION
        AggregateType.BODY_TEMPERATURE.string -> HealthDataTypes.TYPE_BODY_TEMPERATURE
        AggregateType.CERVICAL_MUCUS.string -> HealthDataTypes.TYPE_CERVICAL_MUCUS
        AggregateType.CERVICAL_POSITION.string -> HealthDataTypes.TYPE_CERVICAL_POSITION
        AggregateType.MENSTRUATION.string -> HealthDataTypes.TYPE_MENSTRUATION
        AggregateType.OVULATION_TEST.string -> HealthDataTypes.TYPE_OVULATION_TEST
        AggregateType.VAGINAL_SPOTTING.string -> HealthDataTypes.TYPE_VAGINAL_SPOTTING
        DetailType.STEP_COUNT_DELTA.string -> DataType.TYPE_STEP_COUNT_DELTA
        DetailType.STEP_COUNT_CUMULATIVE.string -> DataType.TYPE_STEP_COUNT_CUMULATIVE
        DetailType.STEP_COUNT_CADENCE.string -> DataType.TYPE_STEP_COUNT_CADENCE
        DetailType.ACTIVITY_SEGMENT.string -> DataType.TYPE_ACTIVITY_SEGMENT
        DetailType.SLEEP_SEGMENT.string -> DataType.TYPE_SLEEP_SEGMENT
        DetailType.CALORIES_EXPENDED.string -> DataType.TYPE_CALORIES_EXPENDED
        DetailType.BASAL_METABOLIC_RATE.string -> DataType.TYPE_BASAL_METABOLIC_RATE
        DetailType.POWER_SAMPLE.string -> DataType.TYPE_POWER_SAMPLE
        DetailType.HEART_RATE_BPM.string -> DataType.TYPE_HEART_RATE_BPM
        DetailType.LOCATION_SAMPLE.string -> DataType.TYPE_LOCATION_SAMPLE
        DetailType.DISTANCE_DELTA.string -> DataType.TYPE_DISTANCE_DELTA
        DetailType.SPEED.string -> DataType.TYPE_SPEED
        DetailType.CYCLING_WHEEL_REVOLUTION.string -> DataType.TYPE_CYCLING_WHEEL_REVOLUTION
        DetailType.CYCLING_WHEEL_RPM.string -> DataType.TYPE_CYCLING_WHEEL_RPM
        DetailType.CYCLING_PEDALING_CUMULATIVE.string -> DataType.TYPE_CYCLING_PEDALING_CUMULATIVE
        DetailType.CYCLING_PEDALING_CADENCE.string -> DataType.TYPE_CYCLING_PEDALING_CADENCE
        DetailType.HEIGHT.string -> DataType.TYPE_HEIGHT
        DetailType.WEIGHT.string -> DataType.TYPE_WEIGHT
        DetailType.BODY_FAT_PERCENTAGE.string -> DataType.TYPE_BODY_FAT_PERCENTAGE
        DetailType.NUTRITION.string -> DataType.TYPE_NUTRITION
        DetailType.HYDRATION.string -> DataType.TYPE_HYDRATION
        DetailType.WORKOUT_EXERCISE.string -> DataType.TYPE_WORKOUT_EXERCISE
        DetailType.MOVE_MINUTES.string -> DataType.TYPE_MOVE_MINUTES
        DetailType.HEART_POINTS.string -> DataType.TYPE_HEART_POINTS
        DetailType.BLOOD_PRESSURE.string -> HealthDataTypes.TYPE_BLOOD_PRESSURE
        DetailType.BLOOD_GLUCOSE.string -> HealthDataTypes.TYPE_BLOOD_GLUCOSE
        DetailType.OXYGEN_SATURATION.string -> HealthDataTypes.TYPE_OXYGEN_SATURATION
        DetailType.BODY_TEMPERATURE.string -> HealthDataTypes.TYPE_BODY_TEMPERATURE
        DetailType.CERVICAL_MUCUS.string -> HealthDataTypes.TYPE_CERVICAL_MUCUS
        DetailType.CERVICAL_POSITION.string -> HealthDataTypes.TYPE_CERVICAL_POSITION
        DetailType.MENSTRUATION.string -> HealthDataTypes.TYPE_MENSTRUATION
        DetailType.OVULATION_TEST.string -> HealthDataTypes.TYPE_OVULATION_TEST
        DetailType.VAGINAL_SPOTTING.string -> HealthDataTypes.TYPE_VAGINAL_SPOTTING
        else -> throw GoogleFitDataTypeException("Unknown data type: $string")
    }
}
