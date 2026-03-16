from historico_navegacao import HistoricoNavegacao

class Usuario:
    def __init__(self, nome, id_usuario):
        self.nome = nome
        self.id = id_usuario
        self.historico = HistoricoNavegacao()

    def mostrar_usuarios(self):
        print("=== DETALHES DO USUÁRIO ===")
        print(f"Nome do usuário: {self.nome}.")
        print(f"ID do usuário: {self.id}.")
        print("====================")

class ExibirMenuUsuarios:
    @staticmethod
    def exibir(fila_principal, scanner_func):
        while True:
            print("\n=== MENU USUÁRIOS ===")
            print("1. Listar Usuários")
            print("2. Adicionar Usuário")
            print("3. Remover Usuário por ID")
            print("4. Voltar ao Menu Principal")
            
            opcao = scanner_func("Escolha uma opção: ")
            
            if opcao == "1":
                for u in fila_principal.fila:
                    u.mostrar_usuarios()
            elif opcao == "2":
                nome = input("Digite o nome do usuário: ")
                id_u = int(input("Digite o ID do usuário: "))
                fila_principal.adicionar_usuario_fila(Usuario(nome, id_u))
            elif opcao == "3":
                id_rem = int(input("Digite o ID do usuário a ser removido: "))
                encontrado = False
                for u in list(fila_principal.fila):
                    if u.id == id_rem:
                        fila_principal.fila.remove(u)
                        print(f"Usuário ID {id_rem} ({u.nome}) removido!")
                        encontrado = True
                        break
                if not encontrado:
                    print(f"Usuário de ID {id_rem} não encontrado!")
            elif opcao == "4":
                break
            else:
                print("Opção inválida!")