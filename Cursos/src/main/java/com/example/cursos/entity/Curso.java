package com.example.cursos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Curso implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Ingrese un nombre")
    @Column(nullable = false, length = 50)
    private String nombre;

    @NotNull(message = "Ingrese una descripción")
    @Column(nullable = false)
    private String descripcion;

    @NotNull(message = "Ingrese el contenido del curso")
    @Column(nullable = false)
    private String contenido;

    @NotNull(message = "Ingrese fecha de inicio")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private Date fechaInicio;

    @NotNull(message = "Ingrese fecha de término")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @Column(nullable = false)
    private Date fechaFin;

    @NotNull(message = "Ingrese cupos totales del curso")
    @Column(nullable = false)
    private Integer cuposTotales;

    @NotNull
    @Column(nullable = false)
    private Integer cuposDisponibles;

    @NotNull(message = "Ingrese una URL de una imagen")
    private String imagen;

    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos;


}
