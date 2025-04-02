package ch.noseryoung.sbdemo01.domain.tracking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, UUID> {
    List<Tracking> findByCode(String code);

    List<Tracking> findByStatusContaining(String status);

    List<Tracking> findByLastUpdateAfter(LocalDate date);

    List<Tracking> findByLastUpdateBefore(LocalDate date);
}
