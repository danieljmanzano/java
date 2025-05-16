public class DVD extends Produto {
    private String idioma, genero, nacionalidade;

    public DVD(int codigo, String nome, String autor, String idioma, String genero, int ano, String nacionalidade) {
        super(codigo, ano, nome, autor);
        this.idioma = idioma;
        this.genero = genero;
        this.nacionalidade = nacionalidade;
        this.tipo = "DVD";
    }

    @Override
    public String toString() { // nao saiu o runcodes ainda... nao sei como deve ser o print. depois arrumo
        return "";
    }
}
