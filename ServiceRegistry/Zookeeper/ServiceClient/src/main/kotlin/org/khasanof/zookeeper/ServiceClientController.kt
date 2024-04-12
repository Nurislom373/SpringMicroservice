package org.khasanof.zookeeper

import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class ServiceClientController(var serviceProviderClient: ServiceProviderClient) {

    /**
     *
     */
    @RequestMapping(path = ["/call"], method = [RequestMethod.GET])
    fun greetingCall(): ResponseEntity<String> {
        return ResponseEntity(serviceProviderClient.greeting("Khasanof"), HttpStatus.OK)
    }
}