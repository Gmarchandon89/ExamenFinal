package com.example.cursos.seguridad;

import com.example.cursos.entity.Administrador;
import com.example.cursos.entity.Alumno;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Usuario implements UserDetails {

    private Administrador administrador;
    private Alumno alumno;

    public Usuario(Administrador administrador, Alumno alumno) {
        this.administrador = administrador;
        this.alumno = alumno;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(administrador != null) return List.of(new SimpleGrantedAuthority("ADMIN"));
        if(alumno != null) return List.of(new SimpleGrantedAuthority("ALUMNO"));
        return null;

    }

    @Override
    public String getPassword() {
        if (administrador != null) return administrador.getContrasena();
        if (alumno != null) return alumno.getContrasena();
        return null;
    }

    @Override
    public String getUsername() {
        if (administrador != null) return administrador.getNombre();
        if (alumno != null) return alumno.getNombre1();
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
