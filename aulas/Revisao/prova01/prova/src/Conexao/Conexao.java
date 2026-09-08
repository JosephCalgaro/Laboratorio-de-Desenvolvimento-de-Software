/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexao {
    public Connection getConexao(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/veiculos?useTimezone=true$serverTimezone=UTC","root","laboratorio");
            System.out.println("Conexão realizada com sucesso!!!!!");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar com o banco de dados"+ex.getMessage());
            return null;
        }
        
    }
    
}
