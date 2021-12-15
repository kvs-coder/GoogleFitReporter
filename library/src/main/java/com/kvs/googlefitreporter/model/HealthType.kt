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
                ActivityType.STEP_COUNT_DELTA.string -> ActivityType.STEP_COUNT_DELTA
                ActivityType.STEP_COUNT_CUMULATIVE.string -> ActivityType.STEP_COUNT_CUMULATIVE
                ActivityType.STEP_COUNT_CADENCE.string -> ActivityType.STEP_COUNT_CADENCE
                ActivityType.ACTIVITY_SEGMENT.string -> ActivityType.ACTIVITY_SEGMENT
                ActivityType.SLEEP_SEGMENT.string -> ActivityType.SLEEP_SEGMENT
                ActivityType.CALORIES_EXPENDED.string -> ActivityType.CALORIES_EXPENDED
                ActivityType.BASAL_METABOLIC_RATE.string -> ActivityType.BASAL_METABOLIC_RATE
                ActivityType.POWER_SAMPLE.string -> ActivityType.POWER_SAMPLE
                ActivityType.HEART_RATE_BPM.string -> ActivityType.HEART_RATE_BPM
                ActivityType.LOCATION_SAMPLE.string -> ActivityType.LOCATION_SAMPLE
                ActivityType.LOCATION_TRACK.string -> ActivityType.LOCATION_TRACK
                ActivityType.DISTANCE_DELTA.string -> ActivityType.DISTANCE_DELTA
                ActivityType.SPEED.string -> ActivityType.SPEED
                ActivityType.CYCLING_WHEEL_REVOLUTION.string -> ActivityType.CYCLING_WHEEL_REVOLUTION
                ActivityType.CYCLING_WHEEL_RPM.string -> ActivityType.CYCLING_WHEEL_RPM
                ActivityType.CYCLING_PEDALING_CUMULATIVE.string -> ActivityType.CYCLING_PEDALING_CUMULATIVE
                ActivityType.CYCLING_PEDALING_CADENCE.string -> ActivityType.CYCLING_PEDALING_CADENCE
                ActivityType.HEIGHT.string -> ActivityType.HEIGHT
                ActivityType.WEIGHT.string -> ActivityType.WEIGHT
                ActivityType.BODY_FAT_PERCENTAGE.string -> ActivityType.BODY_FAT_PERCENTAGE
                ActivityType.NUTRITION.string -> ActivityType.NUTRITION
                ActivityType.HYDRATION.string -> ActivityType.HYDRATION
                ActivityType.WORKOUT_EXERCISE.string -> ActivityType.WORKOUT_EXERCISE
                ActivityType.MOVE_MINUTES.string -> ActivityType.MOVE_MINUTES
                ActivityType.HEART_POINTS.string -> ActivityType.HEART_POINTS
                ActivityType.ACTIVITY_SUMMARY.string -> ActivityType.ACTIVITY_SUMMARY
                ActivityType.BASAL_METABOLIC_RATE_SUMMARY.string -> ActivityType.BASAL_METABOLIC_RATE_SUMMARY
                ActivityType.HEART_POINTS_SUMMARY.string -> ActivityType.HEART_POINTS_SUMMARY
                ActivityType.HEART_RATE_SUMMARY.string -> ActivityType.HEART_RATE_SUMMARY
                ActivityType.LOCATION_BOUNDING_BOX.string -> ActivityType.LOCATION_BOUNDING_BOX
                ActivityType.POWER_SUMMARY.string -> ActivityType.POWER_SUMMARY
                ActivityType.SPEED_SUMMARY.string -> ActivityType.SPEED_SUMMARY
                ActivityType.BODY_FAT_PERCENTAGE_SUMMARY.string -> ActivityType.BODY_FAT_PERCENTAGE_SUMMARY
                ActivityType.WEIGHT_SUMMARY.string -> ActivityType.WEIGHT_SUMMARY
                ActivityType.HEIGHT_SUMMARY.string -> ActivityType.HEIGHT_SUMMARY
                ActivityType.NUTRITION_SUMMARY.string -> ActivityType.NUTRITION_SUMMARY
                VitalType.BLOOD_PRESSURE.string -> VitalType.BLOOD_PRESSURE
                VitalType.BLOOD_GLUCOSE.string -> VitalType.BLOOD_GLUCOSE
                VitalType.OXYGEN_SATURATION.string -> VitalType.OXYGEN_SATURATION
                VitalType.BODY_TEMPERATURE.string -> VitalType.BODY_TEMPERATURE
                VitalType.BASAL_BODY_TEMPERATURE.string -> VitalType.BASAL_BODY_TEMPERATURE
                VitalType.CERVICAL_MUCUS.string -> VitalType.CERVICAL_MUCUS
                VitalType.CERVICAL_POSITION.string -> VitalType.CERVICAL_POSITION
                VitalType.MENSTRUATION.string -> VitalType.MENSTRUATION
                VitalType.OVULATION_TEST.string -> VitalType.OVULATION_TEST
                VitalType.VAGINAL_SPOTTING.string -> VitalType.VAGINAL_SPOTTING
                VitalType.BLOOD_PRESSURE_SUMMARY.string -> VitalType.BLOOD_PRESSURE_SUMMARY
                VitalType.BLOOD_GLUCOSE_SUMMARY.string -> VitalType.BLOOD_GLUCOSE_SUMMARY
                VitalType.OXYGEN_SATURATION_SUMMARY.string -> VitalType.OXYGEN_SATURATION_SUMMARY
                VitalType.BODY_TEMPERATURE_SUMMARY.string -> VitalType.BODY_TEMPERATURE_SUMMARY
                VitalType.BASAL_BODY_TEMPERATURE_SUMMARY.string -> VitalType.BASAL_BODY_TEMPERATURE_SUMMARY
                else -> throw GoogleFitDataTypeException("Unknown health type: $string")
            }
        }
    }

    @Throws(GoogleFitDataTypeException::class)
    fun asOriginal(): DataType = when (string) {
        ActivityType.STEP_COUNT_DELTA.string -> DataType.TYPE_STEP_COUNT_DELTA
        ActivityType.STEP_COUNT_CUMULATIVE.string -> DataType.TYPE_STEP_COUNT_CUMULATIVE
        ActivityType.STEP_COUNT_CADENCE.string -> DataType.TYPE_STEP_COUNT_CADENCE
        ActivityType.ACTIVITY_SEGMENT.string -> DataType.TYPE_ACTIVITY_SEGMENT
        ActivityType.SLEEP_SEGMENT.string -> DataType.TYPE_SLEEP_SEGMENT
        ActivityType.CALORIES_EXPENDED.string -> DataType.TYPE_CALORIES_EXPENDED
        ActivityType.BASAL_METABOLIC_RATE.string -> DataType.TYPE_BASAL_METABOLIC_RATE
        ActivityType.POWER_SAMPLE.string -> DataType.TYPE_POWER_SAMPLE
        ActivityType.HEART_RATE_BPM.string -> DataType.TYPE_HEART_RATE_BPM
        ActivityType.LOCATION_SAMPLE.string -> DataType.TYPE_LOCATION_SAMPLE
        ActivityType.LOCATION_TRACK.string -> DataType.TYPE_LOCATION_TRACK
        ActivityType.DISTANCE_DELTA.string -> DataType.TYPE_DISTANCE_DELTA
        ActivityType.SPEED.string -> DataType.TYPE_SPEED
        ActivityType.CYCLING_WHEEL_REVOLUTION.string -> DataType.TYPE_CYCLING_WHEEL_REVOLUTION
        ActivityType.CYCLING_WHEEL_RPM.string -> DataType.TYPE_CYCLING_WHEEL_RPM
        ActivityType.CYCLING_PEDALING_CUMULATIVE.string -> DataType.TYPE_CYCLING_PEDALING_CUMULATIVE
        ActivityType.CYCLING_PEDALING_CADENCE.string -> DataType.TYPE_CYCLING_PEDALING_CADENCE
        ActivityType.HEIGHT.string -> DataType.TYPE_HEIGHT
        ActivityType.WEIGHT.string -> DataType.TYPE_WEIGHT
        ActivityType.BODY_FAT_PERCENTAGE.string -> DataType.TYPE_BODY_FAT_PERCENTAGE
        ActivityType.NUTRITION.string -> DataType.TYPE_NUTRITION
        ActivityType.HYDRATION.string -> DataType.TYPE_HYDRATION
        ActivityType.WORKOUT_EXERCISE.string -> DataType.TYPE_WORKOUT_EXERCISE
        ActivityType.MOVE_MINUTES.string -> DataType.TYPE_MOVE_MINUTES
        ActivityType.HEART_POINTS.string -> DataType.TYPE_HEART_POINTS
        ActivityType.ACTIVITY_SUMMARY.string -> DataType.AGGREGATE_ACTIVITY_SUMMARY
        ActivityType.BASAL_METABOLIC_RATE_SUMMARY.string -> DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY
        ActivityType.HEART_POINTS_SUMMARY.string -> DataType.AGGREGATE_HEART_POINTS
        ActivityType.HEART_RATE_SUMMARY.string -> DataType.AGGREGATE_HEART_RATE_SUMMARY
        ActivityType.LOCATION_BOUNDING_BOX.string -> DataType.AGGREGATE_LOCATION_BOUNDING_BOX
        ActivityType.POWER_SUMMARY.string -> DataType.AGGREGATE_POWER_SUMMARY
        ActivityType.SPEED_SUMMARY.string -> DataType.AGGREGATE_SPEED_SUMMARY
        ActivityType.BODY_FAT_PERCENTAGE_SUMMARY.string -> DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY
        ActivityType.WEIGHT_SUMMARY.string -> DataType.AGGREGATE_WEIGHT_SUMMARY
        ActivityType.HEIGHT_SUMMARY.string -> DataType.AGGREGATE_HEIGHT_SUMMARY
        ActivityType.NUTRITION_SUMMARY.string -> DataType.AGGREGATE_NUTRITION_SUMMARY
        VitalType.BLOOD_PRESSURE.string -> HealthDataTypes.TYPE_BLOOD_PRESSURE
        VitalType.BLOOD_GLUCOSE.string -> HealthDataTypes.TYPE_BLOOD_GLUCOSE
        VitalType.OXYGEN_SATURATION.string -> HealthDataTypes.TYPE_OXYGEN_SATURATION
        VitalType.BODY_TEMPERATURE.string -> HealthDataTypes.TYPE_BODY_TEMPERATURE
        VitalType.BASAL_BODY_TEMPERATURE.string -> HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE
        VitalType.CERVICAL_MUCUS.string -> HealthDataTypes.TYPE_CERVICAL_MUCUS
        VitalType.CERVICAL_POSITION.string -> HealthDataTypes.TYPE_CERVICAL_POSITION
        VitalType.MENSTRUATION.string -> HealthDataTypes.TYPE_MENSTRUATION
        VitalType.OVULATION_TEST.string -> HealthDataTypes.TYPE_OVULATION_TEST
        VitalType.VAGINAL_SPOTTING.string -> HealthDataTypes.TYPE_VAGINAL_SPOTTING
        VitalType.BLOOD_PRESSURE_SUMMARY.string -> HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY
        VitalType.BLOOD_GLUCOSE_SUMMARY.string -> HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY
        VitalType.OXYGEN_SATURATION_SUMMARY.string -> HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY
        VitalType.BODY_TEMPERATURE_SUMMARY.string -> HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY
        VitalType.BASAL_BODY_TEMPERATURE_SUMMARY.string -> HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY
        else -> throw GoogleFitDataTypeException("Unknown data type: $string")
    }
}