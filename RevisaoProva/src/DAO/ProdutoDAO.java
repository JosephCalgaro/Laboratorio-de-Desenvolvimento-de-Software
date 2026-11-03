
package dao;

import Beans.Produto;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final Connection conn;

    public ProdutoDAO() {
        this.conn = new Conexao().getConexao();
    }

    // Inserir produto
    public boolean inserir(Produto p) {
        if (conn == null) return false;

        String sql = "INSERT INTO Produto "
                   + "(Nome, Preco, Quantidade, id_categoria) "
                   + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getCategoriaId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir produto: "
                    + ex.getMessage());
            return false;
        }
    }

    // Consultar produto por ID
    public Produto getProduto(int id) {
        if (conn == null) return null;

        String sql = "SELECT p.id, p.Nome, p.Preco, "
                   + "p.Quantidade, p.id_categoria, "
                   + "c.nome AS nome_categoria "
                   + "FROM Produto p "
                   + "INNER JOIN Categoria c "
                   + "ON p.id_categoria = c.id "
                   + "WHERE p.id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return montar(rs);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto: "
                    + ex.getMessage());
        }

        return null;
    }

    // Listar produtos
    public List<Produto> listar() {
        List<Produto> lista = new ArrayList<>();

        if (conn == null) return lista;

        String sql = "SELECT p.id, p.Nome, p.Preco, "
                   + "p.Quantidade, p.id_categoria, "
                   + "c.nome AS nome_categoria "
                   + "FROM Produto p "
                   + "INNER JOIN Categoria c "
                   + "ON p.id_categoria = c.id "
                   + "ORDER BY p.Nome";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(montar(rs));
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar produtos: "
                    + ex.getMessage());
        }

        return lista;
    }

    // Buscar por nome do produto ou nome da categoria
    public List<Produto> buscar(String pesquisa) {
        List<Produto> lista = new ArrayList<>();

        if (conn == null) return lista;

        String sql = "SELECT p.id, p.Nome, p.Preco, "
                   + "p.Quantidade, p.id_categoria, "
                   + "c.nome AS nome_categoria "
                   + "FROM Produto p "
                   + "INNER JOIN Categoria c "
                   + "ON p.id_categoria = c.id "
                   + "WHERE p.Nome LIKE ? OR c.nome LIKE ? "
                   + "ORDER BY p.Nome";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String termo = "%" + pesquisa + "%";

            stmt.setString(1, termo);
            stmt.setString(2, termo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(montar(rs));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar produtos: "
                    + ex.getMessage());
        }

        return lista;
    }

    // Editar produto, incluindo categoria
    public boolean editar(Produto p) {
        if (conn == null) return false;

        String sql = "UPDATE Produto "
                   + "SET Nome = ?, Preco = ?, "
                   + "Quantidade = ?, id_categoria = ? "
                   + "WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());
            stmt.setInt(4, p.getCategoriaId());
            stmt.setInt(5, p.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar produto: "
                    + ex.getMessage());
            return false;
        }
    }

    // Excluir produto
    public boolean excluir(int id) {
        if (conn == null) return false;

        String sql = "DELETE FROM Produto WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Erro ao excluir produto: "
                    + ex.getMessage());
            return false;
        }
    }

    // Montar Bean
    private Produto montar(ResultSet rs) throws SQLException {
        Produto p = new Produto();

        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("Nome"));
        p.setPreco(rs.getDouble("Preco"));
        p.setQuantidade(rs.getInt("Quantidade"));
        p.setCategoriaId(rs.getInt("id_categoria"));
        p.setNomeCategoria(rs.getString("nome_categoria"));

        return p;
    }
}
