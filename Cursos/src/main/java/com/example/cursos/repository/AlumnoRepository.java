package com.example.cursos.repository;


import com.example.cursos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    public Optional<Alumno> findByEmail(String email);

}
