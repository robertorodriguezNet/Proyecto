package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.MedidaEntity;
import dam.proyecto.database.repositories.MedidaRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class MedidaController {

    private final MedidaRepository REPOSITORY;

    public MedidaController(Context context ){
        this.REPOSITORY = new MedidaRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return devuelve la colección completa
     */
    public ArrayList<MedidaEntity> getAll(){
        return (ArrayList<MedidaEntity>) REPOSITORY.getAll();
    }

    /**
     * Inserta un objeto en la tabla
     * @param objeto que se quiere insertar
     */
    public void insert( MedidaEntity objeto ){
        REPOSITORY.insert( objeto );
    }

    /**
     * Inserta un objeto en la tabla
     * @param id que se quiere insertar
     * @param name de la medida
     */
    public void insert( String id, String name ){
        REPOSITORY.insert( new MedidaEntity( id, name ) );
    }
}
