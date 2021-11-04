package mn.cache.graphql.monitor

import graphql.execution.preparsed.PreparsedDocumentEntry
import io.micrometer.core.instrument.MeterRegistry
import io.micronaut.caffeine.cache.Cache
import io.micronaut.caffeine.cache.Caffeine
import io.micronaut.configuration.metrics.binder.cache.MicronautCaffeineCacheMetrics
import io.micronaut.context.annotation.Context
import io.micronaut.runtime.event.annotation.EventListener
import io.micronaut.runtime.server.event.ServerStartupEvent
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Context
@Singleton
class OtherService(
    private val meterRegistry: MeterRegistry
) {
    private val logger = LoggerFactory.getLogger(OtherService::class.java)

    private val cache: Cache<String, PreparsedDocumentEntry> = Caffeine
        .newBuilder()
        .maximumSize(100)
        .recordStats()
        .build()

    init {
        MicronautCaffeineCacheMetrics.monitor(
            meterRegistry,
            cache,
            "graphQLDocumentCache"
        )
    }

    @EventListener
    fun onStartup(e: ServerStartupEvent) {
        logger.info("Started OtherService")
    }
}