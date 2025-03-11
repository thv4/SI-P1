package es.udc.sistemasinteligentes.ejemplo2;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;


public class AccionColocarNumero extends Accion {
    private int fila, columna, numero;

    public AccionColocarNumero(int fila, int columna, int numero) {
        this.fila = fila;
        this.columna = columna;
        this.numero = numero;
    }

    @Override
    public boolean esAplicable(Estado es) {
        EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;
        return estado.getCuadrado()[fila][columna] == 0;  // Solo se puede colocar en celdas vacías
    }

    @Override
    public Estado aplicaA(Estado es) {
        EstadoCuadradoMagico estado = (EstadoCuadradoMagico) es;
        int[][] nuevoCuadrado = new int[estado.getCuadrado().length][estado.getCuadrado().length];

        // Copiamos la matriz
        for (int i = 0; i < estado.getCuadrado().length; i++) {
            nuevoCuadrado[i] = estado.getCuadrado()[i].clone();
        }

        // Aplicamos la acción
        nuevoCuadrado[fila][columna] = numero;
        return new EstadoCuadradoMagico(nuevoCuadrado);
    }

    @Override
    public String toString() {
        return "Colocar " + numero + " en (" + fila + ", " + columna + ")";
    }
}
