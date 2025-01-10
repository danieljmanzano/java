package modelos;

public class Titulo {
    String nome;
    int anoDeLancamento, numNotas, somaDeNotas;
    boolean inclusoNoPlano;

    public void avalia(int nota) {
        somaDeNotas += nota;
        numNotas++;
    }

}
