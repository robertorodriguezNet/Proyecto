package dam.proyecto.controllers;

import android.content.Context;

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

    private Context context;
    private TagProductoRepository repository;

    public TagProductoController(Context context ){
        this.context = context;
        this.repository = new TagProductoRepository( context );
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return
     */
    public ArrayList<TagsProductoEntity> getAll(){
        return (ArrayList<TagsProductoEntity>) repository.getAll();
    }


}
