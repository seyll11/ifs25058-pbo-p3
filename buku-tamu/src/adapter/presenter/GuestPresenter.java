package adapter.presenter;

import domain.entity.Guest;
import java.util.List;

/**
 * Presenter yang memformat data dari use case menjadi output layar.
 * Format tampilan dipisahkan dari entity, use case, dan view.
 */
public class GuestPresenter {
    /** Memformat satu tamu menjadi baris teks. */
    private String format(Guest guest) {
        return String.format("[%d] %s | Tujuan: %s", guest.getId(), guest.getName(), guest.getPurpose());
    }

    /** Helper umum untuk menampilkan daftar tamu. */
    private void printList(List<Guest> guests, String header, String emptyMessage) {
        System.out.println(header);
        if (guests.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        for (Guest guest : guests) {
            System.out.println(format(guest));
        }
    }

    /** Menampilkan daftar semua tamu. */
    public void showGuests(List<Guest> guests) {
        printList(guests, "Daftar Tamu:", "- Data tamu belum tersedia!");
    }

    /** Menampilkan hasil pencarian berdasarkan kata kunci. */
    public void showSearchResults(List<Guest> guests, String keyword) {
        printList(guests, "Hasil Pencarian: \"" + keyword + "\"", "- Tamu tidak ditemukan!");
    }

    public void showRegisterSuccess(Guest guest) {
        System.out.printf("Berhasil mendaftarkan tamu: %s%n", format(guest));
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus tamu.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus tamu dengan ID: %d.%n", id);
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    /** Menampilkan pesan kegagalan validasi dari domain. */
    public void showValidationError(String message) {
        System.out.println("[!] " + message);
    }
}
