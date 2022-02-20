package br.ufsm.csi.poow2.farmacia_escola_licitacao.model;

public class Fornecedor {
    private int id;
    private String cnpj;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String sicaf;

    public Fornecedor(int id) {
        this.id = id;
    }

    public Fornecedor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Fornecedor(int id, String cnpj, String nome, String endereco, String telefone, String email, String sicaf) {
        this.id = id;
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.sicaf = sicaf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSicaf() {
        return sicaf;
    }
}
