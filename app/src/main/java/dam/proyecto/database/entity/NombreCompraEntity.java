package dam.proyecto.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Los registros de la tabla se identifican un entero.
 * Cuando la lista se guarda, tan sólo se indica el nombre.
 *
 */
@Entity( tableName = "Nombrecompra")
public class NombreCompraEntity {

    @PrimaryKey()
    @NonNull
    @ColumnInfo private String id;
    @ColumnInfo( name = "nombre" ) private String nombre;
    @ColumnInfo( name = "comercio" ) private int comercio;

    public NombreCompraEntity(@NonNull String id, String nombre, int comercio) {
        this.id = id;
        this.nombre = nombre;
        this.comercio = comercio;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getComercio() {
        return comercio;
    }

    public void setComercio(int comercio) {
        this.comercio = comercio;
    }
}
