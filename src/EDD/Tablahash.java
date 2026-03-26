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
    /**
    * Extrae todos los nombres de los usuarios almacenados en la tabla hash.
    */
    public String[] obtenerNombresUsuarios() {
        int contador = 0;
        
        // 1. Contamos cuántos usuarios hay
        for (int i = 0; i < tabla.length; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                contador++;
                actual = actual.pnext;
            }
        }
        
        // 2. Llenamos el arreglo
        String[] nombres = new String[contador];
        int indice = 0;
        
        for (int i = 0; i < tabla.length; i++) {
            Nodo actual = tabla[i];
            while (actual != null) {
                // Sacamos el dato directo y lo convertimos a Usuario
                Clases.Usuario u = (Clases.Usuario) actual.dato; 
                nombres[indice] = u.getNombre(); 
                
                indice++;
                actual = actual.pnext;
            }
        }
        return nombres;
    }
    public boolean eliminar(String nombre) {
    int i = funcionHash(nombre);
    int inicio = i;
    
    do {
        if (tabla[i] == null) return false; // Si llegamos a un nulo, no existe
        Usuario usuarioTemporal = (Usuario) tabla[i].dato;
        if (usuarioTemporal.getNombre().equals(nombre)) {
            // "Marcamos" como borrado. 
            // Para no romper la tabla, lo ideal es poner un objeto 'borrado' 
            // o simplemente null si tu búsqueda está preparada.
            tabla[i] = null; 
            tamano--;
            return true;
        }
        i = (i + 1) % tabla.length;
    } while (i != inicio);
    
    return false;
}
}
