/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
/**
 *
 * @author Daniel Alonso
 */
public class VisualizadorArbol {
    private Graph graph;

    public void mostrar(Clases.RegistroImpresion[] array, int size) {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("Monticulo Binario");
    
    // 1. Configuración de estilo básica
    graph.setAttribute("ui.stylesheet", 
        "node { " +
        "   fill-color: #3498db; " + // Color azul
        "   size: 45px; " +          // Tamaño del círculo (Súbelo a 45 o 50)
        "   text-alignment: center; " +
        "   text-size: 16px; " +
        "   text-color: white; " +
        "   stroke-mode: plain; " +
        "   stroke-color: black; " +
        "   stroke-width: 2px; " +
        "} " +
        "edge { " +
        "   fill-color: #7f8c8d; " +
        "   size: 3px; " +           // Grosor de la línea
        "}");

    // 2. CREAR TODOS LOS NODOS PRIMERO
    for (int i = 1; i < size; i++) {
        Clases.RegistroImpresion reg = (Clases.RegistroImpresion) array[i];
        if (reg != null) {
            String id = String.valueOf(i);
            Node n = graph.addNode(id);
            n.setAttribute("ui.label", reg.getNombreDocumento() + " (" + reg.getEtiqueta() + ")");
        }
    }

    // 3. CREAR LAS CONEXIONES (ARISTAS) DESPUÉS
    for (int i = 2; i < size; i++) { 
        if (array[i] != null) {
            int padreIdx = i / 2; // Fórmula matemática del Montículo
            
            String hijoId = String.valueOf(i);
            String padreId = String.valueOf(padreIdx);
            
            // Verificamos que ambos nodos existan antes de conectar
            if (graph.getNode(padreId) != null && graph.getNode(hijoId) != null) {
                graph.addEdge(padreId + "-" + hijoId, padreId, hijoId);
            }
        }
    }
try {
    Thread.sleep(100); // Una pausa de 100ms para que el motor cargue
} catch (InterruptedException e) {}
    // 4. Mostrar el gráfico
    Viewer viewer = graph.display();
    viewer.enableAutoLayout(); 
}
    }
   
