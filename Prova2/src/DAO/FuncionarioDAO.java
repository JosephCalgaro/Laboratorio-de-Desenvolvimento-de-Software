/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Beans.Funcionario;
import conexao.Conexao;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author laboratorio
 */
public class FuncionarioDAO {
    
    private final Connection conn;
    
    public FuncionarioDAO(){
        this.conn = new Conexao().getConexao();  
    }
    
    public boolean inserir(Funcionario f){
        if(conn == null) return false;
        
        String sql = "INSERT INTO funcionarios (nome, idade, genero, cargo, salario) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, f.getNome());
            stmt.setInt(2, f.getIdade());
            stmt.setString(3, f.getGenero());
            stmt.setString(4, f.getCargo());
            stmt.setDouble(5, f.getSalario());
            
            return stmt.executeUpdate() > 0;
            
        }catch(SQLException ex){
            System.out.println("Erro ao inserir funcionario: "
                    + ex.getMessage());
            return false;
        }
    }
    
    public List<Funcionario> listar(){
        List<Funcionario> lista = new ArrayList<>();
        
        String sql = "SELECT f.id, f.nome, f.idade, f.genero, f.cargo, f.salario FROM funcionarios AS f ORDER BY f.nome";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while(rs.next()){
                lista.add(montar(rs));
            }
            
        }catch (SQLException ex){
            System.out.println("Erro ao listar produtos: "
                    + ex.getMessage());
        }
        
        return lista;
    }
    
    private Funcionario montar(ResultSet rs) throws SQLException{
        Funcionario f = new Funcionario();
        
        f.setId(rs.getInt("id"));
        f.setNome(rs.getString("Nome"));
        f.setIdade(rs.getInt("idade"));
        f.setGenero(rs.getString("genero"));
        f.setCargo(rs.getString("cargo"));
        f.setSalario(rs.getDouble("salario"));
        
        return f;
    }
    
}
