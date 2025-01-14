package modelos;


import calculos.Classificavel;

public class Episodio extends Titulo implements Classificavel {
    private int numero, duracao;
    private Serie serie, nome;

    public void setInfoEpisodio() {

    }

    @Override
    public float getClassificacao() {
        return (float) this.somaDeNotas / this.numNotas;
    }
}
