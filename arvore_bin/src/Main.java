import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArvBin arvbin = new ArvBin(100); // por algum motivo, o exercício exige que o construtor tenha um tamanho que define a árvore mas não entra com o valor para isso... aí coloquei 100
        ArvAVL arvavl = new ArvAVL(100);
        ArvBal arvbal = new ArvBal(100);
        Scanner scanner = new Scanner(System.in);


        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.isEmpty()) break;

            // partes[0] deve ser o comando a ser operado, partes[1] o nome (ex.: "i Carlos \n i Jorge \n d Carlos")
            String[] partes = linha.split(" ");

            if (partes[0].compareTo("i") == 0) {
                arvbin.insert(partes[1]);
                arvbal.insert(partes[1]);
                arvavl.insert(partes[1]);
            } else if (partes[0].compareTo("d") == 0) {
                arvbin.remove(partes[1]);
                arvbal.remove(partes[1]);
                arvavl.remove(partes[1]);
            }
        }

        System.out.println(arvbin.toString());
        System.out.println();
        System.out.println(arvbal.toString());
        System.out.println();
        System.out.println(arvavl.toString());
        System.out.println();

    }
}
