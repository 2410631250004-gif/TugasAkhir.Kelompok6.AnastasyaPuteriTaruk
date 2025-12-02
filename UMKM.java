public class UMKM {
    private String id; 
    private String nama;
    private String kategori;
    private long omsetBulanan;

    public UMKM(String id, String nama, String kategori, long omsetBulanan) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.omsetBulanan = omsetBulanan;
    }

    // Getter 
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public long getOmsetBulanan() {
        return omsetBulanan;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5s | Nama: %-20s | Kategori: %-15s | Omset: Rp%,d", 
                             id, nama, kategori, omsetBulanan);
    }
}