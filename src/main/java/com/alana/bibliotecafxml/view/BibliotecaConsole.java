package com.alana.bibliotecafxml.view;

import com.alana.bibliotecafxml.controller.BibliotecaController;
import com.alana.bibliotecafxml.model.Emprestimo;
import com.alana.bibliotecafxml.model.Livro;

import java.util.List;
import java.util.Scanner;

public class BibliotecaConsole {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BibliotecaController controller = new BibliotecaController();
        int opcao;

        do {
            System.out.println("\n=== Biblioteca ===");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Listar empréstimos");
            System.out.println("5 - Relatório livros mais emprestados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Título: "); String titulo = sc.nextLine();
                    System.out.print("Autor: "); String autor = sc.nextLine();
                    System.out.print("ISBN: "); String isbn = sc.nextLine();
                    System.out.print("Edição: "); int edicao = sc.nextInt(); sc.nextLine();
                    controller.cadastrarLivro(titulo, autor, isbn, edicao);
                    System.out.println("Livro cadastrado!");
                }
                case 2 -> {
                    List<Livro> livros = controller.getLivros();
                    for (int i = 0; i < livros.size(); i++) {
                        Livro l = livros.get(i);
                        System.out.println(i + " - " + l.getTitulo() + " | " + l.getAutor() +
                                " | ISBN: " + l.getIsbn() + " | Edição: " + l.getEdicao() +
                                " | Emprestimos: " + l.getEmprestimosCount());
                    }
                }
                case 3 -> {
                    List<Livro> livros = controller.getLivros();
                    for (int i = 0; i < livros.size(); i++)
                        System.out.println(i + " - " + livros.get(i).getTitulo());
                    System.out.print("Escolha o índice do livro: "); int idx = sc.nextInt(); sc.nextLine();
                    System.out.print("Nome do usuário: "); String usuario = sc.nextLine();
                    System.out.print("Tipo (aluno/professor): "); String tipo = sc.nextLine();
                    if (controller.emprestarLivro(idx, usuario, tipo)) System.out.println("Livro emprestado!");
                    else System.out.println("Erro: empréstimo duplicado!");
                }
                case 4 -> {
                    List<Emprestimo> emprestimos = controller.getEmprestimos();
                    for (Emprestimo e : emprestimos) {
                        System.out.println(e.getUsuario() + " (" + e.getTipoUsuario() + ") - " +
                                "Livro: " + controller.getLivros().get(e.getIndiceLivro()).getTitulo() +
                                " | Multa: R$" + e.calcularMulta());
                    }
                }
                case 5 -> {
                    List<Livro> livros = controller.getLivros();
                    livros.stream()
                            .sorted((a,b) -> b.getEmprestimosCount() - a.getEmprestimosCount())
                            .forEach(l -> System.out.println(l.getTitulo() + " - Emprestimos: " + l.getEmprestimosCount()));
                }
            }
        } while (opcao != 0);

        sc.close();
    }
}
