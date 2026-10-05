package domain.repository;

import domain.entity.Transaction;
import domain.entity.TransactionType;

/**
 * Port spesifik Transaction yang memperluas generic {@link IRepository}.
 * Menambahkan method pembuatan transaksi baru dengan penomoran ID otomatis.
 */
public interface ITransactionRepository extends IRepository<Transaction, Integer> {
    /**
     * Menyimpan transaksi baru.
     *
     * @return transaksi yang tersimpan (lengkap dengan ID)
     */
    Transaction save(String description, double amount, TransactionType type);
}
