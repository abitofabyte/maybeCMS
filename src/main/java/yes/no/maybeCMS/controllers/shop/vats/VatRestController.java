package yes.no.maybeCMS.controllers.shop.vats;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Vat;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("vats")
public class VatRestController {
    VatRepository vatRepository;

    public VatRestController(VatRepository vatRepository) {
        this.vatRepository = vatRepository;
    }

    @GetMapping
    List<Vat> getAll() {
        return vatRepository.findAll();
    }

    @GetMapping("{id}")
    Vat getById(@PathVariable UUID id) throws VatNotFoundException {
        return vatRepository.findById(id).orElseThrow(VatNotFoundException::new);
    }

    @PostMapping
    Vat create(@RequestBody Vat vat) {
        return vatRepository.save(vat);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws VatNotFoundException {
        var vat = getById(id);
        vatRepository.delete(vat);
    }

    @PatchMapping
    Vat update(@RequestBody Vat vat) throws VatNotFoundException {
        var dbVat = getById(vat.getId());
        dbVat.setName(vat.getName() != null ? vat.getName() : dbVat.getName());
        dbVat.setAmount(vat.getAmount() > 0.0 ? vat.getAmount() : dbVat.getAmount());
        return vatRepository.save(dbVat);
    }
}
