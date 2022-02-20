package br.ufsm.csi.poow2.farmacia_escola_licitacao.dao;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.MateriaPrima;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class MateriaPrimaDAO {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Boolean status;

    public MateriaPrima obter(MateriaPrima mp) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "select * from insumo as i, materiaprima as mp where i.id=mp.id and mp.id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, mp.getId());
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                mp = new MateriaPrima(
                        mp.getId(),
                        this.resultSet.getString("descricao"),
                        this.resultSet.getString("cas"),
                        this.resultSet.getString("dcb"),
                        this.resultSet.getString("dci")
                );
            }
        } catch (SQLException e) { e.printStackTrace();}

        return mp;
    }

    public ArrayList<MateriaPrima> obterTudo() {
        ArrayList<MateriaPrima> materiasprimas = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "select * from insumo as i, materiaprima as mp where i.id=mp.id";
            Statement statement = connection.createStatement();
            this.resultSet = statement.executeQuery(sql);

            while (this.resultSet.next()) {
                MateriaPrima mp = new MateriaPrima(
                        this.resultSet.getInt("id"),
                        this.resultSet.getString("descricao"),
                        this.resultSet.getString("cas"),
                        this.resultSet.getString("dcb"),
                        this.resultSet.getString("dci")
                );
                materiasprimas.add(mp);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return materiasprimas;
    }

    public Boolean inserir(MateriaPrima mp) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "with novoinsumo as (insert into insumo(descricao, tipo) values(?, 'mp') returning id)" +
                    "insert into materiaprima(id, cas, dcb, dci) values ((select id from novoinsumo), ?, ?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, mp.getDescricao());
            this.preparedStatement.setString(2, mp.getCas());
            this.preparedStatement.setString(3, mp.getDcb());
            this.preparedStatement.setString(4, mp.getDci());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.status = true;
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean editar(MateriaPrima mp) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "begin;" +
                    "update insumo set descricao=? where id=?;" +
                    "update materiaprima set cas=?, dcb=?, dci=? where id=?;" +
                    "commit;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, mp.getDescricao());
            this.preparedStatement.setInt(2, mp.getId());
            this.preparedStatement.setString(3, mp.getCas());
            this.preparedStatement.setString(4, mp.getDcb());
            this.preparedStatement.setString(5, mp.getDci());
            this.preparedStatement.setInt(6, mp.getId());
            this.preparedStatement.execute();

            if (this.preparedStatement.getUpdateCount() > 0) {
                this.status = true;
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean remover(MateriaPrima mp) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "begin;" +
                    "delete from fornecimento WHERE idinsumo=?;" +
                    "delete from materiaprima WHERE id=?;" +
                    "delete from insumo WHERE id=?;" +
                    "commit;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, mp.getId());
            this.preparedStatement.setInt(2, mp.getId());
            this.preparedStatement.setInt(3, mp.getId());
            this.preparedStatement.execute();
            this.status = true;
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = false;
        }
        return this.status;
    }
}
