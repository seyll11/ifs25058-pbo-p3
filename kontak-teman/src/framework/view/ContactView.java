package framework.view;

import adapter.presenter.ContactPresenter;
import domain.entity.SortOption;
import domain.exception.EntityNotFoundException;
import domain.exception.ValidationException;
import framework.util.InputUtil;
import usecase.ContactUseCase;

/**
 * Tampilan konsol aplikasi kontak teman.
 * Menerima input user, memanggil use case, dan menangani exception domain.
 * Tidak mengandung logika bisnis - hanya interaksi user.
 */
public class ContactView {
    private final ContactUseCase useCase;
    private final ContactPresenter presenter;

    public ContactView(ContactUseCase useCase, ContactPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    /** Menampilkan menu utama dan loop interaksi user. */
    public void show() {
        boolean running = true;
        while (running) {
            presenter.showContacts(useCase.getAllContacts());
            printMenu();
            String input = InputUtil.input("Pilih");
            System.out.println();
            switch (input) {
                case "1" -> addContact();
                case "2" -> updateContact();
                case "3" -> searchContact();
                case "4" -> sortContact();
                case "5" -> removeContact();
                case "x" -> running = false;
                default -> presenter.showInvalidChoice();
            }
            if (running) {
                System.out.println();
            }
        }
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah");
        System.out.println("2. Ubah");
        System.out.println("3. Cari");
        System.out.println("4. Urutkan");
        System.out.println("5. Hapus");
        System.out.println("x. Keluar");
    }

    /** Form tambah kontak baru. */
    private void addContact() {
        System.out.println("[Menambah Kontak]");
        String name = InputUtil.input("Nama (x Jika Batal)");
        if (name.equals("x")) {
            return;
        }
        String phone = InputUtil.input("Telepon");
        if (phone.equals("x")) {
            return;
        }
        String email = InputUtil.input("Email");
        if (email.equals("x")) {
            return;
        }
        try {
            presenter.showAddSuccess(useCase.addContact(name, phone, email));
        } catch (ValidationException e) {
            presenter.showValidationError(e.getMessage());
        }
    }

    /** Form ubah kontak; field yang dikosongkan tidak diubah (update parsial). */
    private void updateContact() {
        System.out.println("[Mengubah Kontak]");
        String strId = InputUtil.input("ID Kontak yang diubah (x Jika Batal)");
        if (strId.equals("x")) {
            return;
        }
        int id;
        try {
            id = parseId(strId);
        } catch (ValidationException e) {
            presenter.showInvalidId();
            return;
        }
        String newName = InputUtil.input("Nama Baru (Kosongkan jika tidak ingin mengubah)");
        String newPhone = InputUtil.input("Telepon Baru (Kosongkan jika tidak ingin mengubah)");
        String newEmail = InputUtil.input("Email Baru (Kosongkan jika tidak ingin mengubah)");
        // null berarti field tersebut tidak diubah
        try {
            useCase.updateContact(id, blankToNull(newName), blankToNull(newPhone), blankToNull(newEmail));
            presenter.showUpdateSuccess();
        } catch (EntityNotFoundException e) {
            presenter.showUpdateFailed(e.getId());
        } catch (ValidationException e) {
            presenter.showValidationError(e.getMessage());
        }
    }

    /** Form cari kontak berdasarkan nama. */
    private void searchContact() {
        System.out.println("[Mencari Kontak]");
        String keyword = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (!keyword.equals("x")) {
            presenter.showSearchResults(useCase.searchContacts(keyword), keyword);
        }
    }

    /** Form urutkan kontak. */
    private void sortContact() {
        System.out.println("[Mengurutkan Kontak]");
        System.out.println("Pilihan Pengurutan:");
        System.out.println("1. Nama (A-Z)");
        System.out.println("2. Nama (Z-A)");
        System.out.println("x. Batal");
        String input = InputUtil.input("Pilih");
        System.out.println();
        if (input.equals("x")) {
            return;
        }
        SortOption option = mapSortOption(input);
        if (option == null) {
            presenter.showInvalidSortOption();
            return;
        }
        presenter.showSortedContacts(useCase.sortContacts(option));
    }

    /** Form hapus kontak berdasarkan ID. */
    private void removeContact() {
        System.out.println("[Menghapus Kontak]");
        String strId = InputUtil.input("[ID Kontak] yang dihapus (x Jika Batal)");
        if (strId.equals("x")) {
            return;
        }
        try {
            int id = parseId(strId);
            useCase.removeContact(id);
            presenter.showRemoveSuccess();
        } catch (ValidationException e) {
            presenter.showInvalidId();
        } catch (EntityNotFoundException e) {
            presenter.showRemoveFailed(e.getId());
        }
    }

    /** Mengonversi input string menjadi ID; melempar ValidationException jika bukan angka. */
    private int parseId(String value) throws ValidationException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ValidationException("ID harus berupa angka numerik");
        }
    }

    private String blankToNull(String value) {
        return value.isBlank() ? null : value;
    }

    /** Memetakan pilihan menu (1-2) ke {@link SortOption} domain. */
    private SortOption mapSortOption(String input) {
        return switch (input) {
            case "1" -> SortOption.NAME_ASC;
            case "2" -> SortOption.NAME_DESC;
            default -> null;
        };
    }
}
