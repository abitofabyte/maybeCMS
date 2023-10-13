package yes.no.maybeCMS.endpoints.shop.discounts;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Discount;
import yes.no.maybeCMS.services.shop.discounts.DiscountNotFoundException;
import yes.no.maybeCMS.services.shop.discounts.DiscountService;
import yes.no.maybeCMS.validation.Uuid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("discounts")
public class DiscountRestController {
    private final DiscountService discountService;

    @GetMapping
    List<Discount> getAll(@RequestParam Optional<String> name) {
        if (name.isEmpty()) {
            return discountService.getAll();
        }
        return discountService.getAllByName(name.get());
    }

    @GetMapping("{id}")
    Discount getById(@Uuid @PathVariable UUID id) throws DiscountNotFoundException {
        return discountService.getById(id);
    }

    @PostMapping
    Discount create(@RequestBody Discount discount) {
        return discountService.create(discount);
    }

    @DeleteMapping("{id}")
    void delete(@Uuid @PathVariable UUID id) throws DiscountNotFoundException {
        discountService.delete(id);
    }

    @PatchMapping
    Discount update(@RequestBody Discount discount) throws DiscountNotFoundException {
        return discountService.update(discount);
    }
}
