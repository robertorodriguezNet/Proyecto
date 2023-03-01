package dam.proyecto.controllers;

import android.content.Context;
import android.util.Log;

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

public class TagControler {

    private Context context;
    private TagRepository tagRepository;

    public TagControler(Context context ){
        this.context = context;
        this.tagRepository = new TagRepository( context );
    }

    /**
     * Método que devuelve la colección completa de etiquetas
     * @return
     */
    public ArrayList<TagEntity> getAll(){
        return tagRepository.getAll();
    }

    /**
     * Devuelve una colección con los id's de los productos que en sus etiquetas
     * contienen el texto
     * @param texto
     * @return
     */
    public ArrayList<String> getProductosByTag( String texto ){
        ArrayList<String> list = tagRepository.getProductosByTag( texto );
        return list;
    }

    /**
     * Devuelve una colección de etiquetas que contienen el texto pedido
     * @param texto buscado
     * @return
     */
    public ArrayList<TagEntity> getAll( String texto ){
        return tagRepository.getAll( texto );
    }

    /**
     * Devuelve una colección de id de las etiquetas que contienen el texto pedido
     * @param texto buscado
     * @return
     */
    public ArrayList<Integer> getAllId( String texto ){
        return tagRepository.getAllId( texto );
    }

}
