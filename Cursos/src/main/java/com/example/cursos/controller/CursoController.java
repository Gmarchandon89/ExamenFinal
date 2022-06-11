package com.example.cursos.controller;

import com.example.cursos.dao.AlumnoDao;
import com.example.cursos.dao.CursoDao;
import com.example.cursos.entity.Alumno;
import com.example.cursos.entity.Curso;
import com.example.cursos.seguridad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CursoController {

    @Autowired
    private CursoDao cursoDao;

    @Autowired
    private AlumnoDao alumnoDao;


    @GetMapping("/cursos/crear")
    public String formCurso(Curso curso, Model model){
        model.addAttribute("titulo", "Formulario creación de cursos");
        model.addAttribute("curso", curso);
        return "administrador/crearCurso";
    }

    @GetMapping("/cursos/editar/{id}")
    public String editarCursos(@PathVariable("id") Long idCurso, Model model) {
        Curso curso = cursoDao.buscarPorId(idCurso);
        model.addAttribute("titulo", "Formulario: Editar Curso");
        model.addAttribute("cursos", curso);

        return "administrador/editarCurso";
    }

    @GetMapping("/cursos/eliminar/{id}")
    public String eliminarCurso(@PathVariable("id") Long idCurso) {
        cursoDao.eliminar(idCurso);

        return "redirect:/cursos/tabla";
    }

    @GetMapping("/cursos/tabla")
    public String tablaCursos(Model model){
        List<Curso> cursoList = cursoDao.listarTodos();
        model.addAttribute("titulo", "Lista de Cursos");
        model.addAttribute("cursos", cursoList);
        return "administrador/tablaCursos";
    }

    @PostMapping("/cursos/editar")
    public String editCurso(Curso curso) {
        curso.setCuposDisponibles(curso.getCuposTotales());
        cursoDao.guardar(curso);
        return "redirect:/cursos";
    }

    @PostMapping("/cursos/crear")
    public String crearCurso(Curso curso) {
        curso.setCuposDisponibles(curso.getCuposTotales());
        cursoDao.guardar(curso);
        return "redirect:/cursos";

    }

    @GetMapping("/curso/{id}")
    public String verCurso(@PathVariable("id") Curso curso, Model model){
        model.addAttribute("curso", curso);
        return "curso/detalle";
    }
    @GetMapping("/cursoAlumno/{id}")
    public String verCurso(@PathVariable("id") Curso curso, Model model,Authentication usuarioAuth){
        Usuario usuario = (Usuario) usuarioAuth.getPrincipal();
        Alumno alumno = alumnoDao.buscarPorId(usuario.getAlumno().getId());
        model.addAttribute("curso", curso);
        model.addAttribute("cursoId", alumno.getCurso());
        return "curso/detalle";
    }

    @GetMapping("/cursosAlumno")
    public String cursos(Authentication usuarioAuth, Model model) {
        Usuario usuario = (Usuario) usuarioAuth.getPrincipal();
        Alumno alumno = alumnoDao.buscarPorId(usuario.getAlumno().getId());
        List<Curso> cursos = cursoDao.listarTodos();
        model.addAttribute("titulo", "Lista de Cursos");
        model.addAttribute("cursos", cursos);
        model.addAttribute("authId", alumno.getCurso());
        return "curso/cursos";
    }

    @GetMapping("/cursos")
    public String cursos(Model model) {
        List<Curso> cursos = cursoDao.listarTodos();
        model.addAttribute("titulo", "Lista de Cursos");
        model.addAttribute("cursos", cursos);
        return "curso/cursos";
    }

    @GetMapping("")
    public String home(Model model){
        model.addAttribute("titulo", "Cursos Online 2022");
        return "home/home";
    }

}
