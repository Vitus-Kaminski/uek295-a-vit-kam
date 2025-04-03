package ch.noseryoung.sbdemo01.domain.order;

import ch.noseryoung.sbdemo01.domain.tracking.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(UUID orderId, Order updatedOrder) {
        Order existingOrder = getOrderById(orderId);

        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setComment(updatedOrder.getComment());
        existingOrder.setDate(updatedOrder.getDate());

        Tracking updatedTracking = updatedOrder.getTracking();
        if (updatedTracking != null) {
            existingOrder.setTracking(updatedTracking);
        }

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(UUID orderId) {
        Order existingOrder = getOrderById(orderId);
        orderRepository.delete(existingOrder);
    }

    public List<Order> getOrdersBeforeDate(LocalDate date) {
        return orderRepository.findByDateBefore(date);
    }

    public List<Order> getOrdersAfterDate(LocalDate date) {
        return orderRepository.findByDateAfter(date);
    }

    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> getOrdersByComment(String keyword) {
        return orderRepository.findByCommentContaining(keyword);
    }



}
