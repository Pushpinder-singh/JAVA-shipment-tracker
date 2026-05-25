package com.shipment.tracker.dto;

import com.shipment.tracker.shipment;

import jakarta.validation.constraints.NotBlank;

public class shipmentRequest {

    @NotBlank
    private String trackingCode;

    private shipment.Status status; // optional

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public shipment.Status getStatus() {
        return status;
    }

    public void setStatus(shipment.Status status) {
        this.status = status;
    }
}