import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.isEmpty()) break;

            String[] partes = linha.split(",");

            switch (partes[0]) {
                case "I": // inserir
                    System.out.print("Operação inserir ");
                    // esse monte de coisa nas chamadas é de acordo com as entradas do exercício, por isso ta assim
                    if (partes[1].compareTo("Livro") == 0) {
                        System.out.println("livro: " + partes[2]);
                        Livro l = new Livro(partes[2], partes[3], partes[4], partes[5],
                                            Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
                                            Integer.parseInt(partes[8]), partes[9]);
                        loja.inserir(l);

                    } else if (partes[1].compareTo("CD") == 0) {
                        System.out.println("CD: " + partes[2]);
                        CD cd = new CD(partes[2], partes[3], partes[4], Integer.parseInt(partes[5]),
                                       partes[6], Integer.parseInt(partes[7]));
                        loja.inserir(cd);

                    } else if (partes[1].compareTo("DVD") == 0) {
                        System.out.println("DVD: " + Integer.parseInt(partes[2]));
                        DVD dvd = new DVD(partes[2], partes[3], partes[4], partes[5], partes[6],
                                          Integer.parseInt(partes[7]), partes[8]);
                        loja.inserir(dvd);
                    }

                    System.out.println();
                    break;

                case "A": // adicionar
                    loja.adicionar(partes[1], Integer.parseInt(partes[2]));
                    break;

                case "V": // vender
                    System.out.println("Operação de compra: " + partes[1]);
                    if (loja.vender(partes[1], Integer.parseInt(partes[2])))
                        System.out.println("Operação realizada com sucesso: " + partes[1]);
                    break;

                case "P": // procurar
                    System.out.println("Procurando: " + partes[1]);
                    if(!loja.procurar(partes[1]))
                        System.out.println("Produto não encontrado: " + partes[1]);

                    break;

                case "S": // sumário
                    loja.sumario();
                    break;

                default: break;
            }

        }
    }
}
