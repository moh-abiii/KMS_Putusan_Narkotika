package kms_java.view;

import kms_java.model.Putusan;
import kms_java.model.StatistikPutusan;
import kms_java.util.InputHandler;

public class PutusanView {

    public int tampilkanMenu() {
        System.out.println("\n=== MENU KMS PUTUSAN NARKOTIKA ===");
        System.out.println("1. Tambah Putusan");
        System.out.println("2. Tampilkan Semua");
        System.out.println("3. Cari Putusan");
        System.out.println("4. Filter Putusan");
        System.out.println("5. Hapus Putusan");
        System.out.println("6. Tampilkan Statistik");
        System.out.println("0. Keluar");
        return InputHandler.validasiInt("Pilihan Anda: ");
    }

    public Putusan inputFormPutusan() {
        System.out.println("\n--- FORM INPUT DATA PUTUSAN ---");
        String nomor = InputHandler.validasiString("Nomor Perkara: ");
        String pengadilan = InputHandler.validasiString("Nama Pengadilan: ");
        String tanggal = InputHandler.validasiString("Tanggal Putusan: ");
        String nama = InputHandler.validasiString("Nama Terdakwa: ");
        int umur = InputHandler.validasiInt("Usia Terdakwa (Tahun): ");
        String jenis = InputHandler.validasiString("Jenis Narkotika: ");
        double berat = InputHandler.validasiDouble("Berat (Gram): ");
        String pasal = InputHandler.validasiString("Pasal Dilanggar: ");
        String peran = InputHandler.validasiString("Peran Terdakwa: ");
        int vonis = InputHandler.validasiInt("Vonis Hukuman (Bulan): ");
        double denda = InputHandler.validasiDouble("Vonis Denda (Rupiah): ");
        String hakim = InputHandler.validasiString("Nama Hakim Ketua: ");

        return new Putusan(nomor, pengadilan, tanggal, nama, umur, jenis, berat, pasal, peran, vonis, denda, hakim);
    }

    public String inputCari() {
        return InputHandler.validasiString("Masukkan kata kunci pencarian (Nama/Nomor): ");
    }

    public String inputFilter() {
        return InputHandler.validasiString("Masukkan nama pengadilan untuk filter: ");
    }

    public String inputNomor() {
        return InputHandler.validasiString("Masukkan nomor perkara yang akan dihapus: ");
    }

    public void tampilkanDetail(Putusan p) {
        System.out.println("=== DETAIL PUTUSAN ===");
        p.tampilkan(true);
    }

    public void tampilkanStatistik(StatistikPutusan stat) {
        stat.tampilkanLaporan();
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }
}
