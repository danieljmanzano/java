public class CD extends Produto{
    private int num;
    private String gravadora;

    public CD(int codigo, String nome, String autor, int num, String gravadora, int ano) {
        super(codigo, ano, nome, autor);
        this.num = num;
        this.gravadora = gravadora;
    }
}
