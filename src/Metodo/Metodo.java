package Metodo;

import implementaciones.Grafo;

public class Metodo extends Grafo {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.agregarNodo(1);
        grafo.agregarNodo(2);
        grafo.agregarNodo(3);
        grafo.agregarNodo(4);
        grafo.agregarNodo(5);
        grafo.agregarNodo(6);
        grafo.agregarNodo(7);


        grafo.agregarArista(1,2, 3);
        grafo.agregarArista(1,5, 2);
        grafo.agregarArista(2,3, 4);
        grafo.agregarArista(2,4, 6);
        grafo.agregarArista(3,4, 1);
        grafo.agregarArista(5,3, 1);
        grafo.agregarArista(5,7, 2);
        grafo.agregarArista(1,7, 3);

        grafo.eliminarNodo(6);


        grafo.verAristas();//columnas <-- / filas -->
        grafo.dijkstra(1);
    }
}
