package domain.entity;

/**
 * Entity inti yang merepresentasikan satu tamu pada buku tamu.
 * Bebas dari urusan tampilan maupun penyimpanan.
 */
public class Guest {
    /** ID unik tamu, tidak boleh diubah setelah dibuat. */
    private final int id;
    /** Nama tamu. */
    private String name;
    /** Tujuan kunjungan. */
    private String purpose;

    public Guest(int id, String name, String purpose) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurpose() {
        return purpose;
    }

    /** Mengubah nama tamu. */
    public void changeName(String name) {
        this.name = name;
    }

    /** Mengubah tujuan kunjungan. */
    public void changePurpose(String purpose) {
        this.purpose = purpose;
    }
}
