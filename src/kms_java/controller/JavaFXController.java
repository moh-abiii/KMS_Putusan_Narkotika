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
    @FXML private TableColumn<Putusan, String> colNomor, colTerdakwa, colJenis;
    @FXML private TableColumn<Putusan, Double> colBerat;
    @FXML private TableColumn<Putusan, Integer> colVonis;

    private KnowledgeRepository repository;
    private ObservableList<Putusan> observableList;

    public void setRepository(KnowledgeRepository repository) {
        this.repository = repository;
        refreshTable(repository.getDaftarSemua());
    }

    @FXML
    public void initialize() {
        colNomor.setCellValueFactory(new PropertyValueFactory<>("nomorPerkara"));
        colTerdakwa.setCellValueFactory(new PropertyValueFactory<>("namaTerdakwa"));
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenisNarkotika"));
        colBerat.setCellValueFactory(new PropertyValueFactory<>("beratBarangBukti"));
        colVonis.setCellValueFactory(new PropertyValueFactory<>("vonisHukuman"));
    }

    private void refreshTable(ArrayList<Putusan> daftar) {
        observableList = FXCollections.observableArrayList(daftar);
        tablePutusan.setItems(observableList);
    }

    @FXML
    public void handleTambah() {
        try {
            Putusan p = new Putusan();
            p.setNomorPerkara(txtNomor.getText());
            p.setPengadilan(txtPengadilan.getText());
            p.setNamaTerdakwa(txtTerdakwa.getText());
            p.setJenisNarkotika(txtJenis.getText());
            p.setBeratBarangBukti(Double.parseDouble(txtBerat.getText()));
            p.setVonisHukuman(Integer.parseInt(txtVonis.getText()));

            repository.simpan(p);
            refreshTable(repository.getDaftarSemua());
            clearForm();
        } catch (Exception e) {
            showAlert("Error Input", "Pastikan format angka Berat dan Vonis benar!");
        }
    }

    @FXML
    public void handleCari() {
        String keyword = txtCari.getText();
        if (keyword.isEmpty()) {
            refreshTable(repository.getDaftarSemua());
        } else {
            ArrayList<Putusan> hasil = repository.cariByNama(keyword);
            Putusan byNomor = repository.cariByNomor(keyword);
            if (byNomor != null && !hasil.contains(byNomor)) {
                hasil.add(byNomor);
            }
            refreshTable(hasil);
        }
    }

    @FXML
    public void handleHapus() {
        Putusan terpilih = tablePutusan.getSelectionModel().getSelectedItem();
        if (terpilih != null) {
            repository.hapus(terpilih.getNomorPerkara());
            refreshTable(repository.getDaftarSemua());
        } else {
            showAlert("Peringatan", "Pilih data di tabel terlebih dahulu untuk dihapus!");
        }
    }

    @FXML
    public void handleStatistik() {
        int total = repository.getDaftarSemua().size();
        showAlert("Statistik KMS", "Total Berkas Putusan Terolah di Sistem GUI: " + total + " Kasus");
    }

    private void clearForm() {
        txtNomor.clear(); txtPengadilan.clear(); txtTerdakwa.clear();
        txtJenis.clear(); txtBerat.clear(); txtVonis.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
