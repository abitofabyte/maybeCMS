package yes.no.maybeCMS.services.categories;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.entities.shop.Category;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> getAllByName(String name) {
        return categoryRepository.findByNameLike(name);
    }


    public Category getById(UUID id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void delete(UUID id) throws CategoryNotFoundException {
        var category = getById(id);
        categoryRepository.delete(category);
    }

    @Transactional
    public Category update(Category category) throws CategoryNotFoundException {
        var dbCategory = getById(category.getId());
        dbCategory.setName(category.getName());
        dbCategory.setDescription(category.getDescription());
        return categoryRepository.save(dbCategory);
    }

}
