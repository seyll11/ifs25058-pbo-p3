package adapter.presenter;

import domain.entity.Contact;
import java.util.List;

/**
 * Presenter yang memformat data dari use case menjadi output layar.
 * Format tampilan dipisahkan dari entity, use case, dan view.
 */
public class ContactPresenter {
    /** Memformat satu kontak menjadi baris teks. */
    private String format(Contact contact) {
        return String.format("[%d] %s | Telp: %s | Email: %s",
                contact.getId(), contact.getName(), contact.getPhone(), contact.getEmail());
    }

    /** Helper umum untuk menampilkan daftar kontak. */
    private void printList(List<Contact> contacts, String header, String emptyMessage) {
        System.out.println(header);
        if (contacts.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Contact contact : contacts) {
            System.out.println(format(contact));
        }
    }

    /** Menampilkan daftar semua kontak. */
    public void showContacts(List<Contact> contacts) {
        printList(contacts, "Daftar Kontak:", "- Data kontak belum tersedia!");
    }

    /** Menampilkan hasil pencarian berdasarkan kata kunci. */
    public void showSearchResults(List<Contact> contacts, String keyword) {
        printList(contacts, "Hasil Pencarian: \"" + keyword + "\"", "- Kontak tidak ditemukan!");
    }

    /** Menampilkan daftar kontak yang sudah diurutkan. */
    public void showSortedContacts(List<Contact> contacts) {
        printList(contacts, "Daftar Kontak (Terurut):", "- Data kontak belum tersedia!");
    }

    public void showAddSuccess(Contact contact) {
        System.out.printf("Berhasil menambah kontak: %s%n", format(contact));
    }

    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah kontak.");
    }

    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah kontak dengan ID: %d.%n", id);
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus kontak.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus kontak dengan ID: %d.%n", id);
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    public void showInvalidSortOption() {
        System.out.println("[!] Pilihan tidak valid!");
    }

    /** Menampilkan pesan kegagalan validasi dari domain. */
    public void showValidationError(String message) {
        System.out.println("[!] " + message);
    }
}
