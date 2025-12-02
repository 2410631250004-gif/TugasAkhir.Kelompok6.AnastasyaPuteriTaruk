import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ManajemenData {
    private ArrayList<UMKM> daftarUMKM;

    public ManajemenData() {
        this.daftarUMKM = new ArrayList<>();
    }

    public void tambahUMKM(UMKM umkm) {
        daftarUMKM.add(umkm);
    }

    public ArrayList<UMKM> getDaftarUMKM() {
        return daftarUMKM;
    }

   //sorting 
    
    /**
    @param isAscending jika true maka urut kecil ke besar, jika false besar ke kecil
     */
    public void urutkanBerdasarkanOmset(boolean isAscending) {
        this.daftarUMKM = mergeSort(this.daftarUMKM, isAscending);
    }

    private ArrayList<UMKM> mergeSort(ArrayList<UMKM> list, boolean isAscending) {
        // Base Case Rekursi
        if (list.size() <= 1) {
            return list;
        }

        // Divide (Membagi list)
        int tengah = list.size() / 2;
        ArrayList<UMKM> kiri = new ArrayList<>();
        ArrayList<UMKM> kanan = new ArrayList<>();

        for (int i = 0; i < tengah; i++) kiri.add(list.get(i));
        for (int i = tengah; i < list.size(); i++) kanan.add(list.get(i));

        // Conquer (Rekursif)
        kiri = mergeSort(kiri, isAscending);
        kanan = mergeSort(kanan, isAscending);

        // Merge (Menggabungkan)
        return merge(kiri, kanan, isAscending);
    }

    private ArrayList<UMKM> merge(ArrayList<UMKM> kiri, ArrayList<UMKM> kanan, boolean isAscending) {
        ArrayList<UMKM> hasil = new ArrayList<>();
        int i = 0, j = 0;

        while (i < kiri.size() && j < kanan.size()) {
            boolean kondisi;
            long omsetKiri = kiri.get(i).getOmsetBulanan();
            long omsetKanan = kanan.get(j).getOmsetBulanan();

            if (isAscending) {
                // Ascending: Pilih yang lebih KECIL
                kondisi = omsetKiri <= omsetKanan;
            } else {
                // Descending: Pilih yang lebih BESAR
                kondisi = omsetKiri >= omsetKanan;
            }

            if (kondisi) {
                hasil.add(kiri.get(i));
                i++;
            } else {
                hasil.add(kanan.get(j));
                j++;
            }
        }

        while (i < kiri.size()) {
            hasil.add(kiri.get(i));
            i++;
        }
        while (j < kanan.size()) {
            hasil.add(kanan.get(j));
            j++;
        }

        return hasil;
    }

    // Urutkan ID secara Ascending 
    public void urutkanIDAscending() {
        Collections.sort(daftarUMKM, Comparator.comparing(UMKM::getId));
    }

    // searching menggunakan binary search berdasarkan id

    public UMKM cariBerdasarkanID(String idDicari) {
        urutkanIDAscending(); 
        
        int hasilIndex = binarySearchRecursive(daftarUMKM, idDicari, 0, daftarUMKM.size() - 1);
        
        if (hasilIndex != -1) {
            return daftarUMKM.get(hasilIndex);
        }
        return null;
    }

    private int binarySearchRecursive(ArrayList<UMKM> list, String target, int kiri, int kanan) {
        // Base Case: Tidak ketemu
        if (kiri > kanan) {
            return -1;
        }

        int tengah = kiri + (kanan - kiri) / 2;
        String idTengah = list.get(tengah).getId();

        // Bandingkan String ID
        int res = target.compareToIgnoreCase(idTengah);

        if (res == 0) return tengah; // Ketemu
        if (res > 0) return binarySearchRecursive(list, target, tengah + 1, kanan); // Cari di kanan
        return binarySearchRecursive(list, target, kiri, tengah - 1); // Cari di kiri
    }
}