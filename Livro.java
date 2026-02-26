public class Livro {
    private String titulo;
    private String autor;
    private String editora;
    private String genero;
    private int anoPublicacao;
    private int numeroPaginas;
    private FilaDeEsperaUsuario filaEspera;


    //construtores
    public Livro(String titulo, String autor, String editora, String genero, int anoPublicacao, int numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.numeroPaginas = numeroPaginas;
        this.filaEspera = new FilaDeEsperaUsuario();
    }

    //getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public String getGenero() {
        return genero;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public FilaDeEsperaUsuario getFilaEspera() {
        return filaEspera;
    }

    //mostra livros
    public void mostrarLivros() {
        System.out.println("===========================");
        System.out.println("Título: " + titulo + ".");
        System.out.println("Autor: " + autor + ".");
        System.out.println("Editora: " + editora + ".");
        System.out.println("Gênero: " + genero + ".");
        System.out.println("Ano de Publicação: " + anoPublicacao + '.');
        System.out.println("Número de páginas: " + numeroPaginas + ".");
        System.out.println("===========================");
    }
}
