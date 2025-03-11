package es.udc.sistemasinteligentes.ejemplo2;


import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.Nodo;

public class Main {
    public static void main(String[] args) {
        int[][] cuadradoInicial = {
                {2, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        EstadoCuadradoMagico estadoInicial = new EstadoCuadradoMagico(cuadradoInicial);
        ProblemaCuadradoMagico problema = new ProblemaCuadradoMagico(estadoInicial);
        Heuristica heuristica = new HeuristicaCuadradoMagico();

        System.out.println("Estado inicial:");
        System.out.println(estadoInicial);

        try {
            System.out.println("\n===== Resolviendo con A* =====");
            EstrategiaBusqueda estrategiaAStar = new EstrategiaBusquedaAStar(heuristica);
            Nodo[] solucionAStar = estrategiaAStar.soluciona(problema);
            imprimirSolucion(solucionAStar);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void imprimirSolucion(Nodo[] solucion) {
        if (solucion == null) {
            System.out.println("No se encontró solución.");
            return;
        }
        System.out.println("Solución encontrada:");
        for (Nodo nodo : solucion) {
            if (nodo.getAccion() != null) {
                System.out.println(nodo.getAccion());
            }
            System.out.println(nodo.getEstado());
        }
    }
}
