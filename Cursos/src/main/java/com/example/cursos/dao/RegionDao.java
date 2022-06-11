package com.example.cursos.dao;


import com.example.cursos.entity.Region;

import java.util.List;

public interface RegionDao {

    public List<Region> listarTodos();

    public void guardar (Region region);

    public Region buscarPorId(Long id);

    public void eliminar(long id);

}
