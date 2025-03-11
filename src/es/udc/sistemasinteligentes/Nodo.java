package es.udc.sistemasinteligentes;

public class Nodo {
    private Nodo padre;
    private Accion accion;
    private Estado estado;
    private float g; // Costo del camino hasta este nodo
    private float h; // Heurística
    private float f; // f = g + h

    public Nodo(Estado e, Nodo p, Accion a){
        this.estado = e;
        this.padre = p;
        this.accion = a;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Accion getAccion() {
        return accion;
    }

    public Estado getEstado() {
        return estado;
    }

    public float getG() { return g; }
    public float getH() { return h; }
    public float getValorF() { return f; }

    public void setG(float g) {
        this.g = g;
        this.f = this.g + this.h;
    }

    public void setH(float h) {
        this.h = h;
        this.f = this.g + this.h;
    }

    @Override
    public String toString() {
        return "Nodo{Estado=" + estado + ", Accion=" + accion + "}";
    }
}
