package ch.noseryoung.sbdemo01.domain.tracking;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tracking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tracking {

    @Id
    @GeneratedValue
    @Column(name = "tracking_id")
    private UUID trackingId;

    private String code;
    private String status;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
