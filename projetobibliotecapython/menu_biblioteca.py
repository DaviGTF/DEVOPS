import sys
from collections import deque
from .livro import Livro
from .usuario import Usuario, ExibirMenuUsuarios
from .arvore_livros import ArvoreBinariaLivros
from .lista_livros import ListaEncadeadaLivros
from .fila_espera_usuario import FilaDeEsperaUsuario
from .bfs import BFS
from .dfs import DFS

class MenuBiblioteca:
    def __init__(self):
        self.arvore_livros = ArvoreBinariaLivros()
        self.biblioteca = ListaEncadeadaLivros()
        self.fila_usuarios_geral = FilaDeEsperaUsuario()

    def inicializar_dados(self):
        # Livros iniciais baseados no seu código Java
        livros_iniciais = [
           Livro("O Grande Mentecapto", "Fernando Sabino", "Record", "Romance", 1979, 256),
           Livro("Memórias Póstumas de Brás Cubas", "Machado de Assis", "Tipografia Nacional", "Romance", 1881, 160),
           Livro("Vidas Secas", "Graciliano Ramos", "Via Leitura", "Romance", 2024, 112),

           Livro("Percy Jackson e os Olimpianos", "Rick Riordan", "Intrínseca", "Fantasia", 2005, 400),
           Livro("O espadachim de carvão", "Affonso Solano ", "Casa Da Palavra", "Fantasia", 2013, 256),
           Livro("O Hobbit", " John R.R. Tolkien", "HarperCollins", "Fantasia", 2019, 336),

           Livro("A Cor que Caiu do Céu", "H.P. Lovecraft", "Pandorga Editora", "Ficção Científica", 1927, 136),
           Livro("Frankenstein", "Mary Shelley", "Principis", "Ficção Científica", 1818, 240),
           Livro("Jogador Número 1", "Ernest Cline", "Leya", "Ficção Científica", 2019, 446),

           Livro("Drácula", "Bram Stoker", "Darkside", "Terror Gótico", 1897, 580),
           Livro("Carmilla: A Vampira de Karnstein", "Sheridan Le Fanu", "Novo Século", "Terror Gótico", 2022, 160),
           Livro("O Médico e o Monstro", "Robert Louis Stevenson", "L&PM", "Terror Gótico", 2002, 112),

           Livro("Dragon Ball Vol. 34", "Akira Toriyama", "Panini", "Mangá", 2015, 256),
           Livro("JoJo's Bizarre Adventure: Diamond is Unbreakable - Vol. 47", "Hirohiko Araki", "Shueisha", "Mangá", 1996, 192),
	       Livro("Mob Pscyho 100 Vol. 1", "One", "Panini", "Mangá", 2017, 200),
           Livro("Chainsaw Man Vol.1", "Tatsuki Fujimoto", "Panini", "Mangá", 2022, 212),
           Livro("Ordem Paranormal - Iniciação", "Rafael Lange", "Jambô", "Terror", 2023, 229),
           Livro("Full Metal Alchemist Vol.4", "Hiromu Arakawa", "Panini", "Mangá", 2012, 262),

           Livro("Demolidor: A Queda de Murdock", "Frank Miller", "Panini", "Quadrinhos", 2019, 216),
           Livro("Lanterna Verde: a Noite Mais Densa", "Geoff Johns", "Panini", "Quadrinhos", 2009, 304),
           Livro("Homem Aranha: Saga do Clone Vol. 3", "Gerry Conway", "Panini", "Quadrinhos", 2024, 164),
           Livro("Watchmen", "Alan Moore", "Panini", "Quadrinhos", 1987, 416),
           Livro("Sandman: Prelúdios & Noturnos", "Neil Gaiman", "Panini", "Quadrinhos", 1989, 448),
           Livro("V de Vingança", "Alan Moore", "Panini", "Quadrinhos", 1988, 296)
        ]
        
        for l in livros_iniciais:
            self.biblioteca.adicionar_livro(l)
            self.arvore_livros.inserir(l)

    def iniciar(self):
        self.inicializar_dados()
        
        while True:
            print("\n" + "="*30)
            print("      SISTEMA BIBLIOTECA")
            print("="*30)
            print("1. Menu de Livros (CRUD)")
            print("2. Menu de Usuários")
            print("3. Buscar Livro (BFS/DFS)")
            print("4. Recomendações (Dijkstra)")
            print("5. Menu de Espera por Livro")
            print("0. Sair")
            
            opcao = input("Escolha uma opção: ")

            if opcao == "1":
                self._menu_livros()
            elif opcao == "2":
                ExibirMenuUsuarios.exibir(self.fila_usuarios_geral, input)
            elif opcao == "3":
                self._menu_buscas()
            elif opcao == "4":
                self._menu_recomendacoes()
            elif opcao == "5":
                self._menu_espera_especifico()
            elif opcao == "0":
                print("Encerrando sistema...")
                break
            else:
                print("Opção inválida!")

    def _exibir_recomendacoes(self):
        titulo = input("Basear recomendações em qual livro? ")
        livro_origem = self.biblioteca.buscar_livro_por_titulo(titulo)

        if not livro_origem:
            print(f"Livro '{titulo}' não encontrado.")
            return

        # Pega o grafo da biblioteca
        grafo = self.biblioteca.get_grafo_recomendacao().get_grafo()
        distancias = self._dijkstra_simples(grafo, livro_origem)

        print(f"\nRecomendações baseadas em: {livro_origem.titulo}")
        # Ordena por distância e remove o próprio livro (distância 0)
        sorted_recomendacoes = sorted(distancias.items(), key=lambda x: x[1])
        
        for livro, dist in sorted_recomendacoes:
            if dist > 0:
                print(f"Nível de proximidade {dist} - {livro.titulo}")

    def _dijkstra_simples(self, grafo, origem):
        distancias = {origem: 0}
        fila = deque([origem])

        while fila:
            atual = fila.popleft()
            dist_atual = distancias[atual]
            
            # Pega vizinhos (se o livro não estiver no grafo, retorna set vazio)
            for vizinho in grafo.get(atual, set()):
                if vizinho not in distancias:
                    distancias[vizinho] = dist_atual + 1
                    fila.append(vizinho)
        return distancias

    # --- CORREÇÃO OPÇÃO 5: FILA DE ESPERA ---
    def _menu_espera_especifico(self):
        titulo = input("Digite o título do livro para gerenciar a fila: ")
        livro = self.biblioteca.buscar_livro_por_titulo(titulo)
        
        if not livro:
            print(f"O livro '{titulo}' não foi encontrado.")
            return

        # Loop do menu da fila (Baseado no FilaDeEsperaUsuario.java)
        while True:
            print(f"\n=== FILA DE ESPERA: {livro.titulo} ===")
            print("1. Adicionar Usuário à Fila")
            print("2. Mostrar Fila")
            print("3. Remover Primeiro da Fila")
            print("4. Voltar")
            
            op = input("Escolha: ")
            
            if op == "1":
                id_u = int(input("Digite o ID do usuário (da lista geral): "))
                user = self.fila_usuarios_geral.buscar_usuario(id_u)
                if user:
                    livro.fila_espera.adicionar_usuario_fila(user)
                else:
                    print("Usuário não encontrado na lista geral!")
            elif op == "2":
                # Mostra quem está na fila do livro específico
                if not livro.fila_espera.fila:
                    print("Ninguém na fila para este livro.")
                for u in livro.fila_espera.fila:
                    print(f"- {u.nome} (ID: {u.id})")
            elif op == "3":
                livro.fila_espera.remover_usuario_fila()
            elif op == "4":
                break
            else:
                print("Opção inválida.")

    def _mostrar_fila_do_livro(self, livro):
        # Lógica para iterar e mostrar a fila do livro
        if not livro.fila_espera.fila:
            print("Fila vazia.")
        else:
            print(f"Usuários aguardando '{livro.titulo}':")
            for u in livro.fila_espera.fila:
                print(f"- {u.nome} (ID: {u.id})")

    def _menu_buscas(self):
        titulo = input("Digite o título do livro para busca: ")
        print("\n[1] Busca em Largura (BFS)")
        print("[2] Busca em Profundidade (DFS)")
        tipo = input("Escolha o método: ")

        if tipo == "1":
            caminho = self.arvore_livros.buscar_bfs(titulo)
            self._exibir_resultado_busca(caminho, titulo)
        elif tipo == "2":
            caminho = self.arvore_livros.buscar_dfs(titulo)
            self._exibir_resultado_busca(caminho, titulo)

    def _exibir_resultado_busca(self, caminho, alvo):
        if not caminho or caminho[-1].titulo.lower() != alvo.lower():
            print(f"\nLivro '{alvo}' não encontrado na árvore.")
        else:
            print(f"\nLivro encontrado! Caminho percorrido ({len(caminho)} nós):")
            for l in caminho:
                print(f" -> {l.titulo}")

    def _menu_recomendacoes(self):
        titulo = input("Basear recomendações em qual livro? ")
        livro_origem = self.biblioteca.buscar_livro_por_titulo(titulo)
        
        if livro_origem:
            # Chama o Dijkstra que agora tem o import correto
            distancias = self.biblioteca.grafo.dijkstra_simples(livro_origem)
            
            if len(distancias) <= 1:
                print(f"Nenhuma recomendação encontrada para '{livro_origem.titulo}'.")
                return

            print(f"\nRecomendações para: {livro_origem.titulo}")
            # Ordena por distância (proximidade no grafo)
            for livro, dist in sorted(distancias.items(), key=lambda x: x[1]):
                if dist > 0:
                    print(f"Distância {dist}: {livro.titulo} (Gênero: {livro.genero})")
        else:
            print(f"Erro: O livro '{titulo}' não está cadastrado no sistema.")

    def _menu_livros(self):
        # Aqui você pode implementar a lógica de adicionar/remover livros
        # que estava dentro da ListaEncadeada no Java
        print("\n1. Listar todos")
        print("2. Adicionar novo")
        op = input("Opção: ")
        if op == "1":
            self.biblioteca.mostrar_livros()
        elif op == "2":
            t = input("Título: ")
            a = input("Autor: ")
            e = input("Editora: ")
            g = input("Gênero: ")
            ano = int(input("Ano: "))
            p = int(input("Páginas: "))
            novo = Livro(t, a, e, g, ano, p)
            self.biblioteca.adicionar_livro(novo)
            self.arvore_livros.inserir(novo)

    def _menu_espera_especifico(self):
        titulo = input("Digite o título do livro para gerenciar a fila: ")
        livro = self.biblioteca.buscar_livro_por_titulo(titulo)
        if livro:
            # Reutiliza a lógica de menu da fila que estava no Java
            while True:
                print(f"\n--- FILA DE: {livro.titulo} ---")
                print("1. Adicionar usuário da lista geral à fila")
                print("2. Ver fila")
                print("3. Atender próximo (Remover)")
                print("4. Voltar")
                op = input("Escolha: ")
                if op == "1":
                    id_u = int(input("ID do usuário: "))
                    user = self.fila_usuarios_geral.buscar_usuario(id_u)
                    if user: livro.fila_espera.adicionar_usuario_fila(user)
                elif op == "2":
                    for u in livro.fila_espera.fila: print(f"- {u.nome}")
                elif op == "3":
                    livro.fila_espera.remover_usuario_fila()
                elif op == "4": break