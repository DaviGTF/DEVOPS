from fastapi import FastAPI

from .livro import Livro
from .menu_biblioteca import MenuBiblioteca
from .usuario import Usuario

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
    return [
        {"nome": u.nome, "id": u.id}
        for u in biblioteca_logic.fila_usuarios_geral.fila
    ]

@app.post("/usuarios/cadastro")
async def cadastrar_usuario(nome: str, id_usuario: int):
    novo = Usuario(nome, id_usuario)
    biblioteca_logic.fila_usuarios_geral.adicionar_usuario_fila(novo)
    return {"mensagem": f"Usuário {nome} cadastrado com sucesso!", "id": id_usuario}

@app.get("/livros/buscar/{titulo}")
def buscar_livro(titulo: str):
    caminho = biblioteca_logic.arvore_livros.buscar_bfs(titulo)
    if caminho and caminho[-1].titulo.lower() == titulo.lower():
        livro = caminho[-1]
        return {
            "encontrado": True,
            "detalhes": {"titulo": livro.titulo, "autor": livro.autor, "genero": livro.genero}
        }
    return {"encontrado": False, "mensagem": "Livro não encontrado na árvore."}

@app.get("/livros/recomendar/{titulo}")
def recomendar_livros(titulo: str):
    livro_origem = biblioteca_logic.biblioteca.buscar_livro_por_titulo(titulo)
    if not livro_origem:
        return {"erro": "Livro base não encontrado para gerar recomendações."}

    distancias = biblioteca_logic.biblioteca.grafo.dijkstra_simples(livro_origem)

    recomendados = sorted(distancias.items(), key=lambda x: x[1])[1:4]
    return [
        {"titulo": livro.titulo, "proximidade": dist}
        for livro, dist in recomendados
    ]