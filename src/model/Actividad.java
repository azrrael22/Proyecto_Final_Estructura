package model;

import java.util.Iterator;

import estructuras.Cola;
import exceptions.ValidacionTareaOpcionalException;

/**
 * @author Juan Sebastian Snachez Alvarez
 * @author Adriaann Mauricio Rodriguez Rubiano
 */
public class Actividad implements Iterable<Tarea>{



    private String nombre;
    private String descripcion;
    private boolean obligatorio;

    private Cola<Tarea> tareas;

    /**
     * Constructor de la clase Actividad
     * @Param  nombre, descripcion y obligatorio.
     */
    public Actividad(String nombre, String descripcion, boolean obligatorio) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.obligatorio = obligatorio;

        tareas = new Cola<>();

    }

    /*
     * Metodos get y set de la clase Actividad
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    /**
     * Metodo crearTareaFinal
     * @Param  descripcion,obligatorio,tiempo
     * Metodo que pérmite crear una tarea al final de la Cola
     * Valida que no se creen don tareas opcionales seguidas en la cola
     */
    public void crearTareaAlFinal(String descripcion, boolean obligatorio, int tiempo)
            throws ValidacionTareaOpcionalException {

        Tarea tarea = new Tarea(descripcion, obligatorio, tiempo);
        if (obligatorio == false) {
            Tarea auxiliar = null;
            for (Tarea tarea2 : tareas) {
                auxiliar = tarea2;
            }
            if (auxiliar != null && auxiliar.isObligatorio() == false) {
                throw new ValidacionTareaOpcionalException("No se puede crear consecutivo de tarea opcional");
            }
        }

        tareas.adicionarNodoFinal(tarea);
    }

    /**
     * Metodo crearTarea
     * @Param descripcion,obligatorio,tiempo,posicion
     * Metodo que permite crear una tarea en una posicion determinada de la cola
     * Valida que no se creen dos tareas opcionales de seguidas.
     */
    public void crearTarea(String descripcion, boolean obligatorio, int tiempo, int posicion)
            throws ValidacionTareaOpcionalException {
        Tarea nuevaTarea = new Tarea(descripcion, obligatorio, tiempo);
        if (obligatorio == false) {
            Tarea anterior = null;
            Tarea siguiente = null;

            int i = 0;
            for (Tarea tarea2 : tareas) {
                if (i == posicion - 1) {
                    anterior = tarea2;
                }
                if (i == posicion) {
                    siguiente = tarea2;
                    break;
                }

                i++;
            }
            if (anterior != null && anterior.isObligatorio() == false) {
                throw new ValidacionTareaOpcionalException("No se puede crear consecutivo de tarea opcional");
            }

            if (siguiente != null && siguiente.isObligatorio() == false) {
                throw new ValidacionTareaOpcionalException("No se puede crear consecutivo de tarea opcional");
            }
        }

        int longitud = tareas.getLongitud();
        for (int i = 0; i < longitud; i++) {
            Tarea auxTarea = tareas.poll();
            if (i == posicion) {
                tareas.adicionarNodoFinal(nuevaTarea);
            }
            tareas.adicionarNodoFinal(auxTarea);
        }

    }

    /**
     * MetodoBuscarDescripcion
     * @Param descripcion
     * @Return tarea
     * Metodo que permite buscar una tarea en la cola dada su descripcion.
     */
    public Tarea buscarTareaDescripcion(String descripcion) {

        for (Tarea tarea : tareas) {
            if (descripcion.equals(tarea.getDescripcion())) {
                return tarea;
            }
        }

        return null;
    }

    /**
     * Metodo tiempoMaximo
     * Metodo  para determinar el tiempo maximo de una tarea
     * @Return tiempo
     */
    public int tiempoMaximo() {

        int tiempo = 0;
        for (Tarea tarea : tareas) {
            tiempo = tiempo + tarea.getTiempo();
        }

        return tiempo;
    }

    /**
     * Metodo tiempoMinimo
     * Metodo que permite determinar el tiempo minimo de una tarea.
     * @Return tiempo.
     */
    public int tiempoMinimo() {
        int tiempo = 0;
        for (Tarea tarea : tareas) {
            if (tarea.isObligatorio()) {
                tiempo = tiempo + tarea.getTiempo();
            }
        }

        return tiempo;
    }

    /**
     * Metodo toString
     * Metodo que imprime la  actividad con sus parametros concatenados
     */
    @Override
    public String toString() {
        return "Actividad [nombre=" + nombre + ", descripcion=" + descripcion + ", obligatorio=" + obligatorio
                + ", tareas=" + tareas + "]";
    }

    public int obtenerNumeroTareas() {

        return tareas.getLongitud();
    }

    @Override
    public Iterator<Tarea> iterator() {
        return tareas.iterator();
    }
}
