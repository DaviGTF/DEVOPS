import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class FilaDeEsperaUsuario {
    private Queue<Usuario> fila = new LinkedList<>();

    public void adicionarUsuarioFila(Usuario usuario) {
        fila.add(usuario);
        System.out.println("O usuário " + usuario.getNome() + " foi adicionado à lista de espera com sucesso!");
        System.out.println("=====================================================");

    }

    public Iterator<Usuario> iterator() {
        return fila.iterator();
    }

    //metodo buscar usuário
    public Usuario buscarUsuario(int id) {
        for (Usuario usuario : fila) {
            if (usuario.getId() == id) {
                System.out.println("--- USUÁRIO ENCONTRADO ---");
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("ID: " + usuario.getId());
                System.out.println("--------------------------");
                return usuario;
            }
        }
        System.out.println("Ops... O usuário pesquisado não foi encontrado.");
        return null;
    }

    //metodo remover usuario fila
    public void removerUsuarioFila() {
        if (fila.isEmpty()) {
            System.out.println("Operação não pôde ser completada. Não há nenhum usuário na fila de espera.");
        } else {
            Usuario usuarioRemovidoFila = fila.poll();
            System.out.println("O usuário está sendo removido da fila de espera.");
            System.out.println("O usuário " + usuarioRemovidoFila.getNome() + " de ID: " + usuarioRemovidoFila.getId() + " foi removido da fila de espera com sucesso!");
        }
    }

    public void removerPrimeiroUsuario() {
        if (fila.isEmpty()) {
            System.out.println("Ops... A fila está vazia. Nenhum usuário para remover.");
            return;
        }
        Usuario usuarioRemovido = fila.poll();
        System.out.println(
                "O usuário " + usuarioRemovido.getNome() +
                        " (ID: " + usuarioRemovido.getId() +
                        ") foi removido da fila (FIFO)."
        );
    }

    //mostrar usuários
    public void mostrarUsuariosCadastrados() {
        System.out.println("\n=== USUÁRIOS CADASTRADOS ===");
        if (fila.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        int contador = 1;
        for (Usuario usuario : fila) {
            System.out.println("Usuário " + contador + ":");
            usuario.mostrarUsuarios();
            contador++;
        }
    }

    //metodo mostrar fila de espera
    public void mostrarFilaDeEspera() {
        System.out.println("Fila de espera:");
        if (fila.isEmpty()) {
            System.out.println("A lista de espera se encontra vazia.");
        } else {
            for (Usuario usuario : fila) {
                System.out.println("Nome: " + usuario.getNome() + ".");
                System.out.println("Id: " + usuario.getId() + ".");
                System.out.println("==============================");

            }
        }
    }

    //menu fila de espera
    public static class ExibirMenuFilaDeEspera {
        public static void exibir(Livro livroSelecionado, FilaDeEsperaUsuario filaPrincipal, Scanner scanner) {
            FilaDeEsperaUsuario filaDoLivro = livroSelecionado.getFilaEspera();
            int opcao;
            do {
                System.out.println("\n=== FILA DE ESPERA DO LIVRO: " + livroSelecionado.getTitulo() + " ===");
                System.out.println("1. Adicionar Usuário à Fila deste Livro");
                System.out.println("2. Mostrar Fila deste Livro");
                System.out.println("3. Remover Primeiro Usuário da Fila (FIFO)");
                System.out.println("4. Voltar");
                System.out.print("Escolha: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Digite o ID do usuário:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Usuario usuario = filaPrincipal.buscarUsuario(id);
                        if (usuario != null) {
                            filaDoLivro.adicionarUsuarioFila(usuario);
                        }
                        break;
                    case 2:
                        filaDoLivro.mostrarFilaDeEspera();
                        break;
                    case 3:
                        filaDoLivro.removerUsuarioFila();
                        break;
                    case 4:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 4);
        }
    }
}



