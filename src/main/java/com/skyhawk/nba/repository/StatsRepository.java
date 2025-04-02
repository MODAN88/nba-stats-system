package com.skyhawk.nba.repository;

import com.skyhawk.nba.model.GameStats;

public interface StatsRepository {
    void save(GameStats stats);
}