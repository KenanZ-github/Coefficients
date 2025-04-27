package com.kenancode.fullstack_backend.repository;

import com.kenancode.fullstack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByImePrezimeLike(String imePrezime);
        List<User> findByOdsjekLike(String odsjek);
        List<User> findByImePrezimeLikeAndOdsjekLike(String imePrezime, String odsjek);
    }



