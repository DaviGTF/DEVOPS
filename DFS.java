import java.util.ArrayList;

public class DFS {
    public static ArrayList<Livro> buscarLivro(ArvoreBinariaLivros.No no, String tituloAlvo) {
        ArrayList<Livro> caminho = new ArrayList<>();
        buscarRecursivo(no, tituloAlvo, caminho);
        return caminho;
    }

    private static boolean buscarRecursivo(ArvoreBinariaLivros.No no, String tituloAlvo, ArrayList<Livro> caminho) {
        if (no == null) return false;


        caminho.add(no.getLivro());

        if (no.getLivro().getTitulo().equalsIgnoreCase(tituloAlvo)) {
            return true;
        }

        if (buscarRecursivo(no.getEsquerda(), tituloAlvo, caminho) ||
                buscarRecursivo(no.getDireita(), tituloAlvo, caminho)) {
            return true;
        }

        caminho.remove(caminho.size() - 1);
        return false;
    }
}