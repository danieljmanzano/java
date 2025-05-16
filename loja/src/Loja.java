import java.util.ArrayList;

public class Loja {
    private ArrayList<Produto> produtos;
    private ArrayList<Produto> estoque;

    public Loja() {
        // como não tem tamanho definido, coloquei 1000 (talvez mexa depois? nao sei)
        this.produtos = new ArrayList<>();
        this.estoque = new ArrayList<>();
    }

    /* insere um certo produto no "sistema" (não no estoque!) */
    public void inserir(Produto p) {
        produtos.add(p);
    }

    /* adiciona "quant" unidades do produto especificado pelo código no estoque */
    public boolean adicionar(int codigo, int quant) {
        for (Produto p : produtos) {
            if (p.codigo == codigo) { // se encontra o produto
                for (int i = 0; i < quant; i++)  // adiciona a quantidade desejada no estoque
                    estoque.add(p);

                return true;
            }
        }
        return false;
    }

    /* vende "quant" unidades do produto especificado pelo código */
    public boolean vender(int codigo, int quant) {
        int removidos = 0;

        for (int i = 0; i < estoque.size(); i++) {
            if (estoque.get(i).codigo == codigo) {
                estoque.remove(i);
                i--; // decrementa para não pular o próximo elemento
                removidos++;

                if (removidos == quant) return true;
            }
        }

        return false; // se não conseguiu remover a quantidade pedida
    }

    /* busca um produto com base em nome ou em código. a lógica com o "ehNumero" decide qual o objeto de busca */
    public boolean procurar(String str) {
        boolean flag = false;

        if (ehNumero(str)) {
            int codigo = Integer.parseInt(str);
            for (Produto p : produtos) {
                if (p.codigo == codigo) {
                    System.out.println(p);
                    flag = true;
                }
            }
        } else {
            for (Produto p : produtos) {
                if (p.nome.compareTo(str) == 0) {
                    System.out.println(p);
                    flag = true;
                }
            }
        }

        return flag;
    }

    // auxiliar para a pesquisa
    private boolean ehNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void sumario() {
        System.out.println(toString());
    }

    public String toString() {
        StringBuilder aux = new StringBuilder();

        for (Produto p : estoque)
            aux.append(p.toString());

        return aux.toString();
    }

}


