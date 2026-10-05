package domain.entity;

import java.util.List;

/**
 * Entity inti yang merepresentasikan satu kegiatan terjadwal.
 * Bebas dari urusan tampilan maupun penyimpanan.
 */
public class Activity {
    /** Urutan hari dalam sepekan (Senin sebagai hari pertama). */
    private static final List<String> DAY_ORDER =
            List.of("senin", "selasa", "rabu", "kamis", "jumat", "sabtu", "minggu");

    /** ID unik kegiatan, tidak boleh diubah setelah dibuat. */
    private final int id;
    /** Judul kegiatan. */
    private String title;
    /** Hari pelaksanaan kegiatan. */
    private String day;
    /** Waktu pelaksanaan kegiatan (format HH:mm). */
    private String time;

    public Activity(int id, String title, String day, String time) {
        this.id = id;
        this.title = title;
        this.day = day;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    /**
     * Posisi hari dalam sepekan (0 = Senin ... 6 = Minggu).
     * Hari yang tidak dikenali diletakkan di urutan paling akhir.
     */
    public int getDayOrder() {
        int index = DAY_ORDER.indexOf(day.trim().toLowerCase());
        return index >= 0 ? index : DAY_ORDER.size();
    }

    /** Mengubah judul kegiatan. */
    public void changeTitle(String title) {
        this.title = title;
    }

    /** Mengubah hari kegiatan. */
    public void changeDay(String day) {
        this.day = day;
    }

    /** Mengubah waktu kegiatan. */
    public void changeTime(String time) {
        this.time = time;
    }
}
