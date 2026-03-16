class DFS:
    @staticmethod
    def buscar_livro(no, titulo_alvo):
        caminho = []
        DFS._buscar_recursivo(no, titulo_alvo, caminho)
        return caminho

    @staticmethod
    def _buscar_recursivo(no, titulo_alvo, caminho):
        if no is None:
            return False
            
        caminho.append(no.livro)
        
        if no.livro.titulo.lower() == titulo_alvo.lower():
            return True
            
        if (DFS._buscar_recursivo(no.esquerda, titulo_alvo, caminho) or 
            DFS._buscar_recursivo(no.direita, titulo_alvo, caminho)):
            return True
            
        caminho.pop() # Backtracking: remove o último se não encontrou nada nesse ramo
        return False