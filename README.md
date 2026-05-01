# Notification Service

A Kafka consumer microservice that listens to pet lifecycle events published by [pet-api](https://github.com/SlavchoVlakeskiGit/pet-api) and processes notifications.

## What it does

When a pet is created, updated, or deleted in the Pet API, a `PetEvent` is published to the `pet-events` Kafka topic. This service consumes those events and logs a notification message. In a real system this is where you would send emails, push notifications, or SMS messages.

## Tech Stack

- Java 17
- Spring Boot 3.4
- Spring Kafka (consumer)
- No HTTP server — runs as a background process only

## Running

This service is intended to run alongside the Pet API via Docker Compose:

```bash
# From the pet-api directory
docker-compose up --build
```

### Run locally

Requires Kafka running on `localhost:9093`.

```bash
./mvnw spring-boot:run
```

## Event format

```json
{
  "petId": 1,
  "petName": "Rex",
  "eventType": "CREATED",
  "occurredAt": "2026-05-01T17:00:00"
}
```

`eventType` is one of: `CREATED`, `UPDATED`, `DELETED`.

## Related

- [pet-api](https://github.com/SlavchoVlakeskiGit/pet-api) — the event producer
