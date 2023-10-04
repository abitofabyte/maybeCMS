package yes.no.maybeCMS.controllers.shop;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import yes.no.maybeCMS.controllers.shop.categories.CategoryRepository;
import yes.no.maybeCMS.controllers.shop.discounts.DiscountRepository;
import yes.no.maybeCMS.controllers.shop.inventories.InventoryRepository;
import yes.no.maybeCMS.services.products.ProductRepository;
import yes.no.maybeCMS.controllers.shop.tags.TagRepository;
import yes.no.maybeCMS.controllers.shop.vats.VatRepository;
import yes.no.maybeCMS.entities.shop.*;

import java.util.Set;

@Controller
public class TestDataLoader implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;
    private final InventoryRepository inventoryRepository;
    private final TagRepository tagRepository;
    private final VatRepository vatRepository;
    private final ProductRepository productRepository;

    public TestDataLoader(CategoryRepository categoryRepository,
                          DiscountRepository discountRepository,
                          InventoryRepository inventoryRepository,
                          TagRepository tagRepository,
                          VatRepository vatRepository,
                          ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.discountRepository = discountRepository;
        this.inventoryRepository = inventoryRepository;
        this.tagRepository = tagRepository;
        this.vatRepository = vatRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        var testCategory = Category.builder().name("Test Category").description("A category for testing.").build();
        var testDiscount = Discount.builder().name("Test Discount").description("A discount for testing.").percentage(0.0).build();
        var testInventory = Inventory.builder().quantity(99).build();
        var testTag = Tag.builder().name("Test Tag").description("A tag for testing").build();
        var testVat = Vat.builder().name("Normal").amount(0.2).build();

        var testProduct = Product.builder()
                .name("Test Product")
                .description("A Product for testing")
                .category(testCategory)
                .tags(Set.of(testTag))
                .inventory(testInventory)
                .price(19.99)
                .vat(testVat)
                .discount(testDiscount)
                .build();

        categoryRepository.save(testCategory);
        discountRepository.save(testDiscount);
        //inventoryRepository.save(testInventory);
        tagRepository.save(testTag);
        vatRepository.save(testVat);

        productRepository.save(testProduct);

    }
}
