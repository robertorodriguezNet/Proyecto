/**
 * En esta interfaz definimos las consultas que queramos hacer a l base de datos.
 */
package dam.proyecto.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import dam.proyecto.database.entity.*;
import java.util.List;

@Dao
public interface MedidaDao {

    // CREATE -------------------------------------
    @Insert
    void insert( MedidaEntity object);

    @Insert
    void insertAll(List<MedidaEntity> data);

    // READ ---------------------------------------
    @Query( "SELECT * FROM Medida")
    List<MedidaEntity> getAll();

    @Query( "SELECT * FROM Medida WHERE id = :id")
    MedidaEntity findById( String id );

    // UPDATE ------------------------------------
    @Update
    void update( MedidaEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( MedidaEntity object );

    @Query( "DELETE FROM medida" )
    void clear();

}
