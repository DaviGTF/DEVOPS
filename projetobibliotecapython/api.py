from fastapi import FastAPI
from .menu_biblioteca import MenuBiblioteca

app = FastAPI()

biblioteca_logic = MenuBiblioteca()
biblioteca_logic.inicializar_dados()

@app.get("/")
def home():
    return {"mensagem": "Bem-vindo à API da Biblioteca do Davi!"}

@app.get("/livros")
def listar_livros():
    return biblioteca_logic.biblioteca.biblioteca

@app.get("/usuarios")
def listar_usuarios():
    return list(biblioteca_logic.fila_usuarios_geral.fila)