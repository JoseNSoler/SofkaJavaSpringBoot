package com.sofka.project.controllers;

import com.sofka.project.exceptions.APIRequestException;
import com.sofka.project.models.UsuarioModel;
import com.sofka.project.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;


    @GetMapping() // PreDeterminada respuesta para GET /usuario
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }


    @PostMapping() // PreDeterminada respuesta para creacion usuario por POST /usuario
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}") // Respuesta para metodo GET /usuario/{id} - id=numero ID usuario
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return usuarioService.obtenerPorId(id);
    }

    // Respuesta personalizada para metodo GET /usuario/ en campos nombre, prioridad, email EX: /usuario/query?prioridad=1
    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorParametros(@Valid @RequestParam("nombre") Optional<String> nombre,
                                                               @Valid @RequestParam("prioridad") Optional<Long> prioridad,
                                                               @Valid @RequestParam("email") Optional<String> email) {

        if (nombre.isPresent()) return usuarioService.obtenerPorNombre(nombre.get());
        else if (email.isPresent()) return usuarioService.obtenerPorEmail(email.get());
        else if (prioridad.isPresent()) return usuarioService.obtenerPorPrioridad(prioridad.get());
        else throw new APIRequestException("__ERROR metodo escrito no valido", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/{id}") // Respuesta para metodo PUT /usuario/{id} - id=numero ID usuario a modificar
    public UsuarioModel modificarPorId(@RequestBody UsuarioModel usuarioModificado, @PathVariable Long id){
        return usuarioService.modificarPorId(id, usuarioModificado);
    }


    @DeleteMapping(path = "/{id}") // Respuesta para metodo DELETE /usuario/{id} - id=numero ID usuario a eliminar
    public String eliminarPorId(@PathVariable("id") Long id) {
        if(usuarioService.eliminarUsuario(id)) return "Se elimino el usuario con ID: " + id;
        return null;
    }
}
