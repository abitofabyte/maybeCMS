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
    @ManyToOne
    Category category;
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable
    Set<Tag> tags;
//    @OneToOne(cascade = CascadeType.ALL)
//    Inventory inventory;
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
