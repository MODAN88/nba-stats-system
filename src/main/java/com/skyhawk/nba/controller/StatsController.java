package com.skyhawk.nba.controller;

import com.skyhawk.nba.model.GameStats;
import com.skyhawk.nba.service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping("/log")
    public void logStats(@RequestBody GameStats stats) {
        statsService.logStats(stats);
    }

    @GetMapping("/player/{playerId}/season")
    public Map<String, String> getPlayerSeasonStats(@PathVariable int playerId) {
        return statsService.getPlayerSeasonStats(playerId);
    }
}
