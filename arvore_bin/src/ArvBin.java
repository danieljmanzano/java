/*
* alguns dos nomes de variáveis e de funções foram definidos especificamente no exercício, então talvez fique meio sem sentido pensando aos nomes que eu dou pras coisas
*/



public class ArvBin {
    private String[] heap;
    private int len, quant; // len == tamanho máximo da heap. quant == posições ocupadas da heap

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

    private int acha(String v) {
        for (int i = 0; i < len; i++)
            if (v.compareTo(heap[i]) == 0)
                return i;
        return -1;
    }


    public int len() {
        // comicamente, apesar do nome da função, retorno aqui quantos elementos tem na heap (que é "quant", e nao "len")
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

    private boolean insertAux(int index, String v) {
        if (index >= len) return false;

        if (heap[index].isEmpty()) {
            heap[index] = v;
            return true;
        }

        if (v.compareTo(heap[index]) < 0) {
            return insertAux(filhoEsq(index), v);
        } else {
            return insertAux(filhoDir(index), v);
        }
    }


    private int filhoEsq(int index) {
        return 2 * index + 1;
    }


    private int filhoDir(int index) {
        return 2 * index + 2;
    }

    private boolean indiceValido(int index) {
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


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n"); // Início do formato DOT

        // Percorre todos os nós não vazios
        for (int i = 0; i < len; i++) {
            if (indiceValido(i)) {
                // Adiciona as relações com os filhos (se existirem)
                int esq = filhoEsq(i);
                int dir = filhoDir(i);

                // Filho esquerdo
                if (indiceValido(filhoEsq(i))) {
                    sb.append("\"").append(i).append(" ").append(heap[i]).append("\"")
                            .append(" -> ")
                            .append("\"").append(esq).append(" ").append(heap[esq]).append("\"")
                            .append("\n");
                }

                // Filho direito
                if (indiceValido(filhoDir(i))) {
                    sb.append("\"").append(i).append(" ").append(heap[i]).append("\"")
                            .append(" -> ")
                            .append("\"").append(dir).append(" ").append(heap[dir]).append("\"")
                            .append("\n");
                }
            }
        }

        sb.append("}"); // Fechamento do formato DOT
        return sb.toString();
    }

}
