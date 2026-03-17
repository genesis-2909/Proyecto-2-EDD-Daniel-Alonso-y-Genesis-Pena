/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;
import Clases.Usuario;
/**
 * Se crea una tabla de dispersion para poder gestionar usuarios y tambien
 * utiliza encadenamiento para poder resolver colisiones
 * @author Daniel y Genesis
 */
public class Tablahash {
    private Nodo[] tabla;
    private int tamano;

    /**
     * Constructor de la tabla con un tamaño fijo
     */
    public Tablahash(int tamano) {
        this.tamano = tamano;
        this.tabla = new Nodo[tamano];
    }

    /**
     * funcion hash simple basada en el nombre de usuario
     * se supone que clave es el nombre del usuario y retorna
     * retorna en indice dentro del arreglo
     */
    private int funcionHash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash += clave.charAt(i);
        }
        return hash % tamano;
    }

    /**
     * Agrega un usuario en la tabla
     */
    public void insertar(Usuario usuario) {
        int posicion = funcionHash(usuario.getNombre());
        Nodo nuevo = new Nodo(usuario);
        
        if (tabla[posicion] == null) {
            tabla[posicion] = nuevo;
        } else {
            /** Se agrega al inicio de la lista en esa posicion */
            nuevo.pnext = tabla[posicion];
            tabla[posicion] = nuevo;
        }
    }
    
    /**
     * busca un usuario por su nombre en la tabla
     * nombre - el nombre del usuario a buscar
     * retorna un usuario si lo encuentra sino retorna null
     */
    public Usuario buscarUsuario(String nombre) {
        int posicion = funcionHash(nombre);
        Nodo actual = tabla[posicion];

        while (actual != null) {
            Usuario user = (Usuario) actual.dato;
            if (user.getNombre().equals(nombre)) {
                return user;
            }
            actual = actual.pnext;
        }
        return null;
    }

    /**
     * elimina un usuario de la tabla
     * nombre - el nombre del usuario a eliminar
     */
    public void eliminarUsuario(String nombre) {
        int posicion = funcionHash(nombre);
        Nodo actual = tabla[posicion];
        Nodo anterior = null;

        while (actual != null) {
            Usuario user = (Usuario) actual.dato;
            if (user.getNombre().equals(nombre)) {
                if (anterior == null) {
                    tabla[posicion] = actual.pnext; /** Era el primero de la lista */
                } else {
                    anterior.pnext = actual.pnext; /** Saltamos el nodo para eliminarlo */
                }
                System.out.println("Usuario " + nombre + " eliminado.");
                return;
            }
            anterior = actual;
            actual = actual.pnext;
        }
    }
}
