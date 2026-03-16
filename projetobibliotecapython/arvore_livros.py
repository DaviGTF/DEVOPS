from collections import deque
from enum import Enum

class CriterioOrdenacao(Enum):
    TITULO = 1
    AUTOR = 2
    GENERO = 3
    ANO = 4

class No:
    def __init__(self, livro):
        self.livro = livro
        self.esquerda = None
        self.direita = None

class ArvoreBinariaLivros:
    def __init__(self):
        self.raiz = None
        self.criterio = CriterioOrdenacao.TITULO

    def inserir(self, livro):
        self.raiz = self._inserir_rec(self.raiz, livro)

    def _comparar(self, l1, l2):
        if self.criterio == CriterioOrdenacao.TITULO:
            return (l1.titulo.lower() > l2.titulo.lower()) - (l1.titulo.lower() < l2.titulo.lower())
        elif self.criterio == CriterioOrdenacao.AUTOR:
            return (l1.autor.lower() > l2.autor.lower()) - (l1.autor.lower() < l2.autor.lower())
        elif self.criterio == CriterioOrdenacao.GENERO:
            return (l1.genero.lower() > l2.genero.lower()) - (l1.genero.lower() < l2.genero.lower())
        elif self.criterio == CriterioOrdenacao.ANO:
            return l1.ano_publicacao - l2.ano_publicacao
        return 0

    def _inserir_rec(self, atual, livro):
        if atual is None:
            return No(livro)
        if self._comparar(livro, atual.livro) < 0:
            atual.esquerda = self._inserir_rec(atual.esquerda, livro)
        else:
            atual.direita = self._inserir_rec(atual.direita, livro)
        return atual

    # Algoritmo DFS (Busca em Profundidade)
    def buscar_dfs(self, titulo_alvo):
        caminho = []
        self._dfs_rec(self.raiz, titulo_alvo, caminho)
        return caminho

    def _dfs_rec(self, no, titulo, caminho):
        if no is None: return False
        caminho.append(no.livro)
        if no.livro.titulo.lower() == titulo.lower():
            return True
        if self._dfs_rec(no.esquerda, titulo, caminho) or self._dfs_rec(no.direita, titulo, caminho):
            return True
        caminho.pop()
        return False

    # Algoritmo BFS (Busca em Largura)
    def buscar_bfs(self, titulo_alvo):
        caminho = []
        if not self.raiz: return caminho
        fila = deque([self.raiz])
        while fila:
            no = fila.popleft()
            caminho.append(no.livro)
            if no.livro.titulo.lower() == titulo_alvo.lower():
                return caminho
            if no.esquerda: fila.append(no.esquerda)
            if no.direita: fila.append(no.direita)
        return caminho