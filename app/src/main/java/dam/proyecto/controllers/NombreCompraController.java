package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.NombreCompraRepository;

/**
 * @author  Roberto Rodríguez Jiménez
 * @since 05/03/2023
 * @version 2023.03.05
 */
public class NombreCompraController {

    private NombreCompraRepository repository;
    private Context context;

    public NombreCompraController( Context context ) {
        this.context = context;
        this.repository = new NombreCompraRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clearData(){
        repository.clear();
    }

    /**
     * Devuelve la colección completa de NombreCompraEntity
     * @return
     */
    public ArrayList<NombreCompraEntity> getAll(){
        return repository.getAll();
    }

    /**
     * Inserta un objeto en la base de datos
     * @param objeto que se debe insertar
     */
    public void insert( NombreCompraEntity objeto ){
        repository.insert( objeto );
    }

    /**
     * Inserta un objeto NombreCompraEntity a partir de los datos
     * @param id
     * @param nombre
     * @param comercio
     */
    public void insert(String id, String nombre, int comercio){
        insert( new NombreCompraEntity(
           id,
           nombre,
           comercio
        ));
    }

    /**
     * Inserta una colección de objetos
     * @param data colección de objetos
     */
    public void insertAll( List<NombreCompraEntity> data ){
        repository.insertAll( data );
    }

}
