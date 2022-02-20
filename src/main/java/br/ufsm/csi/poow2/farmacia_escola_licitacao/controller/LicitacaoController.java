package br.ufsm.csi.poow2.farmacia_escola_licitacao.controller;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Fornecedor;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Insumo;
import org.springframework.web.bind.annotation.*;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.service.LicitacaoService;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Licitacao;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Fornecimento;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("licitacao")
public class LicitacaoController {
    private static final LicitacaoService service = new LicitacaoService();

    @GetMapping("licitacoes")
    public ArrayList<Licitacao> obterTudo() {
        return service.obterTudo();
    }

    @GetMapping("licitacao/{id}")
    public Licitacao obter(@PathVariable int id) {
        return service.obter(new Licitacao(id));
    }

    @PostMapping("inserir")
    public String inserir() {
        return service.inserir() ? "OK" : "ERRO";
    }

    @DeleteMapping("remover/{id}")
    public String remover(@PathVariable int id) {
        return service.remover(new Licitacao(id)) ? "OK" : "ERRO";
    }

    @PostMapping("licitacao/inserirFornecimento")
    public String inserirFornecimento(@RequestBody Fornecimento f) {
        return service.inserirFornecimento(f) ? "OK" : "ERRO";
    }

    @PutMapping("licitacao/editarFornecimento")
    public String editarFornecimento(@RequestBody Fornecimento f) {
        return service.editarFornecimento(f) ? "OK" : "ERRO";
    }

    @DeleteMapping("licitacao/removerFornecimento")
    public String removerFornecimento(@PathVariable int[] ids) {
        return service.removerFornecimento(
                new Fornecimento(new Licitacao(ids[0]), new Fornecedor(ids[1]), new Insumo(ids[2]))
        ) ? "OK" : "ERRO";
    }
}
