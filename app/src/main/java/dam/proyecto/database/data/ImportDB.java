package dam.proyecto.database.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dam.proyecto.controllers.ComercioController;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.MarcaBlancaController;
import dam.proyecto.controllers.MarcaController;
import dam.proyecto.controllers.MedidaController;
import dam.proyecto.controllers.OfertaController;
import dam.proyecto.controllers.TagController;

/**
 * Importa los registros desde los archivos previamente generados y guardados.
 * Cada entidad debe ser tratada de forma particular debido a los diferentes
 * tipos de datos.
 * <p>
 * *Pedimos a los controladores que guarden los productos pasándoles los datos
 * como parámetros y no como objetos.
 * Esto se hace para unificar la creación de las entidades.
 * Por ejemplo: ProductoEntity guarda un dato "Oferta" que aún no está implementado.
 * El controlador se encargará de darle una solución.
 *
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.05
 * @since 05/03/2023
 */
public class ImportDB {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void importDB(Context c) {
        context = c;

        // Importar las clases auxiliares, que no tienen relaciones
        importarTagEntity();
        importarComercioEntity();
        importarMarcaEntity();
        importarMedidaEntity();
        importarOfertaEntity();
        importarMarcaBlancaEntity();

        importarCompraEntity();
//        importarProductoEntity();
//        importarNombreCompraEntity();
//        importarProductoTagEntity();
    }

    /**
     * Grabar en la base de datos el contenido de file
     *
     * @param file es el fichero que se lee
     * @return StringBuilde el texto con los registros
     */
    private static ArrayList<String> getRegistros(String file) {

        InputStreamReader isr = null;
        BufferedReader bufferedReader;

        ArrayList<String> data = new ArrayList<>();

        try {

            isr = new InputStreamReader(context.openFileInput(file));
            bufferedReader = new BufferedReader(isr);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                data.add(linea);
            }

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
            return data;
        }

    }

    /**
     * Importa el contenido del fichero con los registros guardados.
     */
    private static void importarProductoEntity() {

        String file = "ProductoEntity.csv";

        try {

//            // Borrar los datos
//            ProductoController.clearData(context);
//
//            // Cada línea leída es un ProductoEntity
//            ArrayList<String> registros = getRegistros(file);
//            for (String registro : registros) {
//
//                String[] data = registro.split(",");
//                // Pedimos al controlador de productos que lo guarde *
//                ProductoController.insertProducto(
//                        data[0],
//                        data[1],
//                        Integer.valueOf(data[2]),
//                        Integer.valueOf(data[3]),
//                        data[4],
//                        Float.valueOf(data[5]),
//                        context
//                );
//            }

        } catch (Exception e) {
            Toast.makeText(context, "Error al leer ProductoEntity", Toast.LENGTH_SHORT).show();
            Log.e("LDLC", "Error al importar productos:\n"
                    + e.getMessage());
        }
    }

    /**
     * Importa la entidad CompraEntity.
     * El id de la compra no se importa porque se genera de forma
     * automática al crearse el objeto.
     */
    private static void importarCompraEntity() {

        String file = "CompraEntity.csv";
        CompraController controller = new CompraController(context);

        try {
            // Borrar los datos
            controller.clear();

            // Cada línea leída es un ProductoEntity
            ArrayList<String> registros = getRegistros(file);
            for (String registro : registros) {

                String[] data = registro.split(",");
                // Pedimos al controlador de productos que lo guarde *
                controller.insert(
                        data[0],
                        data[1],
                        Float.parseFloat(data[2]),
                        Float.parseFloat(data[3]),
                        Float.parseFloat(data[4]),
                        Float.parseFloat(data[5]),
                        Integer.parseInt(data[6])
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al leer CompraEntity", Toast.LENGTH_SHORT).show();
            Log.e("LDLC", "Error al importar CompraEntity:\n"
                    + e.getMessage());
        }
    }

    /**
     * Importa la entidad NombreCompraEntity.
     */
    private static void importarNombreCompraEntity() {

//        String file = "NombreCompraEntity.csv";
//        NombreCompraController controller = new NombreCompraController(context);
//
//        try {
//            // Borrar los datos
//            controller.clearData();
//
//            // Cada línea leída es un ProductoEntity
//            ArrayList<String> registros = getRegistros(file);
//            for (String registro : registros) {
//
//                String[] data = registro.split(",");
//                // Pedimos al controlador de productos que lo guarde *
//                controller.insert(
//                        data[0],
//                        data[1],
//                        Integer.valueOf(data[2])
//                );
//            }
//        } catch (Exception e) {
//            Toast.makeText(context, "Error al leer NombreCompraEntity", Toast.LENGTH_SHORT).show();
//            Log.e("LDLC", "Error al importar NombreCompraEntity:\n"
//                    + e.getMessage());
//        }
    }

    /**
     * Importa la entidad TagEntity.
     * El id no es auto-increment
     */
    private static void importarTagEntity() {

        String file = "TagEntity.csv";
        TagController controller = new TagController(context);

        try {
            // Borrar los datos
            controller.clear();

            // Pedir las líneas del ficher de texto
            ArrayList<String> registros = getRegistros(file);

            // Cada línea leída es un ProductoEntity
            for (String registro : registros) {

                String[] data = registro.split(",");
                // Pedimos al controlador de productos que lo guarde
                controller.insert(
                        Integer.parseInt(data[0]),
                        data[1]
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al leer TagEntity", Toast.LENGTH_SHORT).show();
            Log.e("LDLC", "Error al importar TagEntity:\n"
                    + e.getMessage());
        }
    }

    /**
     * Importa la entidad ComercioEntity.
     * El id no es auto-increment
     */
    private static void importarComercioEntity() {

        String file = "ComercioEntity.csv";
        ComercioController controller = new ComercioController(context);

        try {
            // Borrar los datos
            controller.clear();

            // Cada línea leída es un ProductoEntity
            ArrayList<String> registros = getRegistros(file);
            for (String registro : registros) {

                String[] data = registro.split(",");
                controller.insert(
                        Integer.parseInt( data[0]),
                        (data.length == 1)? "" : data[1]
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al leer ComercioEntity", Toast.LENGTH_SHORT).show();
            Log.e("LDLC", "Error al importar ComercioEntity:\n"
                    + e.getMessage());
        }
    }


    /**
     * Importa la entidad TagEntity.
     * El id no es auto-increment
     */
    private static void importarProductoTagEntity() {

        String file = "TagsProductoEntity.csv";
//        TagProductoController controller = new TagProductoController(context);
//
//        try {
//            // Borrar los datos
//            controller.clear();
//
//            // Cada línea leída es un ProductoEntity
//            ArrayList<String> registros = getRegistros(file);
//            for (String registro : registros) {
//
//                String[] data = registro.split(",");
//                // Pedimos al controlador de productos que lo guarde
//                controller.insert(
//                        data[0],
//                        Integer.valueOf(data[1])
//                );
//            }
//        } catch (Exception e) {
//            Toast.makeText(context, "Error al leer TagsProductoEntity", Toast.LENGTH_SHORT).show();
//            Log.e("LDLC", "Error al importar TagsProductoEntity:\n"
//                    + e.getMessage());
//        }
    }

    /**
     * Importa la entidad MarcaEntity.
     * El id no es auto-increment
     */
    private static void importarMarcaEntity() {

        String file = "MarcaEntity.csv";
        MarcaController controller = new MarcaController(context);

        try {
            // Borrar los datos
            controller.clear();

            // Cada línea leída es un ProductoEntity
            ArrayList<String> registros = getRegistros(file);
            for (String registro : registros) {

                String[] data = registro.split(",");

                // Pedimos al controlador de productos que lo guarde
                // Es posible que data tenga tan solo un registro, pues
                // existe un nombre de comercio en blanco
                controller.insert(
                        Integer.parseInt( data[0] ),
                        ( data.length == 1) ? "" : data[1]
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al leer MarcaEntity", Toast.LENGTH_SHORT).show();
            Log.e("LDLC", "Error al importar MarcaEntity:\n"
                    + e.getMessage());
        }
    }

    /**
     * Importa la entidad MedidaEntity.
     * El id no es auto-increment
     */
    private static void importarMedidaEntity() {

        String file = "MedidaEntity.csv";
        MedidaController controller = new MedidaController(context);

        try {
            // Borrar los datos
            controller.clear();

            // Cada línea leída es un ProductoEntity
            ArrayList<String> registros = getRegistros(file);
            for (String registro : registros) {

                String[] data = registro.split(",");

                // Pedimos al controlador de productos que lo guarde
                // Es posible que data tenga tan solo un registro, pues
                // existe un nombre de comercio en blanco
                controller.insert(
                        data[0],
                        (data.length == 1) ? "" : data[1]
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al leer MedidaEntity", Toast.LENGTH_SHORT).show();
            Log.e("LDLC", "Error al importar MedidaEntity:\n"
                    + e.getMessage());
        }
    }

    /**
     * Importa la entidad MedidaEntity.
     * El id no es auto-increment
     */
    private static void importarOfertaEntity() {

        String file = "OfertaEntity.csv";
        OfertaController controller = new OfertaController(context);

        try {
            // Borrar los datos
            controller.clear();

            // Cada línea leída es un ProductoEntity
            ArrayList<String> registros = getRegistros(file);
            for (String registro : registros) {

                String[] data = registro.split(",");
                controller.insert(
                        data[0],
                        data[1]
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al leer OfertaEntity", Toast.LENGTH_SHORT).show();
            Log.e("LDLC", "Error al importar OfertaEntity:\n"
                    + e.getMessage());
        }
    }

    /**
     * Importa la entidad MedidaEntity.
     * El id no es auto-increment
     */
    private static void importarMarcaBlancaEntity() {

        String file = "MarcaBlancaEntity.csv";
        MarcaBlancaController controller = new MarcaBlancaController(context);

        try {
            // Borrar los datos
            controller.clear();

            // Cada línea leída es un ProductoEntity
            ArrayList<String> registros = getRegistros(file);
            for (String registro : registros) {

                String[] data = registro.split(",");
                controller.insert(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        Integer.parseInt(data[2])
                );
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al leer OfertaEntity", Toast.LENGTH_SHORT).show();
            Log.e("LDLC", "Error al importar OfertaEntity:\n"
                    + e.getMessage());
        }
    }

}
