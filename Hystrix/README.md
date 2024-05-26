# Hystrix

## What is hystrix

In a distributed environment, inevitably some of the many service dependencies will fail. Hystrix is a library that
helps you control the interactions between these distributed services by adding latency tolerance and fault tolerance
logic. Hystrix does this by isolating points of access between the services, stopping cascading failures across them,
and providing fallback options, all of which improve your system’s overall resiliency.

History of Hystrix
Hystrix evolved out of resilience engineering work that the Netflix API team began in 2011. In 2012, Hystrix continued
to evolve and mature, and many teams within Netflix adopted it. Today tens of billions of thread-isolated, and hundreds
of billions of semaphore-isolated calls are executed via Hystrix every day at Netflix. This has resulted in a dramatic
improvement in uptime and resilience.

![intro](etc/images/hystrix-command-flow-chart.png)

## What Is Hystrix For?

Hystrix is designed to do the following:

* Give protection from and control over latency and failure from dependencies accessed (typically over the network) via third-party client libraries.
* Stop cascading failures in a complex distributed system.
* Fail fast and rapidly recover.
* Fallback and gracefully degrade when possible.
* Enable near real-time monitoring, alerting, and operational control.

## Reference

[Hystrix Wiki](https://github.com/Netflix/Hystrix/wiki)