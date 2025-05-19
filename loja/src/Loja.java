import java.util.ArrayList;

public class Loja {
    private int livros, cds, dvds;
    private ArrayList<Produto> produtos;

    public Loja() {
        // como não tem tamanho definido, coloquei 1000 (talvez mexa depois? nao sei)
        this.produtos = new ArrayList<>();
        livros = cds = dvds = 0;
    }

    /* insere um certo produto no "sistema" (não no estoque!) */
    public void inserir(Produto p) {
        for (Produto prod : produtos) {
            if (prod.codigo.compareTo(p.codigo) == 0) {
                System.out.println("***Erro: Código já cadastrado: " + p.codigo);
                return;
            }
        }

        produtos.add(p);
        System.out.println("Operação realizada com sucesso");
    }

    /* adiciona "quant" unidades do produto especificado pelo código no estoque */
    public boolean adicionar(String codigo, int quant) {
        for (Produto p : produtos) {
            if (p.codigo.compareTo(codigo) == 0) { // encontrou o produto

                // atualiza contador de tipos (é usado em "sumário")
                if (p.tipo.equals("Livro")) livros += quant;
                else if (p.tipo.equals("CD")) cds += quant;
                else if (p.tipo.equals("DVD")) dvds += quant;

                p.quant += quant; // adiciona a quantidade
                return true;
            }
        }

        System.out.println("***Erro: Código inexistente: " + codigo);
        return false;
    }


    /* vende "quant" unidades do produto especificado pelo código */
    public boolean vender(String codigo, int quant) {
        for (Produto p : produtos) {
            if (p.codigo.compareTo(codigo) == 0) { // encontrou o produto
                if (p.quant >= quant) {
                    p.quant -= quant;

                    // atualiza contador de tipos
                    if (p.tipo.equals("Livro")) livros -= quant;
                    else if (p.tipo.equals("CD")) cds -= quant;
                    else if (p.tipo.equals("DVD")) dvds -= quant;

                    return true;
                } else {
                    System.out.println("***Erro: Estoque insuficiente: " + codigo + " Quantidade: " + quant);
                    return false;
                }
            }
        }

        System.out.println("***Erro: Código inexistente: " + codigo);
        return false;
    }


    /* busca um produto com base em nome ou em código. a lógica com o "ehNumero" decide qual o objeto de busca */
    public boolean procurar(String str) {
        for (Produto p : produtos) {
            // busca pelo nome
            if (p.nome.compareTo(str) == 0) {
                System.out.println("Encontrado: ");
                System.out.println(p);
                return true;
            }
        }

        for (Produto p : produtos) {
            // busca pelo codigo
            if (p.codigo.compareTo(str) == 0) {
                System.out.println("Encontrado: ");
                System.out.println(p);
                return true;
            }
        }

        return false;
    }

    public void sumario() {
        System.out.println("Operação de sumarização: ");

        /* printa primeiro tudo referente aos livros */
        for (Produto p : produtos) {
            if (p.tipo.compareTo("Livro") == 0) {
                System.out.println(p);
                System.out.println("Quantidade: " + p.quant);
            }
        }
        System.out.println();
        System.out.println("Quantidade de Livros: " + livros);
        System.out.println();

        /* depois dos cds */
        for (Produto p : produtos) {
            if (p.tipo.compareTo("CD") == 0) {
                System.out.println(p);
                System.out.println("Quantidade: " + p.quant);
            }
        }
        System.out.println();
        System.out.println("Quantidade de CDs: " + cds);
        System.out.println();

        /* e, por fim, dos dvds */
        for (Produto p : produtos) {
            if (p.tipo.compareTo("DVD") == 0) {
                System.out.println(p);
                System.out.println("Quantidade: " + p.quant);
            }
        }
        System.out.println();
        System.out.println("Quantidade de DVDs: " + dvds);
        System.out.println();
    }
}


