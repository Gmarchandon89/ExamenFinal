package com.example.cursos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class ConfiguracionSeguridad {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> authorize
                        .mvcMatchers("/","/home", "/curso/{id}", "/cursos").permitAll()
                        .mvcMatchers("/css/**","/js/**").permitAll()
                        .mvcMatchers("/alumno/registro").anonymous()
                        .mvcMatchers("/cursos/crear", "/cursos/editar/**", "/cursos/tabla").hasAuthority("ADMIN")
                        .mvcMatchers("/alumno/panel", "/postular/**").hasAuthority("ALUMNO")
                        .anyRequest().authenticated() // permite acceso sin autenticacion se debe cambiar .permitAll() por .authenticated()
                )
                // configuracion del formulario de login
                .formLogin(form -> form
                        //ruta al controller del login por GET
                        .loginPage("/ingreso")
                        //ruta por defecto luego del login exitoso
                        .defaultSuccessUrl("/")
                        //el formulario de login no requiere autenticacion
                        .permitAll()
                )
                //configuracion de cierrre de sesion
                .logout(logout -> logout
                        //ruta para cerrar sesion(por GET)
                        .logoutRequestMatcher(
                                new AntPathRequestMatcher("/salir", "GET")
                        ).logoutSuccessUrl("/")
                )
        ;
        return http.build();
    }
}
