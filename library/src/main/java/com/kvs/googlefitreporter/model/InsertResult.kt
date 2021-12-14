package com.kvs.googlefitreporter.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class InsertResult(
    val appPackageName: String,
    val streamName: String,
    @Contextual val value: Any,
    val startTime: Long,
    val endTime: Long
)