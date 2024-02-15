package ru.nsu.lopatkin.dis.manager.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "interaction.interface", havingValue = "rabbit")
public class RabbitMqConfig {
}
