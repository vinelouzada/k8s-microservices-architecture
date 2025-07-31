package vinelouzada.product.api;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String identifier;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Deprecated
    public Product() {}

    public Product(String name, String description, BigDecimal price, String identifier, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.identifier = identifier;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Category getCategory() {
        return category;
    }

}
