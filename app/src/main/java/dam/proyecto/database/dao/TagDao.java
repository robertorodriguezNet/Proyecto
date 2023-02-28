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
public interface TagDao {

    // CREATE -------------------------------------
    @Insert
    void insert( TagEntity object);

    @Insert
    void insertAll(List<TagEntity> data);

    // READ ---------------------------------------
    @Query( "SELECT * FROM Tag")
    List<TagEntity> getAll();

    @Query( "SELECT * FROM Tag WHERE id = :id")
    TagEntity findById( int id );

    @Query( "SELECT name FROM tag WHERE id = :args ")
    String getNameById( int args );

    // UPDATE ------------------------------------
    @Update
    void update( TagEntity object );

    // DELETE ------------------------------------
    @Delete
    void delete( TagEntity object );

    @Query( "DELETE FROM tag" )
    void clear();

}
