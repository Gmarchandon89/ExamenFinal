package com.example.cursos.service;

import com.example.cursos.dao.CursoDao;
import com.example.cursos.entity.Curso;
import com.example.cursos.repository.AlumnoRepository;
import com.example.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements CursoDao {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    @Override
    public void guardar(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public void actualizarCurso(Curso curso){
        cursoRepository.save(curso);
    }



}
