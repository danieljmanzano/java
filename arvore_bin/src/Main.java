import java.util.Scanner;

/*
exercício para implementar árvores binárias (bst, avl e perf. balanceada) usando como base a representação em um vetor de strings (funciona como a visualização de uma heap)
a ideia principal era treinar os conceitos de herança. a classe árvore binária seria a "base" para as outras duas subclasses
sinceramente, não conseguimos fazer quase nada direito (que exercício maligno misericórdia) e nos baseamos no código do pedro prestes. créditos a ele
*/


/*--------------------------------------------
autores:
    Artur Kenzo Obara Kawazoe, n° USP 15652663
    Daniel Jorge Manzano, n° USP 15446861
--------------------------------------------*/

public class Main {
    public static void main(String[] args) {
        ArvBin arvbin = new ArvBin(100);
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
        scanner.close();
        System.out.println(arvbin.toString());
        System.out.println();
        System.out.println(arvbal.toString());
        System.out.println();
        System.out.println(arvavl.toString());
        System.out.println();

    }
}
