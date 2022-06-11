package com.example.cursos.dao;

import com.example.cursos.entity.Curso;

import java.util.List;

public interface CursoDao {

    public List<Curso> listarTodos();

    public void guardar (Curso curso);

    public Curso buscarPorId(Long id);

    public void eliminar(long id);

    public void actualizarCurso(Curso curso) ;

}
