package model;

/**
 * @author Juan Sebastian Snachez Alvarez
 * @author Adriaann Mauricio Rodriguez Rubiano
 */

public class ProcesoActividad {
    private Proceso proceso;
    private Actividad actividad;

    /**
     * Metodo constructor de la clase ProcesoActividad
     * @Param proceso ,actividad
     */
    public ProcesoActividad(Proceso proceso, Actividad actividad) {
        super();
        this.proceso = proceso;
        this.actividad = actividad;
    }

    /*
     * Metodos get and set de la clase
     */
    public Proceso getProceso() {
        return proceso;
    }


    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }


    public Actividad getActividad() {
        return actividad;
    }


    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    /*
     * Metodo que permite imprimir el proceso con su respectiva actividad .
     */
    @Override
    public String toString() {
        return "ProcesoActividad [proceso=" + proceso.getNombre() + ", actividad=" + actividad + "]";
    }

}