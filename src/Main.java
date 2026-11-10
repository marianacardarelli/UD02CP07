import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int minList = 5;
        int max = 100;

        // Fórmula: (int) (Math.random() * (max - min + 1)) + min
        int numeroAleatorio = (int) (Math.random() * (max - minList + 1)) + minList;
        List<Integer> lista = new ArrayList<>(numeroAleatorio);
        for (int i = 0; i < numeroAleatorio; i++) {
            lista.add((int) (Math.random() * max) + 1);   // valores del 1 al 100
        }

        System.out.println("=== Ordenacion por mezcla concurrente ===");
        System.out.println();
        System.out.println("Lista original (" + lista.size() + " elementos):");
        System.out.println("  " + lista);        // se muestra ANTES de ordenar

        Nodo nodoRaiz = new Nodo(lista);
        nodoRaiz.start();
        nodoRaiz.join();                          // esperar a que todo el árbol termine

        System.out.println();
        System.out.println("Lista ordenada:");
        System.out.println("  " + lista);
    }
}
