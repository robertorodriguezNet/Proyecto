package dam.proyecto.controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dam.proyecto.database.entity.TagsProductoEntity;
import dam.proyecto.database.repositories.TagProductoRepository;
import dam.proyecto.database.repositories.TagRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class TagProductoController {

    private final TagProductoRepository REPOSITORY;
    private final Context CONTEXT;

    public TagProductoController(Context context ){
        this.CONTEXT = context;
        this.REPOSITORY = new TagProductoRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }

    /**
     * Devuelve el primer id válido para insertar en la tabla
     * @return id válido
     */
    private int getNewId(){
        int id = REPOSITORY.getMaxId();
        id++;
        return id;
    }


    /**
     * Devuelve un listado de los tags de un producto con tan sólo los nombres
     * @param idProducto el producto buscado
     * @return listado del los tags
     */
    public ArrayList<String> getNombres( String idProducto ){

        try {
            // Repositorio de tags
            TagController tagController = new TagController( CONTEXT );

            // Obtener los id de los tags que corresponden al producto
            ArrayList<Integer> idTags = REPOSITORY
                    .getTagByProducto(idProducto);

            // Pedimos los nombres y los devolvemos
            return tagController.getNombres(idTags);

        }catch ( Exception e){
            Log.e("LDLC", "TagProductoRepository.getNombres() id: " + idProducto
                    + "\n" + e.getMessage());
            return new ArrayList<>();
        }

    }

    /**
     * Devuelve true si ya hay un producto asociado a la etiqueta
     * @param producto
     * @param tag
     * @return
     */
    public boolean existsAsociacion( String producto, int tag){
        TagsProductoEntity asociacion = null;
        asociacion = REPOSITORY.getAsociacion( producto, tag );
        return ( asociacion != null );
    }

    /**
     * Asocia un producto con una etiqueta
     * @param producto producto
     * @param tag etiqueta
     */
    public void insert( String producto, int tag){
        if( !existsAsociacion( producto, tag ) ) {
            REPOSITORY.insert(new TagsProductoEntity( getNewId(), producto, tag));
        }
    }

    /**
     * Asocia un producto con una etiqueta
     * @param id del registro
     * @param producto asociado
     * @param tag asociado
     */
    public void insert( int id, String producto, int tag){
        if( !existsAsociacion( producto, tag ) ) {
            REPOSITORY.insert(new TagsProductoEntity( id, producto, tag));
        }
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return el listado completo
     */
    public ArrayList<TagsProductoEntity> getAll(){
        return REPOSITORY.getAll();
    }


}
