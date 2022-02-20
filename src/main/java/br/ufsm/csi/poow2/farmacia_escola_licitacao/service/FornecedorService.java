package br.ufsm.csi.poow2.farmacia_escola_licitacao.service;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.dao.FornecedorDAO;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Fornecedor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FornecedorService {
    private static final FornecedorDAO dao = new FornecedorDAO();

    public Fornecedor obter(Fornecedor f) {
        return dao.obter(f);
    }

    public ArrayList<Fornecedor> obterTudo() {
        return dao.obterTudo();
    }

    public boolean inserir(Fornecedor f) {
        return dao.inserir(f);
    }

    public boolean editar(Fornecedor f) {
        return dao.editar(f);
    }

    public boolean remover(Fornecedor f) {
        return dao.remover(f);
    }
}
