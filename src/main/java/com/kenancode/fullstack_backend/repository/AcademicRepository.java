package com.kenancode.fullstack_backend.repository;

import com.kenancode.fullstack_backend.model.Academic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicRepository extends JpaRepository<Academic, Integer> {
    // Add custom query methods if needed
}