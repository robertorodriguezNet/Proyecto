package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.repositories.MarcaRepository;
import dam.proyecto.database.repositories.OfertaRespository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 06/03/2023
 * @version 2023.03.06
 */

public class OfertaController {

    private Context context;
    private OfertaRespository repository;

    public OfertaController(Context context ){
        this.context = context;
        this.repository = new OfertaRespository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        repository.clear();
    }

    /**
     * Método que devuelve la colección completa de comecios
     * @return
     */
    public ArrayList<OfertaEntity> getAll(){
        return repository.getAll();
    }


    /**
     * Inserta un objeto a partir de un objeto
     * @param objeto el objeto que se quiere insertar
     */
    public void insert( OfertaEntity objeto ){
        repository.insert( objeto );
    }

    /**
     * Inserta un nuevo comercio a partir de todos sus datos.
     * Si el comercio existe, no se hace nada.
     * @param id del comercio
     * @param nombre del nombre
     */
    public void insert( String id, String nombre ){
           insert( new OfertaEntity( id, nombre));
    }

    /**
     * Devuelve el objeto marca a partir de su id
     * @param idObjeto id de la marca
     * @return MarcaEntity relacionado con el idComercio
     */
    public OfertaEntity getById( String idObjeto ){
        return repository.findById( idObjeto );
    }




}
