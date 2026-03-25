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
        // Configuramos la propiedad para que use la interfaz de Swing
        System.setProperty("org.graphstream.ui", "swing");
        
        graph = new SingleGraph("Cola de Prioridad (Montículo)");
        
        String styleSheet = 
            "node { fill-color: #2980b9; size: 40px; text-alignment: center; " +
            "text-size: 14px; text-color: white; stroke-mode: plain; stroke-color: black; } " +
            "edge { fill-color: #7f8c8d; width: 2px; }";
        graph.setAttribute("ui.stylesheet", styleSheet);

        // Recorremos el arreglo del montículo
        for (int i = 0; i < size; i++) {
            Clases.RegistroImpresion reg = array[i];
            if (reg != null) {
                String id = String.valueOf(i);
                Node n = graph.addNode(id);
                
                // Ponemos el nombre del doc y su prioridad en el círculo
                n.setAttribute("ui.label", reg.getNombreDocumento() + " (" + reg.getEtiqueta() + ")");
                
                // Conectamos con el padre (si no es la raíz)
                if (i > 0) {
                    int padreIndex = (i - 1) / 2;
                    graph.addEdge(padreIndex + "-" + i, String.valueOf(padreIndex), id);
                }
            }
        }

        // Abrimos la ventana del gráfico
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
}
