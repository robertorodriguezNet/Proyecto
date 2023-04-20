package dam.proyecto.database.data;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static void exportar(Context context) {

        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        Statement statement = null;
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://hl1166.dinaserver.com:3306/ldlc", "ldlc", "LDLC@0wq79s"
            );
            statement = connection.createStatement();

            // Vaciamos las tablas
            vaciarTablas( statement);

            // Insertar datos en las tablas
            insertComercio(statement, context);
            insertCompra(statement, context);
            insertMarca(statement, context);
            insertMarcaBlanca(statement, context);
            insertMedida(statement, context);
            insertNombreCompra(statement, context);
            insertOferta(statement, context);
            insertProductos(statement, context);
            insertTag(statement, context);
            insertTagsProducto(statement, context);

        } catch (Exception e) {
            Log.d("LDLC", "Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                // Do nothing
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private static String getConsulta(String tabla, String columnas, String valores) {

        return "INSERT INTO " + tabla + " (" + columnas + ") VALUES (" + valores + ");";

    }

    private static void insertComercio(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos de ComercioEntity
        ComercioController controller = new ComercioController(context);
        ArrayList<ComercioEntity> resultado = controller.getAll();

        for (ComercioEntity r : resultado) {
            String columnas = ("id,name");
            String valores = r.getId() + ",'" + r.getName() + "'";
            statement.execute( getConsulta("Comercio",
                    columnas,
                    valores
            ) );
        }
    }

    private static void insertCompra(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos de ComercioEntity
        CompraController controller = new CompraController(context);
        ArrayList<CompraEntity> resultado = controller.getAll();

        for (CompraEntity r : resultado) {
            String columnas = ("id,producto,fecha,precio,cantidad,pagado,precioMedido,oferta");

            // PrecioMedido podría aparecer como "Infinity"
            float precioMedio = (r.getPrecioMedido() >= 1000000f)? 0.0f : r.getPrecioMedido();

            String valores = r.getId() + "," +
                    "'" + r.getProducto() + "'," +
                    "'" + r.getFecha() + "'," +
                    r.getPrecio() + "," +
                    r.getCantidad() + "," +
                    r.getPagado() + "," +
                    precioMedio + "," +
                    r.getOferta();

            statement.execute( getConsulta("Compra",
                    columnas,
                    valores
            ) );
        }

    }

    private static void insertMarca(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos de ComercioEntity
        MarcaController controller = new MarcaController(context);
        ArrayList<MarcaEntity> resultado = controller.getAll();

        for (MarcaEntity r : resultado) {
            String columnas = ("id,name");
            String valores = r.getId() + ",'" + r.getName().replace("'", "\\'") + "'";
            statement.execute( getConsulta("Marca",
                    columnas,
                    valores
            ) );
        }

    }


    private static void insertMarcaBlanca(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos de ComercioEntity
        MarcaBlancaController controller = new MarcaBlancaController(context);
        ArrayList<MarcaBlancaEntity> resultado = controller.getAll();

        for (MarcaBlancaEntity r : resultado) {
            String columnas = ("id,marca,comercio");
            String valores = r.getId() + "," +
                    r.getMarca() + "," +
                    r.getComercio();

            statement.execute( getConsulta("MarcaBlanca",
                    columnas,
                    valores
            ) );
        }

    }

    private static void insertMedida(Statement statement, Context context) throws SQLException{

        // Obtenemos todos los objetos de ComercioEntity
        MedidaController controller = new MedidaController(context);
        ArrayList<MedidaEntity> resultado = controller.getAll();

        for (MedidaEntity r : resultado) {
            String columnas = ("id,description");
            String valores = "'" + r.getId() + "','" + r.getDescription() + "'";
            statement.execute( getConsulta("Medida",
                    columnas,
                    valores
            ) );
        }

    }


    private static void insertNombreCompra(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos de ComercioEntity
        NombreCompraController controller = new NombreCompraController(context);
        ArrayList<NombreCompraEntity> resultado = controller.getAll();

        for (NombreCompraEntity r : resultado) {
            String columnas = ("id,nombre,comercio");
            String valores = "'" + r.getId() + "'," +
                    "'" + r.getNombre() + "'," +
                    r.getComercio();

            statement.execute( getConsulta("Nombrecompra",
                    columnas,
                    valores
            ) );
        }

    }

    private static void insertOferta(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos de ComercioEntity
        OfertaController controller = new OfertaController(context);
        ArrayList<OfertaEntity> resultado = controller.getAll();

        for (OfertaEntity r : resultado) {
            String columnas = ("abbr,texto");
            String valores = "'" + r.getAbbr() + "','" + r.getTexto() + "'";
            statement.execute( getConsulta("Oferta",
                    columnas,
                    valores
            ) );
        }

    }


    private static void insertProductos(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos
        ArrayList<ProductoEntity> resultado = ProductoController.getAll(context);

        for (ProductoEntity r : resultado) {
            String columnas = ("id,denominacion,marca,unidades,medida,cantidad");
            String valores = r.getId() + "," +
                    "'" + r.getDenominacion() + "'," +
                    r.getMarca() + "," +
                    r.getUnidades() + "," +
                    "'" + r.getMedida() + "'," +
                    r.getCantidad();

            statement.execute( getConsulta("Productos",
                    columnas,
                    valores
            ) );
        }
    }

    private static void insertTag(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos de ComercioEntity
        TagController controller = new TagController(context);
        ArrayList<TagEntity> resultado = controller.getAll();

        for (TagEntity r : resultado) {
            String columnas = ("id,name");
            String valores = r.getId() + ",'" + r.getName() + "'";
            statement.execute( getConsulta("Tag",
                    columnas,
                    valores
            ) );
        }

    }

    private static void insertTagsProducto(Statement statement, Context context) throws SQLException {

        // Obtenemos todos los objetos de ComercioEntity
        TagProductoController controller = new TagProductoController(context);
        ArrayList<TagsProductoEntity> resultado = controller.getAll();

        for (TagsProductoEntity r : resultado) {
            String columnas = ("id,producto,tag");
            String valores = r.getId() + ",'" + r.getProducto() + "'," + r.getTag();
            statement.execute( getConsulta("TagsProducto",
                    columnas,
                    valores
            ) );
        }

    }


    private static void vaciarTablas(Statement statement) throws SQLException {

        statement.executeQuery("TRUNCATE Comercio");
        statement.executeQuery("TRUNCATE Compra");
        statement.executeQuery("TRUNCATE Marca");
        statement.executeQuery("TRUNCATE MarcaBlanca");
        statement.executeQuery("TRUNCATE Medida");
        statement.executeQuery("TRUNCATE Nombrecompra");
        statement.executeQuery("TRUNCATE Oferta");
        statement.executeQuery("TRUNCATE Productos");
        statement.executeQuery("TRUNCATE Tag");
        statement.executeQuery("TRUNCATE TagsProducto");

    }

}
