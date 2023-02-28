package dam.proyecto.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import dam.proyecto.database.entity.*;

import java.util.List;

@Dao
public interface TagsProductoDao {

    // CREATE -------------------------------------
    @Insert
    void insert( TagsProductoEntity object);

    @Insert
    void insertAll (List<TagsProductoEntity> objects );

    // READ ---------------------------------------
    @Query( "SELECT * FROM TagsProducto")
    List<TagsProductoEntity> getAll();

    @Query( "SELECT tag FROM TagsProducto WHERE producto = :args")
    List<Integer> getTagByProducto( String args);

    @Query( "SELECT * FROM TagsProducto WHERE id = :id")
    TagsProductoEntity findById( String id );

    @Query( "SELECT * FROM tagsproducto WHERE producto = :idProducto AND tag = :idTag")
    TagsProductoEntity getAsociacion( String idProducto, int idTag);

    // UPDATE ------------------------------------
    @Update
    void update( TagsProductoEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( TagsProductoEntity object );


    @Query( "DELETE FROM tagsproducto" )
    void clear();
    
}
