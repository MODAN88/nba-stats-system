package com.skyhawk.nba.service;

import com.skyhawk.nba.model.GameStats;
import com.skyhawk.nba.repository.StatsRepository;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class StatsService {
    private final StatsRepository repo;
    private final Jedis redis;
    private final KafkaProducer<String, String> kafkaProducer;

    public StatsService(StatsRepository repo, Jedis redis, KafkaProducer<String, String> kafkaProducer) {
        this.repo = repo;
        this.redis = redis;
        this.kafkaProducer = kafkaProducer;
    }

    public void logStats(GameStats stats) {
        stats.validate();
        repo.save(stats);
        kafkaProducer.send(new ProducerRecord<>("stats-ingestion", stats.getPlayerId() + "", stats.toString()));
        updateRedisAggregates(stats);
    }

    private void updateRedisAggregates(GameStats stats) {
        String playerKey = "player:" + stats.getPlayerId() + ":season";
        redis.hincrBy(playerKey, "points", stats.getPoints());
        redis.hincrBy(playerKey, "rebounds", stats.getRebounds());
        redis.hincrBy(playerKey, "assists", stats.getAssists());
        redis.hincrBy(playerKey, "steals", stats.getSteals());
        redis.hincrBy(playerKey, "blocks", stats.getBlocks());
        redis.hincrBy(playerKey, "fouls", stats.getFouls());
        redis.hincrBy(playerKey, "turnovers", stats.getTurnovers());
        redis.hincrByFloat(playerKey, "minutesPlayed", stats.getMinutesPlayed());
        redis.hincrBy(playerKey, "games", 1); // Track number of games for averages
    }

    public Map<String, String> getPlayerSeasonStats(int playerId) {
        String playerKey = "player:" + playerId + ":season";
        Map<String, String> stats = redis.hgetAll(playerKey);
        if (stats.isEmpty())
            return new HashMap<>();
        double games = Double.parseDouble(stats.getOrDefault("games", "1"));
        Map<String, String> averages = new HashMap<>();
        averages.put("points", String.format("%.2f", Double.parseDouble(stats.get("points")) / games));
        averages.put("rebounds", String.format("%.2f", Double.parseDouble(stats.get("rebounds")) / games));
        averages.put("assists", String.format("%.2f", Double.parseDouble(stats.get("assists")) / games));
        averages.put("steals", String.format("%.2f", Double.parseDouble(stats.get("steals")) / games));
        averages.put("blocks", String.format("%.2f", Double.parseDouble(stats.get("blocks")) / games));
        averages.put("fouls", String.format("%.2f", Double.parseDouble(stats.get("fouls")) / games));
        averages.put("turnovers", String.format("%.2f", Double.parseDouble(stats.get("turnovers")) / games));
        averages.put("minutesPlayed", String.format("%.2f", Double.parseDouble(stats.get("minutesPlayed")) / games));
        return averages;
    }
}
