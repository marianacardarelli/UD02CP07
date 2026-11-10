import java.util.ArrayList;
import java.util.List;

public class Nodo extends Thread {
    private List<Integer> lista;

    public Nodo(List<Integer> lista) {
        this.lista = lista;
    }

    @Override
    public void run() {
        if (lista.size() > 1) {
            int mitad = lista.size() / 2;
            List<Integer> lista1 = new ArrayList<>();
            List<Integer> lista2 = new ArrayList<>();
            anhade(lista1, 0, mitad);
            anhade(lista2, mitad, lista.size());

            Nodo nodo1 = new Nodo(lista1);
            Nodo nodo2 = new Nodo(lista2);
            nodo1.start();
            nodo2.start();
            try {
                nodo1.join();
                nodo2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            mezcla(lista1, lista2);
        }
    }

    // Añade a l los elementos de la lista del nodo desde 'desde' (incluido) hasta 'hasta' (excluido)
    private void anhade(List<Integer> l, int desde, int hasta) {
        l.addAll(lista.subList(desde, hasta));
    }

    // Mezcla de forma ordenada l1 y l2 (ya ordenadas) dejando el resultado en lista
    private void mezcla(List<Integer> l1, List<Integer> l2) {
        lista.clear();
        int i = 0;
        int j = 0;

        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) <= l2.get(j)) {
                lista.add(l1.get(i));
                i++;
            } else {
                lista.add(l2.get(j));
                j++;
            }
        }
        while (i < l1.size()) {      // sobrantes de l1
            lista.add(l1.get(i));
            i++;
        }
        while (j < l2.size()) {      // sobrantes de l2
            lista.add(l2.get(j));
            j++;
        }
    }
}