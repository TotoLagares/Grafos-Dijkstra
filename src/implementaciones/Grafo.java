package implementaciones;

import api.GrafoTDA;

public class Grafo implements GrafoTDA {
    int[] nodos = new int[20];
    int[][] aristas = new int[20][20];
    int numNodos = 0;

    @Override
    public void agregarArista(int u, int v, int peso) {
        aristas[u][v] = peso;
    }

    public void agregarNodo(int valor) {
        nodos[numNodos] = valor;
        numNodos++;
    }

    public void eliminarNodo(int valor) {
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] == valor) {
                nodos[i] = 0;
                for (int j = i; j < numNodos - 1; j++) {
                    nodos[j] = nodos[j + 1];
                }
            }
        }
        numNodos--;
    }


    public void verAristas() {
        System.out.println("Matriz de Adyacencia (aristas):");
        System.out.print("    ");
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != 0) {
                System.out.printf("%4d", nodos[i]);
            }
        }
        System.out.println();
        for (int i = 0; i < numNodos; i++) {
            if (nodos[i] != 0) {
                System.out.printf("%4d", nodos[i]);
                for (int j = 0; j < numNodos; j++) {
                    if (nodos[j] != 0) {
                        int peso = aristas[nodos[i]][nodos[j]];
                        if (peso == 0) {
                            System.out.print("   -");
                        } else {
                            System.out.printf("%4d", peso);
                        }
                    }
                }
                System.out.println();
            }
        }
    }
}