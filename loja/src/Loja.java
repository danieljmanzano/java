public class Loja {
    private Produto[] produtos;
    private Produto[] estoque;
    private int tam; // tamanho do estoque
    private int num; // numero de produtos cadastrados (tamanho do vetor produtos)

    public Loja() {
        this.produtos = new Produto[1000];
        this.estoque = new Produto[1000];
        tam = 0;
        num = 0;
    }

    public void inserir(Produto p) {
        produtos[num] = p;
        num++;
    }

    public boolean adicionar(int codigo, int quant) {
        for (int i = 0; i < num; i++) {
            if (produtos[i].codigo == codigo) { // quando achar o código referente àquele produto, insiro no estoque a quantidade desejada
                for (int j = tam; (j - tam) < quant; j++)
                    estoque[j] = produtos[i];
                tam += quant;
                return true;
            }
        }
        return false;
    }



}


