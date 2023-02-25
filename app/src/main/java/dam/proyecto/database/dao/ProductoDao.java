package dam.proyecto.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import dam.proyecto.database.entity.ProductoEntity;

import java.util.List;

@Dao
public interface ProductoDao {

    // CREATE -------------------------------------
    @Insert
    void insert( ProductoEntity object);

    @Insert
    void insertAll( List<ProductoEntity> objects );

    // READ ---------------------------------------
    @Query( "SELECT * FROM Productos ORDER BY denominacion ASC")
    List<ProductoEntity> getAll();

    @Query( "SELECT * FROM productos WHERE id = :id")
    ProductoEntity findById( String id );

    @Query( "SELECT * FROM productos WHERE id LIKE '1%'" )
    List<ProductoEntity> getAutomaticId();

    // UPDATE ------------------------------------
    @Update
    void update( ProductoEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( ProductoEntity object );

    @Query( "DELETE FROM productos WHERE id = :id")
    void deleteById( String id );

    @Query( "DELETE FROM productos" )
    void clear();

}

