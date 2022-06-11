package com.example.cursos.repository;

import com.example.cursos.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    public Optional<Administrador> findByUsername(String username);

}
