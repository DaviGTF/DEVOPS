from collections import deque

class BFS:
    @staticmethod
    def buscar_livro(raiz, titulo_alvo):
        caminho = []
        if raiz is None:
            return caminho
        
        fila = deque([raiz])
        
        while fila:
            no = fila.popleft()
            caminho.append(no.livro)
            
            if no.livro.titulo.lower() == titulo_alvo.lower():
                return caminho
            
            if no.esquerda:
                fila.append(no.esquerda)
            if no.direita:
                fila.append(no.direita)
                
        return caminho