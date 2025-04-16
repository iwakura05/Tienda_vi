package com.tienda.repository;

import com.tienda.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    
    Usuario findByUsername(String username);
     //Se usa para buscar un registro de usuario en el proceso de activacion de un nuevo usuario
    Usuario findByUsernameAndPassword(String username, String Password);

    //Se usa para validar si dentro del usuario ya existe un registro con el user o correo indicado
    Usuario findByUsernameOrCorreo(String username, String correo);

    //Se usa para validar si dentro del usuario ya existe un registro con el user o correo indicado
    boolean existsByUsernameOrCorreo(String username, String correo);
}
