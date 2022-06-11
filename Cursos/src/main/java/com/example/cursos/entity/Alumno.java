package com.example.cursos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Alumno implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(message = "Ingrese su rut sin puntos ", max = 10)
    @NotNull(message = "Ingrese su rut")
    @Column(nullable = false, length = 10)
    private String rut;

    @NotNull(message = "Ingrese primer nombre")
    @Column(nullable = false, length = 45)
    private String nombre1;

    @NotNull(message = "Ingrese segundo nombre")
    @Column(nullable = false, length = 45)
    private String nombre2;

    @NotNull(message = "Ingrese primer apellido")
    @Column(nullable = false, length = 45)
    private String apellido1;

    @NotNull(message = "Ingrese segundo apellido")
    @Column(nullable = false, length = 45)
    private String apellido2;

    @Min(message = "Debe ser mayor de edad para registrarse", value = 18)
    @NotNull
    @Column(nullable = false, length = 2)
    private Integer edad;

    @NotNull(message = "Ingrese una direccion")
    @Column(nullable = false)
    private String direccion;
    @NotNull(message = "Seleccione una región")
    @ManyToOne(fetch = FetchType.LAZY, optional = false) //como tiene que buscar
    @JoinColumn(nullable = false)
    private Region region;

    @NotNull(message = "Ingrese un teléfono")
    @Column(nullable = false)
    private String telefono;

    @NotNull(message = "Ingrese un correo")
    @Email(message = "Ingrese un correo válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Ingrese una contraseña")
    @Column(nullable = false)
    private String contrasena;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Curso curso;

}
