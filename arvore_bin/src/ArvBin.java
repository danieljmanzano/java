import java.util.Arrays;

public class ArvBin {
    protected String[] heap;
    protected int len, quant; // len == tamanho máximo da heap. quant == posições ocupadas da heap

    public ArvBin(int len) {
        this.len = len;
        heap = new String[len];
        quant = 0;
        for (int i = 0; i < len; i++)
            heap[i] = "";
    }

    public boolean find(String v) {
        for (int i = 0; i < len; i++)
            if (v.compareTo(heap[i]) == 0)
                return true;

        return false;
    }

    protected int acha(String v) {
        for (int i = 0; i < len; i++)
            if (v.compareTo(heap[i]) == 0)
                return i;
        return -1;
    }

    protected void setNode(int i, String v) {
        if (i < len) heap[i] = v;
    }

    protected String getNode(int i) {
        if (indiceValido(i)) return heap[i];
        return ("");
    }

    public int len() {
        // Comicamente, apesar do nome da função, retorno aqui quantos elementos tem na heap (que é "quant", e nao "len")
        return quant;
    }

    public void insert(String v) {
        // 1) capacidade e duplicata
        if (quant == len) {
            System.out.println("capacidade máxima alcançada!");
            return;
        }

        // 2) percorre iterativamente até achar um slot vazio
        int i = 0;
        while (i < len && !heap[i].isEmpty()) {
            int cmp = v.compareTo(heap[i]);
            if (cmp == 0) {
                // já existe
                return;
            }
            i = (cmp < 0) ? nodeLeft(i) : nodeRight(i);
        }

        // 3) se coube no vetor, insere e conta
        if (i < len) {
            heap[i] = v;
            quant++;
            // se você estiver usando o _hook_ afterInsert, chama aqui:
        }
    }

    protected int nodeLeft(int index) {
        return 2 * index + 1;
    }

    protected int nodeRight(int index) {
        return 2 * index + 2;
    }

    protected boolean indiceValido(int index) {
        return (index >= 0 && index < len && !heap[index].isEmpty());
    }

    public boolean remove(String v) {
        int index = acha(v);
        if (index == -1) return false;

        int left = nodeLeft(index), right = nodeRight(index);
        boolean noLeft  = left  >= len || heap[left].isEmpty();
        boolean noRight = right >= len || heap[right].isEmpty();

        // 1) nó folha
        if (noLeft && noRight) {
            heap[index] = "";
            quant--;
            return true;
        }

        // 2) só um filho -> sobe toda a subárvore
        if (noLeft || noRight) {
            int child = noLeft ? right : left;
            String[] temp = Arrays.copyOf(heap, len);
            adjust(child, child - index, temp);
            quant--;
            return true;
        }

        // 3) dois filhos -> escolher sucessor mais próximo
        //    a) maior da esquerda
        int succL = left, distL = 1;
        while (true) {
            int r = nodeRight(succL);
            if (r < len && !heap[r].isEmpty()) {
                succL = r;
                distL++;
            } else break;
        }
        //    b) menor da direita
        int succR = right, distR = 1;
        while (true) {
            int l = nodeLeft(succR);
            if (l < len && !heap[l].isEmpty()) {
                succR = l;
                distR++;
            } else break;
        }
        //    c) escolhe o mais próximo
        int successor = (distL <= distR ? succL : succR);
        String succVal = heap[successor];

        // 4) remove o sucessor da sua posição original
        int sLeft = nodeLeft(successor), sRight = nodeRight(successor);
        boolean sNoLeft  = sLeft  >= len || heap[sLeft].isEmpty();
        boolean sNoRight = sRight >= len || heap[sRight].isEmpty();

        if (sNoLeft && sNoRight) {
            heap[successor] = "";
        } else {
            int child = sNoLeft ? sRight : sLeft;
            String[] temp = Arrays.copyOf(heap, len);
            adjust(child, child - successor, temp);
        }
        quant--;

        // 5) substitui valor no index original
        heap[index] = succVal;
        return true;
    }

    /**
     * Move recursivamente a subárvore a partir de `filho` para cima em `diff`,
     * usando `temp` como snapshot da árvore antes da remoção.
     */
    protected void adjust(int filho, int diff, String[] temp) {
        if (filho >= len || temp[filho].isEmpty()) return;

        // copia o valor para a nova posição
        heap[filho - diff] = temp[filho];
        // limpa o nó antigo se tiver sido movido
        if (heap[filho - diff].equals(temp[filho])) {
            heap[filho] = "";
        }

        // avança para os filhos, duplicando o deslocamento
        adjust(nodeLeft(filho),  diff * 2, temp);
        adjust(nodeRight(filho), diff * 2, temp);
    }


    protected int countNodes(int i) {
        if (i < heap.length && !heap[i].isEmpty())
            return countNodes(nodeLeft(i)) + countNodes(nodeRight(i)) + 1;
        else
            return 0;
    }

    protected boolean isBalance(int i) {
        if (i < heap.length && !heap[i].isEmpty()) {
            return Math.abs(countNodes(nodeLeft(i)) - countNodes(nodeRight(i))) <= 1 && isBalance(nodeLeft(i)) && isBalance(nodeRight(i));
        } else
            return true;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n"); // Início do formato DOT

        // Para casos onde só existe a raíz (deve printar mesmo sem os filhos)
        if (indiceValido(0) && !indiceValido(nodeRight(0)) && !indiceValido(nodeLeft(0)))
            sb.append("\"").append(0).append(" ").append(heap[0]).append("\" ");

        // Percorre todos os nós não vazios
        for (int i = 0; i < len; i++) {
            if (indiceValido(i)) {
                // Adiciona as relações com os filhos (se existirem)
                int esq = nodeLeft(i);
                int dir = nodeRight(i);

                // Filho esquerdo
                if (indiceValido(nodeLeft(i))) {
                    sb.append("\"").append(i).append(" ").append(heap[i]).append("\"");
                            sb.append(" ->")
                            .append("\"").append(esq).append(" ").append(heap[esq]).append("\" ")
                            .append("\n");
                }

                // Filho direito
                if (indiceValido(nodeRight(i))) {
                    sb.append("\"").append(i).append(" ").append(heap[i]).append("\"")
                            .append(" ->")
                            .append("\"").append(dir).append(" ").append(heap[dir]).append("\" ")
                            .append("\n");
                }

            }
        }

        sb.append("}"); // Fechamento do formato DOT
        return sb.toString();
    }

}
