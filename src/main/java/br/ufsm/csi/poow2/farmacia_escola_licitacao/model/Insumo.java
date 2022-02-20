package br.ufsm.csi.poow2.farmacia_escola_licitacao.model;

public class Insumo {
    protected final int id;
    protected String descricao;
    protected String tipo;

    public Insumo(int id) {
        this.id = id;
    }

    public Insumo(int id, String descricao, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }
}
