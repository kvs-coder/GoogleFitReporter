package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.HealthDataTypes

enum class DetailType(override val string: String, override val properties: List<Property>) :
    HealthType {
    STEP_COUNT_DELTA(DataType.TYPE_STEP_COUNT_DELTA.name, listOf(ActivityProperty.STEPS)),
    STEP_COUNT_CUMULATIVE(DataType.TYPE_STEP_COUNT_CUMULATIVE.name, listOf(ActivityProperty.STEPS)),
    STEP_COUNT_CADENCE(DataType.TYPE_STEP_COUNT_CADENCE.name, listOf(ActivityProperty.RPM)),
    ACTIVITY_SEGMENT(DataType.TYPE_ACTIVITY_SEGMENT.name, listOf(ActivityProperty.ACTIVITY)),
    SLEEP_SEGMENT(DataType.TYPE_SLEEP_SEGMENT.name, listOf(ActivityProperty.SLEEP_SEGMENT_TYPE)),
    CALORIES_EXPENDED(DataType.TYPE_CALORIES_EXPENDED.name, listOf(ActivityProperty.CALORIES)),
    BASAL_METABOLIC_RATE(
        DataType.TYPE_BASAL_METABOLIC_RATE.name,
        listOf(ActivityProperty.CALORIES)
    ),
    POWER_SAMPLE(DataType.TYPE_POWER_SAMPLE.name, listOf(ActivityProperty.WATTS)),
    HEART_RATE_BPM(DataType.TYPE_HEART_RATE_BPM.name, listOf(ActivityProperty.BPM)),
    LOCATION_SAMPLE(
        DataType.TYPE_LOCATION_SAMPLE.name,
        listOf(
            ActivityProperty.LATITUDE,
            ActivityProperty.LONGITUDE,
            ActivityProperty.ACCURACY,
            ActivityProperty.ALTITUDE
        )
    ),
    DISTANCE_DELTA(DataType.TYPE_DISTANCE_DELTA.name, listOf(ActivityProperty.DISTANCE)),
    SPEED(DataType.TYPE_SPEED.name, listOf(ActivityProperty.SPEED)),
    CYCLING_WHEEL_REVOLUTION(
        DataType.TYPE_CYCLING_WHEEL_REVOLUTION.name,
        listOf(ActivityProperty.REVOLUTIONS)
    ),
    CYCLING_WHEEL_RPM(DataType.TYPE_CYCLING_WHEEL_RPM.name, listOf(ActivityProperty.RPM)),
    CYCLING_PEDALING_CUMULATIVE(
        DataType.TYPE_CYCLING_PEDALING_CUMULATIVE.name,
        listOf(ActivityProperty.REVOLUTIONS)
    ),
    CYCLING_PEDALING_CADENCE(
        DataType.TYPE_CYCLING_PEDALING_CADENCE.name,
        listOf(ActivityProperty.RPM)
    ),
    HEIGHT(DataType.TYPE_HEIGHT.name, listOf(ActivityProperty.HEIGHT)),
    WEIGHT(DataType.TYPE_WEIGHT.name, listOf(ActivityProperty.WEIGHT)),
    BODY_FAT_PERCENTAGE(
        DataType.TYPE_BODY_FAT_PERCENTAGE.name,
        listOf(ActivityProperty.PERCENTAGE)
    ),
    NUTRITION(
        DataType.TYPE_NUTRITION.name,
        listOf(ActivityProperty.NUTRIENTS, ActivityProperty.MEAL_TYPE, ActivityProperty.FOOD_ITEM)
    ),
    HYDRATION(DataType.TYPE_HYDRATION.name, listOf(ActivityProperty.VOLUME)),
    WORKOUT_EXERCISE(
        DataType.TYPE_WORKOUT_EXERCISE.name,
        listOf(
            ActivityProperty.EXERCISE,
            ActivityProperty.REPETITIONS,
            ActivityProperty.RESISTANCE_TYPE,
            ActivityProperty.RESISTANCE
        )
    ),
    MOVE_MINUTES(DataType.TYPE_MOVE_MINUTES.name, listOf(ActivityProperty.DURATION)),
    HEART_POINTS(DataType.TYPE_HEART_POINTS.name, listOf(ActivityProperty.INTENSITY)),
    BLOOD_PRESSURE(
        HealthDataTypes.TYPE_BLOOD_PRESSURE.name,
        listOf(
            VitalProperty.BLOOD_PRESSURE_SYSTOLIC,
            VitalProperty.BLOOD_PRESSURE_DIASTOLIC,
            VitalProperty.BODY_POSITION,
            VitalProperty.BLOOD_PRESSURE_MEASUREMENT_LOCATION
        )
    ),
    BLOOD_GLUCOSE(
        HealthDataTypes.TYPE_BLOOD_GLUCOSE.name,
        listOf(
            VitalProperty.BLOOD_GLUCOSE_LEVEL,
            VitalProperty.TEMPORAL_RELATION_TO_MEAL,
            ActivityProperty.MEAL_TYPE,
            VitalProperty.TEMPORAL_RELATION_TO_SLEEP,
            VitalProperty.BLOOD_GLUCOSE_SPECIMEN_SOURCE
        )
    ),
    OXYGEN_SATURATION(
        HealthDataTypes.TYPE_OXYGEN_SATURATION.name,
        listOf(
            VitalProperty.OXYGEN_SATURATION,
            VitalProperty.SUPPLEMENTAL_OXYGEN_FLOW_RATE,
            VitalProperty.OXYGEN_THERAPY_ADMINISTRATION_MODE,
            VitalProperty.OXYGEN_SATURATION_SYSTEM,
            VitalProperty.OXYGEN_SATURATION_MEASUREMENT_METHOD
        )
    ),
    BODY_TEMPERATURE(
        HealthDataTypes.TYPE_BODY_TEMPERATURE.name,
        listOf(VitalProperty.BODY_TEMPERATURE, VitalProperty.BODY_TEMPERATURE_MEASUREMENT_LOCATION)
    ),
    CERVICAL_MUCUS(
        HealthDataTypes.TYPE_CERVICAL_MUCUS.name,
        listOf(VitalProperty.CERVICAL_MUCUS_TEXTURE, VitalProperty.CERVICAL_MUCUS_AMOUNT)
    ),
    CERVICAL_POSITION(
        HealthDataTypes.TYPE_CERVICAL_POSITION.name,
        listOf(
            VitalProperty.CERVICAL_POSITION,
            VitalProperty.CERVICAL_DILATION,
            VitalProperty.CERVICAL_FIRMNESS
        )
    ),
    MENSTRUATION(HealthDataTypes.TYPE_MENSTRUATION.name, listOf(VitalProperty.MENSTRUAL_FLOW)),
    OVULATION_TEST(
        HealthDataTypes.TYPE_OVULATION_TEST.name,
        listOf(VitalProperty.OVULATION_TEST_RESULT)
    ),
    VAGINAL_SPOTTING(
        HealthDataTypes.TYPE_VAGINAL_SPOTTING.name,
        listOf(ActivityProperty.OCCURRENCES)
    );
}
