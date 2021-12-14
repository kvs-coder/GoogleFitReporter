package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.DataType
import com.kvs.googlefitreporter.GoogleFitDataTypeException

enum class HealthType(val originalType: DataType) {
    STEPS(DataType.TYPE_STEP_COUNT_DELTA);

    companion object {
        fun createFrom(name: String): HealthType {
            return when (name) {
                DataType.TYPE_STEP_COUNT_DELTA.name -> STEPS
                else -> throw GoogleFitDataTypeException("Unknown name: $name")
            }
        }
    }
}
