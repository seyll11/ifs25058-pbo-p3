package adapter.repository;

import domain.entity.Activity;
import domain.repository.IActivityRepository;

/**
 * Implementasi repository kegiatan menggunakan basis generic {@link InMemoryRepository}.
 */
public class ActivityRepository extends InMemoryRepository<Activity, Integer> implements IActivityRepository {
    /** Penghitung ID otomatis, bertambah setiap kali kegiatan baru disimpan. */
    private int idCounter = 0;

    public ActivityRepository() {
        super(Activity::getId);
    }

    @Override
    public Activity save(String title, String day, String time) {
        return save(new Activity(nextId(), title, day, time));
    }

    /** Menghasilkan ID unik berikutnya. */
    private int nextId() {
        return ++idCounter;
    }
}
