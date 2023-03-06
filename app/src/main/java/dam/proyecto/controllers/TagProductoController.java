package dam.proyecto.controllers;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dam.proyecto.database.entity.TagsProductoEntity;
import dam.proyecto.database.repositories.TagProductoRepository;
import dam.proyecto.database.repositories.TagRepository;

/**
 * Clase que realiza operaciones con las etiquetas y los productos
 *
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.06
 * @since 06/03/2023
 */
public class TagProductoController {

    private Context context;
    private TagProductoRepository repository;

    public TagProductoController(Context context) {
        this.context = context;
        repository = new TagProductoRepository(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        repository.clear();
    }

    /**
     * Obtiene una colección de productos
     *
     * @return
     */
    public ArrayList<TagsProductoEntity> getAll() {
        return (ArrayList<TagsProductoEntity>) repository.getAll();
    }

    /**
     * Devuelve el último id insertado en la tabla
     *
     * @return el id más alto registrado
     */
    public int getMaxId() {
        return repository.getMaxId();
    }

    /**
     * A partir de un producto devuelve las etiquetas
     * @param idProducto el producto buscado
     * @return
     */
    public ArrayList<String> getNombres( String idProducto ){
        return repository.getNombres( idProducto );
    }

    /**
     * Inserta etiquetas asociadas a productos
     *
     * @param producto
     * @param tag
     */
    public void insert(String producto, int tag) {

        // Comprobar si ya existe la asociación producto-tag
        if (!repository.existsAsociacion( producto, tag)){
            int id = repository.getMaxId();
            id++;
            repository.insert( new TagsProductoEntity(id,producto,tag) );
        }
    }

}
