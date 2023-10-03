package yes.no.maybeCMS.controllers.shop;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import yes.no.maybeCMS.controllers.shop.categories.CategoryRepository;
import yes.no.maybeCMS.controllers.shop.tags.TagRepository;
import yes.no.maybeCMS.entities.shop.Category;
import yes.no.maybeCMS.entities.shop.Tag;

@Controller
public class TestDataLoader implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public TestDataLoader(CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        categoryRepository.save(Category.builder().name("Test Category").description("A category for testing.").build());

        tagRepository.save(Tag.builder().name("Test Tag").description("A tag for testing").build());
    }
}
