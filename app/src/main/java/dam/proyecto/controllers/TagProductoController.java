package dam.proyecto.controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dam.proyecto.database.entity.TagsProductoEntity;
import dam.proyecto.database.repositories.TagProductoRepository;

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
     * @param idProducto el producto buscado en formato código de barras
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
     * Devuelve los tags de un producto
     * @param idProducto producto
     * @return lista de id's asociados al producto
     */
    public ArrayList<Integer> getTagByProducto(String idProducto){
        return (ArrayList<Integer>) REPOSITORY.getTagByProducto( idProducto );
    }

    /**
     * Devuelve el primer tag del producto
     * @param idProducto buscado
     * @return el id del tag
     */
    public int getTagMasImportante(String idProducto ){
        return getTagByProducto( idProducto ).get(0);
    }

    /**
     * Devuelve true si ya hay un producto asociado a la etiqueta
     * @param producto producto buscado
     * @param tag etiqueta asociada
     * @return true si hay asociación
     */
    public boolean existsAsociacion( String producto, int tag){
        TagsProductoEntity asociacion = REPOSITORY.getAsociacion( producto, tag );
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

    /**
     * Devuelve una colección de productos relacionados con el tag
     * @param tag buscado
     * @return colección de id's de productos
     */
    public ArrayList<String> getProcutosByTag( int tag ){
        return REPOSITORY.getProcutosByTag( tag );
    }
}
