package org.khasanof.zookeeper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class ServiceClientApplication

fun main(args: Array<String>) {
    runApplication<ServiceClientApplication>(*args)
}
