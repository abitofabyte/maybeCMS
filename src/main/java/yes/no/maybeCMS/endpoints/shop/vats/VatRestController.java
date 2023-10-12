package yes.no.maybeCMS.endpoints.shop.vats;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Vat;
import yes.no.maybeCMS.services.shop.vats.VatNotFoundException;
import yes.no.maybeCMS.services.shop.vats.VatService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("vats")
public class VatRestController {
    private final VatService vatService;

    public VatRestController(VatService vatService) {
        this.vatService = vatService;
    }

    @GetMapping
    private List<Vat> getAll() {
        return vatService.getAll();
    }

    @GetMapping("{id}")
    private Vat getById(@PathVariable UUID id) throws VatNotFoundException {
        return vatService.getById(id);
    }

    @PostMapping
    private Vat create(@RequestBody Vat vat) {
        return vatService.create(vat);
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable UUID id) throws VatNotFoundException {
        vatService.delete(id);
    }

    @PatchMapping
    private Vat update(@RequestBody Vat vat) throws VatNotFoundException {
        return vatService.update(vat);
    }
}
