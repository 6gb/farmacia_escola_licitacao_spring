package br.ufsm.csi.poow2.farmacia_escola_licitacao.dao;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Embalagem;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class EmbalagemDAO {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Boolean status;

    public Embalagem obter(Embalagem e) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "select * from insumo as i, embalagem as e where i.id=e.id and e.id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, e.getId());
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                e = new Embalagem(
                        e.getId(),
                        this.resultSet.getString("descricao"),
                        this.resultSet.getString("material"),
                        this.resultSet.getInt("capacidade")
                );
            }
        } catch (SQLException exc) { exc.printStackTrace();}

        return e;
    }

    public ArrayList<Embalagem> obterTudo() {
        ArrayList<Embalagem> embalagens = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from insumo as i, embalagem as e where i.id=e.id";
            Statement statement = connection.createStatement();
            this.resultSet = statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Embalagem e = new Embalagem(
                        this.resultSet.getInt("id"),
                        this.resultSet.getString("descricao"),
                        this.resultSet.getString("material"),
                        this.resultSet.getInt("capacidade")
                );
                embalagens.add(e);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return embalagens;
    }

    public Boolean inserir(Embalagem e) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "with novoinsumo as (insert into insumo(descricao, tipo) values(?, 'em') returning id)" +
                    "insert into embalagem(id, material, capacidade) values ((select id from novoinsumo), ?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, e.getDescricao());
            this.preparedStatement.setString(2, e.getMaterial());
            this.preparedStatement.setInt(3, e.getCapacidade());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.status = true;
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean editar(Embalagem e) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "begin;" +
                    "update insumo set descricao=? where id=?;" +
                    "update embalagem set material=?, capacidade=? where id=?;" +
                    "commit;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, e.getDescricao());
            this.preparedStatement.setInt(2, e.getId());
            this.preparedStatement.setString(3, e.getMaterial());
            this.preparedStatement.setInt(4, e.getCapacidade());
            this.preparedStatement.setInt(5, e.getId());
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

    public Boolean remover(Embalagem e) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "begin;" +
                    "delete from fornecimento WHERE idinsumo=?;" +
                    "delete from embalagem WHERE id=?;" +
                    "delete from insumo WHERE id=?;" +
                    "commit;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, e.getId());
            this.preparedStatement.setInt(2, e.getId());
            this.preparedStatement.setInt(3, e.getId());
            this.preparedStatement.execute();
            this.status = true;
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = false;
        }
        return this.status;
    }
}
