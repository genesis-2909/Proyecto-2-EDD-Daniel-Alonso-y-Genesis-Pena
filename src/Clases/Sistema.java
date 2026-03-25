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
        RegistroImpresion registro = new RegistroImpresion(doc, user, reloj, esPrioritario);
        colaImpresion.insertar(registro); // Primitiva insertar [cite: 39]
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
}
