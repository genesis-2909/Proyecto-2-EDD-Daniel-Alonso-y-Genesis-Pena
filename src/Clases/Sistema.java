/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import EDD.MonticuloBinario;
import EDD.Tablahash;
/**
 * Clase para poder llamar las funciones desde la interfaz grafica
 * @author Daniel y Genesis
 */
public class Sistema {
    private Tablahash tablaUsuarios;
    private MonticuloBinario colaImpresion;
    private int reloj; /** El reloj de la simulacion */

    public Sistema() {
        this.tablaUsuarios = new Tablahash(100); /** Tamaño inicial */
        this.colaImpresion = new MonticuloBinario(500); /** Capacidad de la cola */
        this.reloj = 0;
    }

    /**
     * simula el paso del tiempo
     */
    public void avanzarReloj() {
        this.reloj++;
    }

    /**
     * metodo para poder imprimir
     */
    public void enviarAImprimir(Documento doc, Usuario user, boolean esPrioritario) {
        avanzarReloj();
        RegistroImpresion registro = new RegistroImpresion(doc.getNombre(), user.getNombre(), reloj);
        colaImpresion.insertar(registro);
    }

    /**
     * Metodo para liberar impresora
     */
    public void liberarImpresora() {
        RegistroImpresion impreso = colaImpresion.eliminarMin(); /** Primitiva eliminar_min */
        if (impreso != null) {
            System.out.println("Se imprimio: " + impreso.getNombreDocumento());
        }
        
    }
    
    /**
     * Busca un usuario en el sistema y retorna el nombre del usuario
     */
    public Usuario buscarUsuario(String nombre) {
        return tablaUsuarios.buscarUsuario(nombre);
    }
    
    /**
     * Elimina un usuario del sistema y retorna el nombre del usuario
     */
    public void eliminarUsuario(String nombre) {
        tablaUsuarios.eliminarUsuario(nombre);
    }
    
    /** Getters */
    public Tablahash getTablaUsuarios() { return tablaUsuarios; }
    public MonticuloBinario getColaImpresion() { return colaImpresion; }
    
/**
     * Elimina a un usuario completamente del sistema.
     * Borra su registro de la Tabla Hash y limpia la Cola de Impresión de todos
     * los documentos que le pertenecían mediante un proceso de filtrado.
     * * @param nombreUsuario El nombre del usuario a eliminar.
     */
    public void eliminarUsuarioCompleto(String nombreUsuario) {
        // 1. Borrar de la tabla hash
        this.tablaUsuarios.eliminar(nombreUsuario);
        
        // 2. Filtrar el montículo binario
        EDD.MonticuloBinario colaNueva = new EDD.MonticuloBinario(500);
        
        while (this.colaImpresion.getSize() > 0) {
            Clases.RegistroImpresion reg = (Clases.RegistroImpresion) this.colaImpresion.eliminarMin();
            
            // Reinsertamos solo si el documento NO es del usuario eliminado
            if (reg != null && !reg.getNombreUsuario().equals(nombreUsuario)) {
                colaNueva.insertar(reg);
            }
        }
        // Actualizamos la referencia a la nueva cola filtrada
        this.colaImpresion = colaNueva;
    
}   /**
     * Calcula la prioridad del documento basándose en el tipo de usuario y el orden de llegada.
     */
    public int calcularPrioridad(String tipoUsuario, int ordenLlegada) {
        int base;
        switch (tipoUsuario.toLowerCase()) {
            case "prioridad alta": base = 100; break;
            case "prioridad media": base = 500; break;
            case "prioridad baja": base = 1000; break;
            default: base = 2000;
        }
        return base + ordenLlegada;
    }
    
/**
     * Modifica la prioridad de un documento específico que ya se encuentra en la cola.
     * Extrae todos los documentos, actualiza la prioridad del documento objetivo y
     * los vuelve a insertar en el montículo para mantener el orden correcto.
     * * @param nombreDoc El nombre del documento a modificar.
     * @param tipoUsuario El tipo de usuario ("prioridad alta", "media", "baja") para el cálculo.
     * @param ordenLlegada El orden de llegada original o nuevo para desempatar.
     */
    public void modificarPrioridadDocumento(String nombreDoc, String tipoUsuario, int ordenLlegada) {
        EDD.MonticuloBinario colaNueva = new EDD.MonticuloBinario(500);
        
        while (this.colaImpresion.getSize() > 0) {
            Clases.RegistroImpresion reg = (Clases.RegistroImpresion) this.colaImpresion.eliminarMin();
            
            if (reg != null) {
                // Si encontramos el documento, recalculamos y cambiamos su prioridad
                if (reg.getNombreDocumento().equals(nombreDoc)) {
                    int nuevaPrio = calcularPrioridad(tipoUsuario, ordenLlegada);
                    reg.setEtiqueta(nuevaPrio);
                }
                colaNueva.insertar(reg); // Lo insertamos (modificado o intacto)
            }
        }
        // Actualizamos la referencia a la nueva cola reordenada
        this.colaImpresion = colaNueva;
    }
}
