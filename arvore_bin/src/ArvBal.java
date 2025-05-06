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
        // 1) coleta e limpa
        String[] elems = new String[quant];
        int p = 0;
        for (int i = 0; i < len; i++) {
            if (!heap[i].isEmpty()) {
                elems[p++] = heap[i];
                heap[i] = "";
            }
        }
        quant = 0;

        // 2) ordena os elementos
        ordenar(elems);

        // 3) reconstrói em BST height‑balanced com bias
        construirBalanceado(elems, 0, p - 1, /* heapIndex= */ 0, /* isRight= */ false);
    }

    /**
     * @param elems   array ordenado de 0..p-1
     * @param ini     início do segmento em elems
     * @param fim     fim do segmento em elems
     * @param index   posição no heap (array da árvore) onde colocar
     * @param isRight se este nó é filho “direito” de seu pai
     */
    private void construirBalanceado(String[] elems,
                                     int ini, int fim,
                                     int index,
                                     boolean isRight) {
        if (ini > fim || index >= len) return;

        int size = fim - ini + 1;
        int meio;

        if (size == 2) {
            // 2 elementos: raiz usa segundo, subárvores usam primeiro
            if (index == 0) {
                meio = ini + 1;
            } else {
                meio = ini;
            }
        } else if (index > 0 && isRight) {
            // subárvore direita maior bias → ceil
            meio = (ini + fim + 1) / 2;
        } else {
            // raiz (exceto size==2) e subárvore esquerda → floor
            meio = (ini + fim) / 2;
        }

        // coloca o nó
        heap[index] = elems[meio];
        quant++;

        // recursa: esquerda (isRight=false), direita (isRight=true)
        construirBalanceado(elems, ini, meio - 1, nodeLeft(index), false);
        construirBalanceado(elems, meio + 1, fim, nodeRight(index), true);
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
}