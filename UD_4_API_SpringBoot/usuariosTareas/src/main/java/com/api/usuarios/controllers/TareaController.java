package com.api.usuarios.controllers;


import com.api.usuarios.models.TareaModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tareas")
@CrossOrigin(origins = "*")
public class TareaController {


    // Listar todas las tareas de un usario
    // List<TareaModel> findByUsuario(UsuarioModel usuario);

    // GET http://localhost:4000/tareas/usuario/4
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getTareasByUsuario(@PathVariable Long usuarioId){
        return null;
    }


    // Crear una nueva tarea
    @PostMapping
    public ResponseEntity<?> crearTarea(@RequestBody TareaModel tarea){
        return null;
    }

    // Actualizar tarea
    @PutMapping
    public ResponseEntity<?> actualizarTarea(@RequestBody TareaModel tarea){
        return null;
    }
    // Eliminar una tarea
    // DELETE http://localhost:4000/tareas/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarea(@PathVariable Long tareaId){
        return null;
    }

}
