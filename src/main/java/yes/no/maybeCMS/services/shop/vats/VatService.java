package yes.no.maybeCMS.services.shop.vats;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.entities.shop.Vat;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VatService {
    private final VatRepository vatRepository;

    public List<Vat> getAll() {
        return vatRepository.findAll();
    }

    public Vat getById(UUID id) throws VatNotFoundException {
        return vatRepository.findById(id).orElseThrow(VatNotFoundException::new);
    }

    @Transactional
    public Vat create(Vat vat) {
        return vatRepository.save(vat);
    }

    @Transactional
    public void delete(UUID id) throws VatNotFoundException {
        var vat = getById(id);
        vatRepository.delete(vat);
    }

    @Transactional
    public Vat update(Vat vat) throws VatNotFoundException {
        var dbVat = getById(vat.getId());
        dbVat.setName(vat.getName());
        dbVat.setAmount(vat.getAmount());
        return vatRepository.save(dbVat);
    }

}
