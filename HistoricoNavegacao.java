import java.util.ListIterator;
import java.util.Stack;
import java.util.Iterator;

public class HistoricoNavegacao {
    private Stack<Livro> historicoNavegacaoUsuario = new Stack<>();

    //metodo adiconar livro histórico

    public void adicionarLivroHistorico(Livro livro) {
        historicoNavegacaoUsuario.push(livro);
        System.out.println("O livro " + livro.getTitulo() + " foi adicionado ao histórico de navegação do usuário.");
        System.out.println("============================================");
    }

    //metodo remover livro histórico

    public void removerLivroHistorico() {
        if (historicoNavegacaoUsuario.isEmpty()) {
            System.out.println("Ops... A operação não pode ser completada, o histórico se encontra vazio.");
        } else {
            Livro livroRemovido = historicoNavegacaoUsuario.pop();
            System.out.println("O livro " + livroRemovido.getTitulo() + " foi removido do histórico de navegação do usuário com sucesso!");
            System.out.println("============================================");
        }
    }

    public void removerLivroHistoricoPorTitulo(String titulo) {
        Iterator<Livro> iterator = historicoNavegacaoUsuario.iterator();
        boolean removido = false;
        while(iterator.hasNext()) {
            Livro livro = iterator.next();
            if(livro.getTitulo().equalsIgnoreCase(titulo)) {
                iterator.remove();
                System.out.println("O livro " + livro.getTitulo() + " foi removido do histórico de navegação com sucesso!");
                removido = true;
                break;
            }
        }
        if (!removido) {
            System.out.println("Ops... Não foi encontrado nenhum livro com o título \"" + titulo + "\" no histórico.");
        }
        System.out.println("============================================");
    }

    //metodo exibir historico navegacao
    public void exibirHistoricoNavegacao() {
        if (historicoNavegacaoUsuario.isEmpty()) {
            System.out.println("O histórico não possui dados de navegação.");
        } else {
            ListIterator<Livro> iterator = historicoNavegacaoUsuario.listIterator(historicoNavegacaoUsuario.size());
            while (iterator.hasPrevious()) {
                Livro livro = iterator.previous();
                livro.mostrarLivros();
            }
        }
        System.out.println("============================================");
    }
}