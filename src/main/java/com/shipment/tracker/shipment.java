package com.shipment.tracker;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "shipments", uniqueConstraints = {
        @UniqueConstraint(name = "uk_tracking_code", columnNames = "tracking_code")
})
public class shipment {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "tracking_code", nullable = false, unique = true)
    private String trackingCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.CREATED;

    @Column(nullable = false)
    private Instant updatedAt;

    public enum Status {
        CREATED, IN_TRANSIT, DELIVERED, CANCELLED
    }

    @PrePersist
    public void prePersist() {
        this.updatedAt = Instant.now();
        if (this.status == null) {
            this.status = Status.CREATED;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    // Getters & Setters
    public UUID getId() {
        return id;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}