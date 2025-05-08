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

    

    private void rightRotation(int root){
        int l = nodeLeft(root);
        if(l >= this.heap.length || this.heap[l].isEmpty())
            return;
        String[] copy = Arrays.copyOf(this.heap, this.heap.length);

        int r = nodeRight(root);
        int rr = nodeRight(r);
        int rl = nodeLeft(r);
        int ll = nodeLeft(l);
        int lr = nodeRight(l);

        String oldRoot = this.heap[root];
        String newRoot = this.heap[l];

        deleteSubtree(root);

        this.heap[root] = newRoot;
        if(r < this.heap.length){
            this.heap[r] = oldRoot;
        }

        if(lr < copy.length && !copy[lr].isEmpty()){
            adjust(lr, lr - rl, copy);
        }

        if(r < copy.length && !copy[r].isEmpty()){
            adjust(r, r - rr, copy);
        }

        if(ll < copy.length && !copy[ll].isEmpty()){
            adjust(ll, ll - l, copy);
        }
    }

    private void leftRotation(int root){
        int r = nodeRight(root);
        if(r >= this.heap.length || this.heap[r].isEmpty())
            return;
        
        String[] copy = Arrays.copyOf(this.heap, this.heap.length);
        
        int l = nodeLeft(root);
        int lr = nodeRight(l);
        int ll = nodeLeft(l);
        int rl = nodeLeft(r);
        int rr = nodeRight(r);

        String oldRoot = this.heap[root];
        String newRoot = this.heap[r];

        deleteSubtree(root);

        this.heap[root] = newRoot;
        if(l < this.heap.length){
            this.heap[l] = oldRoot;
        }

        if(l < copy.length && !copy[l].isEmpty()){
            adjust(l, l - ll , copy);
        }

        if(rl < copy.length && !copy[rl].isEmpty()){
            adjust(rl,  rl - lr, copy);
        }

        if(rr < copy.length && !copy[rr].isEmpty()){
            adjust(rr, rr - r, copy);
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
            balance(parent(root));
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
            quant--;
            return true;
        }

        if(leftAbsent){
            String[] copy = Arrays.copyOf(heap, heap.length);
            adjust(nodeRight(index), nodeRight(index)-index, copy);
            balance(index);
            quant--;
            return true;
        }

        if(rightAbsent){
            String[] copy = Arrays.copyOf(heap, heap.length);
            adjust(nodeLeft(index), nodeLeft(index) - index, copy);
            balance(index);
            quant--;
            return true;
        }

        int substitute = nodeLeft(index);
        while(nodeRight(substitute) < heap.length && !heap[nodeRight(substitute)].isEmpty()){
            substitute = nodeRight(substitute);
        }

        // o substituto foi escolhido como o maior da esquerda para coincidir com os casos testes
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