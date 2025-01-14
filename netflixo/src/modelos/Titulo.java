package modelos;

public class Titulo {
    String nome, sinopse;
    int anoDeLancamento, numNotas, somaDeNotas;
    boolean inclusoNoPlano;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public boolean isInclusoNoPlano() {
        return inclusoNoPlano;
    }

    public void setInclusoNoPlano(boolean inclusoNoPlano) {
        this.inclusoNoPlano = inclusoNoPlano;
    }

    public int getSomaDeNotas() {
        return somaDeNotas;
    }

    public void setSomaDeNotas(int somaDeNotas) {
        this.somaDeNotas = somaDeNotas;
    }

    public int getNumNotas() {
        return numNotas;
    }

    public void setNumNotas(int numNotas) {
        this.numNotas = numNotas;
    }

    public void avalia(int nota) {
        somaDeNotas += nota;
        numNotas++;
    }

}
