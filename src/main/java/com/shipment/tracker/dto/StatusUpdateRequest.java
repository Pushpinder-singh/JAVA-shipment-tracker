package com.shipment.tracker.dto;

import com.shipment.tracker.shipment;

import jakarta.validation.constraints.NotNull;

public class StatusUpdateRequest {

    @NotNull
    private shipment.Status status;

    public shipment.Status getStatus() {
        return status;
    }

    public void setStatus(shipment.Status status) {
        this.status = status;
    }
}