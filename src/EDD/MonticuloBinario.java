/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;
import Clases.RegistroImpresion;
/**
 *Aqui se implementa un Monticulo Binario (Min-Heap) usando un arreglo y
 * no se utiliza punteros para mantener la estructura de arbol
 * @author Daniel y Genesis
 */
public class MonticuloBinario {
    private RegistroImpresion[] heap;
    private int n; /** Numero de elementos actuales */

    /**
     * Constructor para inicializar el monticulo
     * la capacidad es el maximo de documentos en cola
     */
    public MonticuloBinario(int capacidad) {
        this.heap = new RegistroImpresion[capacidad + 1];
        this.n = 0;
    }

    /**
     * Inserta un nuevo registro en la cola de impresion
     * nuevo es el registro con la etiqueta del tiempo
     */
    public void insertar(RegistroImpresion nuevo) {
        /** valida la capacidad para evitar errores en el codigo */
        if (n >= heap.length - 1) {
            System.out.println("Error, La cola de impresion esta llena ");
            return;
        }
        
        n++;
        heap[n] = nuevo;
        subir(n); /** Restablece el orden del monticulo */
    }

    /**
     * es un metodo auxiliar para subir un elemento
     * i va a ser el indice del elemento a subir
     */
    private void subir(int i) {
        while (i > 1 && heap[i].getEtiqueta() < heap[i / 2].getEtiqueta()) {
            RegistroImpresion temp = heap[i];
            heap[i] = heap[i / 2];
            heap[i / 2] = temp;
            i = i / 2;
        }
    }

    /**
     * elimina y retorna el elemento con la prioridad mas alta, es decir,
     * el de menor tiempo.
     * retorna el registro que debe imprimirse 
     */
    public RegistroImpresion eliminarMin() {
        if (n == 0) return null;
        RegistroImpresion min = heap[1];
        heap[1] = heap[n];
        n--;
        bajar(1); /** Restablece el orden del monticulo */
        return min;
    }

    /**
     * es un metodo auxiliar para bajar un elemento.
     * i va a ser el indice del elemento a bajar
     */
    private void bajar(int i) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && heap[j].getEtiqueta() > heap[j + 1].getEtiqueta()) j++;
            if (heap[i].getEtiqueta() <= heap[j].getEtiqueta()) break;
            
            RegistroImpresion temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
            i = j;
        }
    }
    
 /**
 * elimina un documento especifico de la cola
 * se supone que sigue la logica de bajar su tiempo al minimo, subirlo a la raiz y eliminarlo
 * etiquetaBuscada - la etiqueta de tiempo del documento a eliminar
 */
public void eliminarDocumentoEspecifico(int etiquetaBuscada) {
    int posicion = -1;

    /** 1. buscar la posicion en el arreglo */
    for (int i = 1; i <= n; i++) {
        if (heap[i].getEtiqueta() == etiquetaBuscada) {
            posicion = i;
            break;
        }
    }

    if (posicion != -1) {
        /**  se le asigna un tiempo menor al de todos */
        /** se crea un registro temporal con prioridad maxima */
        RegistroImpresion temporal = new RegistroImpresion("ELIMINAR", -999999);
        heap[posicion] = temporal;

        /** se le hace subir hasta la raiz */
        subir(posicion);

        /** Lo eliminamos usando la primitiva estandar */
        eliminarMin();
        System.out.println("Documento eliminado de la cola con exito.");
    }
}

}
