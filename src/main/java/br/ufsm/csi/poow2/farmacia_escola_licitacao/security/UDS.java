package br.ufsm.csi.poow2.farmacia_escola_licitacao.security;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.dao.UsuarioDAO;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UDS implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = new UsuarioDAO().obter(new Usuario(username));

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos");
        } else {
            UserDetails user = User
                    .withUsername(usuario.getNome())
                    .password(usuario.getSenha())
                    .authorities(usuario.getPermissao())
                    .build();
            return user;
        }
    }

}
