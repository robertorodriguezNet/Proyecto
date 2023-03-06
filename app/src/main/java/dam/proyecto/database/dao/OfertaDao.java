/**
 * En esta interfaz definimos las consultas que queramos hacer a l base de datos.
 */
package dam.proyecto.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.OfertaEntity;

@Dao
public interface OfertaDao {

    // CREATE -------------------------------------
    @Insert
    void insert( OfertaEntity object);

    @Insert
    void insertAll( List<OfertaEntity> objects );

    // READ ---------------------------------------
    @Query( "SELECT * FROM Oferta")
    List<OfertaEntity> getAll();

    @Query( "SELECT * FROM Oferta WHERE id = :id")
    OfertaEntity findById( String id );

    // UPDATE ------------------------------------
    @Update
    void update( OfertaEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( OfertaEntity object );

    @Query( "DELETE FROM oferta" )
    void clear();
}
