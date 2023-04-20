package dam.proyecto.database.data;

import android.content.Context;
import android.util.Log;
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
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.controllers.TagController;
import dam.proyecto.controllers.TagProductoController;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.MarcaBlancaEntity;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.MedidaEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.entity.TagEntity;
import dam.proyecto.database.entity.TagsProductoEntity;

/**
 * Graba un archivo SQL con las consultas INSERT de la base de datos remota
 */
public class ExportarEjemplos {

    private static StringBuilder sql;

    private static Context context;

    public static void exportar(Context c) {

        context = c;

        sql = new StringBuilder();
        sql
                .append(getComercio())
                .append(getCompra())
                .append(getMarca())
                .append(getMarcaBlanca())
                .append(getMedida())
                .append(getNombreCompra())
                .append(getOferta())
                .append(getProductos())
                .append(getTag())
                .append(getTagsProducto())

        ;

        grabar(sql.toString());

//        Log.d("LDLC", sql.toString());

    }

    private static String getConsulta(String tabla, String columnas, String valores) {

        return "INSERT INTO " + tabla + " (" + columnas + ") VALUES (" + valores + ");";

    }

    private static String getComercio() {

        // Obtenemos todos los objetos de ComercioEntity
        ComercioController controller = new ComercioController(context);
        ArrayList<ComercioEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (ComercioEntity r : resultado) {
            String columnas = ("id,name");
            String valores = r.getId() + ",'" + r.getName() + "'";
            consulta.append(
                            getConsulta("Comercio",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();

    }

    private static String getCompra() {

        // Obtenemos todos los objetos de ComercioEntity
        CompraController controller = new CompraController(context);
        ArrayList<CompraEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (CompraEntity r : resultado) {
            String columnas = ("id,producto,fecha,precio,cantidad,pagado,precioMedido,oferta");
            String valores = r.getId() + "," +
                    "'" + r.getProducto() + "'," +
                    "'" + r.getFecha() + "'," +
                    r.getPrecio() + "," +
                    r.getCantidad() + "," +
                    r.getPagado() + "," +
                    r.getPrecioMedido() + "," +
                    r.getOferta();

            consulta.append(
                            getConsulta("Compra",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();

    }

    private static String getMarca() {

        // Obtenemos todos los objetos de ComercioEntity
        MarcaController controller = new MarcaController(context);
        ArrayList<MarcaEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (MarcaEntity r : resultado) {
            String columnas = ("id,name");
            String valores = r.getId() + ",'" + r.getName().replace("'", "\\'") + "'";
            consulta.append(
                            getConsulta("Marca",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();
    }


    private static String getMarcaBlanca() {

        // Obtenemos todos los objetos de ComercioEntity
        MarcaBlancaController controller = new MarcaBlancaController(context);
        ArrayList<MarcaBlancaEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (MarcaBlancaEntity r : resultado) {
            String columnas = ("id,marca,comercio");
            String valores = r.getId() + "," +
                    r.getMarca() + "," +
                    r.getComercio();

            consulta.append(
                            getConsulta("MarcaBlanca",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();
    }

    private static String getMedida() {

        // Obtenemos todos los objetos de ComercioEntity
        MedidaController controller = new MedidaController(context);
        ArrayList<MedidaEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (MedidaEntity r : resultado) {
            String columnas = ("id,description");
            String valores = "'" + r.getId() + "','" + r.getDescription() + "'";
            consulta.append(
                            getConsulta("Medida",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();

    }


    private static String getNombreCompra() {

        // Obtenemos todos los objetos de ComercioEntity
        NombreCompraController controller = new NombreCompraController(context);
        ArrayList<NombreCompraEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (NombreCompraEntity r : resultado) {
            String columnas = ("id,nombre,comercio");
            String valores = "'" + r.getId() + "'," +
                    "'" + r.getNombre() + "'," +
                    r.getComercio();

            consulta.append(
                            getConsulta("Nombrecompra",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();

    }

    private static String getOferta() {

        // Obtenemos todos los objetos de ComercioEntity
        OfertaController controller = new OfertaController(context);
        ArrayList<OfertaEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (OfertaEntity r : resultado) {
            String columnas = ("abbr,texto");
            String valores = "'" + r.getAbbr() + "','" + r.getTexto() + "'";
            consulta.append(
                            getConsulta("Oferta",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();

    }


    private static String getProductos() {

        // Obtenemos todos los objetos
        ArrayList<ProductoEntity> resultado = ProductoController.getAll(context);

        StringBuilder consulta = new StringBuilder();

        for (ProductoEntity r : resultado) {
            String columnas = ("id,denominacion,marca,unidades,medida,cantidad");
            String valores = r.getId() + "," +
                    "'" + r.getDenominacion() + "'," +
                    r.getMarca() + "," +
                    r.getUnidades() + "," +
                    "'" + r.getMedida() + "'," +
                    r.getCantidad();

            consulta.append(
                            getConsulta("Productos",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();
    }

    private static String getTag() {

        // Obtenemos todos los objetos de ComercioEntity
        TagController controller = new TagController(context);
        ArrayList<TagEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (TagEntity r : resultado) {
            String columnas = ("id,name");
            String valores = r.getId() + ",'" + r.getName() + "'";
            consulta.append(
                            getConsulta("Tag",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();

    }

    private static String getTagsProducto() {

        // Obtenemos todos los objetos de ComercioEntity
        TagProductoController controller = new TagProductoController(context);
        ArrayList<TagsProductoEntity> resultado = controller.getAll();

        StringBuilder consulta = new StringBuilder();

        for (TagsProductoEntity r : resultado) {
            String columnas = ("id,producto,tag");
            String valores = r.getId() + ",'" + r.getProducto() + "'," + r.getTag();
            consulta.append(
                            getConsulta("TagsProducto",
                                    columnas.toString(),
                                    valores.toString()
                            )
                    )
                    .append("\n");
        }

        return consulta.toString();

    }

    private static void grabar( String sql ){
        OutputStreamWriter osr = null;
        try{
            osr = new OutputStreamWriter(
                    context.openFileOutput("ldlc.sql", Context.MODE_PRIVATE)
            );

            osr.write(sql);
            osr.flush();

        }catch (IOException e) {
            Toast.makeText(context, "No se pudo crear el archivo LDLC.SQL", Toast.LENGTH_SHORT).show();
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

}
