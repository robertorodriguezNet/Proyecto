package dam.proyecto.database.data;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Importa los datos de ejemplo que hay en la base de datos remota.
 * <p>
 * Hace uso de la clase ExportDB.grabarDB(file, data) para crear los csv.
 * <p>
 * https://youtu.be/APd0nYeEr5U
 * 1.44  Descargar driver MariaDB JDBC
 * https://mariadb.com/kb/en/java-connector-using-gradle/
 */
public class ImportarEjemplos {

    private static Connection connection;

    public static void importar(Context context) {

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

            // Importar los productos
            statement = connection.createStatement();

            // Grabamos el archivo csv
            ExportDB.grabar(
                    "ProductoEntity.csv",
                    getProductos(
                            statement.executeQuery("SELECT * FROM Productos")
                    ),
                    context);

            // Importar los productos desde el archivo csv
            ImportDB.importarProductoEntity( context );

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

    /**
     * Montamos la respuesta.
     * @param rs es el resultado de la consulta
     * @return el texto que debe escribirse en el archivo csv
     * @throws SQLException si da error
     */
    private static String getProductos(ResultSet rs) throws SQLException {

        StringBuilder datos = new StringBuilder();

        while (rs.next()) {
            datos.append(rs.getString("id"))
                    .append(",")
                    .append(rs.getString("denominacion"))
                    .append(",")
//                    .append(rs.getInt("marca"))
                    .append(1)
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

}
