package yes.no.maybeCMS.services.products;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.controllers.shop.inventories.InventoryNotFoundException;
import yes.no.maybeCMS.entities.shop.Product;
import yes.no.maybeCMS.entities.shop.Tag;
import yes.no.maybeCMS.services.categories.CategoryNotFoundException;
import yes.no.maybeCMS.services.categories.CategoryService;
import yes.no.maybeCMS.services.inventories.InventoryService;
import yes.no.maybeCMS.services.tags.TagService;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final InventoryService inventoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, TagService tagService, InventoryService inventoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.inventoryService = inventoryService;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void delete(UUID id) throws ProductNotFoundException {
        var product = getById(id);
        productRepository.delete(product);
    }

    @Transactional
    public Product update(Product product) throws ProductNotFoundException, CategoryNotFoundException, InventoryNotFoundException {
        var dbProduct = getById(product.getId());
        dbProduct.setName(product.getName());
        dbProduct.setDescription(product.getDescription());
        dbProduct.setCategory(categoryService.getById(product.getCategory().getId()));
        dbProduct.setTags(new HashSet<>(tagService.getAllByIds(product.getTags().stream().map(Tag::getId).toList())));
//        dbProduct.setInventory(inventoryService.getById(product.getInventory().getId()));
        return productRepository.save(dbProduct);
    }
}
