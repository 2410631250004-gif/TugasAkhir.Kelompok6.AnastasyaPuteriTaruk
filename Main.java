import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ManajemenData app = new ManajemenData();
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;

        // masukkan data UMKM
        app.tambahUMKM(new UMKM("U001", "Warkop Bagja", "Kuliner", 5000000));
        app.tambahUMKM(new UMKM("U002", "Warminkuy", "Kuliner", 7000000));
        app.tambahUMKM(new UMKM("U003", "Laundry 88", "Jasa", 10000000));
        app.tambahUMKM(new UMKM("U004", "PRDS.CO", "Fashion", 25000000));
        app.tambahUMKM(new UMKM("U005", "Bengkel Motor Siper", "Otomotif", 8000000));
        app.tambahUMKM(new UMKM("U006", "Sriwedari", "Kuliner", 10000000));

        System.out.println("=== APLIKASI GO DIGITAL UMKM-K  ===");
        System.out.println("=== Sistem Manajemen Data UMKM ===");


        while (pilihan != 5) {
            System.out.println("\nMENU UTAMA :");
            System.out.println("1. Tampilkan Semua Data UMKM");
            System.out.println("2. Tambah Data UMKM Baru");
            System.out.println("3. Urutkan Data UMKM (Berdasarkan Omset)");
            System.out.println("4. Telusuri Data UMKM (Berdasarkan ID)");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");

            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka!");
                continue;
            }

            switch (pilihan) {

                case 1:
                    tampilkanData(app);
                    break;

                case 2:
                    System.out.println("\n--- Tambah Data UMKM ---");

                    System.out.print("ID UMKM (Unik): ");
                    String id = scanner.nextLine();

                    System.out.print("Nama UMKM: ");
                    String nama = scanner.nextLine();

                    System.out.print("Kategori: ");
                    String kat = scanner.nextLine();

                    System.out.print("Omset Bulanan (Angka): ");
                    long omset = 0;

                    try {
                        omset = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Omset harus berupa angka!");
                        continue;
                    }

                    app.tambahUMKM(new UMKM(id, nama, kat, omset));
                    System.out.println("Data berhasil disimpan!");
                    break;

                case 3:
                    System.out.println("\n--- Pilih Urutan Sort ---");
                    System.out.println("1. Tertinggi → Terendah (Descending)");
                    System.out.println("2. Terendah → Tertinggi (Ascending)");
                    System.out.print("Pilihan: ");

                    String sortOption = scanner.nextLine();
                    if (sortOption.equals("1")) {
                        app.urutkanBerdasarkanOmset(false);
                        System.out.println("Data berhasil diurutkan (Descending).");
                    } else {
                        app.urutkanBerdasarkanOmset(true);
                        System.out.println("Data berhasil diurutkan (Ascending).");
                    }
                    tampilkanData(app);
                    break;

                case 4:
                    System.out.println("\n--- Cari Data ---");
                    System.out.print("Masukkan ID (misal U001): ");
                    String cari = scanner.nextLine();

                    UMKM hasil = app.cariBerdasarkanID(cari);
                    if (hasil != null) {
                        System.out.println(" UMKM berhasil ditemukan: " + hasil);
                    } else {
                        System.out.println("Data UMKM dengan ID '" + cari + "' tidak dapat ditemukan.");
                    }
                    break;

                case 5:
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    break;

                default:
                    System.out.println("Eror! Pilihan tidak valid.");
            }
        }

        scanner.close();
    }


    private static void tampilkanData(ManajemenData app) {
        System.out.println("\n--- DAFTAR UMKM ---");
        if (app.getDaftarUMKM().isEmpty()) {
            System.out.println("(Data Kosong)");
        } else {
            for (UMKM u : app.getDaftarUMKM()) {
                System.out.println(u);
            }
        }
        System.out.println("-------------------");
    }
}