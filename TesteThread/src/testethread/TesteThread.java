/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package testethread;

/**
 *
 * @author laboratorio
 */
public class TesteThread {
    public static void main(String[] args) {
        MinhaThread thread1 = new MinhaThread("Thread 1 - Mensagem a cada 1 seg", 1000);
        MinhaThread thread2 = new MinhaThread("Thread 2 - Mensagem a cada 1 seg", 1000);
    }
    
}
