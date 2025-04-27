DROP TABLE IF EXISTS Academic;
DROP TABLE IF EXISTS User;

CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ime_prezime VARCHAR(100),
    odsjek VARCHAR(100),
    pozicija VARCHAR(100),
    radno_vrijeme VARCHAR(100),
    akademsko_zvanje VARCHAR(100),
    slika_path VARCHAR(255)
);

CREATE TABLE Academic (
    id INT AUTO_INCREMENT PRIMARY KEY,
    academic_year VARCHAR(100),
    proljetni_koeficijent FLOAT,
    opterecenje FLOAT,
    broj_predmeta INT,
    broj_studenata_na_odsjeku INT,
    osnovni_koeficijent FLOAT,
    umanjenje_koeficijenta_za_rukovodioce_ispod_optimuma FLOAT,
    povecanje_koeficijenta_za_rukovodioce_preko FLOAT,
    povecanje_koeficijenta_po_broju_studenata FLOAT,
    povecanje_koeficijenta_po_broju_predmeta FLOAT,
    koeficijent_za_dekana_po_broju_studenata_na_fakultetu FLOAT,
    koeficijent_za_sefa_odsjeka_po_broju_studenata_na_odsjeku FLOAT,
    koeficijent_za_dodatni_studijski_program FLOAT DEFAULT 1.0,
    dodatni_koeficijent_za_preko_350_studenata_na_odsjeku FLOAT,
    dodatni_koeficijent_za_asistenta_viseg_asistenta FLOAT DEFAULT 1.5,
    ukupan_koeficijent_za_jesenji_semestar VARCHAR(100),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES User(id)
);