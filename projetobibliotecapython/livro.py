class Livro:
    def __init__(self, titulo, autor, editora, genero, ano_publicacao, numero_paginas):
        self.titulo = titulo
        self.autor = autor
        self.editora = editora
        self.genero = genero
        self.ano_publicacao = ano_publicacao
        self.numero_paginas = numero_paginas
        # Importação tardia para evitar recursão circular
        from .fila_espera_usuario import FilaDeEsperaUsuario
        self.fila_espera = FilaDeEsperaUsuario()

    def mostrar_livros(self):
        print("===========================")
        print(f"Título: {self.titulo}.")
        print(f"Autor: {self.autor}.")
        print(f"Editora: {self.editora}.")
        print(f"Gênero: {self.genero}.")
        print(f"Ano: {self.ano_publicacao}.")
        print(f"Páginas: {self.numero_paginas}.")
        print("===========================")