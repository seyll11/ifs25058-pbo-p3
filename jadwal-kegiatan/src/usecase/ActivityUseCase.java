package usecase;

import domain.entity.Activity;
import domain.entity.SortOption;
import domain.exception.EntityNotFoundException;
import domain.exception.ValidationException;
import domain.repository.IActivityRepository;
import java.util.List;
import java.util.Optional;

/**
 * Use case yang menangani logika bisnis jadwal kegiatan.
 * Tidak melakukan I/O - hanya memproses data dan melempar checked exception domain.
 */
public class ActivityUseCase {
    /** Port repository yang di-inject dari luar (Dependency Inversion). */
    private final IActivityRepository activityRepository;

    public ActivityUseCase(IActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    /** Mengambil semua kegiatan. */
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    /** Menambahkan kegiatan baru setelah validasi. */
    public Activity addActivity(String title, String day, String time) throws ValidationException {
        requireFilled(title, "Judul kegiatan tidak boleh kosong!");
        requireFilled(day, "Hari kegiatan tidak boleh kosong!");
        requireFilled(time, "Waktu kegiatan tidak boleh kosong!");
        return activityRepository.save(title, day, time);
    }

    /**
     * Mengubah judul, hari, dan/atau waktu kegiatan.
     * Parameter {@code null} berarti field tersebut tidak diubah.
     */
    public void updateActivity(int id, String title, String day, String time)
            throws EntityNotFoundException, ValidationException {
        Optional<Activity> found = activityRepository.findById(id);
        if (found.isEmpty()) {
            throw new EntityNotFoundException(id);
        }
        // Validasi semua field lebih dulu agar perubahan bersifat atomik
        if (title != null) {
            requireFilled(title, "Judul kegiatan tidak boleh kosong!");
        }
        if (day != null) {
            requireFilled(day, "Hari kegiatan tidak boleh kosong!");
        }
        if (time != null) {
            requireFilled(time, "Waktu kegiatan tidak boleh kosong!");
        }
        Activity activity = found.get();
        if (title != null) {
            activity.changeTitle(title);
        }
        if (day != null) {
            activity.changeDay(day);
        }
        if (time != null) {
            activity.changeTime(time);
        }
        activityRepository.update(activity);
    }

    /** Menghapus kegiatan berdasarkan ID; melempar exception jika tidak ditemukan. */
    public void removeActivity(int id) throws EntityNotFoundException {
        if (!activityRepository.deleteById(id)) {
            throw new EntityNotFoundException(id);
        }
    }

    /** Mencari kegiatan berdasarkan judul (case-insensitive). */
    public List<Activity> searchActivities(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return activityRepository.findBy(a -> a.getTitle().toLowerCase().contains(lowerKeyword));
    }

    /** Mengurutkan kegiatan sesuai kriteria {@link SortOption}. */
    public List<Activity> sortActivities(SortOption option) {
        return activityRepository.findAll().stream()
                .sorted(option.comparator())
                .toList();
    }

    private void requireFilled(String value, String message) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(message);
        }
    }
}
