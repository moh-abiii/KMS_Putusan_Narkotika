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
        // 1. Dataset dummy otomatis minimal 50 objek sesuai panduan tugas besar
        KnowledgeRepository repository = new KnowledgeRepository();
        for (int i = 1; i <= 52; i++) {
            repository.simpan(new Putusan(
                    "10" + i + "/Pid.Sus/2024/PN Sby",
                    "PN Surabaya",
                    "20-04-2025",
                    "Terdakwa GUI Kasus " + i,
                    25,
                    (i % 2 == 0 ? "Sabu-sabu" : "Ganja"),
                    1.2,
                    "Pasal 114 UU Narkotika",
                    "Kurir",
                    60,
                    1000000000.0,
                    "Hakim"
            ));
        }

        // 2. Load File Desain GUI FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/kms_java/view/JavaFXView.fxml"));
        Parent root = loader.load();

        // 3. Hubungkan Repositori Model ke GUI Controller
        JavaFXController controller = loader.getController();
        controller.setRepository(repository);

        // 4. Tampilkan Window Aplikasi Desktop
        primaryStage.setTitle("KMS Putusan Pengadilan Narkotika - Berbasis JavaFX");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Jalankan engine visual JavaFX
    }
}
