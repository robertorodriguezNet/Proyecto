package dam.proyecto.database.entity;

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

    @PrimaryKey( autoGenerate = true ) private int id;
    @ColumnInfo( name = "abbr" ) private String abbr;
    @ColumnInfo( name = "texto" ) private String texto;

    public OfertaEntity(String abbr, String texto ) {
        this.abbr = abbr;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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