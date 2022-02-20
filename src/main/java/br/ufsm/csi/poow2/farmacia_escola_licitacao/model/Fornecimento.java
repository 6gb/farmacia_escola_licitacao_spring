package br.ufsm.csi.poow2.farmacia_escola_licitacao.model;

public class Fornecimento {
    private Licitacao licitacao;
    private Fornecedor fornecedor;
    private Insumo insumo;
    private double preco;
    private double media;

    public Fornecimento() { }

    public Fornecimento(Licitacao licitacao, Fornecedor fornecedor, Insumo insumo) {
        this.licitacao = licitacao;
        this.fornecedor = fornecedor;
        this.insumo = insumo;
    }

    public Fornecimento(Licitacao licitacao, Fornecedor fornecedor, Insumo insumo, double preco) {
        this.licitacao = licitacao;
        this.fornecedor = fornecedor;
        this.insumo = insumo;
        this.preco = preco;
    }

    public Fornecimento(Licitacao licitacao, Fornecedor fornecedor, Insumo insumo, double preco, double media) {
        this.licitacao = licitacao;
        this.fornecedor = fornecedor;
        this.insumo = insumo;
        this.preco = preco;
        this.media = media;
    }

    public Licitacao getLicitacao() {
        return licitacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public double getPreco() {
        return preco;
    }

    public double getMedia() {
        return media;
    }
}
