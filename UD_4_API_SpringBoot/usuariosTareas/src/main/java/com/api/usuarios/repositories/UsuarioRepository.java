package com.api.usuarios.repositories;

import com.api.usuarios.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long> {

    // Método para saber si existe un usuario con el email pasado como argumento
    /*
        SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END
        FROM usuario
        WHERE email = ?:
     */
        boolean existsByEmail(String email);

        //List<UsuarioModel> findByApellidoLike(String apellido);


        //List<UsuarioModel> findByIdGreatherThan(Long Id);

}
