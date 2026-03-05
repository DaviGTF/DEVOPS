import java.util.Scanner;
import java.util.Iterator;

public class Usuario {
    private String nome;
    private int id;
    private HistoricoNavegacao historico;

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.historico = new HistoricoNavegacao();
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public HistoricoNavegacao getHistorico() {
        return historico;
    }

    //mostrar usuarios
    public void mostrarUsuarios() {
        System.out.println("=== DETALHES DO USUÁRIO ===");
        System.out.println("Nome do usuário: " + nome + ".");
        System.out.println("ID do usuário: " + id + ".");
        System.out.println("====================");
    }

    //exibir menu usuarios
    public static class ExibirMenuUsuarios {
        public static void exibir(FilaDeEsperaUsuario fila, Scanner scanner) {
            int opcao;
            do {
                System.out.println("\n=== MENU USUÁRIOS ===");
                System.out.println("1. Listar Usuários");
                System.out.println("2. Adicionar Usuário");
                System.out.println("3. Remover Usuário por ID");
                System.out.println("4. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        fila.mostrarUsuariosCadastrados();
                        break;
                    case 2:
                        System.out.println("Digite o nome do usuário:");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o ID do usuário:");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        fila.adicionarUsuarioFila(new Usuario(nome, id));
                        break;
                        case 3:
                            System.out.println("Digite o ID do usuário a ser removido:");
                            int idRemover = scanner.nextInt();
                            scanner.nextLine();
                            
                            Iterator<Usuario> iterator = fila.iterator();
                            boolean encontrado = false;
                            while (iterator.hasNext()) {
                                Usuario u = iterator.next();
                                if (u.getId() == idRemover) {
                                    iterator.remove();
                                    System.out.println("Usuário ID " + idRemover + " (" + u.getNome() + ") removido!");
                                    encontrado = true;
                                    break;
                                }
                            }
                            if (!encontrado) {
                                System.out.println("Usuário de ID " + idRemover + " não encontrado!");
                            }
                            break;
                    case 4:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 4);
        }
    }
}