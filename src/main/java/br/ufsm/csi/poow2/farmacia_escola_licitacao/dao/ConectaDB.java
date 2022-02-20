package br.ufsm.csi.poow2.farmacia_escola_licitacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaDB {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/farmacia_escola_licitacao";
    private static final String USER = "java";
    private static final String PASSWORD = "pgjdbc";

    public Connection getConexao() {
        Connection conexao = null;

        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return conexao;
    }
}
