package ch.noseryoung.sbdemo01.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private UUID orderId;
    private String status;
    private String comment;
    private LocalDate date;


    public OrderDTO(Order order) {
        this.orderId = order.getOrderId();
        this.status = order.getStatus();
        this.comment = order.getComment();
        this.date = order.getDate();
    }
}
