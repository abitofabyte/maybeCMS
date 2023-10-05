package yes.no.maybeCMS.controllers.shop;

import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;
import yes.no.maybeCMS.services.categories.CategoryRepository;
import yes.no.maybeCMS.controllers.shop.discounts.DiscountRepository;
import yes.no.maybeCMS.controllers.shop.inventories.InventoryRepository;
import yes.no.maybeCMS.services.products.ProductRepository;
import yes.no.maybeCMS.services.tags.TagRepository;
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
                          TagRepository tagRepository,
                          InventoryRepository inventoryRepository,
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
    @Transactional
    public void run(ApplicationArguments args) {


        var testCategory = Category.builder().name("Test Category").description("A category for testing.").build();
        var testDiscount = Discount.builder().name("Test Discount").description("A discount for testing.").percentage(0.0).build();
        var testInventory = Inventory.builder().quantity(99).build();
        var testTag = Tag.builder().name("Test Tag").description("A tag for testing").build();
        var testVat = Vat.builder().name("Normal").amount(0.2).build();

        testCategory = categoryRepository.save(testCategory);
        testDiscount = discountRepository.save(testDiscount);
        testInventory = inventoryRepository.save(testInventory);
        testTag = tagRepository.save(testTag);
        testVat = vatRepository.save(testVat);

        var testProduct = Product.builder()
                .name("Test Product")
                .description("A Product for testing")
                .category(testCategory)
                .tags(Set.of(testTag))
                //.inventory(testInventory)
                .price(19.99)
                .vat(testVat)
                .discount(testDiscount)
                .build();


        testProduct = productRepository.save(testProduct);

        testInventory.setProduct(testProduct);

        inventoryRepository.save(testInventory);


    }
}
