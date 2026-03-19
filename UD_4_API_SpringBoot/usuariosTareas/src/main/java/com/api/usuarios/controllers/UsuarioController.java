package com.api.usuarios.controllers;


import com.api.usuarios.models.UsuarioModel;
import com.api.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET: obtenemos datos
    @GetMapping
    public ArrayList<UsuarioModel> getAllUsers(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public UsuarioModel getUserById(@PathVariable("id") Long id){
        return usuarioRepository.findById(id).get();
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        usuarioRepository.deleteById(id);
        return "Eliminado "+id;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UsuarioModel user){

        // 1º Validar que se envían todos los campos
        if( user.getNombre() == null || user.getApellido() == null ||
        user.getEmail() == null || user.getNombre().trim().isEmpty() || user.getApellido().trim().isEmpty()
                || user.getEmail().trim().isEmpty()){

            return ResponseEntity
                    .badRequest()       // HTTP 4000
                    .body(Map.of("message","Faltan campos obligatorios"));  // Enviamos mensaje JSON con el error
        }

        // 2º Validar que el email tenga el formato correcto
        String emailRegex = "^[A-Za-z0-9_.]+@[A-Za-z0-9_.]+$";
        if( ! user.getEmail().matches(emailRegex)){
            return ResponseEntity
                    .badRequest()       // HTTP 4000
                    .body(Map.of("message","El email "+user.getEmail()+" no tiene un formato válido"));  // Enviamos mensaje JSON con el error
        }

        // 3º Validar que el email no exista
        if(usuarioRepository.existsByEmail(user.getEmail())){
            return ResponseEntity
                    .badRequest()       // HTTP 4000
                    .body(Map.of("message","El email "+user.getEmail()+" ya esta registrado"));  // Enviamos mensaje JSON con el error
        }

        // Si pasa todas las validaciones, registramos el usuario
        usuarioRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/{id}")
    public UsuarioModel updateUser(@RequestBody UsuarioModel usuarioModificado, @PathVariable("id") Long id){

        UsuarioModel usuario = usuarioRepository.findById(id).get();
        usuario.setNombre(usuarioModificado.getNombre());
        usuario.setApellido(usuarioModificado.getApellido());
        usuario.setEmail(usuarioModificado.getEmail());
        return usuarioRepository.save(usuario);

    }

}
