package vinelouzada.product.api.dto;

import vinelouzada.product.api.Category;
import vinelouzada.product.api.Product;

import java.math.BigDecimal;

public record CreateProductRequest(
        String identifier,
        String name,
        String description,
        BigDecimal price,
        Category category
) {
    public Product toModel() {
        return new Product(name, description, price, identifier, category);
    }
}
