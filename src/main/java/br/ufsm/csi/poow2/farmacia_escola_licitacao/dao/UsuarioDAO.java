package br.ufsm.csi.poow2.farmacia_escola_licitacao.dao;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Usuario;

import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class UsuarioDAO {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Boolean status;

    public Usuario obter(Usuario u) {
        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "select * from usuario where usuario.nome=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, u.getNome());
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                u = new Usuario (
                        this.resultSet.getString("nome"),
                        this.resultSet.getString("senha"),
                        this.resultSet.getString("permissao")
                );
            }
        } catch (SQLException exc) { exc.printStackTrace();}

        return u;
    }

    public ArrayList<Usuario> obterTudo() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from usuario";
            Statement statement = connection.createStatement();
            this.resultSet = statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Usuario u = new Usuario(
                        this.resultSet.getString("nome"),
                        this.resultSet.getString("senha"),
                        this.resultSet.getString("permissao")
                );
                usuarios.add(u);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return usuarios;
    }

    public Boolean inserir(Usuario u) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "insert into usuario(nome, senha, permissao) values(?, ?, ?);";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, u.getNome());
            this.preparedStatement.setString(2, u.getSenha());
            this.preparedStatement.setString(3, u.getPermissao());
            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.status = true;
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean editar(Usuario u) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "update usuario set nome=?, senha=?, permissao=? where nome=?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, u.getNome());
            this.preparedStatement.setString(2, u.getSenha());
            this.preparedStatement.setString(3, u.getPermissao());
            this.preparedStatement.setString(4, u.getNome());
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

    public Boolean remover(Usuario u) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "delete from usuario where nome=?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, u.getNome());
            this.preparedStatement.execute();
            this.status = true;
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = false;
        }
        return this.status;
    }
}
