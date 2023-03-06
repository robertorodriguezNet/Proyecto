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
public interface MarcaDao {

    // CREATE -------------------------------------
    @Insert
    void insert(MarcaEntity object);

    @Insert
    void insertAll(List<MarcaEntity> objects);

    // READ ---------------------------------------
    @Query("SELECT * FROM Marca")
    List<MarcaEntity> getAll();

    @Query("SELECT * FROM Marca WHERE id = :id")
    MarcaEntity findById(int id);

    @Query("SELECT * FROM Marca WHERE name = :name")
    MarcaEntity findByName(String name);

    @Query("SELECT MAX(id) FROM marca")
    int getMaxId();

    // UPDATE ------------------------------------
    @Update
    void update(MarcaEntity object);

    // DELETE ------------------------------------
    @Delete
    void delete(MarcaEntity object);

    @Query("DELETE FROM marca")
    void clear();
}
