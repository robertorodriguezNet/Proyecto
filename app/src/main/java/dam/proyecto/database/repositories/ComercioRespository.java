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
 * @since 23/01/2023
 * @version 2023.03.04
 */
public class ComercioRespository extends Repositorio {

    ComercioDao dao = db.comercioDao();

    public ComercioRespository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        dao.clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<ComercioEntity> data) {
        db.comercioDao().insertAll(data);
    }

    /**
     * Devuelve el objeto comercio a partir de su id
     * @param id id del comercio
     * @return ComercioEntity relacionado con el id
     */
    public ComercioEntity findById( int id ){
        return  dao.findById( id );
    }


    /**
     * Devuelve un listado completo de los registros.
     *
     * @return
     */
    public ArrayList<ComercioEntity> getAll() {
        return (ArrayList<ComercioEntity>) dao.getAll();
    }

    /**
     * Devuelve el nombre del comercio con el id recibido
     * @param id del comercio buscado
     * @return String el nombre del comercio
     */
    public String getNombreComercio( int id ){
        return dao.findById( id ).getName();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
