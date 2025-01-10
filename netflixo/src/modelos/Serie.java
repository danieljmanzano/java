package modelos;

import java.util.Scanner;

public class Serie extends Titulo{
    int temporadas, episodios;
    boolean emLancamento;

    // essas funçao de baixo sao a mesma logica das do filme, a diferença é que eu adaptei pra cada um (nao coloquei em titulo por isso)
    public void printaFicha() {
        System.out.println("\n---- " + nome + " ----");
        System.out.println("começada em " + anoDeLancamento);
        System.out.printf("%d temporadas e %d episódios lançados\n", temporadas, episodios);
        System.out.printf("nota média: %.2f. avaliado por %d usuários\n", (float) somaDeNotas / numNotas, numNotas);

        if (emLancamento)
            System.out.println("série ainda em lançamento");
        else
            System.out.println("série finalizada");

        if (inclusoNoPlano)
            System.out.println("disponível para assistir!");
        else
            System.out.println("não incluso no seu plano!");

        for (int i = 0; i < nome.length() + 10; i++) // isso aqui é só bobeira pra printar o numero de "-" certo pra fica legal
            System.out.print("-");
        System.out.println("\n");
    }

    public void setInfoTerminal() { // fiz esse set pra eu usar quando tiver preguiça de ficar colocando os dados no codigo
        Scanner leitura = new Scanner(System.in); // vou colocar os dados como input pelo terminal

        System.out.println("insira as informações referentes à série a ser cadastrada no sistema");
        System.out.print("nome: ");
        nome = leitura.nextLine();
        System.out.print("ano de lançamento: ");
        anoDeLancamento = leitura.nextInt();
        System.out.print("número de episódios: ");
        episodios = leitura.nextInt();
        System.out.print("número de temporadas: ");
        temporadas = leitura.nextInt();
        System.out.print("o filme está incluso no plano? (1/0) ");
        int aux = leitura.nextInt();
        inclusoNoPlano = (aux == 1);
        System.out.print("o filme está em lançamento? (1/0) ");
        aux = leitura.nextInt();
        emLancamento = (aux == 1);
    }

    public void setInfoCodigo(String nome, int anoDeLancamento, boolean inclusoNoPlano, boolean emLancamento, int temporadas, int episodios) { //esse aqui é pra se eu quiser deixar setado no codigo
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
        this.inclusoNoPlano = inclusoNoPlano;
        this.emLancamento = emLancamento;
        this.temporadas = temporadas;
        this.episodios = episodios;
    }
}
