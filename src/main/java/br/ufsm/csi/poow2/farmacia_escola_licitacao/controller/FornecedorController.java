package br.ufsm.csi.poow2.farmacia_escola_licitacao.controller;

import org.springframework.web.bind.annotation.*;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.service.FornecedorService;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Fornecedor;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("fornecedor")
public class FornecedorController {
    private static final FornecedorService service = new FornecedorService();

    @GetMapping("fornecedores")
    public ArrayList<Fornecedor> obterTudo() {
        return service.obterTudo();
    }

    @GetMapping("fornecedor/{id}")
    public Fornecedor obter(@PathVariable int id) {
        return service.obter(new Fornecedor(id));
    }

    @PostMapping("inserir")
    public String inserir(@RequestBody Fornecedor f) {
        return service.inserir(f) ? "OK" : "ERRO";
    }

    @PutMapping("editar")
    public String editar(@RequestBody Fornecedor f) {
        return service.editar(f) ? "OK" : "ERRO";
    }

    @DeleteMapping("remover/{id}")
    public String remover(@PathVariable int id) {
        return service.remover(new Fornecedor(id)) ? "OK" : "ERRO";
    }
}
