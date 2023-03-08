package dam.proyecto.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Representa una oferta aplicada a una CompraEntity
 * @author Roberto Rodríguez
 * @since 16/02/2023
 * @version 2023.03.08
 */
@Entity( tableName = "Oferta" )
public class OfertaEntity {

    @PrimaryKey @NonNull
    private String abbr;
    @ColumnInfo( name = "texto" ) private String texto;

    public OfertaEntity(String abbr, String texto ) {
        this.abbr = abbr;
        this.texto = texto;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return abbr;
    }
}