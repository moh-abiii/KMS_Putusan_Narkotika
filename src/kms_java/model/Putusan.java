package kms_java.model;

//  Inheritance: PutusanNarkotika bisa extend Putusan
//  Interface: Comparable untuk sorting
//  Inheritance: PutusanNarkotika bisa extend Putusan
//  Interface: Comparable untuk sorting
@SuppressWarnings("unused")
public class Putusan implements Comparable<Putusan> {

    // 🔒 Enkapsulasi: semua field private
    private String nomorPerkara;
    private String pengadilan;
    private String tanggalPutusan;
    private String namaTerdakwa;
    private int umurTerdakwa;
    private String jenisNarkotika;
    private double beratBarangBukti;
    private String pasalDilanggar;
    private String peranTerdakwa;
    private int vonisHukuman;   // bulan
    private double vonisDenda;  // rupiah
    private String namaHakim;

    // 🔹 Static counter
    private static int jumlahDibuat = 0;

    // 🏗 Constructor no-arg
    public Putusan() {
        jumlahDibuat++;
    }

    // 🏗 Constructor parameterized
    public Putusan(String nomorPerkara, String pengadilan, String tanggalPutusan,
                   String namaTerdakwa, int umurTerdakwa, String jenisNarkotika,
                   double beratBarangBukti, String pasalDilanggar, String peranTerdakwa,
                   int vonisHukuman, double vonisDenda, String namaHakim) {
        this.nomorPerkara = nomorPerkara;
        this.pengadilan = pengadilan;
        this.tanggalPutusan = tanggalPutusan;
        this.namaTerdakwa = namaTerdakwa;
        this.umurTerdakwa = umurTerdakwa;
        this.jenisNarkotika = jenisNarkotika;
        this.beratBarangBukti = beratBarangBukti;
        this.pasalDilanggar = pasalDilanggar;
        this.peranTerdakwa = peranTerdakwa;
        this.vonisHukuman = vonisHukuman;
        this.vonisDenda = vonisDenda;
        this.namaHakim = namaHakim;
        jumlahDibuat++;
    }

    // 📌 Getter & Setter (enkapsulasi)
    public String getNomorPerkara() { return nomorPerkara; }
    public void setNomorPerkara(String nomorPerkara) { this.nomorPerkara = nomorPerkara; }

    public String getPengadilan() { return pengadilan; }
    public void setPengadilan(String pengadilan) { this.pengadilan = pengadilan; }

    public String getTanggalPutusan() { return tanggalPutusan; }
    public void setTanggalPutusan(String tanggalPutusan) { this.tanggalPutusan = tanggalPutusan; }

    public String getNamaTerdakwa() { return namaTerdakwa; }
    public void setNamaTerdakwa(String namaTerdakwa) { this.namaTerdakwa = namaTerdakwa; }

    public int getUmurTerdakwa() { return umurTerdakwa; }
    public void setUmurTerdakwa(int umurTerdakwa) {
        if (umurTerdakwa > 0) this.umurTerdakwa = umurTerdakwa;
        else System.out.println("Umur tidak boleh negatif!");
    }

    public String getJenisNarkotika() { return jenisNarkotika; }
    public void setJenisNarkotika(String jenisNarkotika) { this.jenisNarkotika = jenisNarkotika; }

    public double getBeratBarangBukti() { return beratBarangBukti; }
    public void setBeratBarangBukti(double beratBarangBukti) {
        if (beratBarangBukti > 0) this.beratBarangBukti = beratBarangBukti;
        else System.out.println("Berat barang bukti harus positif!");
    }

    public String getPasalDilanggar() { return pasalDilanggar; }
    public void setPasalDilanggar(String pasalDilanggar) { this.pasalDilanggar = pasalDilanggar; }

    public String getPeranTerdakwa() { return peranTerdakwa; }
    public void setPeranTerdakwa(String peranTerdakwa) { this.peranTerdakwa = peranTerdakwa; }

    public int getVonisHukuman() { return vonisHukuman; }
    public void setVonisHukuman(int vonisHukuman) { this.vonisHukuman = vonisHukuman; }

    public double getVonisDenda() { return vonisDenda; }
    public void setVonisDenda(double vonisDenda) { this.vonisDenda = vonisDenda; }

    public String getNamaHakim() { return namaHakim; }
    public void setNamaHakim(String namaHakim) { this.namaHakim = namaHakim; }

    // 🔹 Static method
    public static int getJumlahDibuat() { return jumlahDibuat; }

    // 🔹 Overloading
    public void tampilkan() {
        System.out.printf("| %-25s | %-20s | %-10s | %-4d bulan |\n",
                nomorPerkara, namaTerdakwa, jenisNarkotika, vonisHukuman);
    }

    public void tampilkan(boolean detail) {
        if (detail) System.out.println(this);

        else tampilkan();
    }

    // 🔹 Method tambahan: kategori hukuman
    public String getKategoriHukuman() {
        if (vonisHukuman < 12) return "Ringan";
        else if (vonisHukuman <= 36) return "Sedang";
        else return "Berat";
    }

    // 🔹 Overriding toString
    @Override
    public String toString() {
        return "Putusan{" +
                "nomorPerkara='" + nomorPerkara + '\'' +
                ", pengadilan='" + pengadilan + '\'' +
                ", tanggalPutusan='" + tanggalPutusan + '\'' +
                ", namaTerdakwa='" + namaTerdakwa + '\'' +
                ", umurTerdakwa=" + umurTerdakwa +
                ", jenisNarkotika='" + jenisNarkotika + '\'' +
                ", beratBarangBukti=" + beratBarangBukti +
                ", pasalDilanggar='" + pasalDilanggar + '\'' +
                ", peranTerdakwa='" + peranTerdakwa + '\'' +
                ", vonisHukuman=" + vonisHukuman +
                ", vonisDenda=" + vonisDenda +
                ", namaHakim='" + namaHakim + '\'' +
                ", kategori='" + getKategoriHukuman() + '\'' +
                '}';
    }

    // 🔹 Overriding compareTo (interface Comparable)
    @Override
    public int compareTo(Putusan other) {
        return Integer.compare(this.vonisHukuman, other.vonisHukuman);
    }
}
