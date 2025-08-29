package com.alana.bibliotecafxml;

import com.alana.bibliotecafxml.controller.BibliotecaController;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController();
        Scanner sc = new Scanner(System.in);
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
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Edição: ");
                    int edicao = sc.nextInt();
                    sc.nextLine();
                    controller.cadastrarLivro(titulo, autor, isbn, edicao);
                    System.out.println("Livro cadastrado!");
                }

                case 2 -> {
                    List<String> livros = controller.listarLivros();
                    livros.forEach(System.out::println);
                }

                case 3 -> {
                    List<String> livros = controller.listarLivros();
                    livros.forEach(System.out::println);
                    System.out.print("Escolha o índice do livro: ");
                    int idx = sc.nextInt(); sc.nextLine();
                    System.out.print("Nome do usuário: ");
                    String usuario = sc.nextLine();
                    System.out.print("Tipo (aluno/professor): ");
                    String tipo = sc.nextLine();
                    if (controller.emprestarLivro(idx, usuario, tipo))
                        System.out.println("Livro emprestado!");
                    else System.out.println("Erro: empréstimo duplicado!");
                }

                case 4 -> {
                    List<String> emprestimos = controller.listarEmprestimos();
                    emprestimos.forEach(System.out::println);
                }

                case 5 -> {
                    List<String> relatorio = controller.relatorioLivrosMaisEmprestados();
                    relatorio.forEach(System.out::println);
                }

                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}
