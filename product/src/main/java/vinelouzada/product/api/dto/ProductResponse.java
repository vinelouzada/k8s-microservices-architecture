package vinelouzada.product.api.dto;

import vinelouzada.product.api.Product;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String identifier
) {
    public ProductResponse(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getIdentifier());
    }
}
