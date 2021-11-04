This application illustrates an issue with using a caches section in application.yml
in conjunction with MicronautCaffeineCacheMetrics.monitor.

The code is setup to error on startup.

Comment out lines 11-14 in application.yml and restart the application will show it working, but then
there are no stats for the caches in prometheus endpoint.

This code was functioning in 2.5.x, but not 3.1.3.

The stacktrace is reported is:

```text
Caused by: java.lang.IllegalArgumentException: Collector already registered that provides name: cache_eviction_weight
	at io.prometheus.client.CollectorRegistry.register(CollectorRegistry.java:54)
	at io.prometheus.client.Collector.register(Collector.java:175)
	at io.micrometer.prometheus.PrometheusMeterRegistry.lambda$applyToCollector$16(PrometheusMeterRegistry.java:479)
```

when trying to perform MicronautCaffeineCacheMetrics.monitor in the init block of OtherService.

The caches section causes the same name to be registered for the delivery-service cache entry.
