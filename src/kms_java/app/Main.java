package kms_java.app;

import kms_java.model.KnowledgeRepository;
import kms_java.model.Putusan;
import kms_java.controller.JavaFXController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Random;

public class Main extends Application {
    private static final KnowledgeRepository repository = new KnowledgeRepository();


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/kms_java/view/JavaFXView.fxml"));
        Parent root = loader.load();

        JavaFXController controller = loader.getController();
        controller.setRepository(repository);

        primaryStage.setTitle("KMS Putusan Pengadilan Narkotika - Berbasis JavaFX");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        // 1. DATASET MASTER ASLI TIM ANDA YANG SUDAH SAYA LURUSKAN JADI 12 KOLOM UTUH
        String[][] dataAsliIndonesia = {
                {"3188/Pid.Sus/2015/PN Sby", "PN Surabaya", "2015-11-12", "Sarah Dayana Binti Suhari", "33", "Sabu-sabu", "1.42", "Pasal 112 UU Narkotika", "Pengguna", "48", "800000000.0", "Slamet Budiono, S.H."},
                {"222/Pid.Sus/2017/PN Mtp", "PN Martapura", "2017-05-18", "Muhammad Khairudin Bin Faturahman", "45", "Sabu-sabu", "7.97", "Pasal 114 UU Narkotika", "Bandar", "72", "1000000000.0", "Siti Nor, S.H."},
                {"594/Pid.Sus/2024/PT Smg", "PT Semarang", "2024-02-20", "Maulana Bin Musaat Alm", "54", "Ganja", "12.50", "Pasal 111 UU Narkotika", "Bandar", "60", "1000000000.0", "Dewa Bhakti, S.H."},
                {"491/Pid.Sus/2024/PT Pbr", "PT Pekanbaru", "2024-08-14", "Fendi Alias Buntat", "52", "Sabu-sabu", "52.30", "Pasal 114 UU Narkotika", "Bandar", "144", "1500000000.0", "Erwinson Nababan, S.H."},
                {"1655/K/Pid.Sus/2022/MA", "Mahkamah Agung", "2022-04-25", "Ical Alias Ical Bin Rusli", "21", "Sabu-sabu", "0.85", "Pasal 112 UU Narkotika", "Kurir", "48", "800000000.0", "Suparno, S.H."},
                {"4427/K/Pid.Sus/2025/MA", "Mahkamah Agung", "2025-01-10", "Irfan Alias Ippang Bin Ladai", "25", "Ekstasi", "4.15", "Pasal 112 UU Narkotika", "Kurir", "72", "1000000000.0", "Arifin, S.H."},
                {"1981/Pid.Sus/2021/PN Sby", "PN Surabaya", "2021-09-05", "Sarah Dayana Binti Suhari", "25", "Sabu-sabu", "2.10", "Pasal 112 UU Narkotika", "Kurir", "60", "800000000.0", "Slamet Budiono, S.H."},
                {"1822/Pid.Sus/2021/PN Sby", "PN Surabaya", "2021-12-19", "Ari Haryanti Binti Mikih", "43", "Ganja", "18.45", "Pasal 111 UU Narkotika", "Bandar", "84", "1200000000.0", "Kusno, S.H."},
                {"3094/Pid.B/2013/PN Sby", "PN Surabaya", "2013-06-03", "Rachmadi Alias Anting", "20", "Sabu-sabu", "105.0", "Pasal 114 UU Narkotika", "Bandar", "180", "2000000000.0", "Slamet Budiono, S.H."},
                {"572/K/Pid.Sus/2013/MA", "Mahkamah Agung", "2013-10-28", "Mochammad Wahyu Bin Suriya", "46", "Sabu-sabu", "210.0", "Pasal 114 UU Narkotika", "Bandar", "240", "5000000000.0", "Erwinson Nababan, S.H."}
        };

        // 2. BANK VARIASI DATA UNTUK GENERATOR SAKTI 542 KASUS AGAR TIDAK MONOTON KEMBAR
        String[] daftarPasal = {"Pasal 111 ayat (1)", "Pasal 112 ayat (2)", "Pasal 114 ayat (1)", "Pasal 114 ayat (2)", "Pasal 127 ayat (1)"};
        String[] daftarPeran = {"Kurir Perantara", "Bandar Besar", "Pengguna Mandiri", "Penyimpan Logistik", "Pengedar Wilayah"};
        String[] daftarHakim = {"Slamet Budiono, S.H.", "Dewa Bhakti, S.H.", "Suparno, S.H.", "Arifin, S.H.", "Siti Nor, S.H.", "Erwinson Nababan, S.H."};
        String[] daftarPN    = {"PN Surabaya", "PN Sidoarjo", "PN Malang", "PN Gresik", "PN Jombang", "PN Jakarta Pusat"};

        Random rand = new Random(123);

        // 3. GENERATOR PERULANGAN OTOMATIS GENAP 542 BERKAS PERKARA
        for (int i = 1; i <= 542; i++) {
            String[] row = dataAsliIndonesia[(i - 1) % dataAsliIndonesia.length];

            // MEMETAKAN 12 VARIABEL LURUS DAN UNIK DI SETIAP BARIS KASUS
            String pengadilan     = daftarPN[rand.nextInt(daftarPN.length)];
            String nomorPerkara   = (100 + rand.nextInt(900)) + "/Pid.Sus/2024/" + pengadilan.replace("PN ", "PN.");
            String tanggalPutusan = (2020 + rand.nextInt(6)) + "-0" + (1 + rand.nextInt(9)) + "-" + (10 + rand.nextInt(18));
            String namaTerdakwa   = row[3] + " (Kasus #" + i + ")";
            int umurTerdakwa      = Integer.parseInt(row[4]) + rand.nextInt(5) - 2;
            String jenisNarkotika = row[5];

            double berat = Double.parseDouble(row[6]);
            if (i % 3 == 0) berat = Math.round((berat + rand.nextDouble() * 5) * 100.0) / 100.0;

            String pasalDilanggar = jenisNarkotika.equalsIgnoreCase("Ganja") ?
                    "Pasal 111 UU No. 35/2009 tentang Narkotika" : daftarPasal[rand.nextInt(daftarPasal.length)] + " UU No. 35/2009";

            String peranTerdakwa  = daftarPeran[rand.nextInt(daftarPeran.length)];
            int vonisHukuman      = Integer.parseInt(row[9]) + (rand.nextInt(24) - 12);
            if (vonisHukuman < 12) vonisHukuman = 12; // Minimal vonis 1 tahun

            double denda = 800000000.0 + (rand.nextInt(7) * 200000000.0);
            String namaHakim      = daftarHakim[rand.nextInt(daftarHakim.length)];

            // 4. MEMANGGIL CONSTRUCTOR DENGAN URUTAN PARAMETER YANG 100% AKURAT DAN LURUS
            Putusan p = new Putusan(
                    nomorPerkara, pengadilan, tanggalPutusan, namaTerdakwa, umurTerdakwa,
                    jenisNarkotika, berat, pasalDilanggar, peranTerdakwa, vonisHukuman, denda, namaHakim
            );

            repository.simpan(p);
        }

        launch(args);
    }
}
