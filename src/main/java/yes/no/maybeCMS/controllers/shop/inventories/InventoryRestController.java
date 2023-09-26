package yes.no.maybeCMS.controllers.shop.inventories;

import org.springframework.web.bind.annotation.*;
import yes.no.maybeCMS.entities.shop.Inventory;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("inventories")
public class InventoryRestController {
    InventoryRepository inventoryRepository;

    public InventoryRestController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping
    List<Inventory> getAll() {
        return inventoryRepository.findAll();
    }

    @GetMapping("{id}")
    Inventory getById(@PathVariable UUID id) throws InventoryNotFoundException {
        return inventoryRepository.findById(id).orElseThrow(InventoryNotFoundException::new);
    }

    @PostMapping
    Inventory create(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable UUID id) throws InventoryNotFoundException {
        var inventory = getById(id);
        inventoryRepository.delete(inventory);
    }

    @PatchMapping
    Inventory update(@RequestBody Inventory inventory) throws InventoryNotFoundException {
        var dbInventory = getById(inventory.getId());
        dbInventory.setQuantity(inventory.getQuantity() >= 0 ? inventory.getQuantity() : dbInventory.getQuantity());
        return inventoryRepository.save(dbInventory);
    }
}
