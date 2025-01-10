import br.com.netflixo.modelos.Filme;

public class principal {
    public static void main(String[] args) {
        Filme f = new Filme();
        f.setInfoTerminal();
        f.avalia(10);
        f.avalia(7);
        f.avalia(8);
        f.printaFicha();
        f.setInfoCodigo("filme do pelé", 1990, 100, true);
        f.printaFicha();
    }
}
