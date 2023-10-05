package yes.no.maybeCMS.controllers.shop.categories;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Category;
import yes.no.maybeCMS.services.categories.CategoryNotFoundException;
import yes.no.maybeCMS.services.categories.CategoryService;

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
    List<Category> getAll(@RequestParam Optional<String> name) {
        if (name.isEmpty()) {
            return categoryService.getAll();
        }
        return categoryService.getAllByName(name.get());

    }

    @GetMapping("{id}")
    Category getById(@PathVariable UUID id) throws CategoryNotFoundException {
        return categoryService.getById(id);
    }

    @PostMapping
    Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws CategoryNotFoundException {
        categoryService.delete(id);
    }

    @PatchMapping
    Category update(@RequestBody Category category) throws CategoryNotFoundException {
        return categoryService.update(category);
    }
}

