// obs.: nao uso matriz de verdade como tabuleiro. uso um vetor normal e umas ideias matematicas para manipular ele como se fosse uma matriz (pensando no tabuleiro)

public class Table {
    private int i, j, pos; // i e j marcam a posição do 0 na matriz imaginaria do tabuleiro, pos é a posiçao do zero no vetor real
    private int[] tabuleiro; // vetor com os numeros
    private int tam, lado; // tam é quantos numeros tenho, lado é o tamanho do lado numa matriz imaginaria


    public void setTable(int[] vetor, int tam) {
        tabuleiro = vetor;
        this.tam = tam;
        lado = (int) Math.sqrt(tam); // o tabuleiro é um quadrado, então o lado dele é a raiz do tamanho
        posVazia(); // fazendo isso, salvo o i e j da posição vazia do tabuleiro
    }

    private void posVazia() { // função pra salvar em i e j a posição que ocupa meu 0 na matriz imaginaria e em pos a posição no vetor tabuleiro
        for (int i = 0; i < this.tam; i++) {
            if(this.tabuleiro[i] == 0) {
                this.i = i % lado;
                this.j = i / lado;
                this.pos = i;
            }
        }
    }

    public void moveUp() {
        if (j != lado - 1) { // caso minha posição vazia não esteja na borda inferior, posso mover pra cima
            j++;
            tabuleiro[pos] = tabuleiro[pos + lado];
            tabuleiro[pos + lado] = 0;
            pos += lado;
        } // else System.out.println("impossível mover para cima!"); // no runcodes dá ruim se deixar a mensagem... mas caso queira usar é só tirar de comentario

    }

    public void moveDown() {
        if (j != 0) {
            j--;
            tabuleiro[pos] = tabuleiro[pos - lado];
            tabuleiro[pos - lado] = 0;
            pos -= lado;
        } // else System.out.println("impossível mover para baixo!");
    }

    public void moveLeft() {
        if (i != lado - 1) {
            i++;
            tabuleiro[pos] = tabuleiro[pos + 1];
            tabuleiro[pos + 1] = 0;
            pos++;
        } // else System.out.println("impossível mover para esquerda!");
    }

    public void moveRight() {
        if (i != 0) {
            i--;
            tabuleiro[pos] = tabuleiro[pos - 1];
            tabuleiro[pos - 1] = 0;
            pos--;
        } // else System.out.println("impossível mover para direita!");
    }

    public void printTable() {

        String linhaDivisoria = "+"; // pra fazer a linha divisória (ex: +------+------+------+)
        for (int k = 0; k < lado; k++)
            linhaDivisoria += "------+";


        for (int i = 0; i < lado; i++) { // pra fazer cada linha (com os numeros)
            System.out.println(linhaDivisoria);
            System.out.print("|");
            for (int j = 0; j < lado; j++) {
                int index = i * lado + j;
                System.out.printf("  %2d  |", tabuleiro[index]);
            } // detalhe: o i e o j aqui nao tem a ver com o this.i e this.j
            System.out.println();
        }
        System.out.println(linhaDivisoria);
        System.out.println();
    }

    public boolean resultado() { // verifica se o tabuleiro ta em ordem (numeros crescentes)
        for (int i = 0; i < tam - 1; i++)
            if (tabuleiro[i] > tabuleiro[i + 1])
                return false;

        return true;
    }
}
