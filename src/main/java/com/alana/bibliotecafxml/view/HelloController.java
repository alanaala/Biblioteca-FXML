package com.alana.bibliotecafxml.view;

import com.alana.bibliotecafxml.controller.BibliotecaController;
import com.alana.bibliotecafxml.model.Emprestimo;
import com.alana.bibliotecafxml.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    private BibliotecaController controller = new BibliotecaController();

    @FXML private TableView<Livro> tableLivros;
    @FXML private TableColumn<Livro, String> colTitulo;
    @FXML private TableColumn<Livro, String> colAutor;
    @FXML private TableColumn<Livro, String> colIsbn;
    @FXML private TableColumn<Livro, Integer> colEdicao;
    @FXML private TableColumn<Livro, Integer> colEmprestimos;

    @FXML private ListView<String> listEmprestimos;

    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtIsbn;
    @FXML private TextField txtEdicao;

    private ObservableList<Livro> livrosObservable;

    @FXML
    public void initialize() {
        colTitulo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitulo()));
        colAutor.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAutor()));
        colIsbn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIsbn()));
        colEdicao.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getEdicao()).asObject());
        colEmprestimos.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getEmprestimosCount()).asObject());

        livrosObservable = FXCollections.observableArrayList(controller.getLivros());
        tableLivros.setItems(livrosObservable);

        atualizarEmprestimos();
    }

    @FXML
    protected void onCadastrarLivro() {
        try {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String isbn = txtIsbn.getText();
            int edicao = Integer.parseInt(txtEdicao.getText());

            controller.cadastrarLivro(titulo, autor, isbn, edicao);
            livrosObservable.setAll(controller.getLivros());

            txtTitulo.clear();
            txtAutor.clear();
            txtIsbn.clear();
            txtEdicao.clear();

        } catch (Exception e) {
            mostrarAlerta("Erro", "Verifique os dados do livro.");
        }
    }

    @FXML
    protected void onEmprestarLivro() {
        Livro livroSelecionado = tableLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado == null) {
            mostrarAlerta("Atenção", "Selecione um livro para emprestar.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Emprestar Livro");
        dialog.setHeaderText("Informe o nome do usuário e o tipo (aluno/professor)");
        dialog.setContentText("Formato: nome,tipo");

        dialog.showAndWait().ifPresent(input -> {
            String[] dados = input.split(",");
            if (dados.length == 2) {
                controller.emprestarLivro(controller.getLivros().indexOf(livroSelecionado), dados[0].trim(), dados[1].trim());
                atualizarEmprestimos();
                tableLivros.refresh();
            } else {
                mostrarAlerta("Erro", "Formato inválido. Use: nome,tipo");
            }
        });
    }
    @FXML
    protected void onRemoverLivro() {
        Livro livroSelecionado = tableLivros.getSelectionModel().getSelectedItem();
        if (livroSelecionado == null) {
            mostrarAlerta("Atenção", "Selecione um livro para remover.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirmar remoção");
        confirm.setHeaderText(null);
        confirm.setContentText("Deseja realmente remover o livro: " + livroSelecionado.getTitulo() + "?");

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                int indiceLivro = controller.getLivros().indexOf(livroSelecionado);

                controller.getEmprestimos().removeIf(e -> e.getIndiceLivro() == indiceLivro);
                controller.getLivros().remove(livroSelecionado);
                livrosObservable.setAll(controller.getLivros());
                atualizarEmprestimos();
            }
        });
    }



    private void atualizarEmprestimos() {
        listEmprestimos.getItems().clear();
        for (Emprestimo e : controller.getEmprestimos()) {
            listEmprestimos.getItems().add(e.toString());
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
