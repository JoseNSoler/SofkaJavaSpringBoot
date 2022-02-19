package com.sofka.project.services;


import com.sofka.project.exceptions.APIRequestException;
import com.sofka.project.models.UsuarioModel;

import java.util.ArrayList;
import java.util.Optional;


public interface UsuarioServiceInterface {
    // UsuarioModel guardarUsuario(UsuarioModel usuario) throws UsuarioAlreadyExistsException;
    Optional<UsuarioModel> obtenerPorId(Long id) throws APIRequestException;
    boolean eliminarUsuario(Long id) throws APIRequestException;
    ArrayList<UsuarioModel> obtenerPorPrioridad(Long prioridad) throws APIRequestException;
    ArrayList<UsuarioModel> obtenerPorEmail(String email) throws APIRequestException;
    ArrayList<UsuarioModel> obtenerPorNombre(String nombre) throws APIRequestException;
    UsuarioModel modificarPorId(Long id, UsuarioModel usuario) throws APIRequestException;
}
