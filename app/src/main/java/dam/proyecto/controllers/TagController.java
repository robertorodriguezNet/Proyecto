package dam.proyecto.controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.entity.TagEntity;
import dam.proyecto.database.repositories.TagRepository;

/**
 * Clase que realiza operaciones con las etiquetas
 *
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.01
 * @since 01/03/2023
 */

public class TagController {

    private final StringBuilder LOG; // Cadena de texto para mostrar el log

    private final TagRepository REPOSITORY;

    public TagController(Context context) {

        this.REPOSITORY = new TagRepository(context);
        LOG = new StringBuilder();
        LOG.append("TagController");
    }


    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }

    /**
     * Devuelve true si el tag existe
     *
     * @param tag la etigueta buscada
     * @return [true|false] si la etiqueta existe o no
     */
    public boolean exists(String tag) {
        TagEntity tagEntity = REPOSITORY.findByName(tag);
        return tagEntity != null;
    }

    /**
     * Método que devuelve la colección completa de etiquetas
     * @return colección de tags
     */
    public ArrayList<TagEntity> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Devuelve el siguiente valor al más alto registrado en la tabla Tag.
     * Este id es válido para la inserción de un nuevo registro
     *
     * @return id vádlido
     */
    public int getNewId() {
        int id = REPOSITORY.getMaxId();
        id++;
        return id;
    }

    /**
     * Devuelve una colección con los id's de los productos que en sus etiquetas
     * contienen el texto
     * @param texto texto buscado en las etiquetas
     * @return listado de prroductos (id's) que contienen la etiqueta
     */
    public ArrayList<String> getProductosByTag(String texto) {
        return REPOSITORY.getProductosByTag(texto);
    }

    /**
     * Devuelve una colección de etiquetas que contienen el texto pedido
     * @param texto buscado
     * @return colección de etiquetas que contienen el texto buscado
     */
    public ArrayList<TagEntity> getAll(String texto) {
        return REPOSITORY.getAll(texto);
    }

    /**
     * Devuelve una colección de id de las etiquetas que contienen el texto pedido
     * @param texto buscado
     * @return colección de etiquetas (id's) que contienen el texto buscado
     */
    public ArrayList<Integer> getAllId(String texto) {
        return REPOSITORY.getAllId(texto);
    }

    /**
     * Devuelve el id de una etiqueta por su nombre
     * @param name el tag buscado
     * @return [int] id asociado al texot
     */
    public int getIdByName( String name ){
        return REPOSITORY.getIdByName( name );
    }

    /**
     * Devuelve un listado con tan sólo los nombres
     * @return listado (name's) de etiquetas
     */
    public ArrayList<String> getNombres(){
        ArrayList<TagEntity> objetos = getAll();                    // Obtener todos los objetos Tag
        ArrayList<String> nombres = new ArrayList<>();   // Lista para devolver las etiquetas (name)

        for ( TagEntity objeto: objetos ) {
            nombres.add( objeto.getName() );
        }

        return nombres;
    }


    /**
     * Devuelve un listado con las etiquetas pedidas
     * @param ids listado de etiquetas pedidas
     * @return listado de los nombres asociados a los id's
     */
    public ArrayList<String> getNombres( ArrayList<Integer> ids ){

        ArrayList<String> etiquetas = new ArrayList<>();
        for(Integer id : ids) {
            etiquetas.add( REPOSITORY.getNameById( id ));
        }
        return etiquetas;
    }

    /* ******************************************************************************* */
    /* ***** INSERT ****************************************************************** */
    /* ******************************************************************************* */

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<TagEntity> data) {
        REPOSITORY.insertAll(data);
    }

    /**
     * Inserta un objeto
     */
    public void insert(TagEntity tag) {
        REPOSITORY.insert(tag);
    }

    /**
     * Inserta un objeto con id y etiqueta
     * @param id el id de la etiqueta
     * @param tag la etiqueta
     */
    public void insert(int id, String tag) {
        REPOSITORY.insert(new TagEntity(id,tag));
    }

    /**
     * Inserta un nuevo tag y devuelve el id
     *
     * @param tag el texto de la nueva etiqueta
     * @return el id asociado a la nueva etiqueta o -1 si la etiqueta ya existe
     */
    public int insert( String tag) {

        LOG.append(String.format("\ninsert(tag): %s", tag));

        // Comprobamos que el objeto no exista
        if (!exists(tag)) {
            LOG.append("\n" +  tag + " no existe, pedimos un id" );

            // Obtener el id para el tag
            int id = getNewId();

            LOG.append("\nid recibido al insertar el tag: " + id );
            REPOSITORY.insert( new TagEntity( id, tag) );

            Log.i("LDLC", LOG.toString());
            return id;

        }

        LOG.append("\n" +  tag + " existe, se devuelve -1" );

        Log.i("LDLC", LOG.toString());
        return -1;
    }

}
