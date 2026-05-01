package com.example.notificationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class PetEventListener {

    private static final Logger log = LoggerFactory.getLogger(PetEventListener.class);

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(topics = "pet-events", groupId = "notification-service")
    public void handle(PetEvent event) {
        log.info("Received event: type={}, pet={} (id={}), at={}",
                event.eventType(), event.petName(), event.petId(), event.occurredAt());

        String message = switch (event.eventType()) {
            case "CREATED" -> "Welcome " + event.petName() + "! Your profile has been created.";
            case "UPDATED" -> event.petName() + "'s profile has been updated.";
            case "DELETED" -> event.petName() + "'s profile has been removed.";
            default -> "Unknown event for pet " + event.petName();
        };

        log.info("Notification sent: {}", message);
    }

    @DltHandler
    public void handleDlt(PetEvent event) {
        log.error("DLT: failed to process event after all retries — type={}, pet={} (id={}), at={}",
                event.eventType(), event.petName(), event.petId(), event.occurredAt());
    }
}
