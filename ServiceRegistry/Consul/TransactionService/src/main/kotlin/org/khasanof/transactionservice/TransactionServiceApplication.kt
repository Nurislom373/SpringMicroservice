package org.khasanof.transactionservice

import com.github.javafaker.Faker
import lombok.NoArgsConstructor
import lombok.ToString
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableDiscoveryClient
class TransactionServiceApplication {

    @Bean
    fun faker(): Faker {
        return Faker.instance()
    }

}

fun main(args: Array<String>) {
    runApplication<TransactionServiceApplication>(*args)
}

@RestController
@RequestMapping(value = ["/api"])
class SimpleController {

    @Autowired
    private lateinit var faker: Faker

    @GetMapping(value = ["/hello"])
    fun helloWorld(): ResponseEntity<Any> {
        return ResponseEntity.ok(mapOf(Pair("message", "hello world!")))
    }

    @GetMapping(value = ["/trans/{count}"])
    fun getFakeTransactions(@PathVariable count: Int): ResponseEntity<List<Transaction>> {
        return ResponseEntity.ok(createFakeTransaction(count, faker))
    }

}

fun createFakeTransaction(count: Int, faker: Faker): List<Transaction> {
    val transactions = mutableListOf<Transaction>()

    for (i in count downTo 0) {
        transactions.add(
            Transaction(
                i, faker.name().firstName(), RandomStringUtils.random(16, false, true), RandomUtils.nextLong(),
                faker.name().firstName(), RandomStringUtils.random(16, false, true), RandomUtils.nextLong()
            )
        )
    }

    return transactions
}

data class Transaction(
    var id: Int,

    var senderName: String,
    var senderPan: String,
    var senderAmount: Long,

    var recipientName: String,
    var recipientPan: String,
    var recipientAmount: Long
)
