public class ArvAVL extends ArvBin {

    public ArvAVL(int len) {
        super(len);
    }

    @Override
    public void insert(String v) {
        if (quant == len) {
            System.out.println("Capacidade máxima alcançada!");
            return;
        }
        if (find(v)) return;

        insertAVL(0, v);
        quant++;
    }

    private int insertAVL(int index, String v) {
        if (index >= len || index < 0) return -1;

        if (heap[index].isEmpty()) {
            heap[index] = v;
            return index;
        }

        int childIndex;
        if (v.compareTo(heap[index]) < 0) {
            childIndex = filhoEsq(index);
        } else {
            childIndex = filhoDir(index);
        }

        int insertedIndex = insertAVL(childIndex, v);
        if (insertedIndex == -1) return -1;

        rebalance(index);
        return index;
    }

    @Override
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

        // Reinserir todos os elementos mantendo o balanceamento AVL
        for (String elemento : elementos) {
            insert(elemento);
        }

        return true;
    }


    private void rebalance(int index) {
        int balance = getBalance(index);

        if (balance > 1) {
            if (getBalance(filhoEsq(index)) >= 0) {
                rotateRight(index);
            } else {
                rotateLeft(filhoEsq(index));
                rotateRight(index);
            }
        } else if (balance < -1) {
            if (getBalance(filhoDir(index)) <= 0) {
                rotateLeft(index);
            } else {
                rotateRight(filhoDir(index));
                rotateLeft(index);
            }
        }
    }


    private int getBalance(int index) {
        if (index < 0 || index >= len || heap[index].isEmpty()) return 0;
        return height(filhoEsq(index)) - height(filhoDir(index));
    }

    private int height(int index) {
        if (index < 0 || index >= len || heap[index].isEmpty()) return -1;
        return 1 + Math.max(height(filhoEsq(index)), height(filhoDir(index)));
    }

    private void rotateLeft(int index) {
        int right = filhoDir(index);
        if (!indiceValido(right)) return;

        String oldRoot = heap[index];
        String newRoot = heap[right];

        int leftOfRight = filhoEsq(right);
        int rightOfRight = filhoDir(right);

        String[] leftSubtreeOfRight = cloneSubtree(leftOfRight);
        String[] rightSubtreeOfRight = cloneSubtree(rightOfRight);
        String[] leftSubtreeOfRoot = cloneSubtree(filhoEsq(index));

        // Limpa as subárvores antigas
        clearSubtree(index);

        heap[index] = newRoot;

        int newLeft = filhoEsq(index);
        int newRight = filhoDir(index);

        if (newLeft < len) {
            heap[newLeft] = oldRoot;

            restoreSubtree(leftSubtreeOfRoot, filhoEsq(newLeft));
            restoreSubtree(leftSubtreeOfRight, filhoDir(newLeft));
        }

        restoreSubtree(rightSubtreeOfRight, newRight);
    }

    private void rotateRight(int index) {
        int left = filhoEsq(index);
        if (!indiceValido(left)) return;

        String oldRoot = heap[index];
        String newRoot = heap[left];

        int rightOfLeft = filhoDir(left);
        int leftOfLeft = filhoEsq(left);

        String[] rightSubtreeOfLeft = cloneSubtree(rightOfLeft);
        String[] leftSubtreeOfLeft = cloneSubtree(leftOfLeft);
        String[] rightSubtreeOfRoot = cloneSubtree(filhoDir(index));

        clearSubtree(index);

        heap[index] = newRoot;

        int newRight = filhoDir(index);
        int newLeft = filhoEsq(index);

        if (newRight < len) {
            heap[newRight] = oldRoot;

            restoreSubtree(rightSubtreeOfRoot, filhoDir(newRight));
            restoreSubtree(rightSubtreeOfLeft, filhoEsq(newRight));
        }

        restoreSubtree(leftSubtreeOfLeft, newLeft);
    }


    private String[] cloneSubtree(int index) {
        String[] clone = new String[len];
        cloneRecursive(index, 0, clone);
        return clone;
    }

    private void cloneRecursive(int indexOrig, int indexDest, String[] clone) {
        if (!indiceValido(indexOrig) || indexDest >= len) return;

        clone[indexDest] = heap[indexOrig];
        cloneRecursive(filhoEsq(indexOrig), filhoEsq(indexDest), clone);
        cloneRecursive(filhoDir(indexOrig), filhoDir(indexDest), clone);
    }

    private void restoreSubtree(String[] clone, int startIndex) {
        restoreRecursive(clone, 0, startIndex);
    }

    private void restoreRecursive(String[] clone, int indexOrig, int indexDest) {
        if (indexOrig >= len || indexDest >= len) return;
        if (clone[indexOrig] == null || clone[indexOrig].isEmpty()) return;

        heap[indexDest] = clone[indexOrig];
        restoreRecursive(clone, filhoEsq(indexOrig), filhoEsq(indexDest));
        restoreRecursive(clone, filhoDir(indexOrig), filhoDir(indexDest));
    }

    private void clearSubtree(int index) {
        if (index >= len || heap[index].isEmpty()) return;

        clearSubtree(filhoEsq(index));
        clearSubtree(filhoDir(index));
        heap[index] = "";
    }
}