package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.repositories.MarcaRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class MarcaController {

    private final MarcaRepository REPOSITORY;

    public MarcaController(Context context ){
        this.REPOSITORY = new MarcaRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return devuelve el listado de objetos
     */
    public ArrayList<MarcaEntity> getAll(){
        return REPOSITORY.getAll();
    }


//    /**
////     * Devuelve el id de una marca si existe, si no existe, la guarda.
////     * @param marcaStr la marca buscada
////     * @param control No tengo claro para qué lo puse, pero debe ser false
////     * @return el id de la marca
////     */
////    public int getIdByName( String marcaStr, boolean control ){
////
////        String marca = marcaStr.trim();
////
////        // Obtenemos el objeto MarcaEntity
////        MarcaEntity marcaEntity = null;
////        marcaEntity = REPOSITORY.findByName( marca );
////
////        // Si marcaEntity es nulo, es que el objeto no existe
////        if( marcaEntity == null ){
////            // Guardamos la marca
////            REPOSITORY.insert( new MarcaEntity( getNewId(), marca ));
////            marcaEntity = REPOSITORY.findByName( marca );
////        }
////
////        return ( marcaEntity == null ) ? -1 : (int) marcaEntity.getId();
////    }

    /**
     * Devuelve el id de una marca si existe, si no existe, la guarda.
     * @param marcaStr la marca buscada
     * @return el id de la marca
     */
    public int getIdByName( String marcaStr ){

        String marca = marcaStr.trim();

        // Obtenemos el objeto MarcaEntity
        MarcaEntity marcaEntity = REPOSITORY.findByName( marca );

        // Si marcaEntity es nulo, es que el objeto no existe
        if( marcaEntity == null ){
            // Guardamos la marca
            REPOSITORY.insert( new MarcaEntity( getNewId(), marca ));
            marcaEntity = REPOSITORY.findByName( marca );
        }

        return ( marcaEntity == null ) ? -1 : marcaEntity.getId();
    }

    /**
     * Devuelve el nombre de la marca correspondiente al id recibido
     * @param  id del cual hay que devolver el nombre
     * @return nombre de la marca asociada al id
     */
    public String getNameById( int id ){

        MarcaEntity marca = REPOSITORY.findById( id );
        return marca.getName();

    }

    /**
     * Devuelve el primer índice válido de la tabla
     * @return un índice válido para un nuevo registro
     */
    public int getNewId(){
        return 0;
    }


    /**
     * Devuelve un listado con tan sólo los nombres
     * @return nombres de las marcas
     */
    public ArrayList<String> getNombres(){
        ArrayList<MarcaEntity> objetos = getAll();
        ArrayList<String> nombres = new ArrayList<>();

        for ( MarcaEntity objeto: objetos ) {
            nombres.add( objeto.getName() );
        }

        return nombres;
    }


    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaEntity> data ){
        REPOSITORY.insertAll( data );
    }

    /**
     * Insertar un objeto con sólo el nombre
     * @param name nombre del objeto
     */
    public void insert( String name ){
        REPOSITORY.insert( new MarcaEntity( getNewId(), name ));
    }

    /**
     * Insertar un objeto a partir del id y del nombre
     * @param id del objeto
     * @param name del objeto
     */
    public void insert( int id, String name ){
        REPOSITORY.insert( new MarcaEntity( id, name ));
    }


}
