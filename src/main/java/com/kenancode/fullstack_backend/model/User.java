package com.kenancode.fullstack_backend.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ime_prezime")
    private String imePrezime;
    @Column(name = "odsjek")
    private String odsjek;
    private String pozicija;
    private String radnoVrijeme;
    private String akademskoZvanje;

    //private List<CoefficientDetails> coefficientDetailsList;


    private Float proljetniKoeficijent;
    private float opterećenje;
    private int brojPredmeta;
    private int brojStudenataNaOdsjeku;
    private float osnovniKoeficijent;
    private float umanjenjeKoeficijentaZaRukovodioceIspodOptimuma;
    private float povećanjeKoeficijentaZaRukovodiocePreko;
    private float povećanjeKoeficijentaPoBrojuStudenata;
    private float povećanjeKoeficijentaPoBrojuPredmeta;
    private float koeficijentZaDekanaPoBrojuStudenataNaFakultetu;
    private float koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku;
    private float koeficijentZaDodatniStudijskiProgram = 1.0f;
    private float dodatniKoeficijentZaPreko350StudenataNaOdsjeku;
    private float dodatniKoeficijentZaAsistentaVisegAsistenta = 1.5f;
    private String ukupanKoeficijentZaJesenjiSemestar;
    private String slikaPath;

    // Getteri i setteri za polja

    public float getProljetniKoeficijent() {
        return proljetniKoeficijent;
    }

    public void setProljetniKoeficijent(float proljetniKoeficijent) {
        this.proljetniKoeficijent = proljetniKoeficijent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getOdsjek() {
        return odsjek;
    }

    public void setOdsjek(String odsjek) {
        this.odsjek = odsjek;
    }

    public String getRadnoVrijeme() {
        return radnoVrijeme;
    }

    public void setRadnoVrijeme(String radnoVrijeme) {
        this.radnoVrijeme = radnoVrijeme;
    }

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public String getAkademskoZvanje() {
        return akademskoZvanje;
    }

    public void setAkademskoZvanje(String akademskoZvanje) {
        this.akademskoZvanje = akademskoZvanje;
    }

    public float getOpterećenje() {
        return opterećenje;
    }

    public void setOpterećenje(float opterećenje) {
        this.opterećenje = opterećenje;
    }

    public int getBrojPredmeta() {
        return brojPredmeta;
    }

    public void setBrojPredmeta(int brojPredmeta) {
        this.brojPredmeta = brojPredmeta;
    }

    public int getBrojStudenataNaOdsjeku() {
        return brojStudenataNaOdsjeku;
    }

    public void setBrojStudenataNaOdsjeku(int brojStudenataNaOdsjeku) {
        this.brojStudenataNaOdsjeku = brojStudenataNaOdsjeku;
    }

    public float getOsnovniKoeficijent() {
        return osnovniKoeficijent;
    }

    public void setOsnovniKoeficijent(float osnovniKoeficijent) {
        this.osnovniKoeficijent = osnovniKoeficijent;
    }

    public float getUmanjenjeKoeficijentaZaRukovodioceIspodOptimuma() {
        return umanjenjeKoeficijentaZaRukovodioceIspodOptimuma;
    }

    public void setUmanjenjeKoeficijentaZaRukovodioceIspodOptimuma(float umanjenjeKoeficijentaZaRukovodioceIspodOptimuma) {
        this.umanjenjeKoeficijentaZaRukovodioceIspodOptimuma = umanjenjeKoeficijentaZaRukovodioceIspodOptimuma;
    }

    public float getPovećanjeKoeficijentaZaRukovodiocePreko() {
        return povećanjeKoeficijentaZaRukovodiocePreko;
    }

    public void setPovećanjeKoeficijentaZaRukovodiocePreko(float povećanjeKoeficijentaZaRukovodiocePreko) {
        this.povećanjeKoeficijentaZaRukovodiocePreko = povećanjeKoeficijentaZaRukovodiocePreko;
    }

    public float getPovećanjeKoeficijentaPoBrojuStudenata() {
        return povećanjeKoeficijentaPoBrojuStudenata;
    }

    public void setPovećanjeKoeficijentaPoBrojuStudenata(float povećanjeKoeficijentaPoBrojuStudenata) {
        this.povećanjeKoeficijentaPoBrojuStudenata = povećanjeKoeficijentaPoBrojuStudenata;
    }

    public float getPovećanjeKoeficijentaPoBrojuPredmeta() {
        return povećanjeKoeficijentaPoBrojuPredmeta;
    }

    public void setPovećanjeKoeficijentaPoBrojuPredmeta(float povećanjeKoeficijentaPoBrojuPredmeta) {
        this.povećanjeKoeficijentaPoBrojuPredmeta = povećanjeKoeficijentaPoBrojuPredmeta;
    }

    public float getKoeficijentZaDekanaPoBrojuStudenataNaFakultetu() {
        return koeficijentZaDekanaPoBrojuStudenataNaFakultetu;
    }

    public void setKoeficijentZaDekanaPoBrojuStudenataNaFakultetu(float koeficijentZaDekanaPoBrojuStudenataNaFakultetu) {
        this.koeficijentZaDekanaPoBrojuStudenataNaFakultetu = koeficijentZaDekanaPoBrojuStudenataNaFakultetu;
    }

    public float getKoeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku() {
        return koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku;
    }

    public void setKoeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku(float koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku) {
        this.koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku = koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku;
    }

    public float getKoeficijentZaDodatniStudijskiProgram() {
        return koeficijentZaDodatniStudijskiProgram;
    }

    public void setKoeficijentZaDodatniStudijskiProgram(float koeficijentZaDodatniStudijskiProgram) {
        this.koeficijentZaDodatniStudijskiProgram = koeficijentZaDodatniStudijskiProgram;
    }

    public float getDodatniKoeficijentZaPreko350StudenataNaOdsjeku() {
        return dodatniKoeficijentZaPreko350StudenataNaOdsjeku;
    }

    public void setDodatniKoeficijentZaPreko350StudenataNaOdsjeku(float dodatniKoeficijentZaPreko350StudenataNaOdsjeku) {
        this.dodatniKoeficijentZaPreko350StudenataNaOdsjeku = dodatniKoeficijentZaPreko350StudenataNaOdsjeku;
    }

    public float getDodatniKoeficijentZaAsistentaVisegAsistenta() {
        return dodatniKoeficijentZaAsistentaVisegAsistenta;
    }

    public void setDodatniKoeficijentZaAsistentaVisegAsistenta(float dodatniKoeficijentZaAsistentaVisegAsistenta) {
        this.dodatniKoeficijentZaAsistentaVisegAsistenta = dodatniKoeficijentZaAsistentaVisegAsistenta;
    }
    public String getSlikaPath() {
        return slikaPath;
    }

    public void setSlikaPath(String slikaPath) {
        this.slikaPath = slikaPath;
    }
    public void izracunajUkupanKoeficijentZaJesenjiSemestar() {
        float ukupanKoeficijent = osnovniKoeficijent
                + umanjenjeKoeficijentaZaRukovodioceIspodOptimuma
                + povećanjeKoeficijentaZaRukovodiocePreko
                + povećanjeKoeficijentaPoBrojuStudenata
                + povećanjeKoeficijentaPoBrojuPredmeta
                + koeficijentZaDekanaPoBrojuStudenataNaFakultetu
                + koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku
                + koeficijentZaDodatniStudijskiProgram
                + dodatniKoeficijentZaPreko350StudenataNaOdsjeku
                + dodatniKoeficijentZaAsistentaVisegAsistenta;

        // Konverzija u string i postavljanje vrijednosti
        this.ukupanKoeficijentZaJesenjiSemestar = Float.toString(ukupanKoeficijent);
    }
    public String getUkupanKoeficijentZaJesenjiSemestar() {
        return ukupanKoeficijentZaJesenjiSemestar;
    }

    public void setUkupanKoeficijentZaJesenjiSemestar(String ukupanKoeficijentZaJesenjiSemestar) {
        this.ukupanKoeficijentZaJesenjiSemestar = ukupanKoeficijentZaJesenjiSemestar;
    }
}
