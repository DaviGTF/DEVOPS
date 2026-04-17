import pytest
from projetobibliotecapython.api import cadastrar_usuario, buscar_livro, recomendar_livros

@pytest.mark.asyncio
async def test_logica_cadastro_usuario():
    resultado = await cadastrar_usuario(nome="Denji Haiakawa", id_usuario=232)

    assert resultado["id"] == 232
    assert "Denji Haiakawa" in resultado["mensagem"]

def test_logica_buscar_livro_existente():
    resultado = buscar_livro("A Cor que Caiu do Céu")

    assert resultado["encontrado"] is True
    assert resultado["detalhes"]["titulo"] == "A Cor que Caiu do Céu"

def test_logica_recomendar_livro_inexistente():
    resultado = recomendar_livros("TituloInexistente")

    assert "erro" in resultado
    assert "não encontrado" in resultado["erro"]

def test_logica_buscar_livro_vazio():
    resultado = buscar_livro("")

    assert resultado["encontrado"] is False
    assert "não encontrado" in resultado["mensagem"]

@pytest.mark.asyncio
async def test_logica_fluxo_cadastro_e_listagem():
    from projetobibliotecapython.api import cadastrar_usuario, listar_usuarios

    nome_novo = "Lemillion"
    id_novo = 1000000

    await cadastrar_usuario(nome=nome_novo, id_usuario=id_novo)

    todos_usuarios = listar_usuarios()

    usuario_encontrado = any(u['id'] == id_novo for u in todos_usuarios)
    assert usuario_encontrado is True