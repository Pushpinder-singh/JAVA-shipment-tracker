package com.shipment.tracker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface shipmentRepository extends JpaRepository<shipment, UUID> {

    Optional<shipment> findByTrackingCode(String trackingCode);

    boolean existsByTrackingCode(String trackingCode);
}