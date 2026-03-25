/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *Clase nodo para poder manejar colisiones en la tabla hash y tambien
 * para los documentos de cualquier usuario.
 * @author Daniel y Génesis
 */
public class Nodo {
    public Object dato;
    public Nodo pnext;

    public Nodo(Object dato) {
        this.dato = dato;
        this.pnext = null;
    }
}
