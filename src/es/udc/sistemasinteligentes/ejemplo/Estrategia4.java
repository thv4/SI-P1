package es.udc.sistemasinteligentes.ejemplo;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public Estado soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return estadoActual;
    }

    public Nodo[] solucionaa(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        //ArrayList<Nodo> ListaNodos = new ArrayList<Nodo>();
        Nodo nodoActual = new Nodo(p.getEstadoInicial(), null, null);
        //ListaNodos.add(nodoActual);
        explorados.add(nodoActual.getEstado());
        Nodo[] nodos = null;

        int i = 1;
        // Falta implementar la lista de nodos y devolverla
        // Para el apartado B primero aclarar la idea de los esquemas a papel y trasladar a codigo
        System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.getEstado());

        while (!p.esMeta(nodoActual.getEstado())){
            System.out.println((i++) + " - " + nodoActual.getEstado() + " no es meta");
            Accion[] accionesDisponibles = p.acciones(nodoActual.getEstado());
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(nodoActual.getEstado(), acc);
                System.out.println((i++) + " - RESULT(" + nodoActual.getEstado() + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    nodoActual.setEstado(sc);
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(nodoActual.getEstado());
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + nodoActual.getEstado());
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + nodoActual.getEstado());

        //return nodoActual.getEstado();
        return nodos;
    }
}
