package com.example.cursos.service;

import com.example.cursos.entity.Administrador;
import com.example.cursos.entity.Alumno;
import com.example.cursos.repository.AdministradorRepository;
import com.example.cursos.repository.AlumnoRepository;
import com.example.cursos.seguridad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<Administrador> administradorOpt = administradorRepository.findByUsername(username);
        if (administradorOpt.isPresent()){
            return new Usuario(administradorOpt.get(), null);
        }else{
            Optional<Alumno> alumnoOpt = alumnoRepository.findByEmail(username);
            if (alumnoOpt.isPresent()){
                return new Usuario(null, alumnoOpt.get());
        }
        throw new UsernameNotFoundException("Usuario no encontrado!");
        }
    }
}
