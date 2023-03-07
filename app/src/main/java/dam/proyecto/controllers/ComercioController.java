package dam.proyecto.controllers;

import static dam.proyecto.Config.ERROR_CREAR_COMERCIO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.repositories.ComercioRespository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class ComercioController {

    private Context context;
    private ComercioRespository repository;

    public ComercioController(Context context ){
        this.context = context;
        this.repository = new ComercioRespository( context );
    }

    /**
     * Añade un nuevo comercio a la lista
     * @param name nombre del comercio que se añade
     */
    public void addComercio( String name ){

        // Comprobar el nombre del comercio
        int error = -1;
        if( ( name.length() < 3 ) || ( name.length() > 16 ) ){
            error = 0;
        }
        if( ( error == -1) && ( existsComercio( name ) )){
            error = 1;
        }

//        Log.d( "LDLC", "Añadir comercio: " + name );

        if( error >= 0 ){
            Toast.makeText(context,
                    name + "\n" + ERROR_CREAR_COMERCIO[error],
                    Toast.LENGTH_SHORT).show();
        } else {
            insert( name );
        }
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
    public boolean existsComercio( String name ){
        ComercioEntity comercio = null;
        comercio = repository.findByName( name );
        return ( comercio != null );
    }

    /**
     * Método que devuelve la colección completa de comecios
     * @return
     */
    public ArrayList<ComercioEntity> getAll(){
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
     * Inserta un comercio a partir de un objeto comercio
     * @param comercio el comercio que se quiere insertar
     */
    public void insert( ComercioEntity comercio ){
        repository.insert( comercio );
    }

    /**
     * Inserta un comercio a partir del nombre
     * @param name nombre del comercio
     */
    public void insert( String name ){
        // Comprobar si el comercio existe
         if( !existsComercio( name ) ){
             insert( new ComercioEntity( getNuevoId(), name ));
         }
    }

    /**
     * Inserta un nuevo comercio a partir de todos sus datos.
     * Si el comercio existe, no se hace nada.
     * @param id del comercio
     * @param nombre del nombre
     */
    public void insert( int id, String nombre ){
       if( !existsComercio( nombre ) ){
           insert( new ComercioEntity( id, nombre));
       }
    }

    /**
     * Devuelve el objeto comercio a partir de su id
     * @param idComercio id del comercio
     * @return ComercioEntity relacionado con el idComercio
     */
    public ComercioEntity getById( int idComercio ){
        return repository.findById( idComercio );
    }




}
