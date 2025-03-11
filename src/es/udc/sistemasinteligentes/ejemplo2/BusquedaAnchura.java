package es.udc.sistemasinteligentes.ejemplo2;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BusquedaAnchura implements EstrategiaBusqueda {
    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        Queue<Nodo> frontera = new LinkedList<>();
        List<Nodo> explorados = new ArrayList<>();

        Nodo nodoInicial = new Nodo(p.getEstadoInicial(), null, null);
        frontera.add(nodoInicial);

        while (!frontera.isEmpty()) {
            Nodo nodoActual = frontera.poll();
            explorados.add(nodoActual);

            if (p.esMeta(nodoActual.getEstado())) {
                return reconstruye_sol(nodoActual);  // Devuelve la secuencia de nodos hasta la meta
            }

            for (Accion acc : p.acciones(nodoActual.getEstado())) {
                Estado nuevoEstado = p.result(nodoActual.getEstado(), acc);
                Nodo nuevoNodo = new Nodo(nuevoEstado, nodoActual, acc);
                frontera.add(nuevoNodo);
            }
        }

        throw new Exception("No se encontró solución");
    }

    private Nodo[] reconstruye_sol(Nodo nodo) {
        List<Nodo> camino = new ArrayList<>();
        while (nodo != null) {
            camino.add(0, nodo);  // Insertamos al inicio para que el camino esté en orden
            nodo = nodo.getPadre();
        }
        return camino.toArray(new Nodo[0]);
    }
}

