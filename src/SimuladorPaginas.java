public class SimuladorPaginas {

    static int[] cadeiaDePaginas = {7, 0, 1, 2, 0, 3, 0, 4};
    static int NUM_FRAMES = 4;

    public static void main(String[] args) {

        int fifo  = algoritmoFIFO.executar(cadeiaDePaginas, NUM_FRAMES);
        int lru   = algoritmoLRU.executar(cadeiaDePaginas, NUM_FRAMES);
        int otimo = algoritmoOtimo.executar(cadeiaDePaginas, NUM_FRAMES);
        int clock = algoritmoClock.executar(cadeiaDePaginas, NUM_FRAMES);

        System.out.println("FIFO: " + fifo);
        System.out.println("LRU: " + lru);
        System.out.println("Ótimo: " + otimo);
        System.out.println("Clock: " + clock);
    }
}