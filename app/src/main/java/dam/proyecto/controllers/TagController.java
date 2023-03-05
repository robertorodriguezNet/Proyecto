package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.TagEntity;
import dam.proyecto.database.repositories.TagRepository;

/**
 * Clase que realiza operaciones con las etiquetas
 *
 * @author Roberto Rodríguez Jiménez
 * @since 01/03/2023
 * @version 2023.03.01
 */

public class TagController {

    private Context context;
    private TagRepository repository;

    public TagController(Context context ){
        this.context = context;
        this.repository = new TagRepository( context );
    }

    public void clear(){
        repository.clear();
    }

    /**
     * Método que devuelve la colección completa de etiquetas
     * @return
     */
    public ArrayList<TagEntity> getAll(){
        return repository.getAll();
    }


    /**
     * Devuelve una colección de etiquetas que contienen el texto pedido
     * @param texto buscado
     * @return
     */
    public ArrayList<TagEntity> getAll( String texto ){
        return repository.getAll( texto );
    }

    /**
     * Devuelve una colección de id de las etiquetas que contienen el texto pedido
     * @param texto buscado
     * @return
     */
    public ArrayList<Integer> getAllId( String texto ){
        return repository.getAllId( texto );
    }

    /**
     * Devuelve el primer índice válido
     * @return
     */
    public int getIndex(){
        int id = repository.getMaxId();
        id++;
        return id;
    }

    /**
     * Devuelve una colección con los id's de los productos que en sus etiquetas
     * contienen el texto
     * @param texto
     * @return
     */
    public ArrayList<String> getProductosByTag( String texto ){
        ArrayList<String> list = repository.getProductosByTag( texto );
        return list;
    }

    /**
     * Insrta un tag a partir de un objeto
     * @param tag
     */
    public void insert( TagEntity tag ){
        repository.insert( tag );
    }

    /**
     * Inserta un tag desde el nombre
     * El id se pide al repositorio
     * @param tag la etiqueta
     */
    public void insert( String tag ){
        insert( new TagEntity( getIndex(), tag ) );
    }

    /**
     * Inserta un tag desde el nombre
     * El id se recibe como parámetro
     * @param id de la etiqueta
     * @param tag la etiqueta
     */
    public void insert( int id, String tag ){
        insert( new TagEntity( id, tag ) );
    }


}
