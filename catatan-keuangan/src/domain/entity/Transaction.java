package domain.entity;

/**
 * Entity inti yang merepresentasikan satu transaksi keuangan.
 * Bebas dari urusan tampilan maupun penyimpanan.
 */
public class Transaction {
    /** ID unik transaksi, tidak boleh diubah setelah dibuat. */
    private final int id;
    /** Keterangan transaksi. */
    private String description;
    /** Jumlah uang (selalu positif). */
    private double amount;
    /** Jenis transaksi: pemasukan atau pengeluaran. */
    private TransactionType type;

    public Transaction(int id, String description, double amount, TransactionType type) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    /** Mengubah keterangan transaksi. */
    public void changeDescription(String description) {
        this.description = description;
    }

    /** Mengubah jumlah transaksi. */
    public void changeAmount(double amount) {
        this.amount = amount;
    }

    /** Mengubah jenis transaksi. */
    public void changeType(TransactionType type) {
        this.type = type;
    }
}
