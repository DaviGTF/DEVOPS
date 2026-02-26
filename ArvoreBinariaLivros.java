import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class ArvoreBinariaLivros {

    public enum CriterioOrdenacao {
        TITULO, AUTOR, GENERO, ANO
    }

    private No raiz;
    private CriterioOrdenacao criterio = CriterioOrdenacao.TITULO;

    public No getRaiz() {
        return raiz;
    }

    public static class No {
        private Livro livro;
        private No esquerda;
        private No direita;

        public No(Livro livro) {
            this.livro = livro;
        }

        public Livro getLivro() {
            return livro;
        }

        public No getEsquerda() {
            return esquerda;
        }

        public No getDireita() {
            return direita;
        }

    }

    public void setCriterio(CriterioOrdenacao criterio) {
        ArrayList<Livro> livros = new ArrayList<>();
        emOrdem(raiz, livros);
        this.criterio = criterio;
        raiz = null;
        for (Livro livro : livros) {
            inserir(livro);
        }
    }

    public void inserir(Livro livro) {
        raiz = inserirRec(raiz, livro);
    }

    private No inserirRec(No atual, Livro livro) {
        if (atual == null) return new No(livro);

        int comparacao = comparar(livro, atual.livro);
        if (comparacao < 0) {
            atual.esquerda = inserirRec(atual.esquerda, livro);
        } else {
            atual.direita = inserirRec(atual.direita, livro);
        }
        return atual;
    }

    // exibir ordenado pelo critério atual
    public void exibirEmOrdemAgrupado() {
        System.out.println("Livros ordenados por " + criterio + ":");
        ArrayList<Livro> lista = new ArrayList<>();
        emOrdem(raiz, lista);

        for (Livro livro : lista) {
            System.out.println(livro.getTitulo() + " - " + livro.getAutor() + " - " + livro.getGenero() + " - " + livro.getAnoPublicacao());
            System.out.println("===============");
        }
    }

    private void emOrdem(No no, ArrayList<Livro> lista) {
        if (no != null) {
            emOrdem(no.esquerda, lista);
            lista.add(no.livro);
            emOrdem(no.direita, lista);
        }
    }

    private int comparar(Livro l1, Livro l2) {
        switch (criterio) {
            case TITULO:
                return l1.getTitulo().compareToIgnoreCase(l2.getTitulo());
            case AUTOR:
                return l1.getAutor().compareToIgnoreCase(l2.getAutor());
            case GENERO:
                return l1.getGenero().compareToIgnoreCase(l2.getGenero());
            case ANO:
                return Integer.compare(l1.getAnoPublicacao(), l2.getAnoPublicacao());
            default:
                return 0;
        }
    }


    //novos metodos busca
    //busca dfs
    public List<Livro> buscarDFS(String tituloAlvo) {
        List<Livro> visitados = new ArrayList<>();
        buscarDFSRec(raiz, tituloAlvo, visitados);
        return visitados;
    }

    private boolean buscarDFSRec(No node, String tituloAlvo, List<Livro> visitados) {
        if (node == null) {
            return false;
        }
        // visita este nó
        visitados.add(node.livro);
        // se é o alvo, pare
        if (node.livro.getTitulo().equalsIgnoreCase(tituloAlvo)) {
            return true;
        }
        // tenta esquerda
        if (buscarDFSRec(node.esquerda, tituloAlvo, visitados)) {
            return true;
        }
        // tenta direita
        if (buscarDFSRec(node.direita, tituloAlvo, visitados)) {
            return true;
        }
        return false;
    }

    //busca bfs
    public List<Livro> buscarBFS(String tituloAlvo) {
        List<Livro> visitados = new ArrayList<>();
        if (raiz == null) {
            return visitados;
        }
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No node = fila.poll();
            visitados.add(node.livro);
            if (node.livro.getTitulo().equalsIgnoreCase(tituloAlvo)) {
                break;
            }
            if (node.esquerda != null) {
                fila.add(node.esquerda);
            }
            if (node.direita != null) {
                fila.add(node.direita);
            }
        }
        return visitados;
    }
}

