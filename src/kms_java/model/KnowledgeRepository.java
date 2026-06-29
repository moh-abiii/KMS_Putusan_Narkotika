package kms_java.model;

import java.util.ArrayList;

public class KnowledgeRepository {
    private final ArrayList<Putusan> daftarPutusan = new ArrayList<>();


    public void simpan(Putusan p) {
        daftarPutusan.add(p);
    }

    public Putusan cariByNomor(String nomor) {
        for (Putusan p : daftarPutusan) {
            if (p.getNomorPerkara().equalsIgnoreCase(nomor)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Putusan> cariByNama(String nama) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {
            if (p.getNamaTerdakwa().toLowerCase().contains(nama.toLowerCase())) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    @SuppressWarnings("unused")
    public ArrayList<Putusan> filterByJenis(String jenis) {
        // isi kode filterByJenis Anda di bawahnya...

        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {
            if (p.getJenisNarkotika().equalsIgnoreCase(jenis)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {
        ArrayList<Putusan> hasil = new ArrayList<>();
        for (Putusan p : daftarPutusan) {
            if (p.getPengadilan().toLowerCase().contains(pengadilan.toLowerCase())) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    public boolean hapus(String nomor) {
        Putusan p = cariByNomor(nomor);
        if (p != null) {
            daftarPutusan.remove(p);
            return true;
        }
        return false;
    }

    public ArrayList<Putusan> getDaftarSemua() {
        return daftarPutusan;
    }
}
