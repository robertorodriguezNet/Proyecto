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
     * Comprueba la asociación de una marca con un comercio
     * @param comercio buscado
     * @param marca buscada
     * @return true si la asociación existe
     */
    public boolean exists( int comercio, int marca ){
        return REPOSITORY.getByArgs( comercio, marca ) != null;
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return la colección de objetos completa
     */
    public ArrayList<MarcaBlancaEntity> getAll(){
        return REPOSITORY.getAll();
    }

    /**
     * Devuelve las marcas (id) de un comercio dado
     * @param comercio buscado
     * @return listado de marcas
     */
    public ArrayList<Integer> getMarcasByComercio( int comercio ){
        return REPOSITORY.getMarcasByComercio( comercio );
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

    /**
     * Inserta un objeto a partir de todos sus datos
     * @param id identificador
     * @param marca nueva
     * @param comercio al que pertenece la marca
     */
    public void insert( int id, int marca, int comercio ){
        REPOSITORY.insert( new MarcaBlancaEntity( id, marca, comercio ));
    }

}
