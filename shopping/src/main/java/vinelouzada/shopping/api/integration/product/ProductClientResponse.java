package vinelouzada.shopping.api.integration.product;

import java.math.BigDecimal;

public record ProductClientResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String identifier
) {
}
