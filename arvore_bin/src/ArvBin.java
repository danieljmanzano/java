
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
        if (quant == len) {
            System.out.println("capacidade máxima alcançada!");
            return;
        }
        if (find(v)) return;

        insertAux(0, v);
        quant++;
    }

    protected boolean insertAux(int index, String v) {
        if (index >= len) return false;

        if (heap[index].isEmpty()) {
            heap[index] = v;
            return true;
        }

        if (v.compareTo(heap[index]) < 0) {
            return insertAux(nodeLeft(index), v);
        } else {
            return insertAux(nodeRight(index), v);
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

        // Reconstruir a árvore do zero sem o elemento removido
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

        // Reinserir todos os elementos
        for (String elemento : elementos) {
            insert(elemento);
        }

        return true;
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
