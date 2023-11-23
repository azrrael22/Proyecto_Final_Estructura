package model;

/**
 * @author Juan Sebastian Snachez Alvarez
 * @author Adriaann Mauricio Rodriguez Rubiano
 */

import java.util.Iterator;

import estructuras.Cola;
import estructuras.ListaCircularDoble;
import exceptions.ActividadNoExisteException;
import exceptions.ValidacionNombreActividadException;

public class Proceso implements Iterable<Actividad> {

    private String id;
    private String nombre;
    private ListaCircularDoble<Actividad> actividades;
    private int ultimaActividadCreada;

    /**
     * Metodo constructor de la clase Proceso
     * @Param id,nomnre
     * se crea ListaCircularDoble
     */
    public Proceso(String id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;

        actividades = new ListaCircularDoble<Actividad>();
        ultimaActividadCreada = -1;

    }
    /**
     * Metodo CrearActividadAlFinal
     * @Param  nombre,descripcion,obligatorio
     * Metodo que ï¿½permite agregar una actividad al final de las demas actividades
     * que se encuentran en la lista.
     * El metodo valida que no exista el nombre de la nueva actividad creada.
     */
    public void crearActividadAlFinal(String nombre, String descripcion, boolean obligatorio)
            throws ValidacionNombreActividadException {
        Actividad actividad = new Actividad(nombre, descripcion, obligatorio);

        for (Actividad actividad2 : actividades) {
            if (actividad2.getNombre().equals(nombre)) {
                throw new ValidacionNombreActividadException("Ya existe el nombre de la actividad");
            }
        }
        ultimaActividadCreada = actividades.getLongitud();
        actividades.agregarNodoAlFinal(actividad);
    }

    /**
     * Metodo CrearActividad
     * @Param descripcion,obligatorio,predecesora
     * este metodo permite crear una  actividad dado el nombre de la actividad predecesora
     * desplazando el resto de actividades en la lista
     * valida que el nomnbre de la nueva actividad no exista.
     *
     */
    public void crearActividad(String nombre, String descripcion, boolean obligatorio, String predecesora)
            throws ValidacionNombreActividadException {
        Actividad actividad = new Actividad(nombre, descripcion, obligatorio);

        for (Actividad actividad2 : actividades) {
            if (actividad2.getNombre().equals(nombre)) {
                throw new ValidacionNombreActividadException("Ya existe el nombre de la actividad");
            }
        }

        actividades.irPrimerNodo();

        for(int i=0; i<actividades.getLongitud();i++) {
            Actividad actividad2=actividades.obtenerDatoActual();
            if (actividad2.getNombre().equals(predecesora)) {
                actividades.agregarNodoDerecha(actividad);
                ultimaActividadCreada = i + 1;
                break;
            }

            actividades.irSiguienteNodo();
        }


    }

    /**
     * Metodo crearActividadDespuesUltima
     * @Param nombre,descripcion,obligatorio
     * Metodo que permite crear una actividad despues de la ultima actividad creada en la lista
     * Valida que el nombre de la nueva actividad no exista.
     */
    public void crearActividadDespuesUltima(String nombre, String descripcion, boolean obligatorio)
            throws ValidacionNombreActividadException {
        Actividad nuevaActividad = new Actividad(nombre, descripcion, obligatorio);

        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombre)) {
                throw new ValidacionNombreActividadException("Ya existe el nombre de la actividad");
            }
        }

        actividades.irPrimerNodo();

        /*
         * permite agregar la nueva actividad despues de la ultima actividad creada
         * UltimaActividadCreada  incrementa a la posicion de la nueva actividad creada.
         */
        for(int i=0; i<actividades.getLongitud();i++) {
            if (i==ultimaActividadCreada) {
                actividades.agregarNodoDerecha(nuevaActividad);
                ultimaActividadCreada = i + 1;
                break;
            }

            actividades.irSiguienteNodo();
        }

    }

    /**
     * Metodo buscasActividadNombre
     * @Param nombreActividad
     * @Return actividad
     * Permite buscar una actividad dado su nombre como parametro
     */
    public Actividad buscarActividadNombre(String nombreActividad) {

        actividades.irPrimerNodo();
        for (int i = 0; i < actividades.getLongitud(); i++) {
            Actividad actividad = actividades.obtenerDatoActual();
            if (actividad.getNombre().equals(nombreActividad)) {
                return actividad;
            }

            actividades.irSiguienteNodo();
        }

        return null;
    }

    /**
     * Metodo buscarTareaEnActividadNomnbre
     * @Param nombreActividad,descripcion
     * Metodo que  permite  buscar una tarea  en una actividad dado su nombre
     */
    public Tarea buscarTareaEnActividadNombre(String nombreActividad, String descripcion)
            throws ActividadNoExisteException {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombreActividad)) {
                return actividad.buscarTareaDescripcion(descripcion);
            }
        }

        throw new ActividadNoExisteException("La actividad" + nombreActividad + "no existe");
    }

    /**
     * @Param descripcion
     * @return listaBusqueda
     * Metodo que permite buscar la tarea desde el inicio dada su descripcion
     */
    public Cola<Tarea> buscarTareaDesdeElInicio(String descripcion) throws ActividadNoExisteException {
        Cola<Tarea> listaBusqueda = new Cola<Tarea>();

        for (Actividad actividad : actividades) {

            Tarea tarea = actividad.buscarTareaDescripcion(descripcion);
            if (tarea != null) {
                listaBusqueda.adicionarNodoFinal(tarea);
            }

        }
        return listaBusqueda;
    }

    /**
     * Metodo que permite buscar una tarea en la actividad actual dada como parametro su descripcion
     * @Return actividadActual
     */
    public Tarea buscarTareaEnActividadActual(String descripcion) {

        Actividad actividadActual = actividades.obtenerDatoActual();

        return actividadActual.buscarTareaDescripcion(descripcion);

    }

    /*
     * el metodo permite determinar el tiempo maximo  de un proceso
     */
    public int tiempoMaximo() {
        int tiempo = 0;
        for (Actividad actividad : actividades) {
            tiempo = tiempo + actividad.tiempoMaximo();
        }
        return tiempo;
    }

    /*
     * el metodo permite determinar el tiempo minimo de un proceso
     */
    public int tiempoMinimo() {
        int tiempo = 0;
        for (Actividad actividad : actividades) {
            if( actividad.isObligatorio() ) {
                tiempo = tiempo + actividad.tiempoMinimo();
            }
        }
        return tiempo;
    }

    /**
     *@Param nombreActividad1,nombreActividad2
     * este metodo permite intercambiar dos actividades sin sus listas de actividades
     * Si el nombre de una de las actividades ingresadas no existe , el metodo lo valida.
     */
    public void intercambiarActividadesSinTarea(String nombreActividad1, String nombreActividad2)
            throws ActividadNoExisteException {

        Actividad actividad1 = buscarActividadNombre(nombreActividad1);
        Actividad actividad2 = buscarActividadNombre(nombreActividad2);

        if (actividad1 != null && actividad2 != null) {

            actividad1.setNombre(nombreActividad2);
            String descripcionAux = actividad1.getDescripcion();
            boolean obligatorioAux = actividad1.isObligatorio();
            actividad1.setDescripcion(actividad2.getDescripcion());
            actividad1.setObligatorio(actividad2.isObligatorio());
            actividad2.setNombre(nombreActividad1);
            actividad2.setDescripcion(descripcionAux);
            actividad2.setObligatorio(obligatorioAux);

        } else {
            throw new ActividadNoExisteException("Alguna de las actividades no existe");
        }
    }

    /**
     * @Param nombreActividad1,nombreActividad2
     * Este metodo permite  intercambiar dos actividas con su  respectiva lista de tareas
     */
    public void intercambiarActividadesConTarea(String nombreActividad1, String nombreActividad2) {
        Actividad actividad1 = buscarActividadNombre(nombreActividad1);
        Actividad actividad2 = buscarActividadNombre(nombreActividad2);
        Cola<Actividad> listaAuxiliar = new Cola<>();

        if (actividad1 != null && actividad2 != null) {

            for (Actividad actividad : actividades) {
                if (actividad1.getNombre().equals(actividad.getNombre())) {
                    listaAuxiliar.adicionarNodoFinal(actividad2);
                }

                else if (actividad2.getNombre().equals(actividad.getNombre())) {
                    listaAuxiliar.adicionarNodoFinal(actividad1);
                } else {
                    listaAuxiliar.adicionarNodoFinal(actividad);
                }

            }
            actividades.vaciarLista();

            for (Actividad actividad : listaAuxiliar) {
                actividades.agregarNodoAlFinal(actividad);
            }

        }
    }

    /*
     * Metodos get y set de la clase Proceso
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * Metodo  que permite imprimir los procesos con sus respectivos atributos concatenados
     */
    @Override
    public String toString() {
        return "Proceso [id=" + id + ", nombre=" + nombre + ", actividades=" + actividades + ", ultimaActividadCreada="
                + ultimaActividadCreada + "]";
    }

    /*
     * metodo que retorna la longitud de una lista de actividades
     */
    public int obtenerNumeroActividades() {

        return actividades.getLongitud();
    }

    @Override
    public Iterator<Actividad> iterator() {
        return actividades.iterator();
    }

}