package usecase;

import domain.entity.Contact;
import domain.entity.SortOption;
import domain.exception.EntityNotFoundException;
import domain.exception.ValidationException;
import domain.repository.IContactRepository;
import java.util.List;
import java.util.Optional;

/**
 * Use case yang menangani logika bisnis kontak teman.
 * Tidak melakukan I/O - hanya memproses data dan melempar checked exception domain.
 */
public class ContactUseCase {
    /** Port repository yang di-inject dari luar (Dependency Inversion). */
    private final IContactRepository contactRepository;

    public ContactUseCase(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /** Mengambil semua kontak. */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /** Menambahkan kontak baru setelah validasi. */
    public Contact addContact(String name, String phone, String email) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Nama kontak tidak boleh kosong!");
        }
        return contactRepository.save(name, phone == null ? "" : phone, email == null ? "" : email);
    }

    /**
     * Mengubah nama, telepon, dan/atau email kontak.
     * Parameter {@code null} berarti field tersebut tidak diubah.
     */
    public void updateContact(int id, String name, String phone, String email)
            throws EntityNotFoundException, ValidationException {
        Optional<Contact> found = contactRepository.findById(id);
        if (found.isEmpty()) {
            throw new EntityNotFoundException(id);
        }
        if (name != null && name.trim().isEmpty()) {
            throw new ValidationException("Nama kontak tidak boleh kosong!");
        }
        Contact contact = found.get();
        if (name != null) {
            contact.changeName(name);
        }
        if (phone != null) {
            contact.changePhone(phone);
        }
        if (email != null) {
            contact.changeEmail(email);
        }
        contactRepository.update(contact);
    }

    /** Menghapus kontak berdasarkan ID; melempar exception jika tidak ditemukan. */
    public void removeContact(int id) throws EntityNotFoundException {
        if (!contactRepository.deleteById(id)) {
            throw new EntityNotFoundException(id);
        }
    }

    /** Mencari kontak berdasarkan nama (case-insensitive). */
    public List<Contact> searchContacts(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return contactRepository.findBy(c -> c.getName().toLowerCase().contains(lowerKeyword));
    }

    /** Mengurutkan kontak sesuai kriteria {@link SortOption}. */
    public List<Contact> sortContacts(SortOption option) {
        return contactRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }
}
