package domain.repository;

import domain.entity.Contact;

/**
 * Port spesifik Contact yang memperluas generic {@link IRepository}.
 * Menambahkan method pembuatan kontak baru dengan penomoran ID otomatis.
 */
public interface IContactRepository extends IRepository<Contact, Integer> {
    /**
     * Menyimpan kontak baru.
     *
     * @return kontak yang tersimpan (lengkap dengan ID)
     */
    Contact save(String name, String phone, String email);
}
