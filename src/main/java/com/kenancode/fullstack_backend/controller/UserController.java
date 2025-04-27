package com.kenancode.fullstack_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.kenancode.fullstack_backend.model.User;
import com.kenancode.fullstack_backend.model.Academic;
import com.kenancode.fullstack_backend.repository.UserRepository;
import com.kenancode.fullstack_backend.repository.AcademicRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AcademicRepository academicRepository;

    private final String uploadDir = "uploads";

    @PostMapping
    public ResponseEntity<?> createUser(@RequestParam("slikaPath") MultipartFile slikaPath, 
                                      @RequestParam Map<String, String> userData) {
        try {
            System.out.println("Received user data: " + userData);
            
            // Handle file upload
            String fileName = handleFileUpload(slikaPath);
            System.out.println("File uploaded successfully: " + fileName);
            
            // Create User with basic information only
            User user = new User();
            user.setImePrezime(userData.get("imePrezime"));
            user.setOdsjek(userData.get("odsjek"));
            user.setPozicija(userData.get("pozicija"));
            user.setRadnoVrijeme(userData.get("radnoVrijeme"));
            user.setAkademskoZvanje(userData.get("akademskoZvanje"));
            user.setSlikaPath(fileName);
            
            // Save user
            User savedUser = userRepository.save(user);
            System.out.println("User saved successfully with ID: " + savedUser.getId());

            // If you need to create an initial Academic record, you can do it here
            if (userData.containsKey("academicYear")) {
                Academic academic = new Academic();
                academic.setUser(savedUser);
                academic.setAcademicYear(userData.get("academicYear"));
                // Set other Academic fields as needed
                academicRepository.save(academic);
            }
            
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }

    private String handleFileUpload(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate unique filename
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.Osubstring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + fileExtension;

        // Save file
        Path filePath = uploadPath.resolve(newFilename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return newFilename;
    }

    // Ostale postojeće metode ostaju iste
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/search")
    public List<User> searchUsers(
            @RequestParam(required = false) String imePrezime,
            @RequestParam(required = false) String odsjek
    ) {
        if (imePrezime != null && odsjek != null) {
            return userRepository.findByImePrezimeLikeAndOdsjekLike("%" + imePrezime + "%", "%" + odsjek + "%");
        } else if (imePrezime != null) {
            return userRepository.findByImePrezimeLike("%" + imePrezime + "%");
        } else if (odsjek != null) {
            return userRepository.findByOdsjekLike("%" + odsjek + "%");
        } else {
            return userRepository.findAll();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Delete associated image if exists
            if (user.getSlikaPath() != null) {
                Path imagePath = Paths.get(uploadDir, user.getSlikaPath());
                Files.deleteIfExists(imagePath);
            }

            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user: " + e.getMessage());
        }
    }
    @PostMapping("/{userId}/academic-year")
    public ResponseEntity<?> addAcademicYear(@PathVariable Long userId, @RequestBody Academic academic) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            academic.setUser(user);
            academic.izracunajUkupanKoeficijentZaJesenjiSemestar();
            Academic savedAcademic = academicRepository.save(academic);

            return ResponseEntity.ok(savedAcademic);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding academic year: " + e.getMessage());
        }
    }
    @DeleteMapping("/{userId}/academic-year/{academicId}")
    public ResponseEntity<?> deleteAcademicYear(
            @PathVariable Long userId,
            @PathVariable Integer academicId) {
        try {
            Academic academic = academicRepository.findById(academicId)
                    .orElseThrow(() -> new RuntimeException("Academic year not found"));

            // Provjeri da li akademska godina pripada korisniku
            if (!academic.getUser().getId().equals(userId)) {
                return ResponseEntity.badRequest().body("Academic year does not belong to this user");
            }

            academicRepository.delete(academic);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting academic year: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}/academic-year/{academicId}")
    public ResponseEntity<?> getAcademicYear(
            @PathVariable Long userId,
            @PathVariable Integer academicId) {
        try {
            Academic academic = academicRepository.findById(academicId)
                    .orElseThrow(() -> new RuntimeException("Academic year not found"));

            if (!academic.getUser().getId().equals(userId)) {
                return ResponseEntity.badRequest().body("Academic year does not belong to this user");
            }

            return ResponseEntity.ok(academic);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting academic year: " + e.getMessage());
        }
    }

    @PutMapping("/{userId}/academic-year/{academicId}")
    public ResponseEntity<?> updateAcademicYear(
            @PathVariable Long userId,
            @PathVariable Integer academicId,
            @RequestBody Academic academicDetails) {
        try {
            Academic academic = academicRepository.findById(academicId)
                    .orElseThrow(() -> new RuntimeException("Academic year not found"));

            if (!academic.getUser().getId().equals(userId)) {
                return ResponseEntity.badRequest().body("Academic year does not belong to this user");
            }

            // Ažuriraj podatke
            academic.setAcademicYear(academicDetails.getAcademicYear());
            academic.setProljetniKoeficijent(academicDetails.getProljetniKoeficijent());
            // ... ažuriraj ostala polja ...

            academic.izracunajUkupanKoeficijentZaJesenjiSemestar();
            Academic updatedAcademic = academicRepository.save(academic);

            return ResponseEntity.ok(updatedAcademic);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating academic year: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestParam(value = "slikaPath", required = false) MultipartFile slikaPath,
                                        @RequestParam(value = "postojecaSlikaPath", required = false) String postojecaSlikaPath,
                                        @RequestParam Map<String, String> userData) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Update basic information
            if (userData.get("imePrezime") != null) user.setImePrezime(userData.get("imePrezime"));
            if (userData.get("odsjek") != null) user.setOdsjek(userData.get("odsjek"));
            if (userData.get("pozicija") != null) user.setPozicija(userData.get("pozicija"));
            if (userData.get("radnoVrijeme") != null) user.setRadnoVrijeme(userData.get("radnoVrijeme"));
            if (userData.get("akademskoZvanje") != null) user.setAkademskoZvanje(userData.get("akademskoZvanje"));

            // Handle new file upload if provided
            if (slikaPath != null && !slikaPath.isEmpty()) {
                // Delete old image if exists
                if (user.getSlikaPath() != null) {
                    Path oldImagePath = Paths.get(uploadDir, user.getSlikaPath());
                    Files.deleteIfExists(oldImagePath);
                }

                // Upload new image
                String fileName = handleFileUpload(slikaPath);
                user.setSlikaPath(fileName);
            } else if (postojecaSlikaPath != null) {
                // Ako nema nove slike ali imamo postojeću, zadržavamo postojeću
                user.setSlikaPath(postojecaSlikaPath);
            }

            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }
}
