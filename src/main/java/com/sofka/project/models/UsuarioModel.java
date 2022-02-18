package com.sofka.project.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.*;



@Entity
@Table(name = "usuario")
public class UsuarioModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    private String nombre;

    @Email
    private String email;


    private Integer prioridad;




// ------------------------------------------------------- Getter y Setter ------------------------------------------------------------------
    // Getter y Setter para Prioridad
    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
    public Integer getPrioridad() {
        return prioridad;
    }

    // Getter y Setter para id
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    // Getter y Setter para Nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    // Getter y Setter para Email
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
