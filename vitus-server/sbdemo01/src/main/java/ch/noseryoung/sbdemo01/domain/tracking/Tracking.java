package ch.noseryoung.sbdemo01.domain.tracking;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Code darf nicht leer sein")
    @Size(min = 5, max = 20, message = "Code muss zwischen 5 und 20 Zeichen lang sein")
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotBlank(message = "Status darf nicht leer sein")
    @Size(max = 50, message = "Status darf maximal 50 Zeichen lang sein")
    @Column(name = "status", nullable = false)
    private String status;

    @PastOrPresent(message = "Das Datum muss in der Vergangenheit oder Gegenwart liegen")
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;
}
