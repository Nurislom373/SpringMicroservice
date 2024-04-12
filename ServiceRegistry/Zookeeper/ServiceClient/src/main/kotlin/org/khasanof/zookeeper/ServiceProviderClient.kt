package org.khasanof.zookeeper

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "ServiceProvider")
interface ServiceProviderClient {

    /**
     *
     */
    @GetMapping(path = ["/greeting/{name}"])
    fun greeting(@PathVariable name: String): String
}