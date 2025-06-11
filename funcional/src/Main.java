import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/* ------------------------------------------
feito por:
    Daniel Jorge Manzano, n°USP: 15446861
    Heitor Gomes de Oliveira, n°USP: 15458350
    Newton Eduardo Pena Villegas, n°USP: 15481732
------------------------------------------ */

public class Main {

    static class Info {
        String country;
        int confirmed, deaths, recovery, active;

        // construtor
        Info(String country, int confirmed, int deaths, int recovery, int active) {
            this.country = country;
            this.confirmed = confirmed;
            this.deaths = deaths;
            this.recovery = recovery;
            this.active = active;
        }
    }

    public static void main(String[] args) {
        List<Info> infos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // leitura dos dados de entrada
        int n1, n2, n3, n4;
        n1 = scanner.nextInt();
        n2 = scanner.nextInt();
        n3 = scanner.nextInt();
        n4 = scanner.nextInt();

        // leitura do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader("dados.csv"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");

                String country = partes[0];
                int confirmed = Integer.parseInt(partes[1]);
                int deaths = Integer.parseInt(partes[2]);
                int recovery = Integer.parseInt(partes[3]);
                int active = Integer.parseInt(partes[4]);

                infos.add(new Info(country, confirmed, deaths, recovery, active));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // a soma de "Active" de todos os países em que "Confirmed" é maior o igual que n1
        System.out.println(
                infos.stream() // stream de informações da lista
                        .filter(info -> info.confirmed >= n1) // filtra os infos com confirmed >= n1
                        .mapToInt(info -> info.active) // mapeia os valores de active dos infos resgatados
                        .sum() // soma os valores
        );

        // dentre os n2 países com maiores valores de "Active", a soma das "Deaths" dos n3 países com menores valores de "Confirmed".
        System.out.println(
                infos.stream() // stream de informações da lista
                        .sorted(Comparator.comparingInt(info -> -info.active)) // ordena por active em ordem decrescente
                        .limit(n2) // pega os n2 primeiros (com mais active)
                        .sorted(Comparator.comparingInt(info -> info.confirmed)) // ordena por confirmed crescente
                        .limit(n3) // pega os n3 primeiros (menor confirmed entre os n2 com mais active)
                        .mapToInt(info -> info.deaths) // mapeia os valores de death dos infos resgatados
                        .sum() // soma os valores
        );

        // os n4 países com os maiores valores de "Confirmed". Os nomes devem estar em ordem alfabética.
        System.out.println(
                infos.stream() // stream de informações da lista
                        .sorted(Comparator.comparingInt(info -> -info.confirmed)) // ordena por confirmed em ordem decrescente
                        .limit(n4) // pega os n4 primeiros
                        .map(info -> info.country) // mapeia os valores de country dos infos resgatados
                        .sorted() // ordena com base lexicográfica
                        .collect(Collectors.joining("\n")) // adiciona \n entre cada um dos infos
        );

    }
}
