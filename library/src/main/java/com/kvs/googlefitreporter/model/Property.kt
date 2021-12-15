package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.data.HealthFields
import com.kvs.googlefitreporter.GoogleFitDataTypeException

interface Property {

    enum class Format(val id: Int) {
        INTEGER(1),
        FLOAT(2),
        STRING(3),
        MAP(4);

        companion object {
            @Throws(GoogleFitDataTypeException::class)
            fun createFrom(id: Int): Format = when (id) {
                INTEGER.id -> INTEGER
                FLOAT.id -> FLOAT
                STRING.id -> STRING
                MAP.id -> MAP
                else -> throw GoogleFitDataTypeException("Unknown format: $id")
            }
        }
    }

    val string: String

    val format: Format
        get() {
            val original = asOriginal()
            return Format.createFrom(original.format)
        }

    companion object {
        @Throws(GoogleFitDataTypeException::class)
        fun createFrom(string: String): Property = when (string) {
            ActivityProperty.ACTIVITY.string -> ActivityProperty.ACTIVITY
            ActivityProperty.SLEEP_SEGMENT_TYPE.string -> ActivityProperty.SLEEP_SEGMENT_TYPE
            ActivityProperty.CONFIDENCE.string -> ActivityProperty.CONFIDENCE
            ActivityProperty.STEPS.string -> ActivityProperty.STEPS
            ActivityProperty.STEP_LENGTH.string -> ActivityProperty.STEP_LENGTH
            ActivityProperty.DURATION.string -> ActivityProperty.DURATION
            ActivityProperty.BPM.string -> ActivityProperty.BPM
            ActivityProperty.LATITUDE.string -> ActivityProperty.LATITUDE
            ActivityProperty.LONGITUDE.string -> ActivityProperty.LONGITUDE
            ActivityProperty.ACCURACY.string -> ActivityProperty.ACCURACY
            ActivityProperty.ALTITUDE.string -> ActivityProperty.ALTITUDE
            ActivityProperty.DISTANCE.string -> ActivityProperty.DISTANCE
            ActivityProperty.HEIGHT.string -> ActivityProperty.HEIGHT
            ActivityProperty.WEIGHT.string -> ActivityProperty.WEIGHT
            ActivityProperty.PERCENTAGE.string -> ActivityProperty.PERCENTAGE
            ActivityProperty.SPEED.string -> ActivityProperty.SPEED
            ActivityProperty.RPM.string -> ActivityProperty.RPM
            ActivityProperty.REVOLUTIONS.string -> ActivityProperty.REVOLUTIONS
            ActivityProperty.CALORIES.string -> ActivityProperty.CALORIES
            ActivityProperty.WATTS.string -> ActivityProperty.WATTS
            ActivityProperty.VOLUME.string -> ActivityProperty.VOLUME
            ActivityProperty.MEAL_TYPE.string -> ActivityProperty.MEAL_TYPE
            ActivityProperty.FOOD_ITEM.string -> ActivityProperty.FOOD_ITEM
            ActivityProperty.NUTRIENTS.string -> ActivityProperty.NUTRIENTS
            ActivityProperty.EXERCISE.string -> ActivityProperty.EXERCISE
            ActivityProperty.REPETITIONS.string -> ActivityProperty.REPETITIONS
            ActivityProperty.RESISTANCE.string -> ActivityProperty.RESISTANCE
            ActivityProperty.RESISTANCE_TYPE.string -> ActivityProperty.RESISTANCE_TYPE
            ActivityProperty.NUM_SEGMENTS.string -> ActivityProperty.NUM_SEGMENTS
            ActivityProperty.AVERAGE.string -> ActivityProperty.AVERAGE
            ActivityProperty.MAX.string -> ActivityProperty.MAX
            ActivityProperty.MIN.string -> ActivityProperty.MIN
            ActivityProperty.LOW_LATITUDE.string -> ActivityProperty.LOW_LATITUDE
            ActivityProperty.LOW_LONGITUDE.string -> ActivityProperty.LOW_LONGITUDE
            ActivityProperty.HIGH_LATITUDE.string -> ActivityProperty.HIGH_LATITUDE
            ActivityProperty.HIGH_LONGITUDE.string -> ActivityProperty.HIGH_LONGITUDE
            ActivityProperty.OCCURRENCES.string -> ActivityProperty.OCCURRENCES
            ActivityProperty.INTENSITY.string -> ActivityProperty.INTENSITY
            ActivityProperty.CIRCUMFERENCE.string -> ActivityProperty.CIRCUMFERENCE
            VitalProperty.BLOOD_PRESSURE_SYSTOLIC.string -> VitalProperty.BLOOD_PRESSURE_SYSTOLIC
            VitalProperty.BLOOD_PRESSURE_SYSTOLIC_AVERAGE.string -> VitalProperty.BLOOD_PRESSURE_SYSTOLIC_AVERAGE
            VitalProperty.BLOOD_PRESSURE_SYSTOLIC_MIN.string -> VitalProperty.BLOOD_PRESSURE_SYSTOLIC_MIN
            VitalProperty.BLOOD_PRESSURE_SYSTOLIC_MAX.string -> VitalProperty.BLOOD_PRESSURE_SYSTOLIC_MAX
            VitalProperty.BLOOD_PRESSURE_DIASTOLIC.string -> VitalProperty.BLOOD_PRESSURE_DIASTOLIC
            VitalProperty.BLOOD_PRESSURE_DIASTOLIC_AVERAGE.string -> VitalProperty.BLOOD_PRESSURE_DIASTOLIC_AVERAGE
            VitalProperty.BLOOD_PRESSURE_DIASTOLIC_MIN.string -> VitalProperty.BLOOD_PRESSURE_DIASTOLIC_MIN
            VitalProperty.BLOOD_PRESSURE_DIASTOLIC_MAX.string -> VitalProperty.BLOOD_PRESSURE_DIASTOLIC_MAX
            VitalProperty.BODY_POSITION.string -> VitalProperty.BODY_POSITION
            VitalProperty.BLOOD_PRESSURE_MEASUREMENT_LOCATION.string -> VitalProperty.BLOOD_PRESSURE_MEASUREMENT_LOCATION
            VitalProperty.BLOOD_GLUCOSE_LEVEL.string -> VitalProperty.BLOOD_GLUCOSE_LEVEL
            VitalProperty.TEMPORAL_RELATION_TO_MEAL.string -> VitalProperty.TEMPORAL_RELATION_TO_MEAL
            VitalProperty.TEMPORAL_RELATION_TO_SLEEP.string -> VitalProperty.TEMPORAL_RELATION_TO_SLEEP
            VitalProperty.BLOOD_GLUCOSE_SPECIMEN_SOURCE.string -> VitalProperty.BLOOD_GLUCOSE_SPECIMEN_SOURCE
            VitalProperty.OXYGEN_SATURATION.string -> VitalProperty.OXYGEN_SATURATION
            VitalProperty.OXYGEN_SATURATION_AVERAGE.string -> VitalProperty.OXYGEN_SATURATION_AVERAGE
            VitalProperty.OXYGEN_SATURATION_MIN.string -> VitalProperty.OXYGEN_SATURATION_MIN
            VitalProperty.OXYGEN_SATURATION_MAX.string -> VitalProperty.OXYGEN_SATURATION_MAX
            VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE.string -> VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE
            VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE.string -> VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE
            VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN.string -> VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN
            VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX.string -> VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX
            VitalProperty.OXYGEN_THERAPY_ADMINISTRATION_MODE.string -> VitalProperty.OXYGEN_THERAPY_ADMINISTRATION_MODE
            VitalProperty.OXYGEN_SATURATION_SYSTEM.string -> VitalProperty.OXYGEN_SATURATION_SYSTEM
            VitalProperty.OXYGEN_SATURATION_MEASUREMENT_METHOD.string -> VitalProperty.OXYGEN_SATURATION_MEASUREMENT_METHOD
            VitalProperty.BODY_TEMPERATURE.string -> VitalProperty.BODY_TEMPERATURE
            VitalProperty.BODY_TEMPERATURE_MEASUREMENT_LOCATION.string -> VitalProperty.BODY_TEMPERATURE_MEASUREMENT_LOCATION
            VitalProperty.CERVICAL_MUCUS_TEXTURE.string -> VitalProperty.CERVICAL_MUCUS_TEXTURE
            VitalProperty.CERVICAL_MUCUS_AMOUNT.string -> VitalProperty.CERVICAL_MUCUS_AMOUNT
            VitalProperty.CERVICAL_POSITION.string -> VitalProperty.CERVICAL_POSITION
            VitalProperty.CERVICAL_DILATION.string -> VitalProperty.CERVICAL_DILATION
            VitalProperty.CERVICAL_FIRMNESS.string -> VitalProperty.CERVICAL_FIRMNESS
            VitalProperty.MENSTRUAL_FLOW.string -> VitalProperty.MENSTRUAL_FLOW
            VitalProperty.OVULATION_TEST_RESULT.string -> VitalProperty.OVULATION_TEST_RESULT
            else -> throw GoogleFitDataTypeException("Unknown field type: $string")
        }
    }

    @Throws(GoogleFitDataTypeException::class)
    fun asOriginal(): Field = when (string) {
        ActivityProperty.ACTIVITY.string -> Field.FIELD_ACTIVITY
        ActivityProperty.SLEEP_SEGMENT_TYPE.string -> Field.FIELD_SLEEP_SEGMENT_TYPE
        ActivityProperty.CONFIDENCE.string -> Field.FIELD_CONFIDENCE
        ActivityProperty.STEPS.string -> Field.FIELD_STEPS
        ActivityProperty.STEP_LENGTH.string -> Field.FIELD_STEP_LENGTH
        ActivityProperty.DURATION.string -> Field.FIELD_DURATION
        ActivityProperty.BPM.string -> Field.FIELD_BPM
        ActivityProperty.LATITUDE.string -> Field.FIELD_LATITUDE
        ActivityProperty.LONGITUDE.string -> Field.FIELD_LONGITUDE
        ActivityProperty.ACCURACY.string -> Field.FIELD_ACCURACY
        ActivityProperty.ALTITUDE.string -> Field.FIELD_ALTITUDE
        ActivityProperty.DISTANCE.string -> Field.FIELD_DISTANCE
        ActivityProperty.HEIGHT.string -> Field.FIELD_HEIGHT
        ActivityProperty.WEIGHT.string -> Field.FIELD_WEIGHT
        ActivityProperty.PERCENTAGE.string -> Field.FIELD_PERCENTAGE
        ActivityProperty.SPEED.string -> Field.FIELD_SPEED
        ActivityProperty.RPM.string -> Field.FIELD_RPM
        ActivityProperty.REVOLUTIONS.string -> Field.FIELD_REVOLUTIONS
        ActivityProperty.CALORIES.string -> Field.FIELD_CALORIES
        ActivityProperty.WATTS.string -> Field.FIELD_WATTS
        ActivityProperty.VOLUME.string -> Field.FIELD_VOLUME
        ActivityProperty.MEAL_TYPE.string -> Field.FIELD_MEAL_TYPE
        ActivityProperty.FOOD_ITEM.string -> Field.FIELD_FOOD_ITEM
        ActivityProperty.NUTRIENTS.string -> Field.FIELD_NUTRIENTS
        ActivityProperty.EXERCISE.string -> Field.FIELD_EXERCISE
        ActivityProperty.REPETITIONS.string -> Field.FIELD_REPETITIONS
        ActivityProperty.RESISTANCE.string -> Field.FIELD_RESISTANCE
        ActivityProperty.RESISTANCE_TYPE.string -> Field.FIELD_RESISTANCE_TYPE
        ActivityProperty.NUM_SEGMENTS.string -> Field.FIELD_NUM_SEGMENTS
        ActivityProperty.AVERAGE.string -> Field.FIELD_AVERAGE
        ActivityProperty.MAX.string -> Field.FIELD_MAX
        ActivityProperty.MIN.string -> Field.FIELD_MIN
        ActivityProperty.LOW_LATITUDE.string -> Field.FIELD_LOW_LATITUDE
        ActivityProperty.LOW_LONGITUDE.string -> Field.FIELD_LOW_LONGITUDE
        ActivityProperty.HIGH_LATITUDE.string -> Field.FIELD_HIGH_LATITUDE
        ActivityProperty.HIGH_LONGITUDE.string -> Field.FIELD_HIGH_LONGITUDE
        ActivityProperty.OCCURRENCES.string -> Field.FIELD_OCCURRENCES
        ActivityProperty.INTENSITY.string -> Field.FIELD_INTENSITY
        ActivityProperty.CIRCUMFERENCE.string -> Field.FIELD_CIRCUMFERENCE
        VitalProperty.BLOOD_PRESSURE_SYSTOLIC.string -> HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC
        VitalProperty.BLOOD_PRESSURE_SYSTOLIC_AVERAGE.string -> HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_AVERAGE
        VitalProperty.BLOOD_PRESSURE_SYSTOLIC_MIN.string -> HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MIN
        VitalProperty.BLOOD_PRESSURE_SYSTOLIC_MAX.string -> HealthFields.FIELD_BLOOD_PRESSURE_SYSTOLIC_MAX
        VitalProperty.BLOOD_PRESSURE_DIASTOLIC.string -> HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC
        VitalProperty.BLOOD_PRESSURE_DIASTOLIC_AVERAGE.string -> HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_AVERAGE
        VitalProperty.BLOOD_PRESSURE_DIASTOLIC_MIN.string -> HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MIN
        VitalProperty.BLOOD_PRESSURE_DIASTOLIC_MAX.string -> HealthFields.FIELD_BLOOD_PRESSURE_DIASTOLIC_MAX
        VitalProperty.BODY_POSITION.string -> HealthFields.FIELD_BODY_POSITION
        VitalProperty.BLOOD_PRESSURE_MEASUREMENT_LOCATION.string -> HealthFields.FIELD_BLOOD_PRESSURE_MEASUREMENT_LOCATION
        VitalProperty.BLOOD_GLUCOSE_LEVEL.string -> HealthFields.FIELD_BLOOD_GLUCOSE_LEVEL
        VitalProperty.TEMPORAL_RELATION_TO_MEAL.string -> HealthFields.FIELD_TEMPORAL_RELATION_TO_MEAL
        VitalProperty.TEMPORAL_RELATION_TO_SLEEP.string -> HealthFields.FIELD_TEMPORAL_RELATION_TO_SLEEP
        VitalProperty.BLOOD_GLUCOSE_SPECIMEN_SOURCE.string -> HealthFields.FIELD_BLOOD_GLUCOSE_SPECIMEN_SOURCE
        VitalProperty.OXYGEN_SATURATION.string -> HealthFields.FIELD_OXYGEN_SATURATION
        VitalProperty.OXYGEN_SATURATION_AVERAGE.string -> HealthFields.FIELD_OXYGEN_SATURATION_AVERAGE
        VitalProperty.OXYGEN_SATURATION_MIN.string -> HealthFields.FIELD_OXYGEN_SATURATION_MIN
        VitalProperty.OXYGEN_SATURATION_MAX.string -> HealthFields.FIELD_OXYGEN_SATURATION_MAX
        VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE.string -> HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE
        VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE.string -> HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_AVERAGE
        VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN.string -> HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MIN
        VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX.string -> HealthFields.FIELD_SUPPLEMENTAL_OXYGEN_FLOW_RATE_MAX
        VitalProperty.OXYGEN_THERAPY_ADMINISTRATION_MODE.string -> HealthFields.FIELD_OXYGEN_THERAPY_ADMINISTRATION_MODE
        VitalProperty.OXYGEN_SATURATION_SYSTEM.string -> HealthFields.FIELD_OXYGEN_SATURATION_SYSTEM
        VitalProperty.OXYGEN_SATURATION_MEASUREMENT_METHOD.string -> HealthFields.FIELD_OXYGEN_SATURATION_MEASUREMENT_METHOD
        VitalProperty.BODY_TEMPERATURE.string -> HealthFields.FIELD_BODY_TEMPERATURE
        VitalProperty.BODY_TEMPERATURE_MEASUREMENT_LOCATION.string -> HealthFields.FIELD_BODY_TEMPERATURE_MEASUREMENT_LOCATION
        VitalProperty.CERVICAL_MUCUS_TEXTURE.string -> HealthFields.FIELD_CERVICAL_MUCUS_TEXTURE
        VitalProperty.CERVICAL_MUCUS_AMOUNT.string -> HealthFields.FIELD_CERVICAL_MUCUS_AMOUNT
        VitalProperty.CERVICAL_POSITION.string -> HealthFields.FIELD_CERVICAL_POSITION
        VitalProperty.CERVICAL_DILATION.string -> HealthFields.FIELD_CERVICAL_DILATION
        VitalProperty.CERVICAL_FIRMNESS.string -> HealthFields.FIELD_CERVICAL_FIRMNESS
        VitalProperty.MENSTRUAL_FLOW.string -> HealthFields.FIELD_MENSTRUAL_FLOW
        VitalProperty.OVULATION_TEST_RESULT.string -> HealthFields.FIELD_OVULATION_TEST_RESULT
        else -> throw GoogleFitDataTypeException("Unknown field: $string")
    }

}