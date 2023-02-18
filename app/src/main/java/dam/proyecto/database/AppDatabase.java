package dam.proyecto.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dam.proyecto.database.dao.*;
import dam.proyecto.database.data.Ejemplos;
import dam.proyecto.database.data.MedidaData;
import dam.proyecto.database.entity.*;

@Database(
        entities = {
                ComercioEntity.class,
                CompraEntity.class,
                MarcaEntity.class,
                MedidaEntity.class,
                ProductoEntity.class,
                TagEntity.class,
                TagsProductoEntity.class,
                NombreCompraEntity.class,
                OfertaEntity.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    // Instancia que vamos a devolver
    private static AppDatabase INSTANCE;

    // DAO's
    public abstract ComercioDao comercioDao();
    public abstract CompraDao compraDao();
    public abstract MarcaDao marcaDao();
    public abstract MedidaDao medidaDao();
    public abstract ProductoDao productoDao();
    public abstract TagDao tagDao();
    public abstract TagsProductoDao tagsProductoDao();
    public abstract NombreCompraDao nombreCompraDao();
    public abstract OfertaDao ofertaDao();

    // NOmbre de la base de datos
    private static final String DB_NAME = "listadelacompra.db";

    public static AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}