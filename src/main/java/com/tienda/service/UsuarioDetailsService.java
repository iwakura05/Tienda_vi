package com.tienda.service;

import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
import com.tienda.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        //Se busca el usuario con el username en la tabla de usuarios
        Usuario usuario = usuarioRepository.findByUsername(username); //BUSCAR EL REGISTRO DE ESE USERNAME

        //Se valida que el usuario exista...
        if (usuario == null) {

            //No se encontró el usuario...
            throw new UsernameNotFoundException(username);
        }

        //Si estamos acá... SI se encontró el usuario con ese username
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        
        //Se debe crear el arreglo de roles
        var roles = new ArrayList<GrantedAuthority>();
        
        for (Rol rol : usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority("ROLE_"+rol.getNombre()));
        }

        //Se retorna un usuario con username, password y roles
        return new User(usuario.getUsername(),usuario.getPassword(),roles);
    }

}
