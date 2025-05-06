import java.util.Arrays;

public class ArvBal extends ArvBin {

    public ArvBal(int len) {
        super(len);
    }

    @Override
    public void insert(String v) {
        super.insert(v);
        balanceia();
    }

    @Override
    public boolean remove(String v) {
        if (super.remove(v)) {
            balanceia();
            return true;
        }

        return false;
    }

    protected void balanceia() {
        if (!isBalance()) {
            // Coleta os elementos não vazios, ordena e reconstrói
            String[] nonEmpty = Arrays.stream(heap).filter(s -> !s.isEmpty()).sorted().toArray(String[]::new);

            Arrays.fill(heap, "");
            quant = 0;  // Resetar o contador
            reconstruirArvoreBalanceada(nonEmpty, 0, nonEmpty.length - 1);
        }

    }

    private void reconstruirArvoreBalanceada(String[] nonEmpty, int start, int end) {
        if (start > end) return;

        int mid = start + (end - start) / 2;

        super.insert(nonEmpty[mid]);

        reconstruirArvoreBalanceada(nonEmpty, start, mid - 1);
        reconstruirArvoreBalanceada(nonEmpty, mid + 1, end);
    }

}