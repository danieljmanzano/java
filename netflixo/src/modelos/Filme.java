package modelos;

import java.util.Scanner;

public class Filme extends Titulo{
    private int duracao;
    private String diretor;

    public void printaFicha() {
        System.out.println("\n---- " + nome + " ----");
        System.out.println("lançado em " + anoDeLancamento);
        System.out.println("dirigido por " + diretor);
        int horas = duracao / 60;
        int minutos = duracao % 60;
        System.out.println("duração de " + horas + "h" + minutos + "min");
        System.out.printf("nota média: %.2f. avaliado por %d usuários\n", (float) somaDeNotas / numNotas, numNotas);
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

        System.out.println("insira as informações referentes ao filme a ser cadastrado no sistema");
        System.out.print("nome: ");
        nome = leitura.nextLine();
        System.out.print("nome do diretor: ");
        diretor = leitura.nextLine();
        System.out.print("ano de lançamento: ");
        anoDeLancamento = leitura.nextInt();
        System.out.print("duração do filme (em minutos): ");
        duracao = leitura.nextInt();
        System.out.print("o filme está incluso no plano? (1/0) ");
        int noPlano = leitura.nextInt();
        inclusoNoPlano = (noPlano == 1);
    }

    public void setInfoCodigo(String nome, int anoDeLancamento, int duracao, boolean inclusoNoPlano) { //esse aqui é pra se eu quiser deixar setado no codigo
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
        this.duracao = duracao;
        this.inclusoNoPlano = inclusoNoPlano;
    }

}
