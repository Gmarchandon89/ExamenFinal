package com.example.cursos.service;

import com.example.cursos.dao.AdministradorDao;
import com.example.cursos.entity.Administrador;
import com.example.cursos.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService implements AdministradorDao {

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private PasswordEncoder codificadorContrasena;

    @Override
    public Administrador crearAdministrador(Administrador administrador){
        String contrasenaCodificada = codificadorContrasena.encode(administrador.getContrasena());
        administrador.setContrasena(contrasenaCodificada);
        return repository.save(administrador);
    }

    @Override
    public List<Administrador> listarTodos() {
        return null;
    }

    @Override
    public void guardar(Administrador administrador) {

    }

    @Override
    public Administrador buscarPorId(Long id) {
        return null;
    }

    @Override
    public void eliminar(long id) {

    }
}
