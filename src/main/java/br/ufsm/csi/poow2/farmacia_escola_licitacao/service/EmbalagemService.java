package br.ufsm.csi.poow2.farmacia_escola_licitacao.service;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.dao.EmbalagemDAO;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Embalagem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmbalagemService {
    private static final EmbalagemDAO dao = new EmbalagemDAO();

    public Embalagem obter(Embalagem e) {
        return dao.obter(e);
    }

    public ArrayList<Embalagem> obterTudo() {
        return dao.obterTudo();
    }

    public boolean inserir(Embalagem e) {
        return dao.inserir(e);
    }

    public boolean editar(Embalagem e) {
        return dao.editar(e);
    }

    public boolean remover(Embalagem e) {
        return dao.remover(e);
    }
}
