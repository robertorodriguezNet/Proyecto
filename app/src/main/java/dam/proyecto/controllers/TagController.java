package dam.proyecto.controllers;

import android.content.Context;

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

    private final TagRepository repository;

    public TagController(Context context) {
        this.repository = new TagRepository(context);
    }

    /**
     * Devuelve true si el tag existe
     *
     * @param tag la etigueta buscada
     * @return [true|false] si la etiqueta existe o no
     */
    public boolean exists(String tag) {
        TagEntity tagEntity = repository.findByName(tag);
        return tagEntity != null;
    }

    /**
     * Método que devuelve la colección completa de etiquetas
     * @return colección de tags
     */
    public ArrayList<TagEntity> getAll() {
        return repository.getAll();
    }

    /**
     * Devuelve el siguiente valor al más alto registrado en la tabla Tag.
     * Este id es válido para la inserción de un nuevo registro
     *
     * @return id vádlido
     */
    public int getNewId() {
        int id = repository.getMaxId();
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
        return repository.getProductosByTag(texto);
    }

    /**
     * Devuelve una colección de etiquetas que contienen el texto pedido
     * @param texto buscado
     * @return colección de etiquetas que contienen el texto buscado
     */
    public ArrayList<TagEntity> getAll(String texto) {
        return repository.getAll(texto);
    }

    /**
     * Devuelve una colección de id de las etiquetas que contienen el texto pedido
     * @param texto buscado
     * @return colección de etiquetas (id's) que contienen el texto buscado
     */
    public ArrayList<Integer> getAllId(String texto) {
        return repository.getAllId(texto);
    }

    /**
     * Devuelve el id de una etiqueta por su nombre
     * @param name el tag buscado
     * @return [int] id asociado al texot
     */
    public int getIdByName( String name ){
        return repository.getIdByName( name );
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

    /* ******************************************************************************* */
    /* ***** INSERT ****************************************************************** */
    /* ******************************************************************************* */

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<TagEntity> data) {
        repository.insertAll(data);
    }

    /**
     * Inserta un objeto
     */
    public void insert(TagEntity tag) {
        repository.insert(tag);
    }

    /**
     * Inserta un nuevo tag y devuelve el id
     * @param tag el texto de la nueva etiqueta
     * @return el id asociado a la nueva etiqueta
     */
    public int insert(String tag) {

        // Comprobamos que el objeto no exista
        if (!exists(tag)) {

            int id = getNewId();
            repository.insert( new TagEntity( id, tag) );
            return id;
        }

        return -1;
    }

}
