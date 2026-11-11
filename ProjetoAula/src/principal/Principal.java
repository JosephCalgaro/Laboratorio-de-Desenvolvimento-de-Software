/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import beans.Pessoa;
import dao.PessoaDAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;



/**
 *
 * @author laboratorio
 */
public class Principal {
    public static void main(String[] args) {
        int porta = 12345;
        
        try (ServerSocket servidorSocket = new ServerSocket (porta)){
            System.out.println("Sevidor aguardando conexões na porta " + porta);
            
            while(true){
                try{
                    Socket clientSocket = servidorSocket.accept();
                    System.out.println("Conexão aceita de "+ clientSocket.getInetAddress());
                    Thread threadCliente = new ThreadServer(clientSocket);
                    threadCliente.start();
                }catch(IOException ex){
                    System.out.println("Erro ao aceitar conexão de cliente");
                }
            }
        }catch (IOException ex){
            System.out.println("Erro ao criar o ServerSocket");
        }
        
    }
    
}
