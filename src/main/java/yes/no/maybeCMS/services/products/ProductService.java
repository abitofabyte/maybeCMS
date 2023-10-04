package yes.no.maybeCMS.services.products;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.entities.shop.Product;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public Product update(Product product) throws ProductNotFoundException {
        var dbProduct = getById(product.getId());
        dbProduct.setName(product.getName() != null ? product.getName() : dbProduct.getName());
        dbProduct.setDescription(product.getDescription() != null ? product.getDescription() : dbProduct.getDescription());
        dbProduct.setCategory(product.getCategory() != null ? product.getCategory() : dbProduct.getCategory());
        dbProduct.setTags(product.getTags() != null ? product.getTags() : dbProduct.getTags());
        dbProduct.setInventory(product.getInventory() != null ? product.getInventory() : dbProduct.getInventory());
        dbProduct.setPrice(product.getPrice() > 0.0 ? product.getPrice() : dbProduct.getPrice());
        dbProduct.setVat(product.getVat() != null ? product.getVat() : dbProduct.getVat());
        dbProduct.setDiscount(product.getDiscount() != null ? product.getDiscount() : dbProduct.getDiscount());
        return productRepository.save(dbProduct);
    }

}
