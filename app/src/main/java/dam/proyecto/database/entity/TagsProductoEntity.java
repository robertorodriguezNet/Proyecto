package dam.proyecto.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity( tableName = "TagsProducto" )
public class TagsProductoEntity {

    @PrimaryKey( autoGenerate = true )
    @NonNull
    private Long id;
    @NonNull @ColumnInfo( name = "producto" ) private String producto;
    @ColumnInfo( name = "tag" ) @NonNull private int tag;

    public TagsProductoEntity(String producto, int tag) {
        this.producto = producto;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
