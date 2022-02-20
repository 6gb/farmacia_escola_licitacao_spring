package br.ufsm.csi.poow2.farmacia_escola_licitacao.dao;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Fornecedor;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class FornecedorDAO {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Boolean status;

    public Fornecedor obter(Fornecedor f) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "select * from fornecedor where id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, f.getId());
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()) {
                f = new Fornecedor(
                        this.resultSet.getInt("id"),
                        this.resultSet.getString("cnpj"),
                        this.resultSet.getString("nome"),
                        this.resultSet.getString("endereco"),
                        this.resultSet.getString("telefone"),
                        this.resultSet.getString("email"),
                        this.resultSet.getString("sicaf")
                );
            }
        } catch (SQLException e) { e.printStackTrace();}

        return f;
    }

    public ArrayList<Fornecedor> obterTudo() {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "select * from fornecedor";
            Statement statement = connection.createStatement();
            this.resultSet = statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Fornecedor f = new Fornecedor(
                        this.resultSet.getInt("id"),
                        this.resultSet.getString("cnpj"),
                        this.resultSet.getString("nome"),
                        this.resultSet.getString("endereco"),
                        this.resultSet.getString("telefone"),
                        this.resultSet.getString("email"),
                        this.resultSet.getString("sicaf")
                );
                fornecedores.add(f);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return fornecedores;
    }

    public Boolean inserir(Fornecedor f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "insert into fornecedor(cnpj, nome, endereco, telefone, email, sicaf)" +
                    "values (?, ?, ?, ?, ?, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, f.getCnpj());
            this.preparedStatement.setString(2, f.getNome());
            this.preparedStatement.setString(3, f.getEndereco());
            this.preparedStatement.setString(4, f.getTelefone());
            this.preparedStatement.setString(5, f.getEmail());
            this.preparedStatement.setString(6, f.getSicaf());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.status = true;
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean editar(Fornecedor f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "update fornecedor set cnpj=?, nome=?, endereco=?, telefone=?, email=?, sicaf=? where id=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, f.getCnpj());
            this.preparedStatement.setString(2, f.getNome());
            this.preparedStatement.setString(3, f.getEndereco());
            this.preparedStatement.setString(4, f.getTelefone());
            this.preparedStatement.setString(5, f.getEmail());
            this.preparedStatement.setString(6, f.getSicaf());
            this.preparedStatement.setInt(7, f.getId());
            this.preparedStatement.execute();

            if (this.preparedStatement.getUpdateCount() > 0) {
                this.status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean remover(Fornecedor f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "begin;" +
                    "delete from fornecimento WHERE idfornecedor=?;" +
                    "delete from fornecedor WHERE id=?;" +
                    "commit;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, f.getId());
            this.preparedStatement.setInt(2, f.getId());
            this.preparedStatement.execute();
            this.status = true;
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = false;
        }
        return this.status;
    }
}
