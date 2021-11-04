package mn.cache.graphql.monitor

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("mn.cache.graphql.monitor")
		.start()
}

