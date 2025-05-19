public class Produto {
    protected int ano, quant;
    protected String codigo, nome, autor, tipo;

    public Produto(String codigo, int ano, String nome, String autor) {
        this.codigo = codigo;
        this.ano = ano;
        this.nome = nome;
        this.autor = autor;
        this.quant = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tipo).append("\n");
        sb.append("Código: ").append(codigo).append("\n");
        sb.append("Título: ").append(nome).append("\n");

        return sb.toString();
    }
}
