package com.curso.ecommerce.service;


import com.curso.ecommerce.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioService usuarioService;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    HttpSession session;

    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(s);
        if(usuarioOptional.isPresent()){
            session.setAttribute("idusuario", usuarioOptional.get().getId());
            Usuario usuario = usuarioOptional.get();
            return User.builder()
                    .username(usuario.getEmail())
                    .password(passwordEncoder.encode(usuario.getPassword()))
                    .authorities(usuario.getTipo())
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
