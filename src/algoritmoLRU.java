import java.util.*;

public class algoritmoLRU {

    public static int executar(int[] paginas, int frames) {
        System.out.println(">>> Método 2 - LRU");
        System.out.println("-------------------------------------------------------");

        LinkedList<Integer> memoria = new LinkedList<>();
        int faltas = 0;

        for (int pagina : paginas) {
            if (!memoria.contains(pagina)) {
                faltas++;
                if (memoria.size() == frames) memoria.removeFirst();
                memoria.addLast(pagina);
            } else {
                memoria.remove((Integer) pagina);
                memoria.addLast(pagina);
            }
        }

        System.out.printf("  => Total de faltas LRU: %d%n%n", faltas);
        return faltas;
    }
}