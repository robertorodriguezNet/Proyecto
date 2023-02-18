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

    @Query( "SELECT * FROM TagsProducto WHERE id = :id")
    TagsProductoEntity findById( String id );

    // UPDATE ------------------------------------
    @Update
    void update( TagsProductoEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( TagsProductoEntity object );


    @Query( "DELETE FROM tagsproducto" )
    void clear();
    
}
