import java.util.*;

public class algoritmoOtimo {

    public static int executar(int[] paginas, int frames) {
        System.out.println(">>> Método 3 - Ótimo");
        System.out.println("-------------------------------------------------------");

        List<Integer> memoria = new ArrayList<>();
        int faltas = 0;

        for (int i = 0; i < paginas.length; i++) {
            int pagina = paginas[i];

            if (!memoria.contains(pagina)) {
                faltas++;

                if (memoria.size() == frames) {
                    int idxRemover = 0, maiorDist = -1;

                    for (int j = 0; j < memoria.size(); j++) {
                        int pg = memoria.get(j);
                        int proximoUso = Integer.MAX_VALUE;

                        for (int k = i + 1; k < paginas.length; k++) {
                            if (paginas[k] == pg) {
                                proximoUso = k;
                                break;
                            }
                        }

                        if (proximoUso > maiorDist) {
                            maiorDist = proximoUso;
                            idxRemover = j;
                        }
                    }

                    memoria.remove(idxRemover);
                }

                memoria.add(pagina);
            }
        }

        System.out.printf("  => Total de faltas Ótimo: %d%n%n", faltas);
        return faltas;
    }
}