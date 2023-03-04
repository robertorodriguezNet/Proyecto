package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.repositories.ComercioRespository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.04
 */

public class ComercioControler {

    private Context context;
    private ComercioRespository repository;

    public ComercioControler(Context context ){
        this.context = context;
        this.repository = new ComercioRespository( context );
    }

    /**
     * Devuelve el objeto comercio a partir de su id
     * @param idComercio id del comercio
     * @return ComercioEntity relacionado con el idComercio
     */
    public ComercioEntity getById( int idComercio ){
        return repository.findById( idComercio );
    }

    /**
     * Método que devuelve la colección completa de comecios
     * @return
     */
    public ArrayList<ComercioEntity> getAll(){
        return repository.getAll();
    }



}
