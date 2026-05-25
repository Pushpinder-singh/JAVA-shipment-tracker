package com.shipment.tracker.dto;

import java.time.Instant;

public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;
    private Instant timestamp = Instant.now();

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public Instant getTimestamp() { return timestamp; }
}