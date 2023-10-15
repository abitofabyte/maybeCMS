package yes.no.maybeCMS.endpoints.shop.products;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Product;
import yes.no.maybeCMS.services.shop.categories.CategoryNotFoundException;
import yes.no.maybeCMS.services.shop.discounts.DiscountNotFoundException;
import yes.no.maybeCMS.services.shop.products.ProductNotFoundException;
import yes.no.maybeCMS.services.shop.products.ProductService;
import yes.no.maybeCMS.services.shop.vats.VatNotFoundException;
import yes.no.maybeCMS.services.users.UserNotFoundException;
import yes.no.maybeCMS.validation.Uuid;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    private Page<Product> getAll(@RequestParam Optional<String> name, @PageableDefault(size = 20) Pageable pageable) {
        if (name.isEmpty()) {
            return productService.getAllPaged(pageable);
        }
        return productService.getAllByName(name.get(), pageable);
    }

    @GetMapping("seller/{id}")
    private Page<Product> getAllBySellerId(@Uuid @PathVariable UUID id, @PageableDefault(size = 20) Pageable pageable) throws UserNotFoundException {
        return productService.getAllBySeller(id, pageable);
    }

    @GetMapping("category/{id}")
    private Page<Product> getAllByCategoryId(@Uuid @PathVariable UUID id, @PageableDefault(size = 20) Pageable pageable) throws CategoryNotFoundException {
        return productService.getAllByCategory(id, pageable);
    }

    @GetMapping("{id}")
    private Product getById(@Uuid @PathVariable UUID id) throws ProductNotFoundException {
        return productService.getById(id);
    }

    @PostMapping
    private Product create(@Valid @RequestBody Product product) throws DataIntegrityViolationException {
        return productService.createProduct(product);
    }

    @DeleteMapping("{id}")
    private void delete(@Uuid @PathVariable UUID id) throws ProductNotFoundException {
        productService.delete(id);
    }

    @PatchMapping
    private Product update(@Valid @RequestBody Product product) throws ProductNotFoundException, CategoryNotFoundException, DiscountNotFoundException, VatNotFoundException, DataIntegrityViolationException {
        return productService.update(product);
    }
}
