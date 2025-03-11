package es.udc.sistemasinteligentes.ejemplo2;

import es.udc.sistemasinteligentes.Estado;

import java.util.Arrays;

public class EstadoCuadradoMagico extends Estado {
    private int[][] cuadrado;

    public EstadoCuadradoMagico(int[][] cuadrado) {
        this.cuadrado = new int[cuadrado.length][cuadrado.length];
        for (int i = 0; i < cuadrado.length; i++) {
            this.cuadrado[i] = Arrays.copyOf(cuadrado[i], cuadrado[i].length);
        }
    }

    public int[][] getCuadrado() {
        return cuadrado;
    }

    public boolean esCompleto() {
        for (int[] fila : cuadrado) {
            for (int num : fila) {
                if (num == 0) return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EstadoCuadradoMagico)) return false;
        EstadoCuadradoMagico otro = (EstadoCuadradoMagico) obj;
        return Arrays.deepEquals(this.cuadrado, otro.cuadrado);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cuadrado);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(cuadrado).replace("], ", "]\n");
    }
}
