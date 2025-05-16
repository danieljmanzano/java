public class Livro extends Produto {
    private String editora, idioma;
    private int edicao, paginas;

    protected Livro(int codigo, String nome, String autor, String editora, int ano, int edicao, int paginas,
                    String idioma) {
        super(codigo, ano, nome, autor);
        this.edicao = edicao;
        this.editora = editora;
        this.paginas = paginas;
        this.idioma = idioma;
        this.tipo = "Livro";
    }

    @Override
    public String toString() { // nao saiu o runcodes ainda... nao sei como deve ser o print. depois arrumo
        return "";
    }


}
