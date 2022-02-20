package br.ufsm.csi.poow2.farmacia_escola_licitacao.service;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.dao.MateriaPrimaDAO;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.MateriaPrima;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MateriaPrimaService {
    private static final MateriaPrimaDAO dao = new MateriaPrimaDAO();

    public MateriaPrima obter(MateriaPrima mp) {
        return dao.obter(mp);
    }

    public ArrayList<MateriaPrima> obterTudo() {
        return dao.obterTudo();
    }

    public boolean inserir(MateriaPrima mp) {
        return dao.inserir(mp);
    }

    public boolean editar(MateriaPrima mp) {
        return dao.editar(mp);
    }

    public boolean remover(MateriaPrima mp) {
        return dao.remover(mp);
    }
}
