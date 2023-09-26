package yes.no.maybeCMS.controllers.shop.categories;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("categories")
public class CategoryRestController {
    CategoryRepository categoryRepository;

    public CategoryRestController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    List<Category> getAll(@RequestParam Optional<String> name) {
        if (name.isEmpty()) {
            return categoryRepository.findAll();
        }
        return categoryRepository.findByNameLike(name.get());

    }

    @GetMapping("{id}")
    Category getById(@PathVariable UUID id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @PostMapping
    Category save(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws CategoryNotFoundException {
        var category = getById(id);
        categoryRepository.delete(category);
    }

    @PatchMapping
    Category update(@RequestBody Category category) throws CategoryNotFoundException {
        var dbCategory = getById(category.getId());
        dbCategory.setName(category.getName() != null ? category.getName() : dbCategory.getName());
        dbCategory.setDescription(category.getDescription() != null ? category.getDescription() : dbCategory.getDescription());
        return categoryRepository.save(dbCategory);
    }
}

