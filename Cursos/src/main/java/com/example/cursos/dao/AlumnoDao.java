package com.example.cursos.dao;

import com.example.cursos.entity.Alumno;

import java.util.List;

public interface AlumnoDao {

    public List<Alumno> listarTodos();

    public Alumno buscarPorId(Long id);

    public void eliminar(long id);

    public Alumno crearAlumno (Alumno alumno);

    public Alumno actualizarAlumno(Alumno alumno);
}
