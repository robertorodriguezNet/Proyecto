package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.repositories.OfertaRespository;

/**
 * Clase que realiza operaciones
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class OfertaController {

    private final OfertaRespository REPOSITORY;

    public OfertaController(Context context ){
        this.REPOSITORY = new OfertaRespository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return la colección completa de objetos
     */
    public ArrayList<OfertaEntity> getAll(){
        return REPOSITORY.getAll();
    }

    /**
     * Inserta un nuevo objeto en la tabla
     * @param id el id del objeto
     * @param name el nombre del objeto
     */
    public void insert( String id, String name ){
        REPOSITORY.insert( new OfertaEntity( id, name ));
    }

}
