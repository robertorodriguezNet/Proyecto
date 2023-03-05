package dam.proyecto.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * PK no es auto-increment
 *
 * @author Roberto Rodríguez Jiménez
 * @since 18/02/2023
 * @version 2023.03.05
 */
@Entity( tableName = "Tag" )
public class TagEntity {

    @PrimaryKey( autoGenerate = false ) private int id;
    @ColumnInfo( name = "name" ) private String name;

    public TagEntity( int id, String name ) {

        this.name = name;
        this.id = id;
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
