package dam.proyecto.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Representa una oferta aplicada a una CompraEntity
 * @since 2023/02/16
 * @author Roberto Rodríguez
 * @version 2023.02.16
 */
@Entity( tableName = "Oferta" )
public class OfertaEntity {

    @PrimaryKey( autoGenerate = false ) @NonNull
    private String id;
    @ColumnInfo( name = "texto" ) private String texto;

    public OfertaEntity( String id, String texto ) {
        this.texto = texto;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return id + ", " + texto;
    }
}