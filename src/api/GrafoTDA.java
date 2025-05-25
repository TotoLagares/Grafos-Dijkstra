package api;

 public interface GrafoTDA {
    void agregarArista(int u, int v, int peso);
    void agregarNodo(int valor);
    void verAristas();
    void eliminarNodo(int valor);
    void eliminarArista(int u, int v);
    void dijkstra(int origen);
}
