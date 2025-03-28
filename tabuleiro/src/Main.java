/* primeira atividade de poo!!!!
*  temos que simular um tabuleiro (quadrado) com numeros em todas suas posiçoes e uma em específico com 0 (teoricamente vazia)
*  vou receber os numeros e colocar num vetor (que teoricamente simula uma matriz) e consegue fazer operações de movimentação baseadas na posição vazia
*  para movimentar, recebo uma cadeia de caracteres u (up), d (down), l (left) ou r (right) e vou mexendo no tabuleiro (e printo a todo comando)
*  ao fim, vejo se ele ficou organizado (e printo true ou false com base nisso) */

// ex. de entrada: 1 4 2 3 0 5 6 7 8 \n dr

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String linha = scanner.nextLine();
        String[] partes = linha.split(" "); // separo minha linha de entrada em partes de acordo com os espaços
        int tam = partes.length;
        int[] vetor = new int[tam];

        for (int i = 0; i < tam; i++)
            vetor[i] = Integer.parseInt(partes[i]); // cada parte é um numero do vetor


        Table t = new Table();
        t.setTable(vetor, tam); // crio o tabuleiro de fato aqui
        t.printTable();

        linha = scanner.nextLine(); // recebo os comandos como uma unica string (as letras todas coladas, aliás). depois tenho que separar em caracteres
        char[] comandos = linha.toCharArray(); // tenho um vetor com todos os comandos
        int comandosLen = linha.length();

        for (int i = 0; i < comandosLen; i++) { // vou selecionando e executando os comandos
            switch (comandos[i]) {
                case 'u':
                    t.moveUp();
                    t.printTable();
                    break;
                case 'd':
                    t.moveDown();
                    t.printTable();
                    break;
                case 'l':
                    t.moveLeft();
                    t.printTable();
                    break;
                case 'r':
                    t.moveRight();
                    t.printTable();
                    break;
            }
        }

        System.out.println("Posicao final: " + t.resultado()); // true se ficou certo, false se nao
    }
}
