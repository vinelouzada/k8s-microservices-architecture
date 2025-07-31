package vinelouzada.shopping.api.dto;

import vinelouzada.shopping.api.Item;
import vinelouzada.shopping.api.Order;
import vinelouzada.shopping.api.integration.user.UserClientResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderRequest(
        String cpf,
        List<Item> items
) {
    public Order toModel(UserClientResponse user, List<Item> itemsValidated) {
        return new Order(user.id(), getTotal(itemsValidated), LocalDateTime.now(), items);
    }

    public BigDecimal getTotal(List<Item> itemsValidated) {
        return itemsValidated.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
