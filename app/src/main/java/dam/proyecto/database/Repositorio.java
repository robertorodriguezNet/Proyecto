package dam.proyecto.database;

import android.content.Context;
import android.util.Log;

/**
 * Clase que ofrece funciones para gestionar la base de datos.
 */
public class Repositorio {

    protected AppDatabase db;

    public Repositorio( Context context ) {

        this.db = AppDatabase.getInstance( context );

        Log.d("BD", "Repositorio.class:  " + toString() );
    }

    public AppDatabase getDb() {
        return db;
    }

    @Override
    public String toString() {
        return ( db == null ) ? "db nulo" : "Repositorio{" +
                "db=" + db +
                '}';
    }

}
