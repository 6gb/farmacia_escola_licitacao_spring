package br.ufsm.csi.poow2.farmacia_escola_licitacao.model;

public class Embalagem extends Insumo {
    private String material;
    private int capacidade;

    public Embalagem(int id) {
        super(id);
    }

    public Embalagem(int id, String descricao, String material, int capacidade) {
        super(id, descricao, "em");
        this.material = material;
        this.capacidade = capacidade;
    }

    public String getMaterial() {
        return material;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
