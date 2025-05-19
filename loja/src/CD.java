public class CD extends Produto{
    private int trilhas;
    private String gravadora;

    public CD(String codigo, String nome, String autor, int trilhas, String gravadora, int ano) {
        super(codigo, ano, nome, autor);
        this.trilhas = trilhas;
        this.gravadora = gravadora;
        this.tipo = "CD";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Banda: ").append(autor).append("\n");
        sb.append("Gravadora: ").append(gravadora).append("\n");
        sb.append("Ano: ").append(ano).append("\n");
        sb.append("trilhas: ").append(trilhas);

        return sb.toString();
    }
}
