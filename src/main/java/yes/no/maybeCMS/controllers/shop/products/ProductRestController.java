package yes.no.maybeCMS.controllers.shop.products;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Product;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductRestController {
    ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    Product getById(@PathVariable UUID id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @PostMapping
    Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws ProductNotFoundException {
        var product = getById(id);
        productRepository.delete(product);
    }

    @PatchMapping
    Product update(@RequestBody Product product) throws ProductNotFoundException {
        var dbProduct = getById(product.getId());
        dbProduct.setName(product.getName() != null ? product.getName() : dbProduct.getName());
        dbProduct.setDescription(product.getDescription() != null ? product.getDescription() : dbProduct.getDescription());
        dbProduct.setCategory(product.getCategory() != null ? product.getCategory() : dbProduct.getCategory());
        dbProduct.setTags(product.getTags() != null  ? product.getTags() : dbProduct.getTags());
        dbProduct.setInventory(product.getInventory() != null ? product.getInventory() : dbProduct.getInventory());
        dbProduct.setPrice(product.getPrice() > 0.0 ? product.getPrice() : dbProduct.getPrice());
        dbProduct.setVat(product.getVat() != null ? product.getVat() : dbProduct.getVat());
        dbProduct.setDiscount(product.getDiscount() != null ? product.getDiscount() : dbProduct.getDiscount());
        return productRepository.save(dbProduct);
    }
}
