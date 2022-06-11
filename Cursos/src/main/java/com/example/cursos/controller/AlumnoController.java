package com.example.cursos.controller;

import com.example.cursos.dao.AlumnoDao;
import com.example.cursos.dao.CursoDao;
import com.example.cursos.dao.RegionDao;
import com.example.cursos.entity.Alumno;
import com.example.cursos.entity.Curso;
import com.example.cursos.seguridad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AlumnoController {

    @Autowired
    public AlumnoDao alumnoDao;

    @Autowired
    public CursoDao cursoDao;

    @Autowired
    public RegionDao regionDao;


    @GetMapping("/alumno/registro")
    public String registroAlumno(Alumno alumno, Model model){
        model.addAttribute("regiones", regionDao.listarTodos());
        model.addAttribute("alumno", alumno);
        return "alumno/crearAlumno";

    }

    @PostMapping("/alumno/registro")
    public String registroProcesar(Alumno alumno) {
        alumnoDao.crearAlumno(alumno);
        return "/login";
    }

    @GetMapping("/postular/{id}")
    public String postularExitoso(@PathVariable("id") Long idCurso, Authentication usuarioOut){
        Curso curso = cursoDao.buscarPorId(idCurso);
        if(usuarioOut==null){
            return "/home";
        }else{
            Usuario usuario = (Usuario) usuarioOut.getPrincipal();
            Alumno alumno = alumnoDao.buscarPorId(usuario.getAlumno().getId());
            alumno.setCurso(curso);
            curso.setCuposDisponibles(curso.getCuposDisponibles()-1);
            cursoDao.actualizarCurso(curso);
            alumnoDao.actualizarAlumno(alumno);
        }
        return "redirect:/panelUsuario";

    }
    @GetMapping("/panelUsuario")
    public String panelAlumno(Authentication usuarioAuth, Model model){
        Usuario usuario = (Usuario) usuarioAuth.getPrincipal();
        Alumno alumno = alumnoDao.buscarPorId(usuario.getAlumno().getId());
        model.addAttribute("cursos", alumno.getCurso());
        return "/alumno/panelAlumno";

    }



}
