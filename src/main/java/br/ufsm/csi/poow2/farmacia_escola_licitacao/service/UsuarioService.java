package br.ufsm.csi.poow2.farmacia_escola_licitacao.service;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.dao.UsuarioDAO;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioService {
    private static final UsuarioDAO dao = new UsuarioDAO();

    public Usuario obter(Usuario u) {
        return dao.obter(u);
    }

    public ArrayList<Usuario> obterTudo() {
        return dao.obterTudo();
    }

    public boolean inserir(Usuario u) {
        return dao.inserir(u);
    }

    public boolean editar(Usuario u) {
        return dao.editar(u);
    }

    public boolean remover(Usuario u) {
        return dao.remover(u);
    }
}
