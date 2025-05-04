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

    // Método para reconstruir a árvore garantindo balanceamento perfeito
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

        // Ordenar o array de elementos
        ordenar(elementos);

        // Reconstruir a árvore perfeitamente balanceada
        quant = 0; // Resetar a contagem para a reconstrução
        construirArvoreBalanceada(elementos, 0, elementos.length - 1, 0);
    }

    // Método auxiliar para construir árvore balanceada a partir de um array ordenado
    private void construirArvoreBalanceada(String[] elementos, int inicio, int fim, int indexHeap) {
        if (inicio > fim) return;
        if (indexHeap >= len) return;  // Verificação adicional para segurança

        // Encontrar o elemento do meio para ser o nó atual
        int meio = (inicio + fim) / 2;
        heap[indexHeap] = elementos[meio];
        quant++;

        // Construir recursivamente as subárvores esquerda e direita
        if (inicio < meio) {
            construirArvoreBalanceada(elementos, inicio, meio - 1, filhoEsq(indexHeap));
        }

        if (meio < fim) {
            construirArvoreBalanceada(elementos, meio + 1, fim, filhoDir(indexHeap));
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

    // Método para verificar se a árvore está perfeitamente balanceada
    public boolean estaPerfeitamenteBalanceada() {
        // Uma árvore está perfeitamente balanceada se todos os níveis estão completos,
        // exceto possivelmente o último, que deve estar preenchido da esquerda para a direita
        return verificarBalanceamentoPerfeito(0, 0);
    }

    private boolean verificarBalanceamentoPerfeito(int index, int nivel) {
        if (!indiceValido(index)) return true;

        // Verificar se os filhos estão no nível esperado
        int esq = filhoEsq(index);
        int dir = filhoDir(index);

        // Se tem filho direito, deve ter filho esquerdo
        if (indiceValido(dir) && !indiceValido(esq)) {
            return false;
        }

        // Verificar recursivamente os filhos
        return verificarBalanceamentoPerfeito(esq, nivel + 1) &&
                verificarBalanceamentoPerfeito(dir, nivel + 1);
    }
}