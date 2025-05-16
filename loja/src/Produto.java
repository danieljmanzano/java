public class Produto {
    protected int codigo, ano;
    protected String nome, autor, tipo;

    public Produto(int codigo, int ano, String nome, String autor) {
        this.codigo = codigo;
        this.ano = ano;
        this.nome = nome;
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "nome: " + nome + ", codigo: " + codigo;
    }
}
