package vinelouzada.shopping.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
    List<Order> findAllByTotalPriceGreaterThan(BigDecimal totalPrice);
    List<Order> findAllByCreatedAtGreaterThanEqual(LocalDateTime createdAt);
}
