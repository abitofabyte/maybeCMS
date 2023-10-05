package yes.no.maybeCMS.services.inventories;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import yes.no.maybeCMS.controllers.shop.inventories.InventoryNotFoundException;
import yes.no.maybeCMS.controllers.shop.inventories.InventoryRepository;
import yes.no.maybeCMS.entities.shop.Inventory;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {
    InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    public Inventory getById(UUID id) throws InventoryNotFoundException {
        return inventoryRepository.findById(id).orElseThrow(InventoryNotFoundException::new);
    }

    @Transactional
    public Inventory create(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Transactional
    public void delete(UUID id) throws InventoryNotFoundException {
        var inventory = getById(id);
        inventoryRepository.delete(inventory);
    }

    @Transactional
    public Inventory update(Inventory inventory) throws InventoryNotFoundException {
        var dbInventory = getById(inventory.getId());
        dbInventory.setQuantity(inventory.getQuantity());
        return inventoryRepository.save(dbInventory);
    }

}
