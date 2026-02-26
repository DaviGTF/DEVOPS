import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static ArrayList<Livro> buscarLivro(ArvoreBinariaLivros.No raiz, String tituloAlvo) {
        ArrayList<Livro> caminho = new ArrayList<>();
        Queue<ArvoreBinariaLivros.No> fila = new LinkedList<>();

        if (raiz == null) return caminho;

        fila.add(raiz);

        while (!fila.isEmpty()) {
            ArvoreBinariaLivros.No no = fila.poll();
            caminho.add(no.getLivro());

            if (no.getLivro().getTitulo().equalsIgnoreCase(tituloAlvo)) {
                return caminho;
            }

            // Acesso p/ subarvores pelos getters
            if (no.getEsquerda() != null) fila.add(no.getEsquerda());
            if (no.getDireita() != null) fila.add(no.getDireita());
        }

        return caminho;
    }
}