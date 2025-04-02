package ch.noseryoung.sbdemo01.domain.tracking;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRepository;

    public List<Tracking> getAllTrackings() {
        return trackingRepository.findAll();
    }

    public Tracking getTrackingById(UUID trackingId) {
        return trackingRepository.findById(trackingId)
                .orElseThrow(() -> new RuntimeException("Tracking not found"));
    }

    public Tracking createTracking(Tracking tracking) {
        return trackingRepository.save(tracking);
    }

    public Tracking updateTracking(UUID trackingId, Tracking updatedTracking) {
        Tracking existingTracking = getTrackingById(trackingId);

        existingTracking.setCode(updatedTracking.getCode());
        existingTracking.setStatus(updatedTracking.getStatus());
        existingTracking.setLastUpdate(updatedTracking.getLastUpdate());

        return trackingRepository.save(existingTracking);
    }

    public void deleteTracking(UUID trackingId) {
        Tracking existingTracking = getTrackingById(trackingId);
        trackingRepository.delete(existingTracking);
    }
    public List<Tracking> getTrackingByCode(String code) {
        return trackingRepository.findByCode(code);
    }

    public List<Tracking> getTrackingByStatus(String status) {
        return trackingRepository.findByStatusContaining(status);
    }


    public List<Tracking> getTrackingAfterDate(LocalDate date) {
        return trackingRepository.findByLastUpdateAfter(date);
    }

    public List<Tracking> getTrackingBeforeDate(LocalDate date) {
        return trackingRepository.findByLastUpdateBefore(date);
    }


}
