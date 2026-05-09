import java.util.*;

public class algoritmoFIFO {

    public static int executar(int[] paginas, int frames) {
        System.out.println(">>> Método 1 - FIFO (First In, First Out)");
        System.out.println("-------------------------------------------------------");

        Queue<Integer> memoria = new LinkedList<>();
        Set<Integer> emMemoria = new HashSet<>();
        int faltas = 0;

        for (int pagina : paginas) {
            if (!emMemoria.contains(pagina)) {
                faltas++;
                if (memoria.size() == frames) {
                    int removida = memoria.poll();
                    emMemoria.remove(removida);
                }
                memoria.add(pagina);
                emMemoria.add(pagina);
                System.out.printf("  Página %d -> FALTA  | Frames: %s%n", pagina, memoria);
            } else {
                System.out.printf("  Página %d -> HIT    | Frames: %s%n", pagina, memoria);
            }
        }

        System.out.printf("  => Total de faltas FIFO: %d%n%n", faltas);
        return faltas;
    }
}