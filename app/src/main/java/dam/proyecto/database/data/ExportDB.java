package dam.proyecto.database.data;


import android.annotation.SuppressLint;
import android.content.Context;

import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import dam.proyecto.controllers.ComercioController;

import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.MarcaBlancaController;
import dam.proyecto.controllers.MarcaController;
import dam.proyecto.controllers.MedidaController;
import dam.proyecto.controllers.NombreCompraController;
import dam.proyecto.controllers.OfertaController;
import dam.proyecto.controllers.TagController;
import dam.proyecto.controllers.TagProductoController;

import dam.proyecto.database.entity.MarcaBlancaEntity;
import dam.proyecto.database.repositories.ProductoRepository;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.07
 * @see "https://youtu.be/KvM65JoaeFE"
 * @since 17/02/2023
 */
public class ExportDB {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void exportDB(Context c) {

        context = c;

        // Debemos obtner todos los registros de todas las tablas
        exportarComercioEntity();
        exportarCompraEntity();
        exportarMarcaBlancaEntity();
        exportarMarcaEntity();
        exportarMedidaEntity();
        exportarNombreCompraEntity();
        exportarOfertaEntity();
        exportarProductoEntity();
        exportarTagEnity();
        exportarTagsProductoEntity();

    }

    /**
     * Grabar el contenido de data en el archivo file
     *
     * @param file archivo en es que se va a escribir
     * @param data datos que se escribirán en el arcivo
     */
    private static void grabar(String file, String data) {
        OutputStreamWriter osr = null;
        try {
            osr = new OutputStreamWriter(
                    context.openFileOutput(file, Context.MODE_PRIVATE)
            );

            osr.write(data);
            osr.flush();
        } catch (IOException e) {
            Toast.makeText(context, "No se pudo crear el archivo " + file, Toast.LENGTH_SHORT).show();
        } finally {
            if (osr != null) {
                try {
                    osr.close();
                } catch (Exception e) {
                    Toast.makeText(context, "Error al cerrar el fulujo de datos", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private static void exportarComercioEntity() {
        ComercioController controller = new ComercioController(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto -> code.append(objeto.getId())
                .append(",")
                .append(objeto.getName())
                .append("\n"));

        grabar("ComercioEntity.csv", code.toString());
    }


    private static void exportarNombreCompraEntity() {
        NombreCompraController controller = new NombreCompraController(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto -> code.append(objeto.getId())
                .append(",")
                .append(objeto.getNombre())
                .append(",")
                .append(objeto.getComercio())
                .append("\n"));

        grabar("NombreCompraEntity.csv", code.toString());
    }

    /**
     * Exportar los tags.
     * Los id no son autoincrement, hay que exportarlos.
     */
    private static void exportarTagEnity() {
        TagController controller = new TagController(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto -> code.append(objeto.getId())
                .append(",")
                .append(objeto.getName())
                .append("\n"));

        grabar("TagEntity.csv", code.toString());
    }

    /**
     * El id de la compra no se exporta ya que se genera de forma
     * automática al crearse el objeto.
     */
    private static void exportarCompraEntity() {
        CompraController controller = new CompraController(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto ->code.append(objeto.getProducto())
                        .append(",")
                        .append(objeto.getFecha())
                        .append(",")
                        .append(objeto.getCantidad())
                        .append(",")
                        .append(objeto.getPagado())
                        .append(",")
                        .append(objeto.getPrecio())
                        .append(",")
                        .append(objeto.getPrecioMedido())
                        .append(",")
                        .append(objeto.getOferta())
                        .append("\n")
                );

        grabar("CompraEntity.csv", code.toString());
    }

    private static void exportarMarcaBlancaEntity() {
        MarcaBlancaController controller = new MarcaBlancaController(context);
        ArrayList<MarcaBlancaEntity> data = controller.getAll();

        StringBuilder code = new StringBuilder();
        for (MarcaBlancaEntity objeto : data) {
            code.append(objeto.getId())
                    .append(",")
                    .append(objeto.getMarca())
                    .append(",")
                    .append(objeto.getComercio())
                    .append("\n");
        }
        grabar("MarcaBlancaEntity.csv", code.toString() );
    }

    private static void exportarMarcaEntity() {
        MarcaController controller = new MarcaController(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto -> code.append(objeto.getId())
                .append(",")
                .append(objeto.getName())
                .append("\n"));

        grabar("MarcaEntity.csv", code.toString() );
    }

    private static void exportarMedidaEntity() {
        MedidaController controller = new MedidaController(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto -> code.append(objeto.getId())
                .append(",")
                .append(objeto.getDescription())
                .append("\n"));

        grabar("MedidaEntity.csv", code.toString());
    }


    /**
     * Creamos el archivo de productos.
     * El id de cada registro es relevante, no puede cambiar, su formato
     * es el código de barras.
     */
    private static void exportarProductoEntity() {

        ProductoRepository controller = new ProductoRepository(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto -> code.append(objeto.getId())
                .append(",")
                .append(objeto.getDenominacion())
                .append(",")
                .append(objeto.getMarca())
                .append(",")
                .append(objeto.getUnidades())
                .append(",")
                .append(objeto.getMedida())
                .append(",")
                .append(objeto.getCantidad())
                .append("\n"));

        grabar("ProductoEntity.csv", code.toString());
    }

    private static void exportarTagsProductoEntity() {
        TagProductoController controller = new TagProductoController(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto -> code.append( objeto.getId() )
                .append(",")
                .append(objeto.getProducto())
                .append(",")
                .append(objeto.getTag())
                .append("\n"));

        grabar("TagsProductoEntity.csv", code.toString());
    }

    private static void exportarOfertaEntity() {
        OfertaController controller = new OfertaController(context);

        StringBuilder code = new StringBuilder();
        controller.getAll().forEach( objeto -> code.append(objeto.getAbbr())
                .append(",")
                .append(objeto.getTexto())
                .append("\n"));

        grabar("OfertaEntity.csv", code.toString());
    }

}
