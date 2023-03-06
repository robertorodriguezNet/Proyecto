package dam.proyecto.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dam.proyecto.database.entity.MarcaBlancaEntity;

/**
 *  * Marca blanca
 * @author Roberto Rodríguez
 * @since 27/02/2023
 * @version 2023.02.27
 */
@Dao
public interface MarcaBlancaDao {


    // CREATE -------------------------------------
    @Insert
    void insert(MarcaBlancaEntity object);

    @Insert
    void insertAll(List<MarcaBlancaEntity> objects);


    // READ ---------------------------------------
    @Query("SELECT * FROM MarcaBlanca")
    List<MarcaBlancaEntity> getAll();

    @Query( "SELECT MAX(id) FROM marcablanca")
    int getMaxId();

    @Query("SELECT * FROM MarcaBlanca WHERE id = :args")
    MarcaBlancaEntity findById(int args);

    @Query("SELECT * FROM MarcaBlanca WHERE marca = :args")
    List<MarcaBlancaEntity> findByMarca(int args);

    @Query("SELECT * FROM MarcaBlanca WHERE comercio = :args")
    List<MarcaBlancaEntity> findByComercio(int args);


    // UPDATE ------------------------------------
    @Update
    void update(MarcaBlancaEntity object);

    // DELETE ------------------------------------
    @Delete
    void delete(MarcaBlancaEntity object);

    @Query("DELETE FROM marcablanca")
    void clear();

}
