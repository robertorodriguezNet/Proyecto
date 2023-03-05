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
    void insert(TagEntity object);

    @Insert
    void insertAll(List<TagEntity> data);

    // READ ---------------------------------------
    @Query("SELECT * FROM Tag")
    List<TagEntity> getAll();

    @Query("SELECT * FROM Tag WHERE name LIKE '%' || :args || '%' ")
    List<TagEntity> getAll( String args);

    @Query("SELECT id FROM Tag WHERE name LIKE '%' || :args || '%' ")
    List<Integer> getAllId( String args);

    @Query( "select producto from TagsProducto where tag in ( select id from tag where name like '%' || :tag || '%');")
    List<String> getProductosByTag( String tag );

    @Query("SELECT * FROM Tag WHERE id = :args")
    TagEntity findById(int args);

    @Query("SELECT * FROM Tag WHERE name = :args")
    TagEntity findByName(String args);

    @Query("SELECT name FROM tag WHERE id = :args ")
    String getNameById(int args);

    @Query("SELECT id FROM tag WHERE name = :args ")
    int getIdByName(String args);

    // UPDATE ------------------------------------
    @Update
    void update(TagEntity object);

    // DELETE ------------------------------------
    @Delete
    void delete(TagEntity object);

    @Query("DELETE FROM tag")
    void clear();



}
