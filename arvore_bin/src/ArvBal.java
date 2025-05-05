import java.util.LinkedList;
import java.util.Queue;

public class ArvBal extends ArvBin {

    public ArvBal(int len) {
        super(len);
    }

    // Sobrescreve o método de inserção para garantir árvore perfeitamente balanceada
    @Override
    public void insert(String v) {
        if (quant == len) {
            System.out.println("capacidade máxima alcançada!");
            return;
        }
        if (find(v)) return;

        // Adicionar temporariamente o elemento
        // Encontrar uma posição vazia para armazenar temporariamente
        for (int i = 0; i < len; i++) {
            if (heap[i].isEmpty()) {
                heap[i] = v;
                quant++;
                break;
            }
        }

        // Reconstruir toda a árvore para garantir que esteja perfeitamente balanceada
        reconstruirArvoreBalanceada();
    }

    @Override
    public boolean remove(String v) {
        int index = acha(v);
        if (index == -1) return false;

        // Coletar todos os elementos exceto o que será removido
        String[] elementos = new String[quant - 1];
        int pos = 0;
        for (int i = 0; i < len; i++) {
            if (!heap[i].isEmpty() && !heap[i].equals(v)) {
                elementos[pos++] = heap[i];
            }
        }

        // Reinicializar a árvore
        for (int i = 0; i < len; i++) {
            heap[i] = "";
        }
        quant = 0;

        // Reconstruir a árvore com os elementos restantes
        for (String elemento : elementos) {
            heap[quant++] = elemento;
        }

        // Reconstruir para manter o balanceamento perfeito
        reconstruirArvoreBalanceada();

        return true;
    }

    private void reconstruirArvoreBalanceada() {
        // Ordenar os elementos existentes
        String[] elementos = new String[quant];
        int pos = 0;

        for (int i = 0; i < len; i++) {
            if (!heap[i].isEmpty()) {
                elementos[pos++] = heap[i];
                heap[i] = ""; // Limpar os nós após coletar
            }
        }

        ordenar(elementos); // Ordena os elementos por ordem alfabética

        quant = 0;

        // Inserir os elementos ordenados na árvore em ordem de nível (nível por nível, da esquerda para a direita)
        fillPerfectlyBalanced(elementos);
    }

    private void fillPerfectlyBalanced(String[] elementos) {
        // Usamos uma fila de índices para inserir em ordem de nível
        Queue<Integer> fila = new LinkedList<>();
        fila.add(0);
        int i = 0;

        while (!fila.isEmpty() && i < elementos.length) {
            int idx = fila.poll();
            if (idx >= len) continue;

            heap[idx] = elementos[i++];
            quant++;

            fila.add(nodeLeft(idx));
            fila.add(nodeRight(idx));
        }
    }



    // Implementação simples de ordenação (merge sort)
    private void ordenar(String[] arr) {
        if (arr.length <= 1) return;

        String[] temp = new String[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private void mergeSort(String[] arr, String[] temp, int esq, int dir) {
        if (esq < dir) {
            int meio = esq + (dir - esq) / 2;

            mergeSort(arr, temp, esq, meio);
            mergeSort(arr, temp, meio + 1, dir);

            merge(arr, temp, esq, meio, dir);
        }
    }

    private void merge(String[] arr, String[] temp, int esq, int meio, int dir) {
        // Copiar para o array temporário
        for (int i = esq; i <= dir; i++) {
            temp[i] = arr[i];
        }

        int i = esq;
        int j = meio + 1;
        int k = esq;

        while (i <= meio && j <= dir) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        // Copiar elementos restantes
        while (i <= meio) {
            arr[k++] = temp[i++];
        }
    }

    @Override
    public boolean isBalance(int index) {
        int profundidadeEsperada = profundidadeFolha(index);
        return verifica(index, 0, profundidadeEsperada);
    }

    // Retorna a profundidade de qualquer folha encontrada na subárvore
    private int profundidadeFolha(int index) {
        if (!indiceValido(index)) return 0;

        while (indiceValido(nodeLeft(index))) {
            index = nodeLeft(index);
        }

        int profundidade = 0;
        while (index != 0) {
            index = pai(index);
            profundidade++;
        }

        return profundidade;
    }

    // Verifica se todas as folhas estão no mesmo nível esperado
    private boolean verifica(int index, int nivelAtual, int nivelEsperado) {
        if (!indiceValido(index)) return true;

        int esq = nodeLeft(index);
        int dir = nodeRight(index);

        // Nó folha
        if (!indiceValido(esq) && !indiceValido(dir)) {
            return nivelAtual == nivelEsperado;
        }

        // Verifica recursivamente filhos
        return verifica(esq, nivelAtual + 1, nivelEsperado) &&
                verifica(dir, nivelAtual + 1, nivelEsperado);
    }

    private int pai(int index) {
        if (index == 0) return -1;
        return (index - 1) / 2;
    }

}