import java.util.Scanner;

/*
exercício para simular uma "loja" usando ideias de herança e polimorfismo
a classe Loja tem um vetor da classe Produto, com cada elemento podendo ser classificados como Livro, CD ou DVD
definições do pdf para entrada
    -Inserir produtos:
        I,Livro,código,Nome do livro,Autor,Editora,Ano,Edição,páginas,idioma
        I,CD,código,Título do álbum,Cantor ou banda,Número de trilhas,Gravadora,Ano
        I,DVD,código,Nome do filme,Diretor,idioma,Gênero,Ano,Nacionalidade
    -Adicionar produtos no estoque:
        A,código,quantidade
    -Vender produtos do estoque:
        V,código,quantidade
    -Procurar produtos:
        P,código
        P,Nome
    -Sumário da loja:
        S
*/

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();

        while (scanner.hasNextLine()) {
            System.out.println();
            String linha = scanner.nextLine();
            String[] partes = linha.split(",");

            switch (partes[0]) {
                case "I": // inserir
                    System.out.print("Operação inserir ");
                    partes[2] = formataCodigo(partes[2]); // retira os zeros à esquerda do código (caso existam)
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
                        System.out.println("DVD: " + partes[2]);
                        DVD dvd = new DVD(partes[2], partes[3], partes[4], partes[5], partes[6],
                                          Integer.parseInt(partes[7]), partes[8]);
                        loja.inserir(dvd);
                    }

                    break;

                case "A": // adicionar
                    partes[1] = formataCodigo(partes[1]); // retira os zeros à esquerda do código (caso existam)
                    System.out.println("Operação de compra: " + partes[1]);
                    if (loja.adicionar(partes[1], Integer.parseInt(partes[2])))
                        System.out.println("Operação realizada com sucesso: " + partes[1]);

                    break;

                case "V": // vender
                    partes[1] = formataCodigo(partes[1]); // retira os zeros à esquerda do código (caso existam)
                    System.out.println("Operação de venda: " + partes[1]);
                    if (loja.vender(partes[1], Integer.parseInt(partes[2])))
                        System.out.println("Operação realizada com sucesso: " + partes[1]);

                    break;

                case "P": // procurar
                    System.out.println("Procurando: " + partes[1]);
                    partes[1] = formataCodigo(partes[1]); // retira os zeros à esquerda do código (caso existam)
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

    // na saída do exercício, por algum motivo todos os código que começam com zero à esquerda perdem os zeros
    // ou seja, por exemplo, o código 0918273 fica 918273
    public static String formataCodigo(String str) {
        // remove todos os zeros à esquerda usando expressão regular
        return str.replaceFirst("^0+(?!$)", ""); // kkkkkkkkkkk seloco olha essa linha
        // sendo sincero, gpt que fez. nunca ia pensar nisso ta doido
    }

}
