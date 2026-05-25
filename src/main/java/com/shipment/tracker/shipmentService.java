package com.shipment.tracker;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipment.tracker.Exception_Hanlder.DuplicateTrackingCodeException;
import com.shipment.tracker.Exception_Hanlder.InvalidStatusTransitionException;
import com.shipment.tracker.Exception_Hanlder.ShipmentNotFoundException;

import java.util.UUID;

@Service
public class shipmentService {

    private final shipmentRepository repository;

    public shipmentService(shipmentRepository repository) {
        this.repository = repository;
    }

    // @CachePut(value = "shipment", key = "#result.trackingCode")
    public shipment create(String trackingCode, shipment.Status status) {
        shipment shipment = new shipment();
        shipment.setTrackingCode(trackingCode);
        if (status != null)
            shipment.setStatus(status);

        try {
            return repository.save(shipment);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateTrackingCodeException();
        }
    }

    // public shipment getByTrackingCode(String trackingCode) {
    // return repository.findByTrackingCode(trackingCode)
    // .orElseThrow(ShipmentNotFoundException::new);
    // }

    @Cacheable(value = "shipment", key = "#trackingCode", unless = "#result == null")
    @Transactional(readOnly = true)
    public shipment getByTrackingCode(String trackingCode) {
        return repository.findByTrackingCode(trackingCode)
                .orElseThrow(() -> new IllegalArgumentException("Shipment not found"));
    }

    @CachePut(value = "shipment", key = "#trackingCode")
    @Transactional
    public shipment updateStatus(UUID id, shipment.Status newStatus) {
        shipment shipment = repository.findById(id)
                .orElseThrow(ShipmentNotFoundException::new);

        shipment.Status current = shipment.getStatus();

        if (current == newStatus)
            return shipment;

        // if (!current.canTransitionTo(newStatus)) {
        // throw new InvalidStatusTransitionException(current.name(), newStatus.name());
        // }
        shipment.setStatus(newStatus);
        return repository.save(shipment);
    }

}