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

import dam.proyecto.database.entity.NombreCompraEntity;

/**
 * @author Roberto Rodríguez Jiménez
 * @since 26/02/2023
 * @version 2023.02.26
 */
@Dao
public interface NombreCompraDao {

    // CREATE -------------------------------------
    @Insert
    void insert( NombreCompraEntity object);

    @Insert
    void insertAll(List<NombreCompraEntity> data);

    // READ ---------------------------------------
    @Query( "SELECT * FROM Nombrecompra ORDER BY id DESC")
    List<NombreCompraEntity> getAll();

    @Query( "SELECT id FROM Nombrecompra WHERE comercio = :idComercio ORDER BY id DESC")
    List<String> getAllByComercio( int idComercio );


    @Query( "SELECT * FROM Nombrecompra WHERE id = :id")
    NombreCompraEntity findById( String id );

    @Query( "SELECT * FROM Nombrecompra WHERE nombre = :nombre")
    NombreCompraEntity findByName( String nombre );

    @Query( "SELECT comercio FROM Nombrecompra WHERE id = :idCompra ")
    int getIdComercio( String idCompra );

    // UPDATE ------------------------------------
    @Update
    void update( NombreCompraEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( NombreCompraEntity object );

    @Query( "DELETE FROM Nombrecompra" )
    void clear();

}
