package yes.no.maybeCMS.entities.shop;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import yes.no.maybeCMS.entities.users.User;

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
    @Column(unique = true, nullable = false)
    @NotNull
    @Size(min = 3, max = 255)
    String name;
    @Lob
    String description;
    @ManyToOne
    @JoinColumn(nullable = false)
    Category category;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_tag")
    Set<Tag> tags;
    @Column(nullable = false)
    @PositiveOrZero
    double price;
    @ManyToOne
    @JoinColumn(nullable = false)
    Vat vat;
    @ManyToOne
    @JoinColumn(nullable = false)
    Discount discount;
    @ManyToOne
    @JoinColumn(nullable = false)
    User seller;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
