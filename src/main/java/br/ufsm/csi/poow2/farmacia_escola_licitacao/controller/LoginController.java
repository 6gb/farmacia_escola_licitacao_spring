package br.ufsm.csi.poow2.farmacia_escola_licitacao.controller;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Usuario;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.security.JWTU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping("login")
    public ResponseEntity<Object> auth(@RequestBody Usuario u) {
        try {
            final Authentication auth = this.authManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            u.getNome(), u.getSenha())
                    );

            if(auth.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(auth);

                String token = new JWTU().generateToken(u);

                u.setToken(token);
                u.setSenha("");

                return new ResponseEntity<>(u, HttpStatus.OK);
            }

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário ou senha incorretos.", HttpStatus.BAD_REQUEST);
    }
}
