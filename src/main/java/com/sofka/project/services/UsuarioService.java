package com.sofka.project.services;

import java.util.*;

import com.sofka.project.exceptions.APIExceptionHandler;
import com.sofka.project.exceptions.APIRequestException;
import com.sofka.project.models.UsuarioModel;
import com.sofka.project.repositories.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioServiceInterface{
    private UsuarioRepository usuarioRepository;

    @Autowired // Vincular repositorio para emplear funciones en base de datos
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }


    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    // Guardar usuario en DB
    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e){
            throw new APIRequestException("__ERROR No se pudo guardar el usuario en DB", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override // Obtener usuario por id /usuario/{id}
    public Optional<UsuarioModel> obtenerPorId(Long id){
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        System.out.println(usuario);
        if(usuario.isEmpty()) throw new APIRequestException(String.format("__ERROR usuario con ID:%s no identificado en DB", id.intValue()), HttpStatus.NOT_FOUND);
        else return usuario;
    }


    @Override // Obtener por prioridad
    public ArrayList<UsuarioModel> obtenerPorPrioridad(Long prioridad){
        try {
            // Crea una nueva lista que guarda la operacion solicitada
            ArrayList<UsuarioModel> usuarioLista = usuarioRepository.findByPrioridad(prioridad.intValue());
            if (usuarioLista.isEmpty()) throw new Exception();
            else return usuarioLista;
        } catch (Exception e){
            // Excepcion personalizada para prioridad 404 HTTP response
            throw new APIRequestException("__ERROR usuario.Prioridad no encontrado", HttpStatus.NOT_FOUND);
        }

    }
    @Override // Obtener por nombre
    public ArrayList<UsuarioModel> obtenerPorNombre(String nombre){
        try{
            // Crea una nueva lista que guarda la operacion solicitada
            ArrayList<UsuarioModel> usuarioLista = usuarioRepository.findByNombre(nombre);
            if (usuarioLista.isEmpty()) throw new Exception();
            else return usuarioLista;
        } catch (Exception e){
            // Excepcion personalizada para nombre 404 HTTP response
            throw new APIRequestException("__ERROR usuario.Nombre no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @Override // Obtener por nombre
    public ArrayList<UsuarioModel> obtenerPorEmail(String email){
        try{
            // Crea una nueva lista que guarda la operacion solicitada
            ArrayList<UsuarioModel> usuarioLista = usuarioRepository.findByEmail(email);
            // Si la lista esta vacia, arroja una nueva excepcion
            if (usuarioLista.isEmpty()) throw new Exception();
            else return usuarioLista;
        } catch (Exception e){
            // Excepcion personalizada para email 404 HTTP response
            throw new APIRequestException("__ERROR usuario.Email no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @Override // Modificar usuario por ID solo si este existe en DB
    public UsuarioModel modificarPorId(Long id, UsuarioModel usuarioModificado){
        Optional<UsuarioModel> usuarioMain = usuarioRepository.findById(id);

        if(usuarioMain.isEmpty()) throw new APIRequestException(
                String.format("__ERROR usuario con ID:%s no identificado en DB, porfavor crear usuario desde POST", id.intValue()),
                HttpStatus.NOT_FOUND);
        else return usuarioMain.map(usuario -> {
                    usuario.setEmail(usuarioModificado.getEmail() == null ? usuario.getEmail() : usuarioModificado.getEmail());
                    usuario.setNombre(usuarioModificado.getNombre() == null ? usuario.getNombre(): usuarioModificado.getNombre());
                    usuario.setPrioridad(usuarioModificado.getPrioridad() == null ? usuario.getPrioridad():usuarioModificado.getPrioridad());
                    return usuarioRepository.save(usuario);
                }).orElseGet(()->{
                    usuarioModificado.setId(id);
                    return usuarioRepository.save(usuarioModificado);
                });
    }

    @Override
    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        } catch(Exception err){
            throw new APIRequestException(String.format("__ERROR usuario con ID:%s no pudo ser eliminado", id.intValue()), HttpStatus.BAD_REQUEST);
        }
    }
}
