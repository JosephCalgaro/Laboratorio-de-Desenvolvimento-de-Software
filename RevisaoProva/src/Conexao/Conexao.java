package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexao {

    public Connection getConexao(){
        return getConexao("loja");
    }

    public Connection getConexao(String banco){
        Connection conn;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + banco + "?"
                            + "userTimeZone=true&serverTimeZone=UTC",
                    "root","Joecal300422@@");
            System.out.println("Conexão efetuada!");
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar no BD"+e.getMessage());
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
        return  null;
    }
    
}
