package com.skyhawk.nba.repository;

import com.skyhawk.nba.model.GameStats;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class JdbcStatsRepository implements StatsRepository {
    private final Connection conn;

    public JdbcStatsRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(GameStats stats) {
        String sql = "INSERT INTO game_stats (player_id, team_id, game_id, points, rebounds, assists, steals, blocks, fouls, turnovers, minutes_played) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, stats.getPlayerId());
            stmt.setInt(2, stats.getTeamId());
            stmt.setInt(3, stats.getGameId());
            stmt.setInt(4, stats.getPoints());
            stmt.setInt(5, stats.getRebounds());
            stmt.setInt(6, stats.getAssists());
            stmt.setInt(7, stats.getSteals());
            stmt.setInt(8, stats.getBlocks());
            stmt.setInt(9, stats.getFouls());
            stmt.setInt(10, stats.getTurnovers());
            stmt.setFloat(11, stats.getMinutesPlayed());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save stats", e);
        }
    }
}