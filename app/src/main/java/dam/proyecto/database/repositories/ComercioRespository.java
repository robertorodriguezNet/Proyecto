package dam.proyecto.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.ComercioDao;
import dam.proyecto.database.entity.ComercioEntity;

/**
 * @author Roberto Rodríguez
 * @since 23/01/2023
 * @version 2023.03.14
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
     * Devuelve el objeto comercio a partir de su id
     * @param id id del comercio
     * @return ComercioEntity relacionado con el id
     */
    public ComercioEntity findById( int id ){
        return  dao.findById( id );
    }


    /**
     * Devuelve el objeto comercio a partir de su nombre
     * @param name nombre del comercio
     * @return ComercioEntity relacionado con el nombre
     */
    public ComercioEntity findByName( String name ){
        return  dao.findByName( name );
    }


    /**
     * Devuelve un listado completo de los registros.
     *
     * @return la colección de objetos
     */
    public ArrayList<ComercioEntity> getAll() {
        return (ArrayList<ComercioEntity>) dao.getAll();
    }

    /**
     * Devuelve un listado completo de los nombres de los registros.
     *
     * @return la colección de nombres
     */
    public ArrayList<String> getAllNames() {
        return (ArrayList<String>) dao.getAllNames();
    }

    /**
     * Devuelve el id más alto de los registrados
     * @return el id más alto
     */
    public int getMaxId(){
        return dao.getMaxId();
    }

    /**
     * Devuelve el nombre del comercio con el id recibido
     * @param id del comercio buscado
     * @return String el nombre del comercio
     */
    public String getNombreComercio( int id ){
        return dao.findById( id ).getName();
    }

    /**
     * Inserta un comercio en la base de datos
     * @param objeto que se inserta
     */
    public void insert( ComercioEntity objeto){
        dao.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<ComercioEntity> data) {
        dao.insertAll(data);
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
