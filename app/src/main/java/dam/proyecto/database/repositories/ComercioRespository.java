package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.ComercioDao;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.TagEntity;

/**
 * @author Roberto Rodríguez
 * @version 2023.02.19
 * @since 2023/01/23
 */
public class ComercioRespository extends Repositorio {
    public ComercioRespository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        db.comercioDao().clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<ComercioEntity> data) {
        db.comercioDao().insertAll(data);
    }

    /**
     * Devuelve un listado completo de los registros.
     *
     * @return
     */
    public ArrayList<ComercioEntity> getAll() {
        return (ArrayList<ComercioEntity>) getDb().comercioDao().getAll();
    }

    public String getNombreComercio( int id ){
        ComercioEntity comercio = getDb()
                .comercioDao()
                .findById( id );
        return comercio.getName();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
