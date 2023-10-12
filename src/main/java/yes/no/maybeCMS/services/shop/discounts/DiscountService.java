package yes.no.maybeCMS.services.shop.discounts;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import yes.no.maybeCMS.entities.shop.Discount;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiscountService {
    private final DiscountRepository discountRepository;

    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    public List<Discount> getAllByName(String name) {
        return discountRepository.findByNameIgnoreCaseContaining(name);
    }

    public Discount getById(UUID id) throws DiscountNotFoundException {
        return discountRepository.findById(id).orElseThrow(DiscountNotFoundException::new);
    }

    @Transactional
    public Discount create(Discount discount) {
        return discountRepository.save(discount);
    }

    @Transactional
    public void delete(@PathVariable UUID id) throws DiscountNotFoundException {
        var discount = getById(id);
        discountRepository.delete(discount);
    }

    @Transactional
    public Discount update(Discount discount) throws DiscountNotFoundException {
        var dbDiscount = getById(discount.getId());
        dbDiscount.setName(discount.getName());
        dbDiscount.setDescription(discount.getDescription());
        dbDiscount.setPercentage(discount.getPercentage());
        return discountRepository.save(dbDiscount);
    }
}
