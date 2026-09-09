/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova.DB;
import prova.model.Livro;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author laboratorio
 */
public class LivroArquivo {
    
    public void salvar(Livro livro){
        
        try{
            FileWriter arquivo = new FileWriter("livros.txt", true);
            
            arquivo.write(
                livro.getTitulo() + ";" +
                livro.getAutor() + ";" +
                livro.getAnoPub() + ";" +
                livro.getTipo() + ";" +
                livro.getCategoria() + ";" +
                livro.getSitLeitura() + "\n");
            
            arquivo.close();
            
        }catch (IOException e){
            System.out.println("Erro ao salvar livro: " + e.getMessage());
        }
    }
    
    public void salvarTodos(ArrayList<Livro> livros){
        try{
            FileWriter arquivo = new FileWriter("livros.txt");
            
            for(Livro livro : livros){
                arquivo.write(
                livro.getTitulo() + ";" +
                livro.getAutor() + ";" +
                livro.getAnoPub() + ";" +
                livro.getTipo() + ";" +
                livro.getCategoria() + ";" +
                livro.getSitLeitura() + "\n");
            }
           
            arquivo.close();
            
        }catch (IOException e){
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }
    
    
    public ArrayList<Livro> carregar(){
        
        ArrayList<Livro> livros = new ArrayList<>();
        
        try{
            BufferedReader arquivo = new BufferedReader(new FileReader("livros.txt"));
            
            String linha;
            
            while((linha = arquivo.readLine()) != null){
                
                String[] dados = linha.split(";");
                
                Livro livro = new Livro(
                dados[0],
                dados[1],
                dados[2],
                dados[3],
                dados[4],
                dados[5]);
                
                livros.add(livro);
            }
            
            arquivo.close();
        }catch (IOException e){
            System.out.println("Erro ao carregar livros: " + e.getMessage());
        }
        
        return livros;
        
    }
    
    
}
