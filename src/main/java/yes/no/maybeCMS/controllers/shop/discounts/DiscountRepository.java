package yes.no.maybeCMS.controllers.shop.discounts;

import org.springframework.data.jpa.repository.JpaRepository;
import yes.no.maybeCMS.entities.shop.Discount;

import java.util.List;
import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {
    List<Discount> findByNameLike(String name);
}
