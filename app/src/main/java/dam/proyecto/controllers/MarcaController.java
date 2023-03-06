package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.repositories.MarcaRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.04
 */

public class MarcaController {

    private Context context;
    private MarcaRepository repository;

    public MarcaController(Context context ){
        this.context = context;
        this.repository = new MarcaRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        repository.clear();
    }

    /**
     * Comprueba si un comercio ya está registrado a partir de su nombre
     * @param name nombre del comercio buscado
     * @return true si el comercio existe
     */
    public boolean existsMarca( String name ){
        MarcaEntity marca = null;
        marca = repository.findByName( name );
        return ( marca != null );
    }

    /**
     * Método que devuelve la colección completa de comecios
     * @return
     */
    public ArrayList<MarcaEntity> getAll(){
        return repository.getAll();
    }

    /**
     * Devuelve el siguiente id válido de la tabla
     * @return
     */
    public int getNuevoId(){
        int id = repository.getMaxId();
        id++;
        return id;
    }

    /**
     * Inserta un objeto a partir de un objeto
     * @param objeto el objeto que se quiere insertar
     */
    public void insert( MarcaEntity objeto ){
        repository.insert( objeto );
    }

    /**
     * Inserta un comercio a partir del nombre
     * @param name nombre del comercio
     */
    public void insert( String name ){
        // Comprobar si el comercio existe
         if( !existsMarca( name ) ){
             insert( new MarcaEntity( getNuevoId(), name ));
         }
    }

    /**
     * Inserta un nuevo comercio a partir de todos sus datos.
     * Si el comercio existe, no se hace nada.
     * @param id del comercio
     * @param nombre del nombre
     */
    public void insert( int id, String nombre ){
       if( !existsMarca( nombre ) ){
           insert( new MarcaEntity( id, nombre));
       }
    }

    /**
     * Devuelve el objeto marca a partir de su id
     * @param idObjeto id de la marca
     * @return MarcaEntity relacionado con el idComercio
     */
    public MarcaEntity getById( int idObjeto ){
        return repository.findById( idObjeto );
    }




}
