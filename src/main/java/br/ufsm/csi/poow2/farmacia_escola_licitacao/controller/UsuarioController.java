package br.ufsm.csi.poow2.farmacia_escola_licitacao.controller;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Usuario;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.service.UsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("usuario")
public class UsuarioController {
    private static final UsuarioService service = new UsuarioService();

    @GetMapping("usuarios")
    public ArrayList<Usuario> obterTudo() {
        return service.obterTudo();
    }

    @GetMapping("usuario/{nome}")
    public Usuario obter(@PathVariable String nome) {
        return service.obter(new Usuario(nome));
    }

    @PostMapping("inserir")
    public String inserir(@RequestBody Usuario u) {
        u.setSenha(new BCryptPasswordEncoder().encode(u.getSenha()));
        return service.inserir(u) ?  "OK" : "ERRO";
    }

    @PutMapping("editar")
    public String editar(@RequestBody Usuario u) {
        u.setSenha(new BCryptPasswordEncoder().encode(u.getSenha()));
        return service.editar(u) ?  "OK" : "ERRO";
    }

    @DeleteMapping("remover/{nome}")
    public String remover(@PathVariable String nome) {
        return service.remover(new Usuario(nome)) ?  "OK" : "ERRO";
    }
}
