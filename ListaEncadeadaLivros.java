import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;

public class ListaEncadeadaLivros {
    private LinkedList<Livro> biblioteca;

    public ListaEncadeadaLivros() {
        this.biblioteca = new LinkedList<>();
    }

    public LinkedList<Livro> getBiblioteca() {
        return biblioteca;
    }

    private GrafoRecomendacao grafoRecomendacao = new GrafoRecomendacao();

    //metodo adiciona livro
    public void adicionarLivro(Livro livro) {
        biblioteca.add(livro);
        grafoRecomendacao.adicionarLivro(livro);
        grafoRecomendacao.conectarPorGenero(livro);
        grafoRecomendacao.conectarPorAutor(livro);
        grafoRecomendacao.conectarPorEditora(livro);
        grafoRecomendacao.conectarPorAno(livro);
    }
    public void adicionarLivro(Scanner scanner) {
        System.out.println("Digite o título do livro:");
        String titulo = scanner.nextLine();
        System.out.println("Digite o autor do livro:");
        String autor = scanner.nextLine();
        System.out.println("Digite a editora do livro:");
        String editora = scanner.nextLine();
        System.out.println("Digite o gênero do livro:");
        String genero = scanner.nextLine();
        System.out.println("Digite o ano de publicação:");
        int anoPublicacao = scanner.nextInt();
        System.out.println("Digite o número de páginas:");
        int numeroPaginas = scanner.nextInt();
        scanner.nextLine();

        biblioteca.add(new Livro(titulo, autor, editora, genero, anoPublicacao, numeroPaginas));
        System.out.println("O livro foi adicionado à biblioteca com sucesso!");
    }

    public void inicializarGrafoRecomendacao() {
        for (Livro livro : biblioteca) {
            grafoRecomendacao.adicionarLivro(livro);
            grafoRecomendacao.conectarPorGenero(livro);
            grafoRecomendacao.conectarPorAutor(livro);
            grafoRecomendacao.conectarPorEditora(livro);
            grafoRecomendacao.conectarPorAno(livro);
        }
    }

    //metodo busca livro
    public void mostrarLivros() {
        if (biblioteca.isEmpty()) {
            System.out.println("Eita! Parece que a biblioteca se encontra vazia.");
            return;
        }
        for (Livro livro : biblioteca) {
            livro.mostrarLivros();
        }
    }

    //metodo busca livro
    public Livro buscarLivro(Scanner scanner) {
        System.out.println("Digite o título do livro que deseja buscar:");
        String titulo = scanner.nextLine();
        for (Livro livro : biblioteca) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("=== LIVRO ENCONTRADO ===");
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Gênero: " + livro.getGenero());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("Número de Páginas: " + livro.getNumeroPaginas());
                System.out.println("=======================");
                return livro;
            }
        }
        System.out.println("Livro não encontrado: " + titulo);
        return null;
    }

    //metodo remover livro
    public void removerLivro(Scanner scanner) {
        System.out.println("Digite o título do livro que deseja remover:");
        String titulo = scanner.nextLine();
        boolean removido = biblioteca.removeIf(livro -> livro.getTitulo().equalsIgnoreCase(titulo));
        if (removido) {
            System.out.println("O livro \"" + titulo + "\" foi removido da biblioteca com sucesso.");
        } else {
            System.out.println("O livro \"" + titulo + "\" não foi encontrado na biblioteca.");
        }
    }

    //livro pra ter fila gerenciada
    public Livro buscarLivroParaFila(Scanner scanner) {
        System.out.println("Digite o título do livro para gerenciar a fila:");
        String titulo = scanner.nextLine();
        for (Livro livro : biblioteca) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        System.out.println("Livro não encontrado!");
        return null;
    }

    //busca livro por titulo
    public Livro buscarLivroPorTitulo(String titulo) {
        for (Livro livro : biblioteca) {
            if (livro.getTitulo().equalsIgnoreCase(titulo.trim())) {
                return livro;
            }
        }
        System.out.println("Livro não encontrado: " + titulo);
        return null;
    }


    //acesso grafo para Dijkstra
    public HashMap<Livro, Set<Livro>> getGrafoRecomendacao() {
        // converte Map genérico de GrafoRecomendacao
        return new HashMap<>(grafoRecomendacao.getGrafo());
    }

    //metodo  mostrar recomendacoes
    public void mostrarRecomendacoes(Livro livroBase) {
        Set<Livro> recomendacoes = grafoRecomendacao.getRecomendacoes(livroBase);

        System.out.println("\n=== LIVROS RECOMENDADOS (" + livroBase.getGenero() + ") ===");
        if (recomendacoes.isEmpty()) {
            System.out.println("Nenhuma recomendação encontrada para este gênero.");
            return;
        }

        for (Livro livro : recomendacoes) {
            livro.mostrarLivros();
        }
    }

    //exibir menu biblioteca
    public static class ExibirMenuBiblioteca {
        public static void exibir(ListaEncadeadaLivros biblioteca, Scanner scanner) {
            int opcao;
            do {
                System.out.println("\n=== MENU BIBLIOTECA ===");
                System.out.println("1. Adicionar Livro");
                System.out.println("2. Mostrar Livros");
                System.out.println("3. Buscar Livro");
                System.out.println("4. Remover Livro");
                System.out.println("5. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        biblioteca.adicionarLivro(scanner);
                        break;
                    case 2:
                        biblioteca.mostrarLivros();
                        break;
                    case 3:
                        biblioteca.buscarLivro(scanner);
                        break;
                    case 4:
                        biblioteca.removerLivro(scanner);  // Já pede o título internamente
                        break;
                    case 5:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 5);
        }
    }
}