package com.kenancode.fullstack_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

@Entity
public class Academic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "proljetni_koeficijent")
    private Float proljetniKoeficijent;

    @Column(name = "opterecenje")
    private float opterećenje;

    @Column(name = "broj_predmeta")
    private int brojPredmeta;

    @Column(name = "broj_studenata_na_odsjeku")
    private int brojStudenataNaOdsjeku;

    @Column(name = "osnovni_koeficijent")
    private float osnovniKoeficijent;

    @Column(name = "umanjenje_koeficijenta_za_rukovodioce_ispod_optimuma")
    private float umanjenjeKoeficijentaZaRukovodioceIspodOptimuma;

    @Column(name = "povecanje_koeficijenta_za_rukovodioce_preko")
    private float povećanjeKoeficijentaZaRukovodiocePreko;

    @Column(name = "povecanje_koeficijenta_po_broju_studenata")
    private float povećanjeKoeficijentaPoBrojuStudenata;

    @Column(name = "povecanje_koeficijenta_po_broju_predmeta")
    private float povećanjeKoeficijentaPoBrojuPredmeta;

    @Column(name = "koeficijent_za_dekana_po_broju_studenata_na_fakultetu")
    private float koeficijentZaDekanaPoBrojuStudenataNaFakultetu;

    @Column(name = "koeficijent_za_sefa_odsjeka_po_broju_studenata_na_odsjeku")
    private float koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku;

    @Column(name = "koeficijent_za_dodatni_studijski_program")
    private float koeficijentZaDodatniStudijskiProgram = 1.0f;

    @Column(name = "dodatni_koeficijent_za_preko_350_studenata_na_odsjeku")
    private float dodatniKoeficijentZaPreko350StudenataNaOdsjeku;

    @Column(name = "dodatni_koeficijent_za_asistenta_viseg_asistenta")
    private float dodatniKoeficijentZaAsistentaVisegAsistenta = 1.5f;

    @Column(name = "ukupan_koeficijent_za_jesenji_semestar")
    private String ukupanKoeficijentZaJesenjiSemestar;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // No-arg constructor
    public Academic() {
    }




    // Constructor
    public Academic(String academicYear, Float proljetniKoeficijent, float opterećenje, int brojPredmeta, 
                   int brojStudenataNaOdsjeku, float osnovniKoeficijent, 
                   float umanjenjeKoeficijentaZaRukovodioceIspodOptimuma,
                   float povećanjeKoeficijentaZaRukovodiocePreko, float povećanjeKoeficijentaPoBrojuStudenata,
                   float povećanjeKoeficijentaPoBrojuPredmeta, float koeficijentZaDekanaPoBrojuStudenataNaFakultetu,
                   float koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku, float koeficijentZaDodatniStudijskiProgram,
                   float dodatniKoeficijentZaPreko350StudenataNaOdsjeku, float dodatniKoeficijentZaAsistentaVisegAsistenta,
                   String ukupanKoeficijentZaJesenjiSemestar) {
        this.academicYear = academicYear;
        this.proljetniKoeficijent = proljetniKoeficijent;
        this.opterećenje = opterećenje;
        this.brojPredmeta = brojPredmeta;
        this.brojStudenataNaOdsjeku = brojStudenataNaOdsjeku;
        this.osnovniKoeficijent = osnovniKoeficijent;
        this.umanjenjeKoeficijentaZaRukovodioceIspodOptimuma = umanjenjeKoeficijentaZaRukovodioceIspodOptimuma;
        this.povećanjeKoeficijentaZaRukovodiocePreko = povećanjeKoeficijentaZaRukovodiocePreko;
        this.povećanjeKoeficijentaPoBrojuStudenata = povećanjeKoeficijentaPoBrojuStudenata;
        this.povećanjeKoeficijentaPoBrojuPredmeta = povećanjeKoeficijentaPoBrojuPredmeta;
        this.koeficijentZaDekanaPoBrojuStudenataNaFakultetu = koeficijentZaDekanaPoBrojuStudenataNaFakultetu;
        this.koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku = koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku;
        this.koeficijentZaDodatniStudijskiProgram = koeficijentZaDodatniStudijskiProgram;
        this.dodatniKoeficijentZaPreko350StudenataNaOdsjeku = dodatniKoeficijentZaPreko350StudenataNaOdsjeku;
        this.dodatniKoeficijentZaAsistentaVisegAsistenta = dodatniKoeficijentZaAsistentaVisegAsistenta;
        this.ukupanKoeficijentZaJesenjiSemestar = ukupanKoeficijentZaJesenjiSemestar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Float getProljetniKoeficijent() {
        return proljetniKoeficijent;
    }

    public void setProljetniKoeficijent(Float proljetniKoeficijent) {
        this.proljetniKoeficijent = proljetniKoeficijent;
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

    public String getUkupanKoeficijentZaJesenjiSemestar() {
        return ukupanKoeficijentZaJesenjiSemestar;
    }

    public void setUkupanKoeficijentZaJesenjiSemestar(String ukupanKoeficijentZaJesenjiSemestar) {
        this.ukupanKoeficijentZaJesenjiSemestar = ukupanKoeficijentZaJesenjiSemestar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        this.ukupanKoeficijentZaJesenjiSemestar = Float.toString(ukupanKoeficijent);
    }
}
