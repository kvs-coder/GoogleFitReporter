package com.kvs.googlefitreporter.resolver

abstract class ResolverFactory {
    companion object {
        fun create(): CommonResolver {
            return Resolver()
        }
    }
}
