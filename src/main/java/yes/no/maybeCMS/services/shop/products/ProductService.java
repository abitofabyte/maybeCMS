package yes.no.maybeCMS.services.shop.products;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.entities.shop.Product;
import yes.no.maybeCMS.entities.shop.Tag;
import yes.no.maybeCMS.entities.users.User;
import yes.no.maybeCMS.services.shop.categories.CategoryNotFoundException;
import yes.no.maybeCMS.services.shop.categories.CategoryService;
import yes.no.maybeCMS.services.shop.discounts.DiscountNotFoundException;
import yes.no.maybeCMS.services.shop.discounts.DiscountService;
import yes.no.maybeCMS.services.shop.tags.TagService;
import yes.no.maybeCMS.services.shop.vats.VatNotFoundException;
import yes.no.maybeCMS.services.shop.vats.VatService;
import yes.no.maybeCMS.services.users.UserNotFoundException;
import yes.no.maybeCMS.services.users.UserRepository;
import yes.no.maybeCMS.services.users.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final VatService vatService;
    private final DiscountService discountService;
    private final UserService userService;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Page<Product> getAllPaged(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getAllByName(String name, Pageable pageable) {
        return productRepository.findByNameIgnoreCaseContaining(name, pageable);
    }

    public Page<Product> getAllByCategory(UUID id, Pageable pageable) throws CategoryNotFoundException {
        var category = categoryService.getById(id);
        return productRepository.findByCategory(category, pageable);
    }

    public Page<Product> getAllBySeller(UUID id, Pageable pageable) throws UserNotFoundException {
        User seller = userService.getById(id);
        return productRepository.findBySeller(seller, pageable);
    }

    public Product getById(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public Product createProduct(Product product) throws DataIntegrityViolationException {
        return productRepository.save(product);
    }

    @Transactional
    public void delete(UUID id) throws ProductNotFoundException {
        var product = getById(id);
        productRepository.delete(product);
    }

    private Iterable<UUID> getTagIdsForProduct(Product product) {
        return product.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
    }

    @Transactional
    public Product update(Product product) throws ProductNotFoundException, CategoryNotFoundException, VatNotFoundException, DiscountNotFoundException, DataIntegrityViolationException {
        var dbProduct = getById(product.getId());
        dbProduct.setName(product.getName());
        dbProduct.setDescription(product.getDescription());
        dbProduct.setCategory(categoryService.getById(product.getCategory().getId()));
        dbProduct.setTags(tagService.getAllByIds(getTagIdsForProduct(product)));
        dbProduct.setPrice(product.getPrice());
        dbProduct.setVat(vatService.getById(product.getVat().getId()));
        dbProduct.setDiscount(discountService.getById(product.getDiscount().getId()));
        return productRepository.save(dbProduct);
    }
}
