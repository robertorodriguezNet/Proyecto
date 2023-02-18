package dam.proyecto.database.entity;

import android.nfc.Tag;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity( tableName = "Tag" )
public class TagEntity {

    @PrimaryKey( autoGenerate = true ) private long id;
    @ColumnInfo( name = "name" ) private String name;

    public TagEntity( String name ) {
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
