/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prova.model;

/**
 *
 * @author laboratorio
 */
public class Livro {
    
    String titulo;
    String autor;
    String anoPub;
    String tipo;
    String categoria;
    String sitLeitura;
    
    

    public Livro(String titulo, String autor, String anoPub, String tipo, String categoria, String sitLeitura) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPub = anoPub;
        this.tipo = tipo;
        this.categoria = categoria;
        this.sitLeitura = sitLeitura;
    }

    
    
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(String anoPub) {
        this.anoPub = anoPub;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSitLeitura() {
        return sitLeitura;
    }

    public void setSitLeitura(String sitLeitura) {
        this.sitLeitura = sitLeitura;
    }
            
            
    
}
