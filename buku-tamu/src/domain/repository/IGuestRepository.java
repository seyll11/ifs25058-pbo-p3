package domain.repository;

import domain.entity.Guest;

/**
 * Port spesifik Guest yang memperluas generic {@link IRepository}.
 * Menambahkan method pembuatan tamu baru dengan penomoran ID otomatis.
 */
public interface IGuestRepository extends IRepository<Guest, Integer> {
    /**
     * Menyimpan tamu baru.
     *
     * @return tamu yang tersimpan (lengkap dengan ID)
     */
    Guest save(String name, String purpose);
}
