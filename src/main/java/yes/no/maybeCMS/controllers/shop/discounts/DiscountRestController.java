package yes.no.maybeCMS.controllers.shop.discounts;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Discount;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("discounts")
public class DiscountRestController {
    DiscountRepository discountRepository;

    public DiscountRestController(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @GetMapping
    List<Discount> getAll(@RequestParam Optional<String> name) {
        if (name.isEmpty()) {
            return discountRepository.findAll();
        }
        return discountRepository.findByNameLike(name.get());
    }

    @GetMapping("{id}")
    Discount getById(@PathVariable UUID id) throws DiscountNotFoundException {
        return discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
    }

    @PostMapping
    Discount create(@RequestBody Discount discount) {
        return discountRepository.save(discount);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws DiscountNotFoundException {
        var discount = getById(id);
        discountRepository.delete(discount);
    }

    @PatchMapping
    Discount update(@RequestBody Discount discount) throws DiscountNotFoundException {
        var dbDiscount = getById(discount.getId());
        dbDiscount.setName(discount.getName() != null ? discount.getName() : dbDiscount.getName());
        dbDiscount.setDescription(discount.getDescription() != null ? discount.getDescription() : dbDiscount.getDescription());
        dbDiscount.setPercentage(discount.getPercentage() > 0.0 ? discount.getPercentage() : dbDiscount.getPercentage());
        return discountRepository.save(dbDiscount);
    }
}
