package domain.entity;

import java.util.Comparator;

/**
 * Kriteria pengurutan barang.
 * Setiap opsi membawa comparator-nya sendiri sehingga logika pengurutan terpusat di domain.
 */
public enum SortOption {
    /** Nama dari A ke Z (case-insensitive). */
    NAME_ASC(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER)),
    /** Nama dari Z ke A (case-insensitive). */
    NAME_DESC(Comparator.comparing(Item::getName, String.CASE_INSENSITIVE_ORDER).reversed()),
    /** Jumlah stok terkecil lebih dulu. */
    QUANTITY_ASC(Comparator.comparingInt(Item::getQuantity)),
    /** Jumlah stok terbesar lebih dulu. */
    QUANTITY_DESC(Comparator.comparingInt(Item::getQuantity).reversed());

    /** Comparator yang digunakan untuk mengurutkan daftar barang. */
    private final Comparator<Item> comparator;

    SortOption(Comparator<Item> comparator) {
        this.comparator = comparator;
    }

    /** Mengembalikan comparator yang sesuai dengan opsi ini. */
    public Comparator<Item> comparator() {
        return comparator;
    }
}
