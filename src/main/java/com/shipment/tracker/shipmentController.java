package com.shipment.tracker;

import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shipment.tracker.dto.ApiResponse;
import com.shipment.tracker.dto.StatusUpdateRequest;
import com.shipment.tracker.dto.shipmentRequest;

@RestController
@RequestMapping("/api/shipments")
public class shipmentController {

        private final shipmentService service;

        private final shipmentRepository repo;

        public shipmentController(shipmentService service, shipmentRepository repo) {
                this.service = service;
                this.repo = repo;
        }

        @PostMapping
        public ResponseEntity<ApiResponse<shipment>> create(
                        @Valid @RequestBody shipmentRequest request) {

                shipment shipment = service.create(
                                request.getTrackingCode(),
                                request.getStatus());

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(new ApiResponse<>(201, "Shipment created", shipment));
        }

        @PatchMapping("/{id}/status")
        public ResponseEntity<ApiResponse<shipment>> updateStatus(
                        @PathVariable UUID id,
                        @Valid @RequestBody StatusUpdateRequest request) {

                shipment shipment = service.updateStatus(id, request.getStatus());

                return ResponseEntity.ok(
                                new ApiResponse<>(200, "Status updated", shipment));
        }

        @GetMapping("/{trackingCode}")
        public ResponseEntity<ApiResponse<shipment>> get(
                        @PathVariable String trackingCode) {

                shipment shipment = service.getByTrackingCode(trackingCode);

                return ResponseEntity.ok(
                                new ApiResponse<>(200, "Shipment fetched", shipment));
        }
}