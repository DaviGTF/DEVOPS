import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

public class GrafoRecomendacao {
    private HashMap<Livro, Set<Livro>> grafo = new HashMap<>();

    // Adiciona livro ao grafo
    public void adicionarLivro(Livro livro) {
        grafo.putIfAbsent(livro, new HashSet<>());
    }

    // conecta livros mesmo genero
    public void conectarPorGenero(Livro novoLivro) {
        for (Livro livroExistente : grafo.keySet()) {
            if (!livroExistente.equals(novoLivro) &&
                    livroExistente.getGenero().equalsIgnoreCase(novoLivro.getGenero())) {
                grafo.get(novoLivro).add(livroExistente);
                grafo.get(livroExistente).add(novoLivro);
            }
        }
    }

    // conecta livros mesmo autor
    public void conectarPorAutor(Livro novoLivro) {
        for (Livro livroExistente : grafo.keySet()) {
            if (!livroExistente.equals(novoLivro) &&
                    livroExistente.getAutor().equalsIgnoreCase(novoLivro.getAutor())) {
                grafo.get(novoLivro).add(livroExistente);
                grafo.get(livroExistente).add(novoLivro);
            }
        }
    }

    // conecta livros mesma editora
    public void conectarPorEditora(Livro novoLivro) {
        for (Livro livroExistente : grafo.keySet()) {
            if (!livroExistente.equals(novoLivro) &&
                    livroExistente.getEditora().equalsIgnoreCase(novoLivro.getEditora())) {
                grafo.get(novoLivro).add(livroExistente);
                grafo.get(livroExistente).add(novoLivro);
            }
        }
    }

    // conecta livros mesmo ano
    public void conectarPorAno(Livro novoLivro) {
        for (Livro livroExistente : grafo.keySet()) {
            if (!livroExistente.equals(novoLivro) &&
                    livroExistente.getAnoPublicacao() == novoLivro.getAnoPublicacao()) {
                grafo.get(novoLivro).add(livroExistente);
                grafo.get(livroExistente).add(novoLivro);
            }
        }
    }

    // retorna recomendacoes
    public Set<Livro> getRecomendacoes(Livro livro) {
        return grafo.getOrDefault(livro, new HashSet<>());
    }

    // expor grafo interno
    public Map<Livro, Set<Livro>> getGrafo() {
        return grafo;
    }
}
