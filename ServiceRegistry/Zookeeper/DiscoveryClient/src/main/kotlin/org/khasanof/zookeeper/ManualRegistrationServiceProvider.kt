package org.khasanof.zookeeper

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.cloud.zookeeper.serviceregistry.ServiceInstanceRegistration
import org.springframework.cloud.zookeeper.serviceregistry.ZookeeperRegistration
import org.springframework.cloud.zookeeper.serviceregistry.ZookeeperServiceRegistry
import org.springframework.stereotype.Component

@Component
class ManualRegistrationServiceProvider(var serviceRegistry: ZookeeperServiceRegistry) : InitializingBean {

    private final var logger: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     *
     */
    @Override
    override fun afterPropertiesSet() {
        val registration: ZookeeperRegistration = ServiceInstanceRegistration.builder()
                .defaultUriSpec()
                .address("anyUrl")
                .port(10)
                .name("/a/b/c/d/anotherservice")
                .build()

        serviceRegistry.register(registration)
    }
}