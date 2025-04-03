package ch.noseryoung.sbdemo01.domain.order;

import ch.noseryoung.sbdemo01.domain.tracking.Tracking;
import ch.noseryoung.sbdemo01.domain.tracking.TrackingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TrackingService trackingService;


    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the order")
    })
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @Operation(summary = "Get a specific order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved order by ID"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/{orderId}")
    public Object getOrder(@RequestParam(required = false) Boolean mode, @PathVariable UUID orderId) {
        Order order = orderService.getOrderById(orderId);
        if (mode == null) {
            return ResponseEntity.ok(order);
        } else if (mode) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.ok(new OrderDTO(order));
        }
    }


    @Operation(summary = "Update an existing order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the order"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable UUID orderId, @Valid @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(orderId, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @Operation(summary = "Delete an existing order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the order"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID orderId) {

        if (orderService.getOrderById(orderId) != null) {
            orderService.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        } else {
            log.error("Order not found check if already deleted or wrong ID");
            return ResponseEntity.noContent().build();
        }

    }


    @Operation(summary = "Create a new tracking ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the tracking ")
    })
    @PostMapping("/trackings")
    public ResponseEntity<Tracking> createTracking(@Valid @RequestBody Tracking tracking) {
        Tracking createdTracking = trackingService.createTracking(tracking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTracking);
    }


    @Operation(summary = "Get a specific tracking by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tracking by ID"),
            @ApiResponse(responseCode = "404", description = "Tracking not found")
    })
    @GetMapping("/trackings/{trackingId}")
    public ResponseEntity<Tracking> getTracking(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(trackingService.getTrackingById(trackingId));
    }


    @Operation(summary = "Update an existing tracking by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the tracking"),
            @ApiResponse(responseCode = "404", description = "Tracking not found")
    })
    @PutMapping("/trackings/{trackingId}")
    public ResponseEntity<Tracking> updateTracking( @PathVariable UUID trackingId, @Valid @RequestBody Tracking tracking) {
        Tracking updatedTracking = trackingService.updateTracking(trackingId, tracking);
        return ResponseEntity.ok(updatedTracking);
    }

    @Operation(summary = "Delete an existing tracking by ID (only works with abandoned tracking)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted the tracking"),
            @ApiResponse(responseCode = "404", description = "Tracking not found"),
            @ApiResponse(responseCode = "500", description = "this Tracking is bond to an order")
    })
    @DeleteMapping("/trackings/{trackingId}")
    public ResponseEntity<Void> deleteTracking(@PathVariable UUID trackingId) {
        trackingService.deleteTracking(trackingId);
        return ResponseEntity.noContent().build();
    }



    @Operation(summary = "Get a list of all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of orders")
    })
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @Operation(summary = "Get a list of all tracking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tracking ")
    })
    @GetMapping("/trackings")
    public ResponseEntity<List<Tracking>> getAllTrackings() {
        return ResponseEntity.ok(trackingService.getAllTrackings());
    }


    @Operation(summary = "Get orders filtered by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved filtered orders by date"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/date")
    public ResponseEntity<List<Order>> getOrdersByDate(@RequestParam(required = false) String before,
                                                       @RequestParam(required = false) String after) {
        if (before != null) {
            return ResponseEntity.ok(orderService.getOrdersBeforeDate(LocalDate.parse(before)));
        }
        if (after != null) {
            return ResponseEntity.ok(orderService.getOrdersAfterDate(LocalDate.parse(after)));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Get Trackings filtered by date (format: JJJJ-MM-DD)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved filtered Trackings by date"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/trackings/date")
    public ResponseEntity<List<Tracking>> getTrackingsByDate(@RequestParam(required = false) String before,
                                                             @RequestParam(required = false) String after) {
        if (before != null) {
            return ResponseEntity.ok(trackingService.getTrackingBeforeDate(LocalDate.parse(before)));
        }
        if (after != null) {
            return ResponseEntity.ok(trackingService.getTrackingAfterDate(LocalDate.parse(after)));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Get orders by comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved orders by comment")
    })
    @GetMapping("/comment/{contains}")
    public ResponseEntity<List<Order>> getOrdersByComment(@PathVariable String contains) {
        return ResponseEntity.ok(orderService.getOrdersByComment(contains));
    }


    @Operation(summary = "Get orders by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved orders by status")
    })
    @GetMapping("/status")
    public ResponseEntity<List<Order>> getOrdersByStatus(@RequestParam() String status) {
        return ResponseEntity.ok(orderService.getOrdersByStatus(status));
    }

    @Operation(summary = "Get tracking filtered by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tracking by status")
    })
    @GetMapping("/trackings/status/{status}")
    public ResponseEntity<List<Tracking>> getTrackingsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(trackingService.getTrackingByStatus(status));
    }

    @Operation(summary = "Get tracking filtered by code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved tracking by code")
    })
    @GetMapping("/trackings/code/{code}")
    public ResponseEntity<List<Tracking>> getTrackingsByCode(@PathVariable String code) {
        return ResponseEntity.ok(trackingService.getTrackingByCode(code));
    }


}
