from collections import deque

class GrafoRecomendacao:
    def __init__(self):
        self.grafo = {}  # Livro: set(Livros conectados)

    def adicionar_livro(self, livro):
        if livro not in self.grafo:
            self.grafo[livro] = set()

    def conectar_livros(self, novo_livro):
        for existente in self.grafo.keys():
            if existente == novo_livro: continue
            
            # Lógica de conexão original (Gênero, Autor, Editora ou Ano)
            mesmo_genero = existente.genero.lower() == novo_livro.genero.lower()
            mesmo_autor = existente.autor.lower() == novo_livro.autor.lower()
            mesma_editora = existente.editora.lower() == novo_livro.editora.lower()
            mesmo_ano = existente.ano_publicacao == novo_livro.ano_publicacao
            
            if mesmo_genero or mesmo_autor or mesma_editora or mesmo_ano:
                self.grafo[novo_livro].add(existente)
                self.grafo[existente].add(novo_livro)

    def dijkstra_simples(self, origem):
        distancias = {origem: 0}
        fila = deque([origem])
        
        while fila:
            atual = fila.popleft()
            dist_atual = distancias[atual]
            for vizinho in self.grafo.get(atual, set()):
                if vizinho not in distancias:
                    distancias[vizinho] = dist_atual + 1
                    fila.append(vizinho)
        return distancias