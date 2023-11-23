package model;

/**
 * @author Juan Sebastian Snachez Alvarez
 * @author Adriaann Mauricio Rodriguez Rubiano
 */

public class Tarea {

    private String descripcion;
    private boolean obligatorio;
    private int tiempo;

    /**
     * @Param descripcion,obligatorio,tiempo
     * Constructor de la clase tarea
     */
    public Tarea(String descripcion, boolean obligatorio, int tiempo) {
        super();
        this.descripcion = descripcion;
        this.obligatorio = obligatorio;
        this.tiempo = tiempo;
    }

    /*
     * Metodos get and set  de la clase Tarea
     */
    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public boolean isObligatorio() {
        return obligatorio;
    }


    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }


    public int getTiempo() {
        return tiempo;
    }


    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    /*
     * Metodo para imprimir   la tarea con sus respectivos atributos concatenados
     */
    @Override
    public String toString() {
        return "Tarea [descripcion=" + descripcion + ", obligatorio=" + obligatorio + ", tiempo=" + tiempo + "]";
    }
}