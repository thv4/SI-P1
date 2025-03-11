package es.udc.sistemasinteligentes.ejemplo2;


import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.List;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    private int N;
    private int sumaObjetivo;

    public ProblemaCuadradoMagico(EstadoCuadradoMagico estadoInicial) {
        super(estadoInicial);
        this.N = estadoInicial.getCuadrado().length;
        this.sumaObjetivo = N * (N * N + 1) / 2;
    }

    @Override
    public boolean esMeta(Estado estado) {
        EstadoCuadradoMagico cuadrado = (EstadoCuadradoMagico) estado;
        return cuadrado.esCompleto() && verificaCuadradoMagico(cuadrado);
    }

    private boolean verificaCuadradoMagico(EstadoCuadradoMagico estado) {
        int[][] cuadrado = estado.getCuadrado();

        // Verifica filas y columnas
        for (int i = 0; i < N; i++) {
            int sumaFila = 0, sumaColumna = 0;
            for (int j = 0; j < N; j++) {
                sumaFila += cuadrado[i][j];
                sumaColumna += cuadrado[j][i];
            }
            if (sumaFila != sumaObjetivo || sumaColumna != sumaObjetivo) return false;
        }

        // Verifica diagonales
        int sumaDiagonal1 = 0, sumaDiagonal2 = 0;
        for (int i = 0; i < N; i++) {
            sumaDiagonal1 += cuadrado[i][i];
            sumaDiagonal2 += cuadrado[i][N - 1 - i];
        }
        return sumaDiagonal1 == sumaObjetivo && sumaDiagonal2 == sumaObjetivo;
    }

    @Override
    public Accion[] acciones(Estado estado) {
        EstadoCuadradoMagico cuadrado = (EstadoCuadradoMagico) estado;
        List<Accion> acciones = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cuadrado.getCuadrado()[i][j] == 0) {  // Solo en celdas vacías
                    for (int num = 1; num <= N * N; num++) {
                        // Validamos que el número no esté repetido en la matriz
                        if (!estaRepetido(cuadrado.getCuadrado(), num)) {
                            AccionColocarNumero accion = new AccionColocarNumero(i, j, num);
                            if (accion.esAplicable(cuadrado)) {
                                acciones.add(accion);
                            }
                        }
                    }
                    return acciones.toArray(new Accion[0]);  // Solo una casilla a la vez
                }
            }
        }
        return new Accion[0];
    }

    private boolean estaRepetido(int[][] cuadrado, int num) {
        for (int[] fila : cuadrado) {
            for (int valor : fila) {
                if (valor == num) return true;
            }
        }
        return false;
    }

    @Override
    public Estado result(Estado estado, Accion accion) {
        return accion.aplicaA(estado);
    }
}
