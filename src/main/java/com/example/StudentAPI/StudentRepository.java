package com.example.StudentAPI;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // No need to write anything – JpaRepository provides all basic CRUD methods
}
