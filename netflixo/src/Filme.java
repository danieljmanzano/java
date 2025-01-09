import java.util.Scanner;

public class Filme {
    private String nome;
    private int anoDeLancamento, duracao, numNotas, somaDeNotas;
    private float notaMedia;
    private boolean inclusoNoPlano;

    void printaFicha() {
        System.out.println("\n---- " + nome + " ----");
        System.out.println("lançado em " + anoDeLancamento);
        int horas = duracao / 60;
        int minutos = duracao % 60;
        System.out.println("duração de " + horas + "h" + minutos + "min");
        System.out.printf("nota média: %.2f. avaliado por %d usuários\n", notaMedia, numNotas);
        if (inclusoNoPlano)
            System.out.println("disponível para assistir!\n");
        else
            System.out.println("não incluso no seu plano!\n");
    }

    void recebeInfo() {
        Scanner leitura = new Scanner(System.in);

        System.out.println("insira as informações referentes ao filme a ser cadastrado no sistema");
        System.out.print("nome: ");
        nome = leitura.nextLine();
        System.out.print("ano de lançamento: ");
        anoDeLancamento = leitura.nextInt();
        System.out.print("duração do filme (em minutos): ");
        duracao = leitura.nextInt();
        System.out.print("o filme está incluso no plano? (S/N) ");
        char noPlano = leitura.next().charAt(0); //nao sei como funciona muito bem, mas fiz isso pra pegar char
        inclusoNoPlano = (noPlano == 'S');
    }

    void avalia(int nota) {
        somaDeNotas += nota;
        numNotas++;
        notaMedia = (float) somaDeNotas / numNotas;
    }

}
