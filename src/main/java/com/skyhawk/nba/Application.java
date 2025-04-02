
package com.skyhawk.nba;

import com.skyhawk.nba.config.DatabaseConfig;
import com.skyhawk.nba.config.KafkaConfig;
import com.skyhawk.nba.controller.StatsController;
import com.skyhawk.nba.repository.JdbcStatsRepository;
import com.skyhawk.nba.service.StatsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import org.apache.kafka.clients.producer.KafkaProducer;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public StatsService statsService() {
        return new StatsService(
                new JdbcStatsRepository(DatabaseConfig.getConnection()),
                new Jedis("redis", 6379),
                new KafkaProducer<>(KafkaConfig.producerProps()));
    }

    @Bean
    public StatsController statsController(StatsService statsService) {
        return new StatsController(statsService);
    }
}
