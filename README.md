#  Sistema de Biblioteca Escolar

Este projeto é um sistema de gerenciamento de biblioteca escolar desenvolvido em **Java (console)**.  
Ele permite o cadastro de livros, controle de empréstimos, cálculo de multas por atraso e geração de relatórios gerenciais.

---

##  Funcionalidades

-  **Cadastro de Livros**  
  - Agora é obrigatório informar **ISBN** e **Edição**.

-  **Controle de Empréstimos**  
  - Impede que o mesmo aluno/professor pegue o mesmo livro mais de uma vez sem devolver.  
  - Diferencia prazos de devolução:
    - **Alunos** → 7 dias  
    - **Professores** → 15 dias  

-  **Multas Automáticas**  
  - Multa de **R$2,00 por dia de atraso** calculada automaticamente.

-  **Relatórios**  
  - Lista dos livros mais emprestados no mês.  

---

##  Tecnologias Utilizadas

- **Java 17+**
- **Paradigma: Programação Orientada a Objetos (POO)**
- **Execução: Console (CLI)**

---

##  Estrutura do Projeto

└── com.alana.biblioteca/
├── model/ # Classes Livro e Emprestimo
├── controller/ # Lógica de negócio (BibliotecaController)
├── view/ # Interface em Console
└── Main.java # Classe principal


---

## 🚀 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/alanaala/Biblioteca-FXML.git

2. Compile o projeto:

javac -d bin src/com/alana/biblioteca/**/*.java


3. Execute a aplicação:

java -cp bin com.alana.biblioteca.Main


## Tipos de Manutenção Implementadas

Corretiva → Correção da duplicidade de empréstimos.

Adaptativa → Inclusão de ISBN e edição no cadastro de livros.

Evolutiva → Implementação de prazos diferenciados e cálculo de multas.

Perfectiva → Criação do relatório de livros mais emprestados.

 
## Licença

Este projeto foi desenvolvido para fins educacionais e não possui restrições de uso.

