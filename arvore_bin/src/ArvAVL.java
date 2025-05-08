import java.util.Arrays;
public class ArvAVL extends ArvBin {

    public ArvAVL(int len) {
        super(len);
    }

    private int nodeHeight(int current){
        if(current >= this.heap.length || this.heap[current].isEmpty()){
            return -1;
        }
        return Math.max(nodeHeight(current*2 +1), nodeHeight(current*2 + 2)) + 1;
    }

    private int balanceFactor(int root){
        return nodeHeight(root*2+1) - nodeHeight(root*2+2);
    }

    private void deleteSubtree(int root){
        if(root >= this.heap.length || this.heap[root].isEmpty())
            return;
        deleteSubtree(nodeLeft(root));
        deleteSubtree(nodeRight(root));
        this.heap[root] = "";
    }

    private void moveSubTree(String[] copy, int dest, int src){
        if(src >= this.heap.length || copy[src].isEmpty() || dest >= this.heap.length){
            return;
        }
        this.heap[dest] = copy[src];
        moveSubTree(copy, nodeLeft(dest), nodeLeft(src));
        moveSubTree(copy, nodeRight(dest), nodeRight(src));
    }

    private void rightRotation(int root){
        int l = nodeLeft(root);
        if(l >= this.heap.length || this.heap[l].isEmpty())
            return;
        String[] copy = Arrays.copyOf(this.heap, this.heap.length);

        int r = nodeRight(root);
        int ll = nodeLeft(l);
        int lr = nodeRight(l);

        String oldRoot = this.heap[root];
        String newRoot = this.heap[l];

        deleteSubtree(root);

        this.heap[root] = newRoot;
        int newR = nodeRight(root);
        if(newR < this.heap.length){
            this.heap[newR] = oldRoot;
        }

        if(lr < copy.length && !copy[lr].isEmpty()){
            moveSubTree(copy, nodeLeft(newR), lr);
        }

        if(r < copy.length && !copy[r].isEmpty()){
            moveSubTree(copy, nodeRight(newR), r);
        }

        if(ll < copy.length && !copy[ll].isEmpty()){
            moveSubTree(copy, nodeLeft(root), ll);
        }
    }

    private void leftRotation(int root){
        int r = nodeRight(root);
        if(r >= this.heap.length || this.heap[r].isEmpty())
            return;
        
        String[] copy = Arrays.copyOf(this.heap, this.heap.length);
        
        int l = nodeLeft(root);
        int rl = nodeLeft(r);
        int rr = nodeRight(r);

        String oldRoot = this.heap[root];
        String newRoot = this.heap[r];

        deleteSubtree(root);

        this.heap[root] = newRoot;
        int newL = nodeLeft(root);
        if(newL < this.heap.length){
            this.heap[newL] = oldRoot;
        }

        if(l < copy.length && !copy[l].isEmpty()){
            moveSubTree(copy, nodeLeft(newL), l);
        }

        if(rl < copy.length && !copy[rl].isEmpty()){
            moveSubTree(copy, nodeRight(newL), rl);
        }

        if(rr < copy.length && !copy[rr].isEmpty()){
            moveSubTree(copy, nodeRight(root), rr);
        }
    }

    private void balance(int root){
        if(root >= this.heap.length || this.heap[root].isEmpty())
            return;
        int bf = balanceFactor(root);

        if(bf == -2){
            if(balanceFactor(nodeRight(root)) <= 0)
                leftRotation(root);
            else{
                rightRotation(nodeRight(root));
                leftRotation(root);
            }
        }
        else if(bf == 2){
            if(balanceFactor(nodeLeft(root)) >= 0)
                rightRotation(root);
            else{
                leftRotation(nodeLeft(root));
                rightRotation(root);
            }
        }
        
        if(root != 0)
            balance((root-1)/2);
    }

    

    @Override
    public void insert(String v) {
        super.insert(v);
        int index = acha(v);
        if(index != -1)
            balance(index);
    }

    @Override
    public boolean remove(String v) {
        int index = acha(v);
        if(index == -1)
            return false;
        
        boolean leftAbsent = nodeLeft(index) >= this.heap.length || this.heap[nodeLeft(index)].isEmpty();
        boolean rightAbsent = nodeRight(index) >= this.heap.length || this.heap[nodeRight(index)].isEmpty();

        if(leftAbsent && rightAbsent){
            this.heap[index] = "";
            return true;
        }

        if(leftAbsent){
            String[] copy = Arrays.copyOf(heap, heap.length);
            adjust(nodeRight(index), nodeRight(index)-index, copy);
            balance(index);
            return true;
        }

        if(rightAbsent){
            String[] copy = Arrays.copyOf(heap, heap.length);
            adjust(nodeLeft(index), nodeLeft(index) - index, copy);
            balance(index);
            return true;
        }

        int substitute = nodeLeft(index);
        while(nodeRight(substitute) < heap.length && !heap[nodeRight(substitute)].isEmpty()){
            substitute = nodeRight(substitute);
        }

        String substituteString = heap[substitute];

        leftAbsent = nodeLeft(substitute) >= heap.length || heap[nodeLeft(substitute)].isEmpty();
        rightAbsent = nodeRight(substitute) >= heap.length || heap[nodeRight(substitute)].isEmpty();

        if(leftAbsent && rightAbsent){
            heap[substitute] = "";
        }
        else if(leftAbsent){
            String[] copy = Arrays.copyOf(heap, heap.length);
            adjust(nodeRight(substitute), nodeRight(substitute)-substitute, copy);
        }
        else if(rightAbsent){
            String[] copy = Arrays.copyOf(heap, heap.length);
            adjust(nodeLeft(substitute), nodeLeft(substitute)-substitute, copy);
        }
        heap[index] = substituteString;
        balance(index);
        quant--; // Como não é usado o super.remove, decrementa quant aqui
        return true;
    }
}