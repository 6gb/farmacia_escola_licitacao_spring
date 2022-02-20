package br.ufsm.csi.poow2.farmacia_escola_licitacao.controller;
import org.springframework.web.bind.annotation.*;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.service.EmbalagemService;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Embalagem;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("embalagem")
public class EmbalagemController {
    private static final EmbalagemService service = new EmbalagemService();

    @GetMapping("embalagens")
    public ArrayList<Embalagem> obterTudo() {
        return service.obterTudo();
    }

    @GetMapping("embalagem/{id}")
    public Embalagem obter(@PathVariable int id) {
        return service.obter(new Embalagem(id));
    }

    @PostMapping("inserir")
    public String inserir(@RequestBody Embalagem e) {
        return service.inserir(e) ?  "OK" : "ERRO";
    }

    @PutMapping("editar")
    public String editar(@RequestBody Embalagem e) {
        return service.editar(e) ?  "OK" : "ERRO";
    }

    @DeleteMapping("remover/{id}")
    public String remover(@PathVariable int id) {
        return service.remover(new Embalagem(id)) ?  "OK" : "ERRO";
    }
}
