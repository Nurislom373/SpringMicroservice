package org.khasanof.consumer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.cloud.stream.config.ListenerContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

/**
 * @author Nurislom
 * @see org.khasanof.consumer
 * @since 5/2/2024 4:06 PM
 */
@Configuration
public class KafkaTopicConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public NewTopic personEventTopic() {
        return new NewTopic("person-event", 3, (short) 0);
    }

    /**
     *
     * @return
     */
    @Bean
    public ListenerContainerCustomizer<ConcurrentMessageListenerContainer<?, ?>> consumerConfig() {
        return (container, destination, group) -> container.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    }
}
