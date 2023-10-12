package yes.no.maybeCMS.endpoints.shop.categories;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Category;
import yes.no.maybeCMS.services.shop.categories.CategoryNotFoundException;
import yes.no.maybeCMS.services.shop.categories.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("categories")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    private List<Category> getAll(@RequestParam Optional<String> name) {
        if (name.isEmpty()) {
            return categoryService.getAll();
        }
        return categoryService.getAllByName(name.get());
    }

    @GetMapping("{id}")
    private Category getById(@PathVariable UUID id) throws CategoryNotFoundException {
        return categoryService.getById(id);
    }

    @PostMapping
    private Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable UUID id) throws CategoryNotFoundException {
        categoryService.delete(id);
    }

    @PatchMapping
    private Category update(@RequestBody Category category) throws CategoryNotFoundException {
        return categoryService.update(category);
    }
}

