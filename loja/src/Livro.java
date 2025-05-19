public class Livro extends Produto {
    private String editora, idioma;
    private int edicao, paginas;

    protected Livro(String codigo, String nome, String autor, String editora, int ano, int edicao, int paginas,
                    String idioma) {
        super(codigo, ano, nome, autor);
        this.edicao = edicao;
        this.editora = editora;
        this.paginas = paginas;
        this.idioma = idioma;
        this.tipo = "Livro";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Autor: ").append(autor).append("\n");
        sb.append("Editora: ").append(editora).append("\n");
        sb.append("Edição: ").append(edicao).append("\n");
        sb.append("Ano: ").append(ano).append("\n");
        sb.append("Páginas: ").append(paginas).append("\n");
        sb.append("Idioma: ").append(idioma);

        return sb.toString();
    }


}
