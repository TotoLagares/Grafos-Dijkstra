package implementaciones;

import api.GrafoTDA;

public class Grafo implements GrafoTDA {
    int[] nodos = new int[20];
    int[][] aristas = new int[20][20];
    int numNodos = 0;
    final int INF = Integer.MAX_VALUE;

    public Grafo() { // O(n**2)
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                aristas[i][j] = INF;
            }
        }
    }

    public void agregarNodo(int valor) { // O(cte)
        nodos[numNodos] = valor;
        numNodos++;
    }

    public void agregarArista(int u, int v, int peso) { // O(n)
        int i = obtenerIndiceNodo(u);
        int j = obtenerIndiceNodo(v);
        if (i != -1 && j != -1) {
            aristas[i][j] = peso;
        }
    }

    public void eliminarArista(int u, int v) { // O(n)
        int i = obtenerIndiceNodo(u);
        int j = obtenerIndiceNodo(v);
        if (i != -1 && j != -1) {
            aristas[i][j] = INF;
        }
    }
    public void eliminarNodo(int valor) { // // O(n**2)
        int indice=obtenerIndiceNodo(valor);
       nodos[indice] = 0;
       for (int i=indice; i<numNodos-1; i++){
           nodos[i]=nodos[i+1];
       }
        // Mover filas hacia arriba
        for (int i = indice; i < numNodos - 1; i++) {
            for (int j = 0; j < numNodos; j++) {
                aristas[i][j] = aristas[i + 1][j];
            }
        }
        // Mover columnas hacia la izquierda
        for (int j = indice; j < numNodos - 1; j++) {
            for (int i = 0; i < numNodos - 1; i++) {
                aristas[i][j] = aristas[i][j + 1];
            }
        }
        numNodos--;
    }

    public void dijkstra(int origenValor) { // O(n**2)
        int origen = obtenerIndiceNodo(origenValor);
        if (origen == -1) {
            System.out.println("Nodo de origen no encontrado.");
            return;
        }

        int[] distancia = new int[numNodos];
        boolean[] visitado = new boolean[numNodos];
        int[] previo = new int[numNodos];

        for (int i = 0; i < numNodos; i++) {
            distancia[i] = INF;
            previo[i] = -1;
        }
        distancia[origen] = 0;

        for (int i = 0; i < numNodos - 1; i++) {
            int u = verticeMinDistancia(distancia, visitado);
            if (u == -1) break;

            visitado[u] = true;

            for (int v = 0; v < numNodos; v++) {
                if (!visitado[v] &&
                        aristas[u][v] != INF &&
                        distancia[u] + aristas[u][v] < distancia[v]) {

                    distancia[v] = distancia[u] + aristas[u][v];
                    previo[v] = u;
                }
            }
        }

        mostrarDistancias(distancia, previo, origen);
    }


    private int verticeMinDistancia(int[] distancia, boolean[] visitado) { //O(n)
        int min = INF;
        int minIndex = -1;

        for (int i = 0; i < numNodos; i++) {
            if (!visitado[i] && distancia[i] < min) {
                min = distancia[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    private void mostrarDistancias(int[] distancia, int[] previo, int origen) { // O(n)
        System.out.println("Distancias y caminos mínimos desde el nodo " + nodos[origen] + ":");

        for (int i = 0; i < numNodos; i++) {
            System.out.print("→ Hasta " + nodos[i] + " =  peso total ");
            if (distancia[i] == INF) {
                System.out.println("∞ (inaccesible)");
            } else {
                System.out.print(distancia[i] + " | Camino: ");
                mostrarCamino(previo, i);
                System.out.println();
            }
        }
    }


    private void mostrarCamino(int[] previo, int destino) { // O(cte)
        if (previo[destino] != -1) {
            mostrarCamino(previo, previo[destino]);
        }
        System.out.print(nodos[destino] + " ");
    }


    private int obtenerIndiceNodo(int valor) { // O(n)
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    public void verAristas() { //función de testeo
        System.out.println("Matriz de Adyacencia (aristas):");
        System.out.print("    ");
        for (int i = 0; i < numNodos; i++) {
            System.out.printf("%4d", nodos[i]);
        }
        System.out.println();
        for (int i = 0; i < numNodos; i++) {
            System.out.printf("%4d", nodos[i]);
            for (int j = 0; j < numNodos; j++) {
                if (aristas[i][j] == INF) {
                    System.out.print("   -");
                } else {
                    System.out.printf("%4d", aristas[i][j]);
                }
            }
            System.out.println();
        }
    }
}
