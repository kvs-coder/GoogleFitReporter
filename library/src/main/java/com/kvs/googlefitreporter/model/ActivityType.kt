package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.DataType

enum class ActivityType(override val string: String, override val properties: List<Property>) :
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
    LOCATION_TRACK(
        DataType.TYPE_LOCATION_TRACK.name,
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
    ACTIVITY_SUMMARY(
        DataType.AGGREGATE_ACTIVITY_SUMMARY.name,
        listOf(ActivityProperty.ACTIVITY, ActivityProperty.DURATION, ActivityProperty.NUM_SEGMENTS)
    ),
    BASAL_METABOLIC_RATE_SUMMARY(
        DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY.name,
        listOf(ActivityProperty.AVERAGE, ActivityProperty.MAX, ActivityProperty.MIN)
    ),
    HEART_POINTS_SUMMARY(
        DataType.AGGREGATE_HEART_POINTS.name,
        listOf(ActivityProperty.INTENSITY, ActivityProperty.DURATION)
    ),
    HEART_RATE_SUMMARY(
        DataType.AGGREGATE_HEART_RATE_SUMMARY.name,
        listOf(ActivityProperty.AVERAGE, ActivityProperty.MAX, ActivityProperty.MIN)
    ),
    LOCATION_BOUNDING_BOX(
        DataType.AGGREGATE_LOCATION_BOUNDING_BOX.name,
        listOf(
            ActivityProperty.LOW_LATITUDE,
            ActivityProperty.LOW_LONGITUDE,
            ActivityProperty.HIGH_LATITUDE,
            ActivityProperty.HIGH_LONGITUDE
        )
    ),
    POWER_SUMMARY(
        DataType.AGGREGATE_POWER_SUMMARY.name,
        listOf(ActivityProperty.AVERAGE, ActivityProperty.MAX, ActivityProperty.MIN)
    ),
    SPEED_SUMMARY(
        DataType.AGGREGATE_SPEED_SUMMARY.name,
        listOf(ActivityProperty.AVERAGE, ActivityProperty.MAX, ActivityProperty.MIN)
    ),
    BODY_FAT_PERCENTAGE_SUMMARY(
        DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY.name,
        listOf(ActivityProperty.AVERAGE, ActivityProperty.MAX, ActivityProperty.MIN)
    ),
    WEIGHT_SUMMARY(
        DataType.AGGREGATE_WEIGHT_SUMMARY.name,
        listOf(ActivityProperty.AVERAGE, ActivityProperty.MAX, ActivityProperty.MIN)
    ),
    HEIGHT_SUMMARY(
        DataType.AGGREGATE_HEIGHT_SUMMARY.name,
        listOf(ActivityProperty.AVERAGE, ActivityProperty.MAX, ActivityProperty.MIN)
    ),
    NUTRITION_SUMMARY(
        DataType.AGGREGATE_NUTRITION_SUMMARY.name,
        listOf(ActivityProperty.NUTRIENTS, ActivityProperty.MEAL_TYPE)
    );
}
