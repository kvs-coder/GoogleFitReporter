package com.kvs.googlefitreporter.resolver

import com.kvs.googlefitreporter.model.HealthType

abstract class ResolverFactory {
    companion object {
        fun createFrom(type: HealthType): CommonResolver {
            return when (type) {
                HealthType.STEPS -> StepCountResolver()
            }
        }
    }
}
