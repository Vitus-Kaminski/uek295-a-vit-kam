package ch.noseryoung.sbdemo01.domain.order;

import ch.noseryoung.sbdemo01.domain.tracking.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Alle Bestellungen abrufen
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Eine Bestellung nach ID abrufen
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Neue Bestellung anlegen
    public Order createOrder(Order order) {
        // Wenn Tracking direkt mitangelegt wird, wird es dank CascadeType.ALL auch gespeichert
        return orderRepository.save(order);
    }

    // Bestellung aktualisieren
    public Order updateOrder(UUID orderId, Order updatedOrder) {
        Order existingOrder = getOrderById(orderId);

        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setComment(updatedOrder.getComment());
        existingOrder.setDate(updatedOrder.getDate());

        // Tracking ggf. aktualisieren
        Tracking updatedTracking = updatedOrder.getTracking();
        if (updatedTracking != null) {
            existingOrder.setTracking(updatedTracking);
        }

        return orderRepository.save(existingOrder);
    }

    // Bestellung löschen
    public void deleteOrder(UUID orderId) {
        Order existingOrder = getOrderById(orderId);
        orderRepository.delete(existingOrder);
    }
}
