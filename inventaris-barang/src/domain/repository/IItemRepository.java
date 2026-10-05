package domain.repository;

import domain.entity.Item;

/**
 * Port spesifik Item yang memperluas generic {@link IRepository}.
 * Menambahkan method pembuatan barang baru dengan penomoran ID otomatis.
 */
public interface IItemRepository extends IRepository<Item, Integer> {
    /**
     * Menyimpan barang baru.
     *
     * @return barang yang tersimpan (lengkap dengan ID)
     */
    Item save(String name, int quantity, String category);
}
