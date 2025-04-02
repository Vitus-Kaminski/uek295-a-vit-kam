package ch.noseryoung.sbdemo01.domain.order;

import ch.noseryoung.sbdemo01.domain.tracking.Tracking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;

    private String status;
    private String comment;
    private LocalDate date;

    @OneToOne(cascade = CascadeType.PERSIST) // Kein ALL, um ungewollte Löschungen zu vermeiden
    @JoinColumn(name = "tracking_id", referencedColumnName = "tracking_id", nullable = false)
    private Tracking tracking;
}
