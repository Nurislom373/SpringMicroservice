package org.khasanof.zookeeper

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.stereotype.Component

@Component
class ServiceProviderInstances(var discoveryClient: DiscoveryClient) : InitializingBean {

    private final var logger: Logger = LoggerFactory.getLogger(this.javaClass)
    private final var serviceNames: List<String> = listOf("ServiceClient", "ServiceProvider")

    /**
     *
     */
    @Override
    override fun afterPropertiesSet() {
        serviceNames.forEach {
            val instances = discoveryClient.getInstances(it)
            instances.forEach { instance ->
                logger.debug("each service instance IP address : {}, instance ID : {}, URI : {}",
                        instance.host + ":" + instance.port, instance.instanceId, instance.uri)
            }
        }
    }
}