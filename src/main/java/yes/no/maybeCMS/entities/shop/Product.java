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
    Category category;
    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    Set<Tag> tags;
    @OneToOne(cascade = CascadeType.ALL)
    Inventory inventory;
    double price;
    @ManyToOne(cascade = CascadeType.ALL)
    Vat vat;
    @ManyToOne(cascade = CascadeType.ALL)
    Discount discount;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
