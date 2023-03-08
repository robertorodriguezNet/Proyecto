package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.MarcaBlancaEntity;
import dam.proyecto.database.repositories.MarcaBlancaRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class MarcaBlancaController {

    private final MarcaBlancaRepository REPOSITORY;

    public MarcaBlancaController(Context context ){
        this.REPOSITORY = new MarcaBlancaRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return la colección de objetos completa
     */
    public ArrayList<MarcaBlancaEntity> getAll(){
        return REPOSITORY.getAll();
    }

    /**
     * Devuleve el primer id válido para insertar un objeto
     * @return id válido
     */
    public int getNewId(){
        int id = REPOSITORY.getMaxId();
        id++;
        return id;
    }

    /**
     * Inserta un objeto a partir de la marca y el comercio
     * @param marca nueva
     * @param comercio al que pertenece la marca
     */
    public void insert( int marca, int comercio ){
        REPOSITORY.insert( new MarcaBlancaEntity( getNewId(), marca, comercio ));
    }

}
