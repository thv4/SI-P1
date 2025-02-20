package es.udc.sistemasinteligentes;

public class Nodo {
    private Nodo padre;
    private Accion accion;
    private Estado estado;

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

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
