import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (linha.isEmpty()) break;

            String[] partes = linha.split(", ");

            switch (partes[0]) {
                case "I": // inserir
                    if (partes[1].compareTo("Livro") == 0) {
                        Livro l = new Livro(Integer.parseInt(partes[2]), partes[3], partes[4], partes[5],
                                            Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
                                            Integer.parseInt(partes[8]), partes[9]);
                        loja.inserir(l);

                    } else if (partes[1].compareTo("CD") == 0) {
                        CD cd = new CD(Integer.parseInt(partes[2]), partes[3], partes[4], Integer.parseInt(partes[5]),
                                       partes[6], Integer.parseInt(partes[7]));
                        loja.inserir(cd);

                    } else if (partes[1].compareTo("DVD") == 0) {
                        DVD dvd = new DVD(Integer.parseInt(partes[2]), partes[3], partes[4], partes[5], partes[6],
                                          Integer.parseInt(partes[7]), partes[8]);
                        loja.inserir(dvd);
                    }
                    break;

                case "A": // adicionar

                    break;

                case "V": // vender

                    break;

                case "P": // procurar

                    break;

                case "S": // sumário

                    break;

                default: break;
            }

        }
    }
}
