package dam.proyecto.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import dam.proyecto.database.entity.CompraEntity;

import java.util.List;

@Dao
public interface CompraDao {

    // CREATE -------------------------------------
    @Insert
    void insert( CompraEntity object);

    @Insert
    void insertAll( List<CompraEntity> objects);

    // READ ---------------------------------------
    @Query( "SELECT * FROM Compra")
    List<CompraEntity> getAll();

    @Query( "SELECT * FROM Compra WHERE id = :id")
    CompraEntity findById( String id );

    @Query( "SELECT * FROM Compra WHERE fecha LIKE :fecha")
    List<CompraEntity> findByFecha( String fecha );

    @Query( " SELECT MAX( precio ) FROM compra WHERE producto = :producto ")
    float getMaxPrecio( String producto );

    // UPDATE ------------------------------------
    @Update
    void update( CompraEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( CompraEntity object );

    @Query( "DELETE FROM compra" )
    void clear();
}
