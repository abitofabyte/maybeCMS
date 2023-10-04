package yes.no.maybeCMS.services.products;

import org.springframework.data.jpa.repository.JpaRepository;
import yes.no.maybeCMS.entities.shop.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
