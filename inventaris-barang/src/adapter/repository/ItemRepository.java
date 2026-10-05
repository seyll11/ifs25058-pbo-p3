package adapter.repository;

import domain.entity.Item;
import domain.repository.IItemRepository;

/**
 * Implementasi repository barang menggunakan basis generic {@link InMemoryRepository}.
 */
public class ItemRepository extends InMemoryRepository<Item, Integer> implements IItemRepository {
    /** Penghitung ID otomatis, bertambah setiap kali barang baru disimpan. */
    private int idCounter = 0;

    public ItemRepository() {
        super(Item::getId);
    }

    @Override
    public Item save(String name, int quantity, String category) {
        return save(new Item(nextId(), name, quantity, category));
    }

    /** Menghasilkan ID unik berikutnya. */
    private int nextId() {
        return ++idCounter;
    }
}
