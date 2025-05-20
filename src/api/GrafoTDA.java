package api;

 public interface GrafoTDA {
    void agregarArista(int u, int v, int peso);
    void agregarNodo(int valor);
    void verAristas();
    void eliminarNodo(int valor);
    //void dijkstra(int origen);
}
