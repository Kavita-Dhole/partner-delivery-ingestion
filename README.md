# Partner Delivery Ingestion (Kafka + Spring Boot)

A practice project modelling how partner deliveries can be ingested as events:
accepted over REST, published to Kafka, and processed by a consumer.

## Goal
Build a small event-driven pipeline that validates deliveries at the point of
entry, routes failures to a dead-letter topic, and evolves its message schema
in a backward-compatible way.

## Status
- [x] Kafka running locally in Docker
- [x] Producer (REST -> Kafka) and consumer (logs the message)
- [ ] Validation and dead-letter topic
- [ ] Schema evolution (backward compatible)
- [ ] Tests and architecture notes

## Run it
Requires Docker and Java 17+.

```bash
docker compose up -d
./mvnw spring-boot:run      # or: mvn spring-boot:run
```

Send a test delivery:

```bash
curl -X POST http://localhost:8080/deliveries/label-a \
  -H "Content-Type: application/json" \
  -d '{"title":"Test track"}'
```

The application log should show `Received delivery: {"title":"Test track"}`.

## Design notes
- Message key = partner id, so one partner's deliveries stay ordered within a partition.
- Topic has 3 partitions so consumers can scale out later.
