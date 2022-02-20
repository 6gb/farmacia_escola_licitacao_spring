package br.ufsm.csi.poow2.farmacia_escola_licitacao.model;

public class MateriaPrima extends Insumo {
    private String cas;
    private String dcb;
    private String dci;

    public MateriaPrima(int id) {
        super(id);
    }

    public MateriaPrima(int id, String descricao, String cas, String dcb, String dci) {
        super(id, descricao, "mp");
        this.cas = cas;
        this.dcb = dcb;
        this.dci = dci;
    }

    public String getCas() {
        return cas;
    }

    public String getDcb() {
        return dcb;
    }

    public String getDci() {
        return dci;
    }
}
