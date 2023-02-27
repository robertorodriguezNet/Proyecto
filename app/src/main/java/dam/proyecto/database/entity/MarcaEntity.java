package dam.proyecto.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity( tableName = "Marca")
public class MarcaEntity {

    @PrimaryKey( autoGenerate = true ) private long id;
    @ColumnInfo( name = "name" ) private String name;

    public MarcaEntity( String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
