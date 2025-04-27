package com.kenancode.fullstack_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ime_prezime")
    private String imePrezime;
    
    @Column(name = "odsjek")
    private String odsjek;
    
    @Column(name = "pozicija")
    private String pozicija;
    
    @Column(name = "radno_vrijeme")
    private String radnoVrijeme;
    
    @Column(name = "akademsko_zvanje")
    private String akademskoZvanje;
    
    @Column(name = "slika_path")
    private String slikaPath;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Academic> academics;


    // No-arg constructor
    public User() {
    }

    // Constructor with fields
    public User(String imePrezime, String odsjek, String pozicija, String radnoVrijeme, 
                String akademskoZvanje, String slikaPath) {
        this.imePrezime = imePrezime;
        this.odsjek = odsjek;
        this.pozicija = pozicija;
        this.radnoVrijeme = radnoVrijeme;
        this.akademskoZvanje = akademskoZvanje;
        this.slikaPath = slikaPath;
    }

    // Basic getters and setters
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

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public String getRadnoVrijeme() {
        return radnoVrijeme;
    }

    public void setRadnoVrijeme(String radnoVrijeme) {
        this.radnoVrijeme = radnoVrijeme;
    }

    public String getAkademskoZvanje() {
        return akademskoZvanje;
    }

    public void setAkademskoZvanje(String akademskoZvanje) {
        this.akademskoZvanje = akademskoZvanje;
    }

    public String getSlikaPath() {
        return slikaPath;
    }

    public void setSlikaPath(String slikaPath) {
        this.slikaPath = slikaPath;
    }

    public List<Academic> getAcademics() {
        return academics;
    }

    public void addAcademic(Academic academic) {
        academics.add(academic);
        academic.setUser(this);
    }
}