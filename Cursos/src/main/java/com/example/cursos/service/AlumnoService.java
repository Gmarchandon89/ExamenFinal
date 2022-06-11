package com.example.cursos.service;


import com.example.cursos.dao.AlumnoDao;
import com.example.cursos.entity.Alumno;
import com.example.cursos.entity.Curso;
import com.example.cursos.repository.AlumnoRepository;
import com.example.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService implements AlumnoDao {
    @Autowired
    private AlumnoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private PasswordEncoder codificadorContrasena;

    @Override
    public Alumno crearAlumno (Alumno alumno) {
        String contrasenaCodificada = codificadorContrasena.encode(alumno.getContrasena());
        alumno.setContrasena(contrasenaCodificada);
        return repository.save(alumno);
    }

    @Override
    public Alumno actualizarAlumno(Alumno alumno){
        return repository.save(alumno);
    }

    @Override
    public List<Alumno> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Alumno buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(long id) {
        repository.deleteById(id);

    }


}
