public class CD extends Produto{
    private int num;
    private String gravadora;

    public CD(int codigo, String nome, String autor, int num, String gravadora, int ano) {
        super(codigo, ano, nome, autor);
        this.num = num;
        this.gravadora = gravadora;
        this.tipo = "CD";
    }

    @Override
    public String toString() { // nao saiu o runcodes ainda... nao sei como deve ser o print. depois arrumo
        return "";
    }
}
