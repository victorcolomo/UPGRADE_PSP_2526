package com.api.usuarios.controllers;


import com.api.usuarios.models.TareaModel;
import com.api.usuarios.models.UsuarioModel;
import com.api.usuarios.repositories.TareaRepository;
import com.api.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tareas")
@CrossOrigin(origins = "*")
public class TareaController {


    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar todas las tareas de un usario
    // List<TareaModel> findByUsuario(UsuarioModel usuario);

    // GET http://localhost:4000/tareas/usuario/4
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> getTareasByUsuario(@PathVariable Long usuarioId){

        // Comprobamos que exista el usuario
        Optional<UsuarioModel> usuarioOpt =  usuarioRepository.findById(usuarioId);

        if(usuarioOpt.isEmpty()){
            return ResponseEntity.status(404)
                    .body(Map.of("message","Usuario con ID"+usuarioId+" no encontrado"));
        }

        List<TareaModel> tareasUSer = tareaRepository.findByUsuario(usuarioOpt.get());

        return ResponseEntity.ok(tareasUSer);
    }


    // Crear una nueva tarea
    // POST http://localhost:4000/tareas/usuario/4
    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> crearTarea(@PathVariable Long usuarioId,  @RequestBody TareaModel tarea){

        // Comprobamos que exista el usuario
        Optional<UsuarioModel> usuarioOpt =  usuarioRepository.findById(usuarioId);

        if(usuarioOpt.isEmpty()){
            return ResponseEntity.status(404)
                    .body(Map.of("message","Usuario con ID"+usuarioId+" no encontrado"));
        }

        // Comprobamos campos obligatorios
        if(tarea.getTitulo() == null || tarea.getTitulo().isEmpty()){
            return ResponseEntity.status(404)
                    .body(Map.of("message","El título es obligatorio"));
        }

        tarea.setUsuario(usuarioOpt.get());
        tareaRepository.save(tarea);
        return ResponseEntity.ok(tarea);
    }

    // Actualizar tarea
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTarea(@PathVariable Long idTarea, @RequestBody TareaModel tareaModificada){

        // Comprobar si la tarea a modificar existe
        Optional<TareaModel> tareaOpt =  tareaRepository.findById(idTarea);

        if(tareaOpt.isEmpty()){
            return ResponseEntity.status(404)
                    .body(Map.of("message","Tarea con ID"+idTarea+" no encontrada"));
        }

        TareaModel tarea = tareaOpt.get();

        if(tareaModificada.getTitulo() != null){
            tarea.setTitulo(tareaModificada.getTitulo());
        }
        if(tareaModificada.getDescripcion() != null){
            tarea.setDescripcion(tareaModificada.getDescripcion());
        }
        if(tareaModificada.getCompletada() != null){
            tarea.setCompletada(tareaModificada.getCompletada());
        }

        tareaRepository.save(tarea);
        return ResponseEntity.ok(tarea);
    }
    // Eliminar una tarea
    // DELETE http://localhost:4000/tareas/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTarea(@PathVariable Long idTarea){
        // Comprobar si la tarea a modificar existe
        Optional<TareaModel> tareaOpt =  tareaRepository.findById(idTarea);

        if(tareaOpt.isEmpty()){
            return ResponseEntity.status(404)
                    .body(Map.of("message","Tarea con ID"+idTarea+" no encontrada"));
        }

        try {
            tareaRepository.deleteById(idTarea);

            return ResponseEntity.ok()
                    .body(Map.of("message", "Tarea con ID" + idTarea + " eliminada correctamente"));

        }catch (Exception e){
            return ResponseEntity.status(500)
                    .body(Map.of("message", "Error al elimina la tarea con ID " + idTarea));
        }
    }

}
