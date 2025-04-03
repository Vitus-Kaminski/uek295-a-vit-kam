package ch.noseryoung.sbdemo01.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByDateBefore(LocalDate date);

    List<Order> findByDateAfter(LocalDate date);

    List<Order> findByStatus(String status);

    List<Order> findByCommentContaining(String keyword);


}
