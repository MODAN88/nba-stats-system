package com.skyhawk.nba.model;

public class GameStats {
    private int playerId;
    private int teamId;
    private int gameId;
    private int points;
    private int rebounds;
    private int assists;
    private int steals;
    private int blocks;
    private int fouls;
    private int turnovers;
    private float minutesPlayed;

    // Constructor
    public GameStats(int playerId, int teamId, int gameId, int points, int rebounds, int assists, int steals,
            int blocks, int fouls, int turnovers, float minutesPlayed) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.gameId = gameId;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.fouls = fouls;
        this.turnovers = turnovers;
        this.minutesPlayed = minutesPlayed;
    }

    // Getters and Setters
    public int getPlayerId() {
        return playerId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getPoints() {
        return points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public int getSteals() {
        return steals;
    }

    public int getBlocks() {
        return blocks;
    }

    public int getFouls() {
        return fouls;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public float getMinutesPlayed() {
        return minutesPlayed;
    }

    public void validate() {
        if (fouls > 6)
            throw new IllegalArgumentException("Fouls cannot exceed 6");
        if (minutesPlayed < 0 || minutesPlayed > 48.0)
            throw new IllegalArgumentException("Minutes played must be between 0 and 48");
    }

    @Override
    public String toString() {
        return "GameStats{" +
                "playerId=" + playerId +
                ", teamId=" + teamId +
                ", gameId=" + gameId +
                ", points=" + points +
                ", rebounds=" + rebounds +
                ", assists=" + assists +
                ", steals=" + steals +
                ", blocks=" + blocks +
                ", fouls=" + fouls +
                ", turnovers=" + turnovers +
                ", minutesPlayed=" + minutesPlayed +
                '}';
    }
}