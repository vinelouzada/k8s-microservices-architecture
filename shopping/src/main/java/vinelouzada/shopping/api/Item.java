package vinelouzada.shopping.api;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "items")
@Embeddable
public class Item {
    private Long productId;
    private BigDecimal price;

    @Deprecated
    public Item() {}

    public Item(Long productId, BigDecimal price) {
        this.productId = productId;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
