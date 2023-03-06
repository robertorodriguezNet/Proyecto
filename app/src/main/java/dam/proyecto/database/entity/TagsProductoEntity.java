package dam.proyecto.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 * @author Roberto Rodríguez
 * @since 17/01/2023
 * @version 2023.03.06
 */
@Entity( tableName = "TagsProducto" )
public class TagsProductoEntity {

    @PrimaryKey( autoGenerate = false )
    @NonNull
    private int id;
    @NonNull @ColumnInfo( name = "producto" ) private String producto;
    @ColumnInfo( name = "tag" ) @NonNull private int tag;

    public TagsProductoEntity(int id, String producto, int tag) {
        this.id = id;
        this.producto = producto;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
