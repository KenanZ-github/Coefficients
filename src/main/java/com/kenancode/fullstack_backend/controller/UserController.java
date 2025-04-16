
package com.kenancode.fullstack_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.kenancode.fullstack_backend.model.User;
import com.kenancode.fullstack_backend.repository.UserRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final String uploadDir = "uploads";

    public UserController() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (Exception e) {
            throw new RuntimeException("Could not create upload folder!");
        }
    }

    @PostMapping
    public ResponseEntity<?> addUser(
            //TODO Use java POJO instead of requestParam
            @RequestParam(value = "profilnaSlika", required = false) MultipartFile file,
            @RequestParam String imePrezime,
            @RequestParam String odsjek,
            @RequestParam String pozicija,
            @RequestParam String radnoVrijeme,
            @RequestParam String akademskoZvanje,
            @RequestParam Float proljetniKoeficijent,
            @RequestParam Float osnovniKoeficijent,
            @RequestParam Float umanjenjeKoeficijentaZaRukovodioceIspodOptimuma,
            @RequestParam Float povećanjeKoeficijentaZaRukovodiocePreko,
            @RequestParam Float povećanjeKoeficijentaPoBrojuStudenata,
            @RequestParam Float povećanjeKoeficijentaPoBrojuPredmeta,
            @RequestParam Float koeficijentZaDekanaPoBrojuStudenataNaFakultetu,
            @RequestParam Float koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku,
            @RequestParam(required = false, defaultValue = "1.0") float koeficijentZaDodatniStudijskiProgram,
            @RequestParam Float dodatniKoeficijentZaPreko350StudenataNaOdsjeku,
            @RequestParam(required = false, defaultValue = "1.5") float dodatniKoeficijentZaAsistentaVisegAsistenta
    ) {
        try {
            User user = new User();

            user.setImePrezime(imePrezime);
            user.setOdsjek(odsjek);
            user.setPozicija(pozicija);
            user.setRadnoVrijeme(radnoVrijeme);
            user.setAkademskoZvanje(akademskoZvanje);
            user.setProljetniKoeficijent(proljetniKoeficijent);
            user.setOsnovniKoeficijent(osnovniKoeficijent);
            user.setUmanjenjeKoeficijentaZaRukovodioceIspodOptimuma(umanjenjeKoeficijentaZaRukovodioceIspodOptimuma);
            user.setPovećanjeKoeficijentaZaRukovodiocePreko(povećanjeKoeficijentaZaRukovodiocePreko);
            user.setPovećanjeKoeficijentaPoBrojuStudenata(povećanjeKoeficijentaPoBrojuStudenata);
            user.setPovećanjeKoeficijentaPoBrojuPredmeta(povećanjeKoeficijentaPoBrojuPredmeta);
            user.setKoeficijentZaDekanaPoBrojuStudenataNaFakultetu(koeficijentZaDekanaPoBrojuStudenataNaFakultetu);
            user.setKoeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku(koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku);
            user.setKoeficijentZaDodatniStudijskiProgram(koeficijentZaDodatniStudijskiProgram);
            user.setDodatniKoeficijentZaPreko350StudenataNaOdsjeku(dodatniKoeficijentZaPreko350StudenataNaOdsjeku);
            user.setDodatniKoeficijentZaAsistentaVisegAsistenta(dodatniKoeficijentZaAsistentaVisegAsistenta);

            // Izračunaj ukupan koeficijent
            user.izracunajUkupanKoeficijentZaJesenjiSemestar();

            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir).resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                user.setSlikaPath(fileName);
            }

            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false) String imePrezime,
            @RequestParam(required = false) String odsjek
    ) {
        List<User> users;

        if (imePrezime != null && !imePrezime.isEmpty() && odsjek != null && !odsjek.isEmpty()) {
            users = userRepository.findByImePrezimeContainingIgnoreCaseAndOdsjekContainingIgnoreCase(imePrezime, odsjek);
        } else if (imePrezime != null && !imePrezime.isEmpty()) {
            users = userRepository.findByImePrezimeContainingIgnoreCase(imePrezime);
        } else if (odsjek != null && !odsjek.isEmpty()) {
            users = userRepository.findByOdsjekContainingIgnoreCase(odsjek);
        } else {
            users = userRepository.findAll();
        }

        return ResponseEntity.ok(users);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestParam(value = "profilnaSlika", required = false) MultipartFile file,
            @RequestParam String imePrezime,
            @RequestParam String odsjek,
            @RequestParam String pozicija,
            @RequestParam String radnoVrijeme,
            @RequestParam String akademskoZvanje,
            @RequestParam Float proljetniKoeficijent,
            @RequestParam Float osnovniKoeficijent,
            @RequestParam Float umanjenjeKoeficijentaZaRukovodioceIspodOptimuma,
            @RequestParam Float povećanjeKoeficijentaZaRukovodiocePreko,
            @RequestParam Float povećanjeKoeficijentaPoBrojuStudenata,
            @RequestParam Float povećanjeKoeficijentaPoBrojuPredmeta,
            @RequestParam Float koeficijentZaDekanaPoBrojuStudenataNaFakultetu,
            @RequestParam Float koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku,
            @RequestParam(required = false, defaultValue = "1.0") float koeficijentZaDodatniStudijskiProgram,
            @RequestParam Float dodatniKoeficijentZaPreko350StudenataNaOdsjeku,
            @RequestParam(required = false, defaultValue = "1.5") float dodatniKoeficijentZaAsistentaVisegAsistenta
    ) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        try {
            User user = optionalUser.get();

            user.setImePrezime(imePrezime);
            user.setOdsjek(odsjek);
            user.setPozicija(pozicija);
            user.setRadnoVrijeme(radnoVrijeme);
            user.setAkademskoZvanje(akademskoZvanje);
            user.setProljetniKoeficijent(proljetniKoeficijent);
            user.setOsnovniKoeficijent(osnovniKoeficijent);
            user.setUmanjenjeKoeficijentaZaRukovodioceIspodOptimuma(umanjenjeKoeficijentaZaRukovodioceIspodOptimuma);
            user.setPovećanjeKoeficijentaZaRukovodiocePreko(povećanjeKoeficijentaZaRukovodiocePreko);
            user.setPovećanjeKoeficijentaPoBrojuStudenata(povećanjeKoeficijentaPoBrojuStudenata);
            user.setPovećanjeKoeficijentaPoBrojuPredmeta(povećanjeKoeficijentaPoBrojuPredmeta);
            user.setKoeficijentZaDekanaPoBrojuStudenataNaFakultetu(koeficijentZaDekanaPoBrojuStudenataNaFakultetu);
            user.setKoeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku(koeficijentZaSefaOdsjekaPoBrojuStudenataNaOdsjeku);
            user.setKoeficijentZaDodatniStudijskiProgram(koeficijentZaDodatniStudijskiProgram);
            user.setDodatniKoeficijentZaPreko350StudenataNaOdsjeku(dodatniKoeficijentZaPreko350StudenataNaOdsjeku);
            user.setDodatniKoeficijentZaAsistentaVisegAsistenta(dodatniKoeficijentZaAsistentaVisegAsistenta);

            user.izracunajUkupanKoeficijentZaJesenjiSemestar();

            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir).resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                user.setSlikaPath(fileName);
            }

            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        try {
            User user = optionalUser.get();

            // Obrisi sliku ako postoji
            if (user.getSlikaPath() != null) {
                Path filePath = Paths.get("uploads").resolve(user.getSlikaPath());
                Files.deleteIfExists(filePath);
            }

            userRepository.deleteById(id);
            return ResponseEntity.ok().body("User deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška: " + e.getMessage());
        }
    }


}