package yes.no.maybeCMS.controllers.shop.products;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.services.categories.CategoryNotFoundException;
import yes.no.maybeCMS.controllers.shop.discounts.DiscountNotFoundException;
import yes.no.maybeCMS.controllers.shop.inventories.InventoryNotFoundException;
import yes.no.maybeCMS.controllers.shop.vats.VatNotFoundException;
import yes.no.maybeCMS.entities.shop.Product;
import yes.no.maybeCMS.services.products.ProductNotFoundException;
import yes.no.maybeCMS.services.products.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    private Product getById(@PathVariable UUID id) throws ProductNotFoundException {
        return productService.getById(id);
    }

    @PostMapping
    Product create(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws ProductNotFoundException {
        productService.delete(id);
    }

    @PatchMapping
    Product update(@RequestBody Product product) throws ProductNotFoundException, CategoryNotFoundException, InventoryNotFoundException, DiscountNotFoundException, VatNotFoundException {
        return productService.update(product);
    }
}
