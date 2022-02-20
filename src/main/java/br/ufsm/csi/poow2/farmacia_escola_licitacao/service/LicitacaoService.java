package br.ufsm.csi.poow2.farmacia_escola_licitacao.service;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Licitacao;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Fornecimento;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.dao.LicitacaoDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LicitacaoService {
    private static final LicitacaoDAO licitacaoDAO = new LicitacaoDAO();

    public Licitacao obter(Licitacao l) {
        return licitacaoDAO.obter(l);
    }

    public ArrayList<Licitacao> obterTudo() {
        return licitacaoDAO.obterTudo();
    }

    public boolean inserir() {
        return licitacaoDAO.inserir();
    }

    public boolean remover(Licitacao l) {
        return licitacaoDAO.remover(l);
    }

    public boolean inserirFornecimento(Fornecimento f) {
        return licitacaoDAO.inserirFornecimento(f);
    }

    public boolean editarFornecimento(Fornecimento f) {
        return licitacaoDAO.editarFornecimento(f);
    }

    public boolean removerFornecimento(Fornecimento f) {
        return licitacaoDAO.removerFornecimento(f);
    }
}
