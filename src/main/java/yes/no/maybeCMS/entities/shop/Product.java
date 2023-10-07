package yes.no.maybeCMS.entities.shop;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

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
    @ManyToOne
    Category category;
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "product_tag")
    Set<Tag> tags;
    double price;
    @ManyToOne
    Vat vat;
    @ManyToOne
    Discount discount;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
