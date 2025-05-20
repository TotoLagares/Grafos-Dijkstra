package Metodo;

import implementaciones.Grafo;

public class Metodo extends Grafo {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.agregarNodo(2);
        grafo.agregarNodo(4);
        grafo.agregarNodo(6);
        grafo.agregarNodo(8);

        grafo.agregarArista(2,4, 150);
        grafo.agregarArista(2,6, 150);
        grafo.agregarArista(4,6, 300);
        grafo.agregarArista(6,8, 350);
        grafo.agregarArista(8,6, 350);
        grafo.agregarArista(8,4, 450);

        grafo.eliminarNodo(4);

        grafo.verAristas();//columnas <-- / filas -->
    }
}
