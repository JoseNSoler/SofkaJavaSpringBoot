package com.sofka.project.repositories;

import com.sofka.project.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    // Nombre personalizado para busqueda findByName, Email
    ArrayList<UsuarioModel> findByPrioridad(Integer prioridad);
    ArrayList<UsuarioModel> findByNombre(String nombre);
    ArrayList<UsuarioModel> findByEmail(String email);
}
