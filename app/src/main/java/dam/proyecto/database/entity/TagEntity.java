package dam.proyecto.database.entity;

import android.nfc.Tag;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity( tableName = "Tag" )
public class TagEntity {

    @PrimaryKey( autoGenerate = false ) private int id;
    @ColumnInfo( name = "name" ) private String name;

    public TagEntity( int id, String name ) {
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
