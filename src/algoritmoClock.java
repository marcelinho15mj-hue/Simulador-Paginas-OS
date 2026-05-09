import java.util.*;

public class algoritmoClock {

    public static int executar(int[] paginas, int frames) {
        System.out.println(">>> Método 4 - Clock");
        System.out.println("-------------------------------------------------------");

        int[] memoria = new int[frames];
        int[] bitUso = new int[frames];
        Arrays.fill(memoria, -1);

        int ponteiro = 0, faltas = 0;

        for (int pagina : paginas) {
            boolean hit = false;

            for (int j = 0; j < frames; j++) {
                if (memoria[j] == pagina) {
                    bitUso[j] = 1;
                    hit = true;
                    break;
                }
            }

            if (!hit) {
                faltas++;

                while (bitUso[ponteiro] == 1) {
                    bitUso[ponteiro] = 0;
                    ponteiro = (ponteiro + 1) % frames;
                }

                memoria[ponteiro] = pagina;
                bitUso[ponteiro] = 1;
                ponteiro = (ponteiro + 1) % frames;
            }
        }

        System.out.printf("  => Total de faltas Clock: %d%n%n", faltas);
        return faltas;
    }
}