package mn.cache.graphql.monitor

import io.micronaut.cache.annotation.Cacheable
import io.micronaut.context.annotation.Context
import jakarta.inject.Singleton

@Context
@Singleton
open class DeliveryService {
    companion object {
        const val CACHE_NAME = "DeliveryService"
    }

    @Cacheable(parameters = ["id"], value = [CACHE_NAME])
    open fun fetchDeliveryService(id: String): String {
        return "fetch-$id"
    }
}