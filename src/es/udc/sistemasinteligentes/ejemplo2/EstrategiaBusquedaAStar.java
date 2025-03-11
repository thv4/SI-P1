package es.udc.sistemasinteligentes.ejemplo2;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EstrategiaBusquedaAStar implements EstrategiaBusqueda {
    private Heuristica heuristica;

    public EstrategiaBusquedaAStar(Heuristica heuristica) {
        this.heuristica = heuristica;
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        PriorityQueue<Nodo> frontera = new PriorityQueue<>((a, b) -> Float.compare(a.getValorF(), b.getValorF()));
        List<Nodo> explorados = new ArrayList<>();

        Nodo nodoInicial = new Nodo(p.getEstadoInicial(), null, null);
        nodoInicial.setG(0);  // Costo desde el inicio
        nodoInicial.setH(heuristica.evaluar(nodoInicial.getEstado()));  // Heurística
        frontera.add(nodoInicial);

        while (!frontera.isEmpty()) {
            Nodo nodoActual = frontera.poll();
            explorados.add(nodoActual);

            if (p.esMeta(nodoActual.getEstado())) {
                return reconstruye_sol(nodoActual);
            }

            for (Accion acc : p.acciones(nodoActual.getEstado())) {
                Estado nuevoEstado = p.result(nodoActual.getEstado(), acc);
                Nodo nuevoNodo = new Nodo(nuevoEstado, nodoActual, acc);
                nuevoNodo.setG(nodoActual.getG() + acc.getCoste());  // Costo del camino
                nuevoNodo.setH(heuristica.evaluar(nuevoEstado));  // Heurística
                frontera.add(nuevoNodo);
            }
        }

        throw new Exception("No se encontró solución");
    }

    private Nodo[] reconstruye_sol(Nodo nodo) {
        List<Nodo> camino = new ArrayList<>();
        while (nodo != null) {
            camino.add(0, nodo);
            nodo = nodo.getPadre();
        }
        return camino.toArray(new Nodo[0]);
    }
}

