package kms_java.controller;

import kms_java.model.Putusan;
import kms_java.model.KnowledgeRepository;
import kms_java.view.PutusanView;
import java.util.ArrayList;

@SuppressWarnings("all")
public class PutusanController {
    private final KnowledgeRepository repository;
    private final PutusanView view;

    public PutusanController(KnowledgeRepository repository, PutusanView view) {
        this.repository = repository;
        this.view = view;
    }

    public void tambahPutusan(Putusan p) {
        repository.simpan(p);
        view.tampilkanPesan("[Sukses] Data putusan berhasil ditambahkan.");
    }

    public void tampilkanSemua() {
        ArrayList<Putusan> daftar = repository.getDaftarSemua();
        if (daftar.isEmpty()) {
            view.tampilkanPesan("Belum ada data di dalam sistem.");
            return;
        }

        System.out.println("\n===========================================================================");
        System.out.printf("| %-25s | %-20s | %-10s | %-12s |\n", "NOMOR PERKARA", "NAMA TERDAKWA", "JENIS", "VONIS");
        System.out.println("===========================================================================");

        for (Putusan p : daftar) {
            p.tampilkan();
        }

        System.out.println("===========================================================================");
        System.out.println("Total Entitas Objek Terbuat: " + kms_java.model.Putusan.getJumlahDibuat());
    }

    public void cariPutusan(String keyword) {
        Putusan p = repository.cariByNomor(keyword);
        if (p != null) {
            view.tampilkanDetail(p);
        } else {
            ArrayList<Putusan> hasilNama = repository.cariByNama(keyword);
            if (!hasilNama.isEmpty()) {
                for (Putusan pNama : hasilNama) {
                    pNama.tampilkan();
                }
            } else {
                view.tampilkanPesan("Data tidak ditemukan.");
            }
        }
    }

    public void filterPutusan(String kriteria) {
        ArrayList<Putusan> hasil = repository.filterByPengadilan(kriteria);
        if (hasil.isEmpty()) {
            view.tampilkanPesan("Tidak ada data yang cocok dengan filter pengadilan.");
        } else {
            for (Putusan p : hasil) {
                p.tampilkan();
            }
        }
    }

    public void hapusPutusan(String nomor) {
        boolean sukses = repository.hapus(nomor);
        if (sukses) {
            view.tampilkanPesan("Putusan dengan nomor " + nomor + " berhasil dihapus.");
        } else {
            view.tampilkanPesan("Putusan dengan nomor " + nomor + " tidak ditemukan.");
        }
    }

    public void tampilkanStatistik() {
        ArrayList<Putusan> daftar = repository.getDaftarSemua();
        kms_java.model.StatistikPutusan statistik = new kms_java.model.StatistikPutusan(daftar);
        view.tampilkanStatistik(statistik);
    }
}
