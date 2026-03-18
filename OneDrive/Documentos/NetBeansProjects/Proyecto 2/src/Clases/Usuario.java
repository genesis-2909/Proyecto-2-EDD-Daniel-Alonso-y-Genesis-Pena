/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import EDD.Nodo;
/**
 * clase que va a representar a un usuario del sistema
 * @author Daniel y Genesis
 */
public class Usuario {
    private String nombre;
    private String tipoPrioridad; /** prioridad alta, media o baja */
    private Nodo listaDocumentos; /** lista enlazada manual de documentos */

    public Usuario(String nombre, String tipoPrioridad) {
        this.nombre = nombre;
        this.tipoPrioridad = tipoPrioridad;
        this.listaDocumentos = null;
    }

    /**
     * se encarga de agregar un documento a la lista personal del usuario
     * doc es el documento creado
     */
    public void agregarDocumento(Documento doc) {
        Nodo nuevo = new Nodo(doc);
        if (listaDocumentos == null) {
            listaDocumentos = nuevo;
        } else {
            nuevo.pnext = listaDocumentos;
            listaDocumentos = nuevo;
        }
    }
    /** Getters */
    public String getNombre() { return nombre; }
    public String getTipoPrioridad() { return tipoPrioridad; }
    public Nodo getListaDocumentos() { return listaDocumentos; }
}
