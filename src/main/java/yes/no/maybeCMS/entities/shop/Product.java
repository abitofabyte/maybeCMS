package yes.no.maybeCMS.entities.shop;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    UUID id;
    String name;
    String description;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    Category category;
    @ManyToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    Set<Tag> tags;
    @OneToOne(cascade = CascadeType.ALL)
    Inventory inventory;
    double price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "VAT_ID")
    Vat vat;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DISCOUNT_ID")
    Discount discount;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
