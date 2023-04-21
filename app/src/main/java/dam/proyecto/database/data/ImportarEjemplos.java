package dam.proyecto.database.data;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Importa los datos de ejemplo que hay en la base de datos remota.
 *
 * En lugar de crear archivos csv, debe insertarlos en la base de datos
 *
 * https://youtu.be/APd0nYeEr5U
 * 1.44  Descargar driver MariaDB JDBC
 * https://mariadb.com/kb/en/java-connector-using-gradle/
 */
public class ImportarEjemplos {

    private static Connection connection;

    /**
     * Importa los datos desde la base de datos remota a la base de datos local.
     * @param context contexto
     * @param total true si queremos importar todos los datos, false si solo queremos
     *             importar los productoc
     */
    public static void importar(Context context, boolean total) {

        StrictMode.ThreadPolicy policy = new StrictMode
                .ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        connection = null;
        Statement statement = null;
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://hl1166.dinaserver.com:3306/ldlc", "ldlc", "LDLC@0wq79s"
            );

            statement = connection.createStatement();

            // Importar los datos
            importarProductos(statement, context, total);

            // Si, además, queremos el resto de datos
            if(total){
                importarMarcas(statement,context);
                importarComercio(statement,context);
                importarCompras(statement,context);
                importarMarcaBlanca(statement,context);
                importarMedida(statement,context);
                importarNombreCompra(statement,context);
                importarOfertas(statement,context);
                importarTag(statement,context);
                importarTagsProducto(statement,context);
            }


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

    private static void importarComercio(Statement statement, Context context) throws SQLException{

        // Grabamos el archivo csv
        ExportDB.grabar(
                "ComercioEntity.csv",
                getComercio(
                        statement.executeQuery("SELECT * FROM Comercio")
                ),
                context);

        // Importar los productos desde el archivo csv
        ImportDB.importarComercioEntity( context );

    }

    private static void importarCompras(Statement statement, Context context) throws SQLException{

        // Grabamos el archivo csv
        ExportDB.grabar(
                "CompraEntity.csv",
                getCompra(
                        statement.executeQuery("SELECT * FROM Compra")
                ),
                context);

        // Importar los productos desde el archivo csv
        ImportDB.importarCompraEntity( context );

    }

    private static void importarMarcas(Statement statement, Context context) throws SQLException{

        // Grabamos el archivo csv
        ExportDB.grabar(
                "MarcaEntity.csv",
                getMarca(
                        statement.executeQuery("SELECT * FROM Marca")
                ),
                context);

        // Importar los productos desde el archivo csv
        ImportDB.importarMarcaEntity( context );

    }

    private static void importarMarcaBlanca(Statement statement, Context context) throws SQLException{

        // Grabamos el archivo csv
        ExportDB.grabar(
                "MarcaBlancaEntity.csv",
                getMarcaBlanca(
                        statement.executeQuery("SELECT * FROM MarcaBlanca")
                ),
                context);

        // Importar los productos desde el archivo csv
        ImportDB.importarMarcaBlancaEntity( context );

    }

    private static void importarMedida(Statement statement, Context context) throws SQLException{

        // Grabamos el archivo csv
        ExportDB.grabar(
                "MedidaEntity.csv",
                getMedida(
                        statement.executeQuery("SELECT * FROM Medida")
                ),
                context);

        // Importar los productos desde el archivo csv
        ImportDB.importarMedidaEntity( context );

    }

    private static void importarNombreCompra(Statement statement, Context context) throws SQLException{

        // Grabamos el archivo csv
        ExportDB.grabar(
                "NombreCompraEntity.csv",
                getNombreCompra(
                        statement.executeQuery("SELECT * FROM Nombrecompra")
                ),
                context);

        // Importar los productos desde el archivo csv
        ImportDB.importarNombreCompraEntity( context );

    }

    private static void importarOfertas(Statement statement, Context context) throws SQLException{

        // Grabamos el archivo csv
        ExportDB.grabar(
                "OfertaEntity.csv",
                getOfertas(
                        statement.executeQuery("SELECT * FROM Oferta")
                ),
                context);

        // Importar los productos desde el archivo csv
        ImportDB.importarOfertaEntity( context );

    }

    /**
     * Importamos los productos desde la base de datos remota
     * @param statement el statement de la conexión
     * @param context el contexto
     */
    private static void importarProductos(Statement statement, Context context, boolean total) throws SQLException {

            // Grabamos el archivo csv
            ExportDB.grabar(
                    "ProductoEntity.csv",
                    getProductos(
                            statement.executeQuery("SELECT * FROM Productos"),
                            total
                    ),
                    context
            );

            // Importar los productos desde el archivo csv
            ImportDB.importarProductoEntity( context );

    }

    private static void importarTag(Statement statement, Context context) throws SQLException {

            // Grabamos el archivo csv
            ExportDB.grabar(
                    "TagEntity.csv",
                    getTags(
                            statement.executeQuery("SELECT * FROM Tag")
                    ),
                    context);

            // Importar los productos desde el archivo csv
            ImportDB.importarTagEntity( context );

    }

    private static void importarTagsProducto(Statement statement, Context context) throws SQLException {

            // Grabamos el archivo csv
            ExportDB.grabar(
                    "TagsProductoEntity.csv",
                    getTagsProducto(
                            statement.executeQuery("SELECT * FROM TagsProducto")
                    ),
                    context);

            // Importar los productos desde el archivo csv
            ImportDB.importarProductoTagEntity( context );

    }



    private static String getComercio(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append( (int) rs.getInt("id"))
                    .append(",")
                    .append(rs.getString("name"))
                    .append("\n");
        }

        return datos.toString();
    }

    /**
     * En la tabla Compra no debemos añadir el campo id, ya que el controlador lo
     * crea a partir de id del producto y de la fecha.
     * El POJO para CompraEntity no recibe el id en el constructor.
     * @param rs
     * @return
     * @throws SQLException
     */
    private static String getCompra(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append( rs.getString("producto"))
                    .append(",")
                    .append(rs.getString("fecha"))
                    .append(",")
                    .append(rs.getFloat("pagado"))
                    .append(",")
                    .append(rs.getFloat("cantidad"))
                    .append(",")
                    .append(rs.getFloat("precio"))
                    .append(",")
                    .append(rs.getFloat("precioMedido"))
                    .append(",")
                    .append(rs.getInt("oferta"))
                    .append("\n");
        }

        return datos.toString();
    }

    private static String getMarca(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append( (int) rs.getInt("id"))
                    .append(",")
                    .append(rs.getString("name"))
                    .append("\n");
        }

        return datos.toString();
    }

    private static String getMarcaBlanca(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append( (int) rs.getInt("id"))
                    .append(",")
                    .append( (int) rs.getInt("marca"))
                    .append(",")
                    .append(rs.getInt("comercio"))
                    .append("\n");
        }

        return datos.toString();
    }

    private static String getMedida(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append(  rs.getString("id"))
                    .append(",")
                    .append( rs.getString("description"))
                    .append("\n");
        }

        return datos.toString();
    }

    private static String getNombreCompra(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append(  rs.getString("id"))
                    .append(",")
                    .append( rs.getString("nombre"))
                    .append(",")
                    .append( rs.getInt("comercio"))
                    .append("\n");
        }

        return datos.toString();
    }

    private static String getOfertas(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append(  rs.getString("abbr"))
                    .append(",")
                    .append( rs.getString("texto"))
                    .append("\n");
        }

        return datos.toString();
    }


    /**
     * Devolvemos el texto del archivo csv que después será importado para
     * insertar los datos en la base de datos local.
     * @param rs es el resultado de la consulta
     * @return el texto que debe escribirse en el archivo csv
     * @throws SQLException si da error
     */
    private static String getProductos(ResultSet rs, boolean total) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append(rs.getString("id"))
                    .append(",")
                    .append(rs.getString("denominacion"))
                    .append(",")
                    .append((total)? rs.getInt("marca") : 1)
                    .append(",")
                    .append(rs.getInt("unidades"))
                    .append(",")
                    .append(rs.getString("medida"))
                    .append(",")
                    .append(rs.getFloat("cantidad"))
                    .append("\n");
        }

        return datos.toString();
    }



    private static String getTags(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append( (int) rs.getInt("id"))
                    .append(",")
                    .append(rs.getString("name"))
                    .append("\n");
        }

        return datos.toString();
    }


    private static String getTagsProducto(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append( (int) rs.getInt("id"))
                    .append(",")
                    .append(rs.getString("producto"))
                    .append(",")
                    .append(rs.getInt("tag"))
                    .append("\n");
        }

        return datos.toString();
    }

}
