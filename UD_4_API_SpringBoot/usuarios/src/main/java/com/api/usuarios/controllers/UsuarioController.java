package com.api.usuarios.controllers;


import com.api.usuarios.models.UsuarioModel;
import com.api.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public UsuarioModel saveUser(@RequestBody UsuarioModel user){
        return usuarioRepository.save(user);
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
