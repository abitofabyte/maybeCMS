package yes.no.maybeCMS.endpoints.shop;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Product;
import yes.no.maybeCMS.services.shop.categories.CategoryNotFoundException;
import yes.no.maybeCMS.services.shop.discounts.DiscountNotFoundException;
import yes.no.maybeCMS.services.shop.products.ProductNotFoundException;
import yes.no.maybeCMS.services.shop.products.ProductService;
import yes.no.maybeCMS.services.shop.vats.VatNotFoundException;
import yes.no.maybeCMS.services.users.UserNotFoundException;
import yes.no.maybeCMS.services.validation.Uuid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    //todo: search
    private Page<Product> getAll(@RequestParam Optional<String> name, @PageableDefault(size = 20) Pageable pageable) {
        if (name.isEmpty()) {
            return productService.getAllPaged(pageable);
        }
        return productService.getAllByName(name.get(), pageable);
    }

    @GetMapping("seller/{id}")
    private Page<Product> getAllBySellerId(@Uuid @PathVariable UUID id, @PageableDefault(size = 20) Pageable pageable) throws UserNotFoundException {
        return productService.getAllBySellerId(id, pageable);
    }

    @GetMapping("category/{categoryName}")
    private Page<Product> getAllByCategoryName(@PathVariable String categoryName, @RequestParam Optional<String> productName, @RequestParam Optional<List<String>> tags, @PageableDefault(size = 10) Pageable pageable) throws CategoryNotFoundException {
        if (tags.isEmpty()) {
            return productService.getAllByCategory(categoryName, pageable);
        } else {
            return productService.getAllByCategoryAndTags(categoryName, tags.get(), pageable);
        }
    }

    @GetMapping("manage")
    private Page<Product> getAllByAuthentication(Authentication authentication, @PageableDefault(size = 20) Pageable pageable) throws UserNotFoundException {
        return productService.getAllBySellerEmail(authentication.getName(), pageable);
    }

    @GetMapping("{name}")
    private Product getByName(@PathVariable String name) throws ProductNotFoundException {
        return productService.getByName(name);
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
