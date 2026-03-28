from .livro import Livro
from .grafo_recomendacao import GrafoRecomendacao

class ListaEncadeadaLivros:
    def __init__(self):
        self.biblioteca = []
        self.grafo = GrafoRecomendacao()

    def adicionar_livro(self, livro):
        self.biblioteca.append(livro)
        self.grafo.adicionar_livro(livro)
        self.grafo.conectar_livros(livro)

    def buscar_livro_por_titulo(self, titulo):
        for livro in self.biblioteca:
            if livro.titulo.lower() == titulo.strip().lower():
                return livro
        return None

    def mostrar_livros(self):
        if not self.biblioteca:
            print("A biblioteca está vazia.")
        for livro in self.biblioteca:
            livro.mostrar_livros()