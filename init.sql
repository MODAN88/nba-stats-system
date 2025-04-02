CREATE TABLE game_stats (
    id SERIAL PRIMARY KEY,
    player_id INT NOT NULL,
    team_id INT NOT NULL,
    game_id INT NOT NULL,
    points INT NOT NULL,
    rebounds INT NOT NULL,
    assists INT NOT NULL,
    steals INT NOT NULL,
    blocks INT NOT NULL,
    fouls INT NOT NULL CHECK (fouls <= 6),
    turnovers INT NOT NULL,
    minutes_played FLOAT NOT NULL CHECK (minutes_played >= 0 AND minutes_played <= 48)
);
