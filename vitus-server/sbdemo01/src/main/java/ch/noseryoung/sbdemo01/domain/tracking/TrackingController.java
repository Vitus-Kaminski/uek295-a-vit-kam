package ch.noseryoung.sbdemo01.domain.tracking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping
    public ResponseEntity<List<Tracking>> getAllTrackings() {
        return ResponseEntity.ok(trackingService.getAllTrackings());
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<Tracking> getTracking(@PathVariable UUID trackingId) {
        return ResponseEntity.ok(trackingService.getTrackingById(trackingId));
    }

    @PostMapping
    public ResponseEntity<Tracking> createTracking(@RequestBody Tracking tracking) {
        Tracking createdTracking = trackingService.createTracking(tracking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTracking);
    }

    @PutMapping("/{trackingId}")
    public ResponseEntity<Tracking> updateTracking(@PathVariable UUID trackingId,
                                                   @RequestBody Tracking tracking) {
        Tracking updatedTracking = trackingService.updateTracking(trackingId, tracking);
        return ResponseEntity.ok(updatedTracking);
    }

    @DeleteMapping("/{trackingId}")
    public ResponseEntity<Void> deleteTracking(@PathVariable UUID trackingId) {
        trackingService.deleteTracking(trackingId);
        return ResponseEntity.noContent().build();
    }
}
