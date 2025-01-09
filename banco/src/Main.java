import java.util.Scanner;

//"desafio" da alura pra treinar. muito basico digassi de passage, mas fiz aí

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        int opcao = 0;
        float saldo = 0;

        String telaInicial = """
                bem vindo ao banco do daniel
                insira seu nome para criar uma conta""";

        String telaLoop = """
                
                digite um dos números a seguir de acordo com sua opção desejada:
                1 - consultar dados
                2 - depositar valor
                3 - sacar valor
                4 - sair""";

        System.out.println(telaInicial);
        String nome = leitura.nextLine();

        while (true) {
            System.out.println(telaLoop);
            opcao = leitura.nextInt();

            if (opcao == 1) {
                System.out.println("\nnome do usuário: " + nome);
                System.out.println("saldo de R$ " + saldo);

            } else if (opcao == 2) {
                System.out.println("digite o valor a ser depositado");
                float depositar = leitura.nextFloat();
                saldo += depositar;
                System.out.println("\nnovo saldo de R$ " + saldo);

            } else if (opcao == 3) {
                System.out.println("digite o valor a ser retirado");
                float sacar = leitura.nextFloat();

                if (sacar > saldo)
                    System.out.println("saldo insuficiente!");

                else{
                    saldo -= sacar;
                    System.out.println("\nvocê sacou R$ " + sacar + ". novo saldo de R$ " + saldo);
                }

            } else if (opcao == 4)
                break;

            else System.out.println("\ndigite uma opçao válida");

        }

        System.out.println("obrigado por usar nosso serviço. volte sempre");
    }
}
//que boberaiada. estou me sentindo uma criança