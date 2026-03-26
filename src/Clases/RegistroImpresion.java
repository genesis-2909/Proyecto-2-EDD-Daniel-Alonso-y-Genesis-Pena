/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * Clase que representa un documento enviado a la cola de impresión.
 * Almacena la información básica del documento, su propietario y su prioridad.
 */
public class RegistroImpresion {
    private String nombreDocumento;
    private String nombreUsuario;
    private int etiquetaTiempo;

    /**
     * Constructor de la clase RegistroImpresion.
     * * @param nombreDocumento El nombre o título del documento a imprimir.
     * @param nombreUsuario El nombre del usuario dueño del documento.
     * @param etiquetaTiempo El valor de prioridad o etiqueta de tiempo calculada.
     */
    public RegistroImpresion(String nombreDocumento, String nombreUsuario, int etiquetaTiempo) {
        this.nombreDocumento = nombreDocumento;
        this.nombreUsuario = nombreUsuario;
        this.etiquetaTiempo = etiquetaTiempo;
    }

    /**
     * Obtiene el nombre del documento.
     * * @return Un String con el nombre del documento.
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * Obtiene el nombre del usuario dueño del documento.
     * * @return Un String con el nombre del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Obtiene la etiqueta de tiempo o prioridad del documento.
     * * @return Un entero que representa la prioridad en la cola.
     */
    public int getEtiqueta() {
        return etiquetaTiempo;
    }

    /**
     * Modifica la etiqueta de tiempo (prioridad) del documento.
     * * @param etiquetaTiempo El nuevo valor de prioridad a asignar.
     */
    public void setEtiqueta(int etiquetaTiempo) {
        this.etiquetaTiempo = etiquetaTiempo;
    }
}