package usecase;

import domain.entity.Guest;
import domain.exception.EntityNotFoundException;
import domain.exception.ValidationException;
import domain.repository.IGuestRepository;
import java.util.List;

/**
 * Use case yang menangani logika bisnis buku tamu.
 * Tidak melakukan I/O - hanya memproses data dan melempar checked exception domain.
 */
public class GuestUseCase {
    /** Port repository yang di-inject dari luar (Dependency Inversion). */
    private final IGuestRepository guestRepository;

    public GuestUseCase(IGuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    /** Mengambil semua tamu. */
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    /** Mendaftarkan tamu baru setelah validasi. */
    public Guest registerGuest(String name, String purpose) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("Nama tamu tidak boleh kosong!");
        }
        if (purpose == null || purpose.trim().isEmpty()) {
            throw new ValidationException("Tujuan kunjungan tidak boleh kosong!");
        }
        return guestRepository.save(name, purpose);
    }

    /** Mencari tamu berdasarkan nama (case-insensitive). */
    public List<Guest> searchGuests(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return guestRepository.findBy(g -> g.getName().toLowerCase().contains(lowerKeyword));
    }

    /** Menghapus tamu berdasarkan ID; melempar exception jika tidak ditemukan. */
    public void removeGuest(int id) throws EntityNotFoundException {
        if (!guestRepository.deleteById(id)) {
            throw new EntityNotFoundException(id);
        }
    }
}
