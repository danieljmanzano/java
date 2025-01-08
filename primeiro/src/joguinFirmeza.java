import java.util.Random;
import java.util.Scanner;

public class joguinFirmeza {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        int aleatorio = new Random().nextInt(50);
        System.out.println("adivinhe o número aleatório em até cinco tentativas (nao passa de 50");
        int num;

        for (int i = 0; i < 5; i++) {
            num = leitura.nextInt();
            if (num > aleatorio)
                System.out.println("seu número é maior!");
            else if (num < aleatorio)
                System.out.println("seu número é menor!");
            else {
                System.out.println("pabens");
                break;
            }
        }
    }
}
