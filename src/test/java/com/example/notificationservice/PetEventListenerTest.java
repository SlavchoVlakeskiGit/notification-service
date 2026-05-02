package com.example.notificationservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PetEventListenerTest {

    private PetEventListener listener;

    @BeforeEach
    void setUp() {
        listener = new PetEventListener();
    }

    @Test
    void handle_createdEvent_doesNotThrow() {
        PetEvent event = new PetEvent(1L, "Rex", "CREATED", "2024-01-01T00:00:00", "Dog");
        assertDoesNotThrow(() -> listener.handle(event));
    }

    @Test
    void handle_updatedEvent_doesNotThrow() {
        PetEvent event = new PetEvent(2L, "Luna", "UPDATED", "2024-01-01T00:00:00", "Cat");
        assertDoesNotThrow(() -> listener.handle(event));
    }

    @Test
    void handle_deletedEvent_doesNotThrow() {
        PetEvent event = new PetEvent(3L, "Milo", "DELETED", "2024-01-01T00:00:00", "Rabbit");
        assertDoesNotThrow(() -> listener.handle(event));
    }

    @Test
    void handle_unknownEventType_doesNotThrow() {
        PetEvent event = new PetEvent(4L, "Bella", "TRANSFERRED", "2024-01-01T00:00:00", "Dog");
        assertDoesNotThrow(() -> listener.handle(event));
    }

    @Test
    void handleDlt_logsAndDoesNotThrow() {
        PetEvent event = new PetEvent(5L, "Max", "CREATED", "2024-01-01T00:00:00", "Dog");
        assertDoesNotThrow(() -> listener.handleDlt(event));
    }
}
