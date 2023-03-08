package dam.proyecto.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity( tableName = "Marca")
public class MarcaEntity {

    @PrimaryKey( autoGenerate = true ) private int id;
    @ColumnInfo( name = "name" ) private String name;

    public MarcaEntity( int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
