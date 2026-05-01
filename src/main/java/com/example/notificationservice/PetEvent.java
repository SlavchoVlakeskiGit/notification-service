package com.example.notificationservice;

public record PetEvent(Long petId, String petName, String eventType, String occurredAt) {}
