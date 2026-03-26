/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 * clase que representa un documento creado por un usuario
 * el cual va a contener informacion como nombre, tamaño y tipo
 * @author Daniel y Genesis
 */
public class Documento {
    private String nombre;
    private int tamano;
    private String tipo;

    /**
     * Constructor para un nuevo documento
     * nombre - nombre del archivo
     * tamano - cantidad de paginas o peso
     * tipo - extension o categoria del documento
     */
    public Documento(String nombre, int tamano, String tipo) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.tipo = tipo;
    }

    /** Getters */
    public String getNombre() { return nombre; }
    public int getTamano() { return tamano; }
    public String getTipo() { return tipo; }
}
