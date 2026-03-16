from collections import deque

class FilaDeEsperaUsuario:
    def __init__(self):
        self.fila = deque()

    def adicionar_usuario_fila(self, usuario):
        self.fila.append(usuario)
        print(f"Usuário {usuario.nome} adicionado à fila.")

    def remover_usuario_fila(self):
        if not self.fila:
            print("Fila vazia.")
            return None
        usuario = self.fila.popleft()
        print(f"Usuário {usuario.nome} removido da fila.")
        return usuario

    def buscar_usuario(self, id_u):
        for u in self.fila:
            if u.id == id_u:
                return u
        return None