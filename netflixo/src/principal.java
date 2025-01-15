import modelos.Filme;
import modelos.Serie;

// fazendo curso da alura. chegou em certo ponto que cansei de mexer aqui pq nao vi mais motivo
// o negocio aqui nao faz nada de interessante nao, o curso nao terminou em algo legal como eu esperava
// pra completar acabei me desinteressando disso aqui e nao quis inventar mais coisa pra ficar ok
// basicamente esse projeto aqui é bem cocô e só vai servir pra mais pra frente eu olhar e lembrar de algumas coisa sobre a linguagem (ou pelo menos é o que espero)
// a ideia era que fizesse a base de alguma plataforma de streaming simples. coisa só pra exercitar as ideia de POO, nada realmente aplicavel ou interessante

public class principal {
    public static void main(String[] args) {
        Filme f = new Filme();
        f.setInfoTerminal();
        f.avalia(10);
        f.avalia(7);
        f.avalia(8);
        f.printaFicha();

        Serie s = new Serie();
        s.setInfoTerminal();
        s.avalia(9);
        s.avalia(6);
        s.printaFicha();

    }
}
