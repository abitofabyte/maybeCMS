package yes.no.maybeCMS.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import yes.no.maybeCMS.entities.shop.*;
import yes.no.maybeCMS.entities.users.User;
import yes.no.maybeCMS.securtiy.Role;
import yes.no.maybeCMS.services.shop.categories.CategoryRepository;
import yes.no.maybeCMS.services.shop.discounts.DiscountRepository;
import yes.no.maybeCMS.services.shop.products.ProductRepository;
import yes.no.maybeCMS.services.shop.tags.TagRepository;
import yes.no.maybeCMS.services.shop.vats.VatRepository;
import yes.no.maybeCMS.services.users.UserRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@RequiredArgsConstructor
public class TestData {

    private static final String lorem = """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus mollis, risus vitae faucibus consequat, turpis lorem ornare eros, in dictum arcu est bibendum ex. Curabitur condimentum sapien egestas, bibendum tellus vel, laoreet turpis. Vivamus aliquam magna elementum libero pellentesque, vitae porttitor erat laoreet. Maecenas auctor urna ac nibh finibus varius. Sed eget vestibulum sem. Ut at tellus justo. Nulla ligula purus, tempor sit amet libero eget, vehicula varius dolor. Pellentesque fringilla malesuada magna.

            Aenean nec arcu enim. Donec a nibh sapien. Cras id porta turpis. Donec faucibus lacinia venenatis. Sed fringilla metus sit amet ultrices scelerisque. Curabitur ut libero at ligula dapibus rutrum quis vel velit. Aliquam nec elit id ipsum auctor posuere ut eu risus. Duis congue dui nibh, sed dapibus ligula finibus in. Sed aliquet ut lacus sed maximus. Praesent suscipit interdum neque in pellentesque. Duis bibendum dolor et sem fermentum vehicula in in purus. Quisque vel ante efficitur, lobortis erat porttitor, finibus sapien. Sed sed odio accumsan, molestie metus id, accumsan lectus.

            Donec semper tempus ligula, vel dignissim ante pellentesque nec. Integer neque lacus, placerat molestie suscipit vel, sollicitudin in neque. Nulla ligula dolor, mattis feugiat sapien at, rhoncus finibus urna. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis posuere tincidunt dolor. Ut vehicula molestie molestie. Sed malesuada augue et libero sollicitudin lacinia. Duis convallis neque vel nulla varius interdum. Proin maximus sed mi at volutpat.

            Phasellus in interdum tortor, lacinia pharetra augue. Nam pretium quam sed faucibus sollicitudin. Integer dui tellus, commodo vel tellus a, consequat sodales erat. Phasellus luctus metus leo, sed hendrerit mauris mattis vulputate. Integer cursus vitae urna at commodo. Pellentesque a ligula nec lacus pellentesque cursus. Praesent est eros, viverra eget turpis at, porta sagittis lectus. Mauris mauris quam, mattis et eleifend sed, fringilla vitae est. Vivamus sagittis, mauris nec vulputate aliquet, sapien turpis vulputate augue, quis mollis lacus ipsum quis turpis. Nunc volutpat eros at commodo tincidunt. Nulla rhoncus velit id quam laoreet sagittis. Nam interdum bibendum molestie. Curabitur ullamcorper libero vel sodales lobortis. Aenean sit amet cursus sapien. Proin consequat metus sit amet semper semper.

            Fusce ultricies tincidunt fringilla. Etiam ut lacus ultrices, convallis tellus et, consectetur enim. Vivamus id nisi convallis, tincidunt felis non, ornare lorem. Phasellus tincidunt sed ex nec gravida. Proin faucibus at dui at commodo. Aenean ac urna odio. Suspendisse mauris elit, fringilla vel risus sed, commodo congue dolor. Donec eu sapien porta, accumsan ligula at, mollis ligula. Vivamus consequat eget nulla nec tempus. Curabitur quis lorem libero. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed augue magna, finibus nec dictum sit amet, ullamcorper id erat. Fusce egestas eu dui non vehicula. Nullam eu lobortis eros.""";

    private final Random random;

    private final CategoryRepository categoryRepository;
    private final DiscountRepository discountRepository;
    private final TagRepository tagRepository;
    private final VatRepository vatRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private List<Category> generateCategories(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(i -> Category.builder()
                        .name("Category " + i)
                        .description("Test category #" + i)
                        .build())
                .toList();
    }

    private List<Discount> generateDiscounts(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(i -> Discount.builder()
                        .name("Discount " + i)
                        .description("Test discount #" + i)
                        .build())
                .toList();
    }

    private List<Tag> generateTags(int amount) {
        return IntStream.range(0, amount)
                .mapToObj(i -> Tag.builder()
                        .name("Tag " + i)
                        .description("Test tag #" + i)
                        .build())
                .toList();
    }

    private List<Vat> generateVats() {
        return List.of(
                Vat.builder().name("Normal").amount(0.2).build(),
                Vat.builder().name("Reduced").amount(0.1).build()
        );
    }

    private int getIntInRange(int max) {
        return random.nextInt(0, max);
    }

    private List<Integer> getRandomIds(int amount, int max) {
        var indices = new ArrayList<>(IntStream.range(0, max).boxed().toList());
        Collections.shuffle(indices);
        return indices.stream().limit(amount).toList();
    }

    private Set<Tag> getRandomTags(List<Tag> tags, int minTags, int maxTags) {
        return getRandomIds(random.nextInt(minTags, maxTags), tags.size()).stream().map(tags::get).collect(Collectors.toSet());
    }

    private List<User> generateUsers(int amount) {
        var users = new ArrayList<>(IntStream.range(0, amount)
                .mapToObj(i -> User.builder()
                        .handle("User #" + i)
                        .email("user" + i + "@example.com")
                        .password(passwordEncoder.encode("123"))
                        .profilePicture("https://picsum.photos/400/400")
                        .roles(Set.of(Role.REGISTERED))
                        .lastLogin(LocalDateTime.now())
                        .build()
                ).toList());
        users.add(User.builder()
                .handle("Yes")
                .email("marcus.migotti@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .profilePicture("/public/admin.png")
                .roles(Set.of(Role.REGISTERED, Role.SELLER, Role.ADMIN))
                .lastLogin(LocalDateTime.now())
                .build());
        return users;
    }

    private Set<String> generateImageUrls(int max) {
        var picsumUrl = "https://picsum.photos/id/";

        return IntStream.range(0, getIntInRange(max))
                .mapToObj(i -> picsumUrl + getIntInRange(999) + "/800/600")
                .collect(Collectors.toSet());
    }

    private List<Product> generateProducts(int amount,
                                           List<User> sellers,
                                           List<Category> categories,
                                           List<Tag> tags,
                                           double minPrice,
                                           double maxPrice,
                                           List<Discount> discounts,
                                           List<Vat> vats) {

        return IntStream.range(0, amount)
                .mapToObj(i -> Product.builder()
                        .name("Product #" + i)
                        .description(lorem)
                        .category(categories.get(getIntInRange(categories.size())))
                        .tags(getRandomTags(tags, 0, 5))
                        .price(random.nextDouble(minPrice, maxPrice))
                        .discount(discounts.get(getIntInRange(discounts.size())))
                        .vat(vats.get(getIntInRange(vats.size())))
                        .seller(sellers.get(getIntInRange(sellers.size())))
                        .images(generateImageUrls(10))
                        .build()
                ).toList();
    }

    @Bean
    public ApplicationRunner populate() {
        return args -> {
            var users = userRepository.saveAll(generateUsers(10));
            var categories = categoryRepository.saveAll(generateCategories(4));
            var discounts = discountRepository.saveAll(generateDiscounts(3));
            var tags = tagRepository.saveAll(generateTags(45));
            var vats = vatRepository.saveAll(generateVats());

            productRepository.saveAll(generateProducts(100, users, categories, tags, 10, 100, discounts, vats));
        };
    }
}
