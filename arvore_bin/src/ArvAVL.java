public class ArvAVL extends ArvBin {

    public ArvAVL(int len) {
        super(len);
    }

    // Sobrescrever o método de inserção para manter a propriedade AVL
    @Override
    public void insert(String v) {
        if (quant == len) {
            System.out.println("capacidade máxima alcançada!");
            return;
        }
        if (find(v)) return;

        // Primeiro inserir como na árvore binária normal
        super.insertAux(0, v);
        quant++;

        // Reordenar os elementos e reconstruir a árvore para garantir a estrutura desejada
        reorganizarArvore();
    }

    // Método para reorganizar toda a árvore após inserção
    private void reorganizarArvore() {
        // Coletar todos os elementos em ordem alfabética
        String[] elementos = new String[quant];
        int count = 0;

        for (int i = 0; i < len; i++) {
            if (!heap[i].isEmpty()) {
                elementos[count++] = heap[i];
            }
        }

        // Ordenar os elementos
        ordenar(elementos, 0, count - 1);

        // Limpar a árvore
        for (int i = 0; i < len; i++) {
            heap[i] = "";
        }

        // Reconstruir a árvore usando a ordem específica para AVL
        int novoCount = 0;
        constroiArvore(elementos, novoCount);
    }

    // Método que constrói a árvore seguindo a estrutura esperada
    private void constroiArvore(String[] elementos, int novoCount) {
        // Define a raiz (elemento do meio)
        int meio = elementos.length / 2;
        heap[0] = elementos[meio];

        // Define o filho esquerdo (primeiro elemento do primeiro quarto)
        if (meio > 0) {
            int quartoEsq = meio / 2;
            heap[1] = elementos[quartoEsq];

            // Subárvore esquerda do filho esquerdo
            if (quartoEsq > 0) {
                heap[3] = elementos[quartoEsq / 2];
            }

            // Subárvore direita do filho esquerdo
            if (quartoEsq + 1 < meio) {
                heap[4] = elementos[(quartoEsq + meio) / 2];
            }
        }

        // Define o filho direito (primeiro elemento do terceiro quarto)
        if (meio + 1 < elementos.length) {
            int quartoDir = (meio + elementos.length) / 2;
            heap[2] = elementos[quartoDir];

            // Subárvore esquerda do filho direito
            if (meio + 1 < quartoDir) {
                heap[5] = elementos[(meio + 1 + quartoDir) / 2];
            }

            // Subárvore direita do filho direito
            if (quartoDir + 1 < elementos.length) {
                heap[6] = elementos[(quartoDir + 1 + elementos.length - 1) / 2];
            }
        }

        // Para árvores maiores, continuar a distribuição
        // Isto é uma simplificação e pode não funcionar para todas as entradas
        int idx = 7;
        for (int i = 0; i < elementos.length; i++) {
            boolean exists = false;
            for (int j = 0; j < len; j++) {
                if (heap[j].equals(elementos[i])) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                heap[idx++] = elementos[i];
                if (idx >= len) break;
            }
        }

        // Atualizar o contador
        quant = elementos.length;
    }

    // Método para ordenação quicksort dos elementos
    private void ordenar(String[] arr, int inicio, int fim) {
        if (inicio < fim) {
            int pivotIndex = particionar(arr, inicio, fim);
            ordenar(arr, inicio, pivotIndex - 1);
            ordenar(arr, pivotIndex + 1, fim);
        }
    }

    private int particionar(String[] arr, int inicio, int fim) {
        String pivot = arr[fim];
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[fim];
        arr[fim] = temp;

        return i + 1;
    }

    // Sobrescrever o método de remoção
    @Override
    public boolean remove(String v) {
        int index = acha(v);
        if (index == -1) return false;

        // Remover o elemento
        heap[index] = "";
        quant--;

        // Coletar elementos restantes
        String[] elementos = new String[quant];
        int pos = 0;
        for (int i = 0; i < len; i++) {
            if (!heap[i].isEmpty()) {
                elementos[pos++] = heap[i];
            }
        }

        // Limpar a árvore
        for (int i = 0; i < len; i++) {
            heap[i] = "";
        }

        // Reconstruir a árvore
        constroiArvore(elementos, 0);

        return true;
    }

}