# NBA Stats Backend

## Overview
A scalable backend system for logging and aggregating NBA player statistics, built with Java, PostgreSQL, Redis, and Kafka. Deployable via Docker Compose.

## Prerequisites
- Docker
- Docker Compose
- Maven

## Setup and Running
1. Clone the repository.
2. Build the project: `mvn clean package`
3. Start the services: `docker-compose up --build`
4. Access the API:
   - POST `/api/stats/log` - Log game stats (JSON payload)
   - GET `/api/stats/player/{playerId}/season` - Get season averages

## Example Payload
```json
{
  "playerId": 1,
  "teamId": 1,
  "gameId": 1,
  "points": 20,
  "rebounds": 10,
  "assists": 5,
  "steals": 2,
  "blocks": 1,
  "fouls": 3,
  "turnovers": 2,
  "minutesPlayed": 36.5
}
```

## AWS Deployment
- Use ECS for the app container.
- RDS for PostgreSQL.
- ElastiCache for Redis.
- MSK for Kafka.
- ALB for load balancing.