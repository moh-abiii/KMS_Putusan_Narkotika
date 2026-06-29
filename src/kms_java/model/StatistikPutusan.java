package kms_java.model;

import java.util.ArrayList;

public class StatistikPutusan {
    private ArrayList<Putusan> daftarPutusan;

    // Constructor
    public StatistikPutusan(ArrayList<Putusan> daftarPutusan) {
        this.daftarPutusan = daftarPutusan;
    }

    // Metode ini yang dibaca oleh PutusanView.java
    public void tampilkanLaporan() {
        if (daftarPutusan == null || daftarPutusan.isEmpty()) {
            System.out.println("Data kosong. Tidak dapat menghitung statistik.");
            return;
        }

        double totalSabu = 0, totalGanja = 0, totalEkstasi = 0;
        int jmlSabu = 0, jmlGanja = 0, jmlEkstasi = 0;
        int totalHukuman = 0;

        for (Putusan p : daftarPutusan) {
            totalHukuman += p.getVonisHukuman();
            String jenis = p.getJenisNarkotika().toLowerCase();
            if (jenis.contains("sabu")) {
                totalSabu += p.getBeratBarangBukti();
                jmlSabu++;
            } else if (jenis.contains("ganja")) {
                totalGanja += p.getBeratBarangBukti();
                jmlGanja++;
            } else if (jenis.contains("ekstasi")) {
                totalEkstasi += p.getBeratBarangBukti();
                jmlEkstasi++;
            }
        }

        System.out.println("\n========== STATISTIK PENGETAHUAN KASUS ==========");
        System.out.println("Total Berkas Putusan Diolah: " + daftarPutusan.size());
        System.out.println("Rata-rata Vonis Hukuman    : " + (totalHukuman / daftarPutusan.size()) + " bulan");
        System.out.println("=================================================");
    }
}
