package br.ufsm.csi.poow2.farmacia_escola_licitacao.dao;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Insumo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class InsumoDAO {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Boolean status;

    public Insumo obter(Insumo i) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "select * from insumo where id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, i.getId());
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                i = new Insumo(
                        i.getId(),
                        this.resultSet.getString("descricao"),
                        this.resultSet.getString("tipo")
                );
            }
        } catch (SQLException exc) { exc.printStackTrace();}

        return i;
    }

    public ArrayList<Insumo> obterTudo() {
        ArrayList<Insumo> insumos = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from insumo as i, embalagem as e where i.id=e.id";
            Statement statement = connection.createStatement();
            this.resultSet = statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Insumo i = new Insumo(
                        this.resultSet.getInt("id"),
                        this.resultSet.getString("descricao"),
                        this.resultSet.getString("tipo")
                );
                insumos.add(i);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return insumos;
    }
}
