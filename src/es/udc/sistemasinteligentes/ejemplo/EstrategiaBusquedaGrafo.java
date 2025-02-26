package es.udc.sistemasinteligentes.ejemplo;
import es.udc.sistemasinteligentes.*;

import java.util.*;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda {

    public EstrategiaBusquedaGrafo(){
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        Queue<Nodo> frontera = new LinkedList<>();  // Usamos una cola para búsqueda en anchura
        Set<Estado> explorados = new HashSet<>();  // Conjunto para evitar repetir estados

        Nodo nodoInicial = new Nodo(p.getEstadoInicial(), null, null);
        frontera.add(nodoInicial);

        int i = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + nodoInicial.getEstado());

        while (!frontera.isEmpty()) {
            Nodo nodoActual = frontera.poll();  // Sacamos el primer nodo de la frontera
            explorados.add(nodoActual.getEstado());

            System.out.println((i++) + " - Explorando " + nodoActual.getEstado());

            if (p.esMeta(nodoActual.getEstado())) {
                System.out.println((i++) + " - FIN - " + nodoActual.getEstado());
                return reconstruye_sol(nodoActual);
            }

            for (Accion acc : p.acciones(nodoActual.getEstado())) {
                Estado nuevoEstado = p.result(nodoActual.getEstado(), acc);
                if (!explorados.contains(nuevoEstado)) {  // Evitamos repetir estados
                    Nodo nuevoNodo = new Nodo(nuevoEstado, nodoActual, acc);
                    frontera.add(nuevoNodo);
                    System.out.println((i++) + " - Estado agregado a frontera: " + nuevoEstado);
                } else {
                    System.out.println((i++) + " - Estado ya explorado: " + nuevoEstado);
                }
            }
        }

        throw new Exception("No se ha podido encontrar una solución");
    }

    private Nodo[] reconstruye_sol(Nodo nodo) {
        List<Nodo> camino = new ArrayList<>();
        while (nodo != null) {
            camino.add(0, nodo);  // Insertamos al inicio para mantener el orden correcto
            nodo = nodo.getPadre();
        }
        return camino.toArray(new Nodo[0]);
    }
}