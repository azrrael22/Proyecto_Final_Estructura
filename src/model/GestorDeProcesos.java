package model;

import java.util.Iterator;

import estructuras.Cola;
import estructuras.ListaEnlazada;
import exceptions.ValidacionIDProcesoException;

/**
 * @author Juan Sebastian Snachez Alvarez
 * @author Adriaann Mauricio Rodriguez Rubiano
 */
public class GestorDeProcesos implements Iterable<Proceso>{

    private ListaEnlazada<Proceso> procesos;

    public GestorDeProcesos() {

        procesos = new ListaEnlazada<Proceso>() ;

    }

    /**
     * Metodo crearProceso
     * @Param  id,nombre
     * Metodo que permite crear un nuevo proceso validando por medio del id que el proceso  no exista
     */
    public void crearProceso (String id, String nombre) throws ValidacionIDProcesoException {
        Proceso nuevoProceso = new Proceso (id, nombre);

        for (Proceso proceso : procesos) {
            if (proceso.getId().equals(id)) {
                throw new ValidacionIDProcesoException("Ya existe el id del proceso");
            }
        }
        procesos.agregarNodoADerecha(nuevoProceso);
    }

    /**
     * Metodo  buscarActividad
     * @Param nombreActividad
     * Return listaAuxiliar
     * Metodo que permite buscar una actividad  dado como parametro el nomnbre de la actividad.
     * El metodo retorna  una  lista que incluye todo el proceso  con todos los datos de la actividad buscada
     */
    public Cola <ProcesoActividad> buscarActividad (String nombreActividad){

        Cola <ProcesoActividad> listaAuxiliar = new Cola <>();

        for (Proceso proceso : procesos) {
            Actividad actividad = proceso.buscarActividadNombre(nombreActividad);
            if(actividad!=null) {
                listaAuxiliar.adicionarNodoFinal(new ProcesoActividad(proceso, actividad));
            }
        }


        return listaAuxiliar;
    }

    /**
     * Metodo obtenerProceso
     * @Param id
     * @Return Proceso
     * Metodo que permite obtener un proceso dado el id de este.
     */
    public Proceso obtenerProceso(String id) {
        for (Proceso proceso : procesos) {
            if (proceso.getId().equals(id)) {
                return proceso;
            }
        }
        return null;
    }
    /*
     * Metodo que permite imprimir los procesos concatenados.
     */
    @Override
    public String toString() {
        return "GestorDeProcesos [procesos=" + procesos + "]";
    }
    @Override
    public Iterator<Proceso> iterator() {
        return procesos.iterator();
    }


}