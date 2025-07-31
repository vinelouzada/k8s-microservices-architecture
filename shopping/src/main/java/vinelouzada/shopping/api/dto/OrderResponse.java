package vinelouzada.shopping.api.dto;

import vinelouzada.shopping.api.Item;
import vinelouzada.shopping.api.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        Long userId,
        BigDecimal total,
        List<Item> items,
        LocalDateTime createdAt
) {
    public OrderResponse(Order order) {
        this(order.getId(), order.getUserId(), order.getTotalPrice(), order.getItems(), order.getCreatedAt());
    }
}
