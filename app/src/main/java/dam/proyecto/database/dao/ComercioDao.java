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
public interface ComercioDao {

    // CREATE -------------------------------------
    @Insert
    void insert( ComercioEntity object);

    @Insert
    void insertAll( List<ComercioEntity> objects );

    // READ ---------------------------------------


    @Query( "SELECT * FROM Comercio WHERE id = :id")
    ComercioEntity findById( int id );

    @Query( "SELECT * FROM Comercio WHERE name = :param")
    ComercioEntity findByName( String param );

    @Query( "SELECT * FROM Comercio ORDER BY name")
    List<ComercioEntity> getAll();

    @Query( "SELECT name FROM Comercio ORDER BY name")
    List<String> getAllNames();

    @Query( "SELECT MAX(id) FROM comercio")
    int getMaxId();

    // UPDATE ------------------------------------
    @Update
    void update( ComercioEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( ComercioEntity object );

    @Query( "DELETE FROM comercio" )
    void clear();
}
