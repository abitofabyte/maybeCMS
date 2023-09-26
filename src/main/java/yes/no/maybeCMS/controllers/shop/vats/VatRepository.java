package yes.no.maybeCMS.controllers.shop.vats;

import org.springframework.data.jpa.repository.JpaRepository;
import yes.no.maybeCMS.entities.shop.Vat;

import java.util.UUID;

public interface VatRepository extends JpaRepository<Vat, UUID> {
}
