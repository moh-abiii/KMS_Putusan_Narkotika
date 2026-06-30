package kms_java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import kms_java.model.KnowledgeRepository;
import kms_java.model.Putusan;
import java.util.ArrayList;

@SuppressWarnings("all")
public class JavaFXController {
    @FXML private TextField txtNomor, txtPengadilan, txtTerdakwa, txtJenis, txtBerat, txtVonis, txtCari;
    @FXML private TableView<Putusan> tablePutusan;

    @FXML private TableColumn<Putusan, String> colNomor;
    @FXML private TableColumn<Putusan, String> colTerdakwa;
    @FXML private TableColumn<Putusan, String> colJenis;
    @FXML private TableColumn<Putusan, Double> colBerat;
    @FXML private TableColumn<Putusan, String> colVonis;

    private TableColumn<Putusan, String> colPengadilan = new TableColumn<>("Pengadilan");
    private TableColumn<Putusan, String> colTanggal = new TableColumn<>("Tanggal Putusan");
    private TableColumn<Putusan, Integer> colUmur = new TableColumn<>("Umur");
    private TableColumn<Putusan, String> colPasal = new TableColumn<>("Pasal Dilanggar");
    private TableColumn<Putusan, String> colPeran = new TableColumn<>("Peran");
    private TableColumn<Putusan, Double> colDenda = new TableColumn<>("Vonis Denda");
    private TableColumn<Putusan, String> colHakim = new TableColumn<>("Nama Hakim");

    private KnowledgeRepository repository;
    private ObservableList<Putusan> observableList;

    // 🔹 Counter index bergilir
    private int pasalIndex = 0;
    private int peranIndex = 0;
    private int hakimIndex = 0;

    public void setRepository(KnowledgeRepository repository) {
        this.repository = repository;
        refreshTable(repository.getDaftarSemua());
    }

    @FXML
    public void initialize() {
        if (colNomor != null) colNomor.setCellValueFactory(new PropertyValueFactory<>("nomorPerkara"));
        if (colTerdakwa != null) colTerdakwa.setCellValueFactory(new PropertyValueFactory<>("namaTerdakwa"));
        if (colJenis != null) colJenis.setCellValueFactory(new PropertyValueFactory<>("jenisNarkotika"));
        if (colBerat != null) colBerat.setCellValueFactory(new PropertyValueFactory<>("beratBarangBukti"));

        colPengadilan.setCellValueFactory(new PropertyValueFactory<>("pengadilan"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggalPutusan"));
        colUmur.setCellValueFactory(new PropertyValueFactory<>("umurTerdakwa"));
        colPasal.setCellValueFactory(new PropertyValueFactory<>("pasalDilanggar"));
        colPeran.setCellValueFactory(new PropertyValueFactory<>("peranTerdakwa"));
        colDenda.setCellValueFactory(new PropertyValueFactory<>("vonisDenda"));
        colHakim.setCellValueFactory(new PropertyValueFactory<>("namaHakim"));

        if (colVonis != null) {
            colVonis.setCellValueFactory(cellData -> {
                int totalBulan = cellData.getValue().getVonisHukuman();
                if (totalBulan < 12) {
                    return new javafx.beans.property.SimpleStringProperty(totalBulan + " Bulan");
                } else {
                    int tahun = totalBulan / 12;
                    int sisaBulan = totalBulan % 12;
                    if (sisaBulan == 0) {
                        return new javafx.beans.property.SimpleStringProperty(tahun + " Tahun (" + totalBulan + " Bulan)");
                    } else {
                        return new javafx.beans.property.SimpleStringProperty(tahun + " Tahun " + sisaBulan + " Bulan (" + totalBulan + " Bulan)");
                    }
                }
            });
        }

        if (tablePutusan != null) {
            tablePutusan.getColumns().clear();
            tablePutusan.getColumns().addAll(
                    colNomor, colTerdakwa, colJenis, colBerat, colVonis,
                    colPengadilan, colTanggal, colUmur, colPasal, colPeran, colDenda, colHakim
            );
        }
    }

    @FXML
    public void handleTambah() {
        try {
            if (txtNomor.getText().isEmpty() || txtTerdakwa.getText().isEmpty()) {
                showAlert("Validasi Gagal", "Nomor Perkara dan Nama Terdakwa wajib diisi!");
                return;
            }

            Putusan p = new Putusan();
            p.setNomorPerkara(txtNomor.getText());
            p.setNamaTerdakwa(txtTerdakwa.getText());
            p.setJenisNarkotika(txtJenis.getText());
            p.setBeratBarangBukti(txtBerat.getText().isEmpty() ? 0.0 : Double.parseDouble(txtBerat.getText().trim()));
            p.setVonisHukuman(txtVonis.getText().isEmpty() ? 0 : Integer.parseInt(txtVonis.getText().trim()));

            p.setPengadilan(txtPengadilan.getText().isEmpty() ? "PN Surabaya" : txtPengadilan.getText());
            p.setTanggalPutusan("2026-06-29");
            p.setUmurTerdakwa(20 + (repository.getDaftarSemua().size() % 30));

            // 🔹 Array pilihan
            String[] pasalOptions = {
                    "Pasal 112 UU No. 35/2009",
                    "Pasal 114 UU No. 35/2009",
                    "Pasal 127 UU No. 35/2009"
            };
            String[] peranOptions = {"Bandar", "Kurir", "Pengguna"};
            String[] hakimOptions = {
                    "Slamet Budiono, S.H.",
                    "Rina Kartika, S.H., M.H.",
                    "Budi Santoso, S.H.",
                    "Dewi Lestari, S.H."
            };

            // 🔹 Ambil bergilir sesuai index
            p.setPasalDilanggar(pasalOptions[pasalIndex]);
            p.setPeranTerdakwa(peranOptions[peranIndex]);
            p.setNamaHakim(hakimOptions[hakimIndex]);

            // Geser index (looping)
            pasalIndex = (pasalIndex + 1) % pasalOptions.length;
            peranIndex = (peranIndex + 1) % peranOptions.length;
            hakimIndex = (hakimIndex + 1) % hakimOptions.length;

            p.setVonisDenda(1000000000.0);

            repository.simpan(p);
            refreshTable(repository.getDaftarSemua());
            clearForm();
            showAlert("Sukses", "Data Putusan Baru Berhasil Ditambahkan!");
        } catch (Exception e) {
            showAlert("Eror Input", "Format pengisian angka salah!");
        }
    }

    @FXML
    public void handleCari() {
        String keyword = txtCari.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            refreshTable(repository.getDaftarSemua());
            return;
        }
        ArrayList<Putusan> hasilFilter = new ArrayList<>();
        for (Putusan p : repository.getDaftarSemua()) {
            if (p.getNomorPerkara().toLowerCase().contains(keyword) || p.getNamaTerdakwa().toLowerCase().contains(keyword)) {
                hasilFilter.add(p);
            }
        }
        refreshTable(hasilFilter);
    }

    @FXML
    public void handleHapus() {
        Putusan terpilih = tablePutusan.getSelectionModel().getSelectedItem();
        if (terpilih != null) {
            repository.getDaftarSemua().remove(terpilih);
            refreshTable(repository.getDaftarSemua());
            showAlert("Sukses", "Data Berhasil Dihapus!");
        } else {
            showAlert("Peringatan", "Pilih data terlebih dahulu!");
        }
    }

    @FXML
    public void handleStatistik() {
        showAlert("Statistik KMS", "Total Berkas Terolah: " + repository.getDaftarSemua().size() + " Kasus");
    }

    private void refreshTable(ArrayList<Putusan> list) {
        observableList = FXCollections.observableArrayList(list);
        tablePutusan.setItems(observableList);
    }

    private void clearForm() {
        txtNomor.clear(); txtPengadilan.clear(); txtTerdakwa.clear(); txtJenis.clear(); txtBerat.clear(); txtVonis.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title); alert.setHeaderText(null); alert.setContentText(content);
        alert.showAndWait();
    }
}
