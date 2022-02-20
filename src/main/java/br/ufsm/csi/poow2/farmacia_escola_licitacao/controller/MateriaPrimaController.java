package br.ufsm.csi.poow2.farmacia_escola_licitacao.controller;
import org.springframework.web.bind.annotation.*;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.service.MateriaPrimaService;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.MateriaPrima;
import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("materia-prima")
public class MateriaPrimaController {
    private static final MateriaPrimaService service = new MateriaPrimaService();

    @GetMapping("materias-primas")
    public ArrayList<MateriaPrima> obterTudo() {
        return service.obterTudo();
    }

    @GetMapping("materia-prima/{id}")
    public MateriaPrima obter(@PathVariable int id) {
        return service.obter(new MateriaPrima(id));
    }

    @PostMapping("inserir")
    public String inserir(@RequestBody MateriaPrima mp) {
        return service.inserir(mp) ?  "OK" : "ERRO";
    }

    @PutMapping("editar")
    public String editar(@RequestBody MateriaPrima mp) {
        return service.editar(mp) ?  "OK" : "ERRO";
    }

    @DeleteMapping("remover/{id}")
    public String remover(@PathVariable int id) {
        return service.remover(new MateriaPrima(id)) ?  "OK" : "ERRO";
    }
}
