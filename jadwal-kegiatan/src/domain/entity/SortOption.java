package domain.entity;

import java.util.Comparator;

/**
 * Kriteria pengurutan kegiatan.
 * Setiap opsi membawa comparator-nya sendiri sehingga logika pengurutan terpusat di domain.
 */
public enum SortOption {
    /** Urutkan menurut hari dalam sepekan (Senin-Minggu), lalu menurut waktu. */
    DAY(Comparator.comparingInt(Activity::getDayOrder).thenComparing(Activity::getTime)),
    /** Urutkan menurut waktu paling awal. */
    TIME(Comparator.comparing(Activity::getTime, String.CASE_INSENSITIVE_ORDER)),
    /** Judul dari A ke Z (case-insensitive). */
    TITLE_ASC(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER)),
    /** Judul dari Z ke A (case-insensitive). */
    TITLE_DESC(Comparator.comparing(Activity::getTitle, String.CASE_INSENSITIVE_ORDER).reversed());

    /** Comparator yang digunakan untuk mengurutkan daftar kegiatan. */
    private final Comparator<Activity> comparator;

    SortOption(Comparator<Activity> comparator) {
        this.comparator = comparator;
    }

    /** Mengembalikan comparator yang sesuai dengan opsi ini. */
    public Comparator<Activity> comparator() {
        return comparator;
    }
}
