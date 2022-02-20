package br.ufsm.csi.poow2.farmacia_escola_licitacao.dao;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Fornecedor;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Insumo;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Licitacao;
import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Fornecimento;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;

@Repository
public class LicitacaoDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Boolean status;

    public Licitacao obter(Licitacao l) {
        ArrayList<Fornecimento> fs = new ArrayList<>();
        Fornecimento f = new Fornecimento();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql = "select distinct datacriacao, idlicitacao, idfornecedor, nome, idinsumo, descricao, tipo, " +
                    "preco, avg(preco) over (partition by idinsumo) " +
                    "from licitacao as l, fornecimento as fm, fornecedor as fd, insumo as i, " +
                    "embalagem as e, materiaprima as mp " +
                    "where l.id=? and l.id=fm.idlicitacao and fm.idfornecedor=fd.id and fm.idinsumo=i.id " +
                    "and (i.id=e.id or i.id=mp.id)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, l.getId());
            this.resultSet = this.preparedStatement.executeQuery();
            while (this.resultSet.next()) {
                f = new Fornecimento(
                        new Licitacao(
                                this.resultSet.getInt("idlicitacao"),
                                this.resultSet.getDate("datacriacao")
                        ),
                        new Fornecedor(
                                this.resultSet.getInt("idfornecedor"),
                                this.resultSet.getString("nome")
                        ),
                        new Insumo(
                                this.resultSet.getInt("idinsumo"),
                                this.resultSet.getString("descricao"),
                                this.resultSet.getString("tipo")
                        ),
                        this.resultSet.getDouble("preco"),
                        this.resultSet.getDouble("avg")
                );
                fs.add(f);
            }
            l = new Licitacao(
                    f.getLicitacao().getId(),
                    f.getLicitacao().getDataCriacao(),
                    fs
            );
        } catch (SQLException e) { e.printStackTrace();}

        return l;
    }

    public ArrayList<Licitacao> obterTudo() {
        ArrayList<Licitacao> licitacoes = new ArrayList<>();

        try (Connection connection = new ConectaDB().getConexao()) {
            this.sql =  "select * from licitacao";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()) {
                Licitacao l = new Licitacao(
                        this.resultSet.getInt("id"),
                        this.resultSet.getDate("dataCriacao")
                );
                licitacoes.add(l);
            }
        } catch (SQLException e) { e.printStackTrace();}

        return licitacoes;
    }

    public Boolean inserir() {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "insert into licitacao(datacriacao) values (CURRENT_DATE)";
            this.statement = connection.createStatement();
            this.statement.execute(sql);
            this.status = true;
        } catch (SQLException exc) {
            exc.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean remover(Licitacao l) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "begin;" +
                    "delete from fornecimento WHERE idlicitacao=?;" +
                    "delete from licitacao WHERE id=?;" +
                    "commit;";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, l.getId());
            this.preparedStatement.setInt(2, l.getId());
            this.preparedStatement.execute();
            this.status = true;
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean inserirFornecimento(Fornecimento f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "insert into fornecimento(idlicitacao, idfornecedor, idinsumo, preco) values (?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, f.getLicitacao().getId());
            this.preparedStatement.setInt(2, f.getFornecedor().getId());
            this.preparedStatement.setInt(3, f.getInsumo().getId());
            this.preparedStatement.setDouble(4, f.getPreco());
            this.resultSet = this.preparedStatement.executeQuery();
            this.status = true;
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean editarFornecimento(Fornecimento f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql =
                    "update fornecimento set preco=? " +
                            "where idlicitacao=? and idfornecedor=? and idinsumo=?";

            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setDouble(1, f.getPreco());
            this.preparedStatement.setInt(2, f.getLicitacao().getId());
            this.preparedStatement.setInt(3, f.getFornecedor().getId());
            this.preparedStatement.setInt(4, f.getInsumo().getId());
            this.resultSet = this.preparedStatement.executeQuery();
            this.status = true;
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = false;
        }
        return this.status;
    }

    public Boolean removerFornecimento(Fornecimento f) {
        try(Connection connection = new ConectaDB().getConexao()) {
            this.sql = "delete from fornecimento where idlicitacao=? and idfornecedor=? and idinsumo=?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, f.getLicitacao().getId());
            this.preparedStatement.setInt(2, f.getFornecedor().getId());
            this.preparedStatement.setInt(3, f.getInsumo().getId());
            this.resultSet = this.preparedStatement.executeQuery();
            this.status = true;
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = false;
        }
        return this.status;
    }
}
