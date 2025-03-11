package es.udc.sistemasinteligentes.ejemplo2;

import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;

public class HeuristicaCuadradoMagico extends Heuristica {

    @Override
    public float evaluar(Estado estado) {
        EstadoCuadradoMagico cuadrado = (EstadoCuadradoMagico) estado;
        int[][] matriz = cuadrado.getCuadrado();
        int N = matriz.length;
        int sumaObjetivo = N * (N * N + 1) / 2;
        int errorTotal = 0;

        // Evaluar filas y columnas
        for (int i = 0; i < N; i++) {
            int sumaFila = 0, sumaColumna = 0;
            for (int j = 0; j < N; j++) {
                sumaFila += matriz[i][j];
                sumaColumna += matriz[j][i];
            }
            errorTotal += Math.abs(sumaObjetivo - sumaFila);
            errorTotal += Math.abs(sumaObjetivo - sumaColumna);
        }

        // Evaluar diagonales
        int sumaDiagonal1 = 0, sumaDiagonal2 = 0;
        for (int i = 0; i < N; i++) {
            sumaDiagonal1 += matriz[i][i];
            sumaDiagonal2 += matriz[i][N - 1 - i];
        }
        errorTotal += Math.abs(sumaObjetivo - sumaDiagonal1);
        errorTotal += Math.abs(sumaObjetivo - sumaDiagonal2);

        return errorTotal;
    }
}

