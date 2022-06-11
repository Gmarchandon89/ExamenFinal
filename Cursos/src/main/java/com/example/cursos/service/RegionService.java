package com.example.cursos.service;

import com.example.cursos.dao.RegionDao;
import com.example.cursos.entity.Region;
import com.example.cursos.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService implements RegionDao {

    @Autowired
    private RegionRepository repository;

    public List<Region> listar(){
        return repository.findAll();
    }

    @Override
    public List<Region> listarTodos() {
        return repository.findAll();
    }

    @Override
    public void guardar(Region region) {

    }

    @Override
    public Region buscarPorId(Long id) {
        return null;
    }

    @Override
    public void eliminar(long id) {

    }
}
