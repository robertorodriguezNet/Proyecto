package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.NombreCompraRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.08
 */

public class NombreCompraController {

    private final NombreCompraRepository REPOSITORY;

    public NombreCompraController(Context context ){
        this.REPOSITORY = new NombreCompraRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return listado de objetos
     */
    public ArrayList<NombreCompraEntity> getAll(){
        return (ArrayList<NombreCompraEntity>) REPOSITORY.getAll();
    }

    /**
     * Obtener un nombre de la compra a partir del id
     * @param id el id de la compra, con formato aammddhhmm
     * @return nombre de la compra
     */
    public NombreCompraEntity getById( String id ){
        return REPOSITORY.getById( id );
    }

    /**
     * Inserta un nuevo registro
     * @param id de la compra
     * @param nombre que se le da a la compra
     * @param comercio en el que se compra (int id)
     */
    public void insert(String id, String nombre, int comercio){
        REPOSITORY.insert( new NombreCompraEntity( id, nombre, comercio));
    }

    public void update(NombreCompraEntity nombreCompra) {
        REPOSITORY.update( nombreCompra );
    }


}
