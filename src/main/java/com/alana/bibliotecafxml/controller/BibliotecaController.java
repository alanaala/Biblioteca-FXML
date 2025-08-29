package com.alana.bibliotecafxml.controller;

import com.alana.bibliotecafxml.model.Emprestimo;
import com.alana.bibliotecafxml.model.Livro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BibliotecaController {
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void cadastrarLivro(String titulo, String autor, String isbn, int edicao) {
        livros.add(new Livro(titulo, autor, isbn, edicao));
    }

    public List<Livro> getLivros() { return livros; }
    public List<Emprestimo> getEmprestimos() { return emprestimos; }

    public Livro getLivroPorIndice(int indice) {
        if (indice >= 0 && indice < livros.size()) return livros.get(indice);
        return null;
    }

    public boolean emprestarLivro(int indiceLivro, String usuario, String tipoUsuario) {
        for (Emprestimo e : emprestimos) {
            if (e.getIndiceLivro() == indiceLivro && e.getUsuario().equalsIgnoreCase(usuario)) {
                return false;
            }
        }
        emprestimos.add(new Emprestimo(indiceLivro, usuario, tipoUsuario, new Date()));
        livros.get(indiceLivro).incrementarEmprestimos();
        return true;
    }

    public void removerLivro(int indice) {
        if (indice < 0 || indice >= livros.size()) return;
        livros.remove(indice);
        emprestimos.removeIf(e -> e.getIndiceLivro() == indice);
    }

    public void removerEmprestimo(int indice) {
        if (indice < 0 || indice >= emprestimos.size()) return;
        Emprestimo e = emprestimos.get(indice);
        livros.get(e.getIndiceLivro()).decrementarEmprestimos();
        emprestimos.remove(indice);
    }

    public List<String> listarLivros() {
        List<String> lista = new ArrayList<>();
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            lista.add(i + " - " + l.getTitulo() + " | " + l.getAutor() + " | ISBN: " +
                    l.getIsbn() + " | Edição: " + l.getEdicao() + " | Emprestimos: " + l.getEmprestimosCount());
        }
        return lista;
    }

    public List<String> listarEmprestimos() {
        List<String> lista = new ArrayList<>();
        for (Emprestimo e : emprestimos) {
            Livro l = livros.get(e.getIndiceLivro());
            lista.add(e.getUsuario() + " (" + e.getTipoUsuario() + ") - Livro: " + l.getTitulo() +
                    " | Multa: R$" + e.calcularMulta());
        }
        return lista;
    }

    public List<String> relatorioLivrosMaisEmprestados() {
        List<Livro> ordenados = new ArrayList<>(livros);
        ordenados.sort(Comparator.comparingInt(Livro::getEmprestimosCount).reversed());
        List<String> lista = new ArrayList<>();
        for (Livro l : ordenados) {
            lista.add(l.getTitulo() + " - Emprestimos: " + l.getEmprestimosCount());
        }
        return lista;
    }
}
