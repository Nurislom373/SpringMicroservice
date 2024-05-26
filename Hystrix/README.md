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