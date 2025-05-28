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
           eliminarArista(indice,i+1);
           eliminarArista(i+1,indice);

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
        int[] distancia = new int[numNodos];       // Almacena las distancias mínimas desde el nodo origen.
        boolean[] visitado = new boolean[numNodos]; // Marca qué nodos ya han sido procesados.
        int[] previo = new int[numNodos];          // Guarda el nodo previo en el camino más corto hacia cada nodo.

        for (int i = 0; i < numNodos; i++) {
            distancia[i] = INF;  // Todas las distancias iniciales son infinitas (representación de inaccesibles).
            previo[i] = -1;      // Todos los nodos previos son desconocidos (-1).
        }
        distancia[origen] = 0;   // La distancia al nodo de inicio es 0.

        //Itera para calcular las distancias mínimas.
        for (int i = 0; i < numNodos - 1; i++) {
            // Encuentra el nodo con la menor distancia que aún no haya sido visitado.
            int u = verticeMinDistancia(distancia, visitado);
            if (u == -1) break;  // Si no queda nodo alcanzable, termina el bucle.

            visitado[u] = true;  // Marca el nodo actual (u) como visitado.

            // Relaja las aristas del nodo actual (u) hacia sus nodos vecinos.
            for (int v = 0; v < numNodos; v++) {
                // Comprueba que el nodo `v` no esté visitado, que haya una conexión (`aristas[u][v] != INF`),
                // y que se pueda mejorar la distancia actual hacia `v` a través de `u`.
                if (!visitado[v] &&
                        aristas[u][v] != INF &&
                        distancia[u] + aristas[u][v] < distancia[v]) {

                    // Actualiza la distancia mínima hacia el nodo `v`.
                    distancia[v] = distancia[u] + aristas[u][v];
                    // Guarda el nodo previo para reconstruir el camino mínimo.
                    previo[v] = u;
                }
            }
        }
        // Muestra los resultados finales de las distancias mínimas y los caminos óptimos.
        mostrarDistancias(distancia, previo, origen);
    }


    private int verticeMinDistancia(int[] distancia, boolean[] visitado) { // O(n)
        // Inicializa una variable para la distancia mínima encontrada.
        int min = INF;
        // Inicializa el índice del nodo con la distancia mínima actual.
        // Al principio, no se ha encontrado un nodo válido, por lo tanto se fija en -1.
        int minIndex = -1;
        // Itera sobre todos los nodos del grafo.
        for (int i = 0; i < numNodos; i++) {

            // Comprueba si el nodo no ha sido visitado previamente (`!visitado[i]`)
            // y si tiene una distancia menor que la mínima registrada hasta ahora (`distancia[i] < min`).
            if (!visitado[i] && distancia[i] < min ) {

                // Si cumple las condiciones, actualiza la distancia mínima.
                min = distancia[i];

                // Registra el índice del nodo que tiene la distancia mínima encontrada.
                minIndex = i;
            }
        }
        // Devuelve el índice del nodo con la menor distancia que aún no ha sido visitado.
        // Si no se encuentra un nodo válido, retorna -1.
        return minIndex;
    }

    private void mostrarDistancias(int[] distancia, int[] previo, int origen) { // O(n)
        System.out.println("Distancias y caminos mínimos desde el nodo " + nodos[origen] + ":");
        // Itera sobre todos los nodos del grafo.
        for (int i = 0; i < numNodos; i++) {
            // Imprime información acerca del nodo destino.
            System.out.print("→ Hasta " + nodos[i] + " =  peso total ");
            // Verifica si el nodo es inaccesible desde el nodo origen.
            if (distancia[i] == INF) {
                // Si la distancia es infinita, lo marca como inaccesible.
                System.out.println("∞ (inaccesible)");
            } else {
                // Si el nodo es accesible, imprime la distancia total.
                System.out.print(distancia[i] + " | Camino: ");
                // Llama al método mostrarCamino para reconstruir y mostrar la ruta completa hacia el nodo actual.
                mostrarCamino(previo, i);

                // Salta a la siguiente línea para continuar con el resto de nodos.
                System.out.println();
            }
        }
    }

    private void mostrarCamino(int[] previo, int destino) { // O(cte)
        // Comprueba si hay un nodo previo en el camino hacia el nodo destino.
        // Si `previo[destino] == -1` significa que el nodo es el origen del camino o no tiene predecesor.
        if (previo[destino] != -1) {
            // Llama recursivamente a mostrarCamino para seguir reconstruyendo el camino
            // desde el nodo `previo[destino]` hacia el nodo de origen.
            mostrarCamino(previo, previo[destino]);
        }
        // Cuando se llega al nodo deseado o al origen, se imprime el valor del nodo actual.
        // Esto asegura que el camino se imprima en orden: desde el origen hacia el destino.
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
