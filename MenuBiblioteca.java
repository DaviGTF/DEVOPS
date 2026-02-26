import java.util.*;

public class MenuBiblioteca {

    private ArvoreBinariaLivros arvoreLivros = new ArvoreBinariaLivros();
    private ListaEncadeadaLivros biblioteca = new ListaEncadeadaLivros(); // Movido para nível de classe

    //menu de inicializacao sistema
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        biblioteca.inicializarGrafoRecomendacao();

        //adicionando livros
        Livro[] livros = {
                new Livro("O Grande Mentecapto", "Fernando Sabino", "Record", "Romance", 1979, 256),
                new Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", "Tipografia Nacional", "Romance", 1881, 160),
                new Livro("Vidas Secas", "Graciliano Ramos", "Via Leitura", "Romance", 2024, 112),

                new Livro("Percy Jackson e os Olimpianos", "Rick Riordan", "Intrínseca", "Fantasia", 2005, 400),
                new Livro("O espadachim de carvão", "Affonso Solano ", "Casa Da Palavra", "Fantasia", 2013, 256),
                new Livro("O Hobbit", " John R.R. Tolkien", "HarperCollins", "Fantasia", 2019, 336),

                new Livro("A Cor que Caiu do Céu", "H.P. Lovecraft", "Pandorga Editora", "Ficção Científica", 1927, 136),
                new Livro("Frankenstein", "Mary Shelley", "Principis", "Ficção Científica", 1818, 240),
                new Livro("Jogador Número 1", "Ernest Cline", "Leya", "Ficção Científica", 2019, 446),

                new Livro("Drácula", "Bram Stoker", "Darkside", "Terror Gótico", 1897, 580),
                new Livro("Carmilla: A Vampira de Karnstein", "Sheridan Le Fanu", "Novo Século", "Terror Gótico", 2022, 160),
                new Livro("O Médico e o Monstro", "Robert Louis Stevenson", "L&PM", "Terror Gótico", 2002, 112),

                new Livro("Dragon Ball Vol. 34", "Akira Toriyama", "Panini", "Mangá", 2015, 256),
                new Livro("JoJo's Bizarre Adventure: Diamond is Unbreakable - Vol. 47", "Hirohiko Araki", "Shueisha", "Mangá", 1996, 192),
                new Livro("Mob Pscyho 100 Vol. 1", "One", "Panini", "Mangá", 2017, 200),

                new Livro("Demolidor: A Queda de Murdock", "Frank Miller", "Panini", "Quadrinhos", 2019, 216),
                new Livro("Lanterna Verde: a Noite Mais Densa", "Geoff Johns", "Panini", "Quadrinhos", 2009, 304),
                new Livro("Homem Aranha: Saga do Clone Vol. 3", "Gerry Conway", "Panini", "Quadrinhos", 2024, 164),
                new Livro("Watchmen", "Alan Moore", "Panini", "Quadrinhos", 1987, 416),
                new Livro("Sandman: Prelúdios & Noturnos", "Neil Gaiman", "Panini", "Quadrinhos", 1989, 448),
                new Livro("V de Vingança", "Alan Moore", "Panini", "Quadrinhos", 1988, 296),
        };

        for (Livro livro : livros) {
            biblioteca.adicionarLivro(livro);
            arvoreLivros.inserir(livro);
        }
        //========================================================================

        //usuarios criados p/ exemplo
        Usuario davi = new Usuario("davi gustavo", 259);
        Usuario ana = new Usuario("ana beatriz", 118);
        Usuario maria = new Usuario("maria do rosario", 210);

        //Inicialização da fila e grafo
        FilaDeEsperaUsuario fila = new FilaDeEsperaUsuario();
        biblioteca.inicializarGrafoRecomendacao();

        // adicionar usuários à fila
        fila.adicionarUsuarioFila(davi);
        fila.adicionarUsuarioFila(ana);
        fila.adicionarUsuarioFila(maria);

        // alimentando historico dos usuarios com livros
        // Davi: 2 livros
        davi.getHistorico().adicionarLivroHistorico(new Livro("O Grande Mentecapto", "Fernando Sabino", "Record", "Romance", 1979, 256));
        davi.getHistorico().adicionarLivroHistorico(new Livro("Drácula", "Bram Stoker", "Darkside", "Terror Gótico", 1897, 580));

        // Ana: 1 livro
        ana.getHistorico().adicionarLivroHistorico(new Livro("Percy Jackson e os Olimpianos", "Rick Riordan", "Intrínseca", "Fantasia", 2005, 400));

        // Maria: 2 livros
        maria.getHistorico().adicionarLivroHistorico(new Livro("Frankenstein", "Mary Shelley", "Principis", "Ficção Científica", 1818, 240));
        maria.getHistorico().adicionarLivroHistorico(new Livro("A Cor que Caiu do Céu", "H.P. Lovecraft", "Pandorga Editora", "Ficção Científica", 1927, 136));

        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Biblioteca");
            System.out.println("2. Fila de Espera");
            System.out.println("3. Histórico de Navegação");
            System.out.println("4. Usuários");
            System.out.println("5. Sugestões por Gênero");
            System.out.println("6. Buscar Livros com Árvore Binária");
            System.out.println("7. Recomendações por Dijkstra");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    ListaEncadeadaLivros.ExibirMenuBiblioteca.exibir(biblioteca, scanner);
                    break;
                case 2:
                    System.out.println("Selecione um livro para gerenciar filas:");
                    Livro livroSelecionado = biblioteca.buscarLivroParaFila(scanner);
                    if (livroSelecionado != null) {
                        FilaDeEsperaUsuario.ExibirMenuFilaDeEspera.exibir(livroSelecionado, fila, scanner);
                    }
                    break;
                case 3:
                    System.out.println("Digite o ID do usuário:");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuario = fila.buscarUsuario(idUsuario);
                    if (usuario != null) {
                        exibirSubmenuHistorico(usuario.getHistorico(), scanner);
                    } else {
                        System.out.println("Usuário não encontrado!");
                    }
                    break;
                case 4:
                    Usuario.ExibirMenuUsuarios.exibir(fila, scanner);
                    scanner.nextLine();  // Limpa buffer extra
                    break;
                case 5:
                    System.out.println("Digite o título do livro base de recomendação:");
                    String titulo = scanner.nextLine();
                    Livro livroBase = biblioteca.buscarLivroPorTitulo(titulo);
                    if (livroBase != null) {
                        biblioteca.mostrarRecomendacoes(livroBase);
                    }
                    break;
                case 6:
                    exibirSubmenuArvore(scanner);
                    break;
                case 7:
                    exibirSubmenuDijkstra(scanner);
                    break;
                case 8:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 8);

        scanner.close();
    }

    //submenu arvore binaria
    private void exibirSubmenuArvore(Scanner scanner) {
        int escolha;
        do {
            System.out.println("\n=== MENU DE BUSCA ÁRVORE BINÁRIA ===");
            System.out.println("1. Ordenar por Título (A-Z)");
            System.out.println("2. Ordenar por Autor (A-Z)");
            System.out.println("3. Ordenar por Gênero");
            System.out.println("4. Ordenar por Ano de Publicação");
            System.out.println("5. Buscar Livro por Título (DFS/BFS)");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    arvoreLivros.setCriterio(ArvoreBinariaLivros.CriterioOrdenacao.TITULO);
                    System.out.println("\nLivros ordenados por título:");
                    arvoreLivros.exibirEmOrdemAgrupado();
                    break;
                case 2:
                    arvoreLivros.setCriterio(ArvoreBinariaLivros.CriterioOrdenacao.AUTOR);
                    System.out.println("\nLivros ordenados por autor:");
                    arvoreLivros.exibirEmOrdemAgrupado();
                    break;
                case 3:
                    arvoreLivros.setCriterio(ArvoreBinariaLivros.CriterioOrdenacao.GENERO);
                    System.out.println("\nLivros ordenados por gênero:");
                    arvoreLivros.exibirEmOrdemAgrupado();
                    break;
                case 4:
                    arvoreLivros.setCriterio(ArvoreBinariaLivros.CriterioOrdenacao.ANO);
                    System.out.println("\nLivros ordenados por ano:");
                    arvoreLivros.exibirEmOrdemAgrupado();
                    break;
                case 5:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    buscarLivroNaArvore(titulo);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (escolha != 6);
    }

    //submenu historico de navegacao
    private void exibirSubmenuHistorico(HistoricoNavegacao historico, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== MENU HISTÓRICO DE NAVEGAÇÃO ===");
            System.out.println("1. Adicionar Livro ao Histórico");
            System.out.println("2. Remover Livro por Título");
            System.out.println("3. Exibir Histórico");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
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

                    Livro livroNovo = new Livro(titulo, autor, editora, genero, anoPublicacao, numeroPaginas);
                    historico.adicionarLivroHistorico(livroNovo);
                    break;
                case 2:
                    System.out.println("Digite o título do livro a ser removido:");
                    String tituloRemover = scanner.nextLine();
                    historico.removerLivroHistoricoPorTitulo(tituloRemover);
                    break;
                case 3:
                    historico.exibirHistoricoNavegacao();
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    //buscador de livro na arvore binaria
    private void buscarLivroNaArvore(String titulo) {
        ArvoreBinariaLivros.No raiz = arvoreLivros.getRaiz();

        // Busca com DFS
        ArrayList<Livro> caminhoDFS = DFS.buscarLivro(raiz, titulo);
        System.out.println("\n=== RESULTADO DFS ===");
        exibirResultadoBusca(caminhoDFS, titulo);

        // Busca com BFS
        ArrayList<Livro> caminhoBFS = BFS.buscarLivro(raiz, titulo);
        System.out.println("\n=== RESULTADO BFS ===");
        exibirResultadoBusca(caminhoBFS, titulo);
    }

    //exibe submenu dijkstra
    private void exibirSubmenuDijkstra(Scanner scanner) {
        System.out.println("\n== RECOMENDAÇÕES POR DIJKSTRA ==");
        System.out.print("Digite o título do livro de referência: ");
        String tituloOrigem = scanner.nextLine();

        Livro origem = null;
        for (Livro l : biblioteca.getBiblioteca()) {
            if (l.getTitulo().equalsIgnoreCase(tituloOrigem)) {
                origem = l;
                break;
            }
        }

        if (origem == null) {
            System.out.println("Livro não encontrado na biblioteca.");
            return;
        }

        Map<Livro, Integer> distancias = dijkstraSimples(
                biblioteca.getGrafoRecomendacao(),
                origem
        );

        System.out.println("\nDistâncias a partir de '" + origem.getTitulo() + "':");
        distancias.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> System.out.println(
                        entry.getValue() + " - " + entry.getKey().getTitulo()
                ));
    }

    private Map<Livro, Integer> dijkstraSimples(
            HashMap<Livro, Set<Livro>> grafo,
            Livro origem
    ) {
        Map<Livro, Integer> distancias = new HashMap<>();
        Queue<Livro> fila = new LinkedList<>();
        distancias.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Livro atual = fila.poll();
            int distanciaAtual = distancias.get(atual);
            for (Livro vizinho : grafo.getOrDefault(atual, new HashSet<>())) {
                if (!distancias.containsKey(vizinho)) {
                    distancias.put(vizinho, distanciaAtual + 1);
                    fila.add(vizinho);
                }
            }
        }
        return distancias;
    }

    //exibe resultados busca
    private void exibirResultadoBusca(ArrayList<Livro> caminho, String tituloAlvo) {
        if (caminho.isEmpty() ||
                !caminho.get(caminho.size() - 1).getTitulo().equalsIgnoreCase(tituloAlvo)) {
            System.out.println("Livro '" + tituloAlvo + "' não encontrado.");
        } else {
            System.out.println("Livro encontrado! Caminho percorrido:");
            for (Livro livro : caminho) {
                System.out.println(" - " + livro.getTitulo());
            }
        }
        System.out.println("======================");
    }
}


