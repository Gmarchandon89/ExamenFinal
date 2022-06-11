package com.example.cursos.dao;

import com.example.cursos.entity.Administrador;


import java.util.List;

public interface AdministradorDao {

    public List<Administrador> listarTodos();

    public void guardar (Administrador administrador);//eliminar luego

    public Administrador buscarPorId(Long id);

    public void eliminar(long id);

    public Administrador crearAdministrador(Administrador administrador);
}
