package yes.no.maybeCMS.endpoints.shop.vats;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Vat;
import yes.no.maybeCMS.services.shop.vats.VatNotFoundException;
import yes.no.maybeCMS.services.shop.vats.VatService;
import yes.no.maybeCMS.validation.Uuid;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("vats")
public class VatRestController {
    private final VatService vatService;

    @GetMapping
    private List<Vat> getAll() {
        return vatService.getAll();
    }

    @GetMapping("{id}")
    private Vat getById(@Uuid @PathVariable UUID id) throws VatNotFoundException {
        return vatService.getById(id);
    }

    @PostMapping
    private Vat create(@Valid @RequestBody Vat vat) {
        return vatService.create(vat);
    }

    @DeleteMapping("{id}")
    private void delete(@Uuid @PathVariable UUID id) throws VatNotFoundException {
        vatService.delete(id);
    }

    @PatchMapping
    private Vat update(@Valid @RequestBody Vat vat) throws VatNotFoundException {
        return vatService.update(vat);
    }
}
