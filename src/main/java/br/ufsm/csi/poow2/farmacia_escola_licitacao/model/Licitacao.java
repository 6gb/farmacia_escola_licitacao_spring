package br.ufsm.csi.poow2.farmacia_escola_licitacao.model;

import java.sql.Date;
import java.util.ArrayList;

public class Licitacao {
    private final int id;
    private Date dataCriacao;
    private ArrayList<Fornecimento> fornecimentos;

    public Licitacao(int id) {
        this.id = id;
    }

    public Licitacao(int id, Date dataCriacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
    }

    public Licitacao(int id, Date dataCriacao, ArrayList<Fornecimento> fornecimentos) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.fornecimentos = fornecimentos;
    }

    public int getId() {
        return id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public ArrayList<Fornecimento> getFornecimentos() { return fornecimentos; }

    public void setFornecimentos(ArrayList<Fornecimento> fornecimentos) {
        this.fornecimentos = fornecimentos;
    }
}
