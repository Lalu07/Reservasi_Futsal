package firdaus.rizkika.reservasi_futsal.database;

public class SimpanData {
    String IdFutsal;
    String NamaPemesan;
    String TanggalPemesanan;
    String PaketWaktu;
    String HargaPaket;

    public SimpanData() {
    }

    public SimpanData(String namaPemesan, String tanggalPemesanan, String paketWaktu, String hargaPaket) {
        NamaPemesan = namaPemesan;
        TanggalPemesanan = tanggalPemesanan;
        PaketWaktu = paketWaktu;
        HargaPaket = hargaPaket;
    }

    public String getIdFutsal() {
        return IdFutsal;
    }

    public void setIdFutsal(String idFutsal) {
        IdFutsal = idFutsal;
    }

    public String getNamaPemesan() {
        return NamaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        NamaPemesan = namaPemesan;
    }

    public String getTanggalPemesanan() {
        return TanggalPemesanan;
    }

    public void setTanggalPemesanan(String tanggalPemesanan) {
        TanggalPemesanan = tanggalPemesanan;
    }

    public String getPaketWaktu() {
        return PaketWaktu;
    }

    public void setPaketWaktu(String paketWaktu) {
        PaketWaktu = paketWaktu;
    }

    public String getHargaPaket() {
        return HargaPaket;
    }

    public void setHargaPaket(String hargaPaket) {
        HargaPaket = hargaPaket;
    }
}
