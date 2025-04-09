package ch.noseryoung.sbdemo01.domain.order;

import ch.noseryoung.sbdemo01.domain.tracking.Tracking;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
public class
Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;

    @NotBlank(message = "Status darf nicht leer sein")
    @Size(max = 50, message = "Status darf maximal 50 Zeichen lang sein")
    @Column(name = "status", nullable = false)
    private String status;

    @Size(max = 255, message = "Kommentar darf maximal 255 Zeichen lang sein")
    @Column(name = "comment")
    private String comment;

    @NotNull(message = "Datum darf nicht null sein")
    @PastOrPresent(message = "Datum darf nicht in der Zukunft liegen")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotNull(message = "Tracking darf nicht null sein")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tracking_id", referencedColumnName = "tracking_id", nullable = false)
    private Tracking tracking;
}
