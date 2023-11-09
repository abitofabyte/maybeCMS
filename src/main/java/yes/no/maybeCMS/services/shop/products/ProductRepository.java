package yes.no.maybeCMS.services.shop.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yes.no.maybeCMS.entities.shop.Category;
import yes.no.maybeCMS.entities.shop.Product;
import yes.no.maybeCMS.entities.shop.Tag;
import yes.no.maybeCMS.entities.users.User;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByNameIgnoreCaseContaining(String name, Pageable pageable);
    Page<Product> findBySeller(User seller, Pageable pageable);
    Page<Product> findByCategory(Category category, Pageable pageable);
    Page<Product> findByCategoryAndTagsIn(Category category, Set<Tag> tags, Pageable pageable);
    Optional<Product> findByName(String name);

    void deleteAllBySeller(User seller);
}
