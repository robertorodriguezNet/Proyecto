package dam.proyecto.database.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * Importa los registros desde los archivos previamente generados y guardados.
 * Cada entidad debe ser tratada de forma particular debido a los diferentes
 * tipos de datos.
 *
 * *Pedimos a los controladores que guarden los productos pasándoles los datos
 * como parámetros y no como objetos.
 * Esto se hace para unificar la creación de las entidades.
 * Por ejemplo: ProductoEntity guarda un dato "Oferta" que aún no está implementado.
 * El controlador se encargará de darle una solución.
 *
 * @author  Roberto Rodríguez Jiménez
 * @since 05/03/2023
 * @version 2023.03.05
 */
public class ImportDB {

    private static Context context;

    public static void importDB(Context c) {
        context = c;
        importarProductos();
    }

    /**
     * Grabar en la base de datos el contenido de file
     *
     * @param file
     * @return StringBuilde el texto con los registros
     */
    private static BufferedReader getRegistros(String file) {

        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;
        try {

            isr = new InputStreamReader(context.openFileInput(file));
            bufferedReader = new BufferedReader(isr);

        } catch (Exception e) {
            Toast.makeText(context, "No se pudo leer el archivo " + file, Toast.LENGTH_SHORT).show();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e) {
                    Toast.makeText(context, "Error al cerrar el fulujo de datos", Toast.LENGTH_SHORT).show();
                }
            }
            return bufferedReader;
        }

    }

    /**
     * Importa el contenido del fichero con los registros guardados
     */
    private static void importarProductos() {

        String file = "ProductoEntity.csv";
        BufferedReader bufferedReader = getRegistros(file);                 // Contenido del archivo

        ProductoEntity entity;                                        // Entidad de la base de datos

        String linea;                             // Guarda cada línea leída desde el BufferedReader
        try {

            // Borrar los datos

            // Cada línea leída es un ProductoEntity
            while ((linea = bufferedReader.readLine()) != null) {

                // Array con los datos del objeto que se va a crear
                String[] data = linea.split(",");

                // Pedimos al controlador de productos que lo guarde *
                ProductoController.insertProducto(
                        data[0],
                        data[1],
                        Integer.valueOf( data[2] ),
                        Integer.valueOf( data[3] ),
                        data[4],
                        Float.valueOf( data[5] ),
                        context
                );

            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al leer ProductoEntity", Toast.LENGTH_SHORT).show();
        }


    }

}
