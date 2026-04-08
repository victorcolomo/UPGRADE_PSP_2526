package com.api.usuarios.repositories;

import com.api.usuarios.models.TareaModel;
import com.api.usuarios.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<TareaModel, Long> {

    // Todas las tareas de un usuario
    List<TareaModel> findByUsuario(UsuarioModel usuario);

    // Contar las tareas de un usuario
    long countByUsuario(UsuarioModel usuario);

    // Tareas de un usuario que esten Completadas
    List<TareaModel> findByUsuarioAndCompletadaTrue(UsuarioModel usuario);
}
