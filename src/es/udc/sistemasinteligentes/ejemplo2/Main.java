package es.udc.sistemasinteligentes.ejemplo2;


import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Definir un estado inicial con algunas celdas ya llenas (ejemplo de 3x3)
        int[][] cuadradoInicial = {
                {2, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        EstadoCuadradoMagico estadoInicial = new EstadoCuadradoMagico(cuadradoInicial);
        ProblemaCuadradoMagico problema = new ProblemaCuadradoMagico(estadoInicial);

        System.out.println("Estado inicial:");
        System.out.println(estadoInicial);

        try {
            System.out.println("\n===== Resolviendo con búsqueda en anchura =====");
            EstrategiaBusqueda estrategia1 = new BusquedaAnchura();
            Nodo[] solucionAnchura = estrategia1.soluciona(problema);
            imprimirSolucion(solucionAnchura);

            System.out.println("\n===== Resolviendo con búsqueda en profundidad =====");
            EstrategiaBusqueda estrategia2 = new BusquedaProfundidad();
            Nodo[] solucionProfundidad = estrategia2.soluciona(problema);
            imprimirSolucion(solucionProfundidad);

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
            if (nodo.getAccion() != null) {  // Evitar imprimir "null" en el nodo inicial
                System.out.println(nodo.getAccion());
            }
            System.out.println(nodo.getEstado());
        }
    }
}
