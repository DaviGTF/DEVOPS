# projetobibliotecapython/api.py
from fastapi import FastAPI
from .menu_biblioteca import MenuBiblioteca

app = FastAPI()

# Inicializamos a lógica da biblioteca e carregamos os dados
biblioteca_logic = MenuBiblioteca()
biblioteca_logic.inicializar_dados()

@app.get("/")
def home():
    return {"mensagem": "Bem-vindo à API da Biblioteca do Davi!"}

@app.get("/livros")
def listar_livros():
    # Pegamos a lista de livros que está dentro de ListaEncadeadaLivros
    return biblioteca_logic.biblioteca.biblioteca

@app.get("/usuarios")
def listar_usuarios():
    # Retornamos os usuários que estão na fila geral
    return list(biblioteca_logic.fila_usuarios_geral.fila)