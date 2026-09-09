/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio4.DAO;
import exercicio4.model.Aluno;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author josep
 */
public class AlunoArquivo {
    
    public void salvar(Aluno aluno){
        
        try{
            FileWriter arquivo = new FileWriter("alunos.txt", true);

            arquivo.write(
                aluno.getNome() + ";" +
                aluno.getDataNascimento() + ";" +
                aluno.getSexo() + ";" +
                aluno.getMatricula() + ";" +
                aluno.getCurso() + ";" +
                aluno.getCpf() + ";" +
                aluno.getEndereco() + ";" +
                aluno.getTelefone() + "\n"
            );

            arquivo.close();
        }catch (IOException e){
            System.out.println("Erro ao salvar aluno: " + e.getMessage());
        }
    }
    
    
    public void salvarTodos(ArrayList<Aluno> alunos) {

    try {

        FileWriter arquivo = new FileWriter("alunos.txt");

        for (Aluno aluno : alunos) {

            arquivo.write(
                aluno.getNome() + ";" +
                aluno.getDataNascimento() + ";" +
                aluno.getSexo() + ";" +
                aluno.getMatricula() + ";" +
                aluno.getCurso() + ";" +
                aluno.getCpf() + ";" +
                aluno.getEndereco() + ";" +
                aluno.getTelefone() + "\n"
            );
        }

        arquivo.close();

    } catch (IOException e) {
        System.out.println("Erro ao salvar alunos: " + e.getMessage());
    }
}
    
    public ArrayList<Aluno> carregar() {

        ArrayList<Aluno> alunos = new ArrayList<>();
        
            System.out.println("ENTROU NO CARREGAR");
        
        try{
            
            System.out.println("Tentando abrir arquivo...");
            
            BufferedReader arquivo = new BufferedReader(new FileReader("alunos.txt"));
            
            System.out.println("Arquivo encontrado!");
            
            String linha;
            
            while((linha = arquivo.readLine()) != null){
                
                String[] dados = linha.split(";");

                Aluno aluno = new Aluno(
                    dados[0],
                    dados[1],
                    dados[2],
                    dados[3],
                    dados[4],
                    dados[5],
                    dados[6],
                    dados[7]
                );

                alunos.add(aluno);
            }
            arquivo.close();
        } catch(IOException e){
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }
        
        return alunos;
    }
}
