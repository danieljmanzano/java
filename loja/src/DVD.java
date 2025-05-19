public class DVD extends Produto {
    private String idioma, genero, nacionalidade;

    public DVD(String codigo, String nome, String autor, String idioma, String genero, int ano, String nacionalidade) {
        super(codigo, ano, nome, autor);
        this.idioma = idioma;
        this.genero = genero;
        this.nacionalidade = nacionalidade;
        this.tipo = "DVD";
    }

    @Override
    public String toString() { // nao saiu o runcodes ainda... nao sei como deve ser o print. depois arrumo
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Diretor: ").append(autor).append("\n");
        sb.append("Gênero: ").append(genero).append("\n");
        sb.append("Ano: ").append(ano).append("\n");
        sb.append("Nacionalidade: ").append(nacionalidade).append("\n");
        sb.append("Idioma: ").append(idioma);

        return sb.toString();

    }
}
