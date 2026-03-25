/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.filechooser.FileNameExtensionFilter;
import EDD.Tablahash;

/**
 * clase encargada de manejar la lectura del archivo CSV.
 * @author Daniel y genesis
 */
public class LectorCSV {
    /**
     * Abre un selector de archivos y procesa el CSV seleccionado.
     *  tablaUsuarios - la tabla hash donde se guardaran los usuarios cargados
     */
    public void cargarUsuarios(Tablahash tablaUsuarios) {
        // Configuracion del selector de archivos 
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos CSV", "csv");
        selector.setFileFilter(filtro);

        int resultado = selector.showOpenDialog(null);

        /** Si el usuario selecciona un archivo y presiona "abrir" */
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                /** Saltamos la primera linea si es el encabezado (usuario, tipo) */
                br.readLine(); 

                while ((linea = br.readLine()) != null) {
                    /** Separamos por coma */
                    String[] datos = linea.split(",");
                    
                    if (datos.length >= 2) {
                        String nombre = datos[0].trim();
                        String prioridad = datos[1].trim();
                        
                        /** se crea el usuario y lo metemos en la tabla */
                        Usuario nuevoUsuario = new Usuario(nombre, prioridad);
                        tablaUsuarios.insertar(nuevoUsuario);
                    }
                }
                System.out.println("Usuarios cargados exitosamente ");
            } catch (Exception e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        }
    }
}
