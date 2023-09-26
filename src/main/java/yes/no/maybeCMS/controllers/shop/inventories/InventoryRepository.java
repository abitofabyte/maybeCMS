package yes.no.maybeCMS.controllers.shop.inventories;

import org.springframework.data.jpa.repository.JpaRepository;
import yes.no.maybeCMS.entities.shop.Inventory;

import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
}
