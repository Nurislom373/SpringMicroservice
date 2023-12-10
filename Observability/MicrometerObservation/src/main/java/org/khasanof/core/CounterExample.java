package org.khasanof.core;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Nurislom
 * @see org.khasanof.core
 * @since 12/10/2023 6:26 PM
 */
public class CounterExample {

    static void example1() {
        Random random = new Random();
        MeterRegistry registry = new SimpleMeterRegistry();
        Counter counter = registry.counter("counter");

        IntStream.range(0, 100)
                .forEach(i -> {
                    if (random.nextBoolean()) {
                        counter.increment();
                    }
                });

        counter.measure().forEach(System.out::println);
    }

    static void createCounterExample(MeterRegistry registry) {
        Counter counter = Counter.builder("counter")
                .baseUnit("beans")
                .description("a description of what this counter does")
                .tag("region", "test")
                .register(registry);

        System.out.println("counter = " + counter);
    }

}
