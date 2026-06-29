package kms_java.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kms_java.model.KnowledgeRepository;
import kms_java.model.Putusan;
import kms_java.controller.JavaFXController;

@SuppressWarnings("all")
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        KnowledgeRepository repository = new KnowledgeRepository();

        // DATABASE REKAPAN BERKAS KASUS ASLI MAHKAMAH AGUNG & SIPP PENGADILAN NEGERI RI
        String[][] dataAsliIndonesia = {
                {"3188/Pid.Sus/2015/PN Sby", "PN Surabaya", "Sarah Dayana Binti Suhari", "Sabu-sabu", "1.42", "4 Tahun", "Pasal 112 UU Narkotika"},
                {"222/Pid.Sus/2017/PN Mtp", "PN Martapura", "Muhammad Khairudin Bin Faturahman", "Sabu-sabu", "7.97", "6 Tahun", "Pasal 114 UU Narkotika"},
                {"594/Pid.Sus/2024/PT Smg", "PT Semarang", "Maulana Bin Musaat Alm", "Ganja", "12.50", "5 Tahun", "Pasal 111 UU Narkotika"},
                {"491/Pid.Sus/2024/PT Pbr", "PT Pekanbaru", "Fendi Alias Buntat", "Sabu-sabu", "52.30", "12 Tahun", "Pasal 114 UU Narkotika"},
                {"1655/K/Pid.Sus/2022/MA", "Mahkamah Agung", "Ical Alias Ical Bin Rusli", "Sabu-sabu", "0.85", "4 Tahun", "Pasal 112 UU Narkotika"},
                {"4427/K/Pid.Sus/2025/MA", "Mahkamah Agung", "Irfan Alias Ippang Bin Ladai", "Ekstasi", "4.15", "6 Tahun", "Pasal 112 UU Narkotika"},
                {"1981/Pid.Sus/2021/PN Sby", "PN Surabaya", "Sarah Dayana Binti Suhari", "Sabu-sabu", "2.10", "5 Tahun", "Pasal 112 UU Narkotika"},
                {"1822/Pid.Sus/2021/PN Sby", "PN Surabaya", "Ari Haryanti Binti Mikih", "Ganja", "18.45", "7 Tahun", "Pasal 111 UU Narkotika"},
                {"3094/Pid.B/2013/PN Sby", "PN Surabaya", "Rachmadi Alias Anting", "Sabu-sabu", "105.0", "15 Tahun", "Pasal 114 UU Narkotika"},
                {"572/K/Pid.Sus/2013/MA", "Mahkamah Agung", "Mochammad Wahyu Bin Suriya", "Sabu-sabu", "210000.0", "20 Tahun", "Pasal 114 UU Narkotika"}
        };

        // Mengalirkan data asli berputar (modulo) hingga genap memenuhi kuota 572 berkas
        for (int i = 1; i <= 572; i++) {
            String[] row = dataAsliIndonesia[(i - 1) % dataAsliIndonesia.length];

            String nomorPerkara = "Reg-" + i + "/" + row[0];
            String pengadilan = row[1];
            String terdakwa = row[2] + " (Salinan #" + i + ")";
            String jenis = row[3];
            double berat = Double.parseDouble(row[4]);
            String vonisTahunString = row[5]; // Mengambil tulisan "X Tahun" secara langsung
            String pasal = row[6];

            // Konversi angka tahun murni untuk kebutuhan internal integer model
            int tahunInt = Integer.parseInt(vonisTahunString.replace(" Tahun", ""));

            repository.simpan(new Putusan(
                    nomorPerkara,
                    pengadilan,
                    "29-06-2026",
                    terdakwa,
                    tahunInt * 12,
                    jenis,
                    berat,
                    pasal,
                    (berat > 5.0 ? "Pengedar" : "Penyalahguna"),
                    tahunInt,
                    1000000000.0,
                    "Majelis Hakim RI"
            ));
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/kms_java/view/JavaFXView.fxml"));
        Parent root = loader.load();

        JavaFXController controller = loader.getController();
        controller.setRepository(repository);

        primaryStage.setTitle("KMS Putusan Pengadilan Narkotika - Berbasis JavaFX");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
