package com.alana.bibliotecafxml.model;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private int edicao;
    private int emprestimosCount;

    public Livro(String titulo, String autor, String isbn, int edicao) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.edicao = edicao;
        this.emprestimosCount = 0;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public int getEdicao() { return edicao; }
    public int getEmprestimosCount() { return emprestimosCount; }

    public void incrementarEmprestimos() { emprestimosCount++; }
    public void decrementarEmprestimos() { if (emprestimosCount > 0) emprestimosCount--; }
}
