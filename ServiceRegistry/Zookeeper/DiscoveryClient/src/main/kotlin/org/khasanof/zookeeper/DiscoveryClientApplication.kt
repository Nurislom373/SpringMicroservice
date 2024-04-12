package org.khasanof.zookeeper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DiscoveryClientApplication

fun main(args: Array<String>) {
    runApplication<DiscoveryClientApplication>(*args)
}
