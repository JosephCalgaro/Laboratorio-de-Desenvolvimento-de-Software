/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Beans.Categoria;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josep
 */
public class CategoriaDAO {
    
    private final Connection conn;
    
    public CategoriaDAO(){
        this.conn = new Conexao().getConexao();
    }
    
    public boolean inserir(Categoria c){
        if (conn == null) return false;
        
        String sql = "INSERT INTO categoria (nome) VALUES (?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, c.getNome());
            
            return stmt.executeUpdate() > 0;
            
        }catch (SQLException ex){
            System.out.println("Erro ao inserir categoria: " + ex.getMessage());
            return false;
        }
    }
    
    private Categoria montar(ResultSet rs) throws SQLException{
        Categoria c = new Categoria();
        
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        
        return c;
    }
    
    public Categoria getCategoria(int id){
        if(conn == null) return null;
        
        String sql = "SELECT id, nome FROM categoria WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return montar(rs);
                }
            }
            
        }catch (SQLException ex){
            System.out.println("Erro ao consultar categoria: " + ex.getMessage());
           
        }
        return null;
    }
    
    
    public List<Categoria> listar(){
        List<Categoria> lista = new ArrayList<>();
        
        if(conn == null) return lista;
        
        String sql = "SELECT id, nome FROM categoria ORDER BY nome";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                lista.add(montar(rs));
            }
            
        }catch (SQLException ex){
             System.out.println("Erro ao listar categorias: "
                    + ex.getMessage());
        }
        return lista;
    }
    
    
    public List<Categoria> buscarPorNome(String nome){
        List<Categoria> lista = new ArrayList<>();
        
        if (conn == null) return lista;
        
        String sql = "SELECT id, nome FROM categoria WHERE nome LIKE ? ORDER BY nome";
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, "%" + nome + "%");
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    lista.add(montar(rs));
                }
            }
        }catch(SQLException  ex){
            System.out.println("Erro ao buscar categorias: "
                    + ex.getMessage());
        }
        return lista;
    }
    
    public boolean editar(Categoria c){
        if(conn == null) return false;
        
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getId());
            
            return stmt.executeUpdate() > 0;
            
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar categoria: "
                    + ex.getMessage());
            return false;
        }
    }
    
    public boolean excluir(int id){
        if(conn == null) return false;
        
        String sql = "DELETE FROM categoria WHERE id = ?";
        
        
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            
            return stmt.executeUpdate() > 0;
            
        }catch(SQLException ex){
             System.out.println("Erro ao excluir categoria: "
                    + ex.getMessage());
            return false;
        }
    }
}
