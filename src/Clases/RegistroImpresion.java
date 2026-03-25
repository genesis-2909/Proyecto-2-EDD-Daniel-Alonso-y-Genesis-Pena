/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * representa un documento dentro de la cola de prioridad
 * @author Daniel y Genesis
 */
public class RegistroImpresion {
    private String nombreDocumento;
    private int etiquetaTiempo; /**  determina la prioridad en el monticulo */

    /**
     * crea un registro para la cola aplicando la logica de prioridad
     *  doc - documento que se va a imprimir
     *  usuario - dueño para poder saber su nivel de prioridad
     *  tiempoActual - valor actual del reloj del sistema
     *  esPrioritario - si el usuario decidio enviarlo como prioritario
     */
    public RegistroImpresion(Documento doc, Usuario usuario, int tiempoActual, boolean esPrioritario) {
        this.nombreDocumento = doc.getNombre();
        
        if (esPrioritario) {
            /** Si es prioritario, restamos tiempo para que el valor sea menor y suba en el Min-Heap*/       
            this.etiquetaTiempo = calcularPrioridad(usuario, tiempoActual);
        } else {
            /** Si no, entra con el tiempo normal del reloj */
            this.etiquetaTiempo = tiempoActual;
        }
    }
    
    /**
     * constructor para la eliminacion, que permite crear un registro
     * con una etiqueta manual para forzar la subida al inicio
     */
    public RegistroImpresion(String nombre, int etiqueta) {
        this.nombreDocumento = nombre;
        this.etiquetaTiempo = etiqueta;
    }

    /**
     * altera el tiempo segun el tipo de usuario que sea
     */
    private int calcularPrioridad(Usuario u, int tiempo) {
        if (u.getTipoPrioridad().equals("prioridad_alta")) {
            return tiempo - 100; /** Valor arbitrario para que vaya al inicio */
        } else if (u.getTipoPrioridad().equals("prioridad_media")) {
            return tiempo - 50;
        }
        return tiempo; /** Prioridad baja se queda igual */
    }
    
    /** Getters */
    public int getEtiqueta() { return etiquetaTiempo; }
    public String getNombreDocumento() { return nombreDocumento; }
}
