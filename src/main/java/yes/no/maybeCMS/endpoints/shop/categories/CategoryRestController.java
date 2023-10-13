package yes.no.maybeCMS.endpoints.shop.categories;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Category;
import yes.no.maybeCMS.services.shop.categories.CategoryNotFoundException;
import yes.no.maybeCMS.services.shop.categories.CategoryService;
import yes.no.maybeCMS.validation.Uuid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    @GetMapping
    private List<Category> getAll(@RequestParam Optional<String> name) {
        if (name.isEmpty()) {
            return categoryService.getAll();
        }
        return categoryService.getAllByName(name.get());
    }

    @GetMapping("{id}")
    private Category getById(@Uuid @PathVariable UUID id) throws CategoryNotFoundException {
        return categoryService.getById(id);
    }

    @PostMapping
    private Category save(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("{id}")
    private void delete(@Uuid @PathVariable UUID id) throws CategoryNotFoundException {
        categoryService.delete(id);
    }

    @PatchMapping
    private Category update(@Valid @RequestBody Category category) throws CategoryNotFoundException {
        return categoryService.update(category);
    }
}

