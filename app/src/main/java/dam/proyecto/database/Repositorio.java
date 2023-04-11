package dam.proyecto.database;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * Clase que ofrece funciones para gestionar la base de datos.
 */
public class Repositorio {

    protected AppDatabase db;

    public Repositorio( Context context ) {
        this.db = AppDatabase.getInstance( context );
    }

    public AppDatabase getDb() {
        return db;
    }

    @NonNull
    @Override
    public String toString() {
        return ( db == null ) ? "db nulo" : "Repositorio{" +
                "db=" + db +
                '}';
    }

}
