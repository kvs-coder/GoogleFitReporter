package com.kvs.googlefitreporter.model

import com.google.android.gms.fitness.data.DataType

enum class HealthType(val original: DataType) {
    STEPS(DataType.TYPE_STEP_COUNT_DELTA)
}
