class HistoricoNavegacao:
    def __init__(self):
        self.historico = []  # Usamos lista como Pilha (LIFO)

    def adicionar_livro_historico(self, livro):
        self.historico.append(livro)
        print(f"O livro {livro.titulo} foi adicionado ao histórico.")
        print("============================================")

    def remover_livro_historico(self):
        if not self.historico:
            print("Ops... O histórico está vazio.")
        else:
            livro = self.historico.pop()
            print(f"O livro {livro.titulo} foi removido do histórico.")
        print("============================================")

    def remover_livro_por_titulo(self, titulo):
        for i, livro in enumerate(self.historico):
            if livro.titulo.lower() == titulo.lower():
                del self.historico[i]
                print(f"Livro {titulo} removido do histórico.")
                return
        print(f"Livro '{titulo}' não encontrado.")

    def exibir_historico_navegacao(self):
        if not self.historico:
            print("O histórico não possui dados.")
        else:
            # Exibe do mais recente para o mais antigo (reverse)
            for livro in reversed(self.historico):
                livro.mostrar_livros()
        print("============================================")