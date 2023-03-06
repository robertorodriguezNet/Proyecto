package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.MedidaEntity;
import dam.proyecto.database.repositories.MarcaRepository;
import dam.proyecto.database.repositories.MedidaRepository;

/**
 * Clase que realiza operaciones con las medidad
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.04
 */

public class MedidaController {

    private Context context;
    private MedidaRepository repository;

    public MedidaController(Context context ){
        this.context = context;
        this.repository = new MedidaRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        repository.clear();
    }

    /**
     * Devuelve la colección completa de medidas.
     * @return
     */
    public ArrayList<MedidaEntity> getAll(){
        return (ArrayList<MedidaEntity>) repository.getAll();
    }

    /**
     * Inserta objeto en MedidaEntity
     * @param objeto que se inserta
     */
    public void insert( MedidaEntity objeto ){
        repository.insert( objeto );
    }

    /**
     * Inserta un objeto MedidaEntity a partir del id y nombre
     * @param id
     * @param name
     */
    public void insert( String id, String name ){
        insert( new MedidaEntity( id, name ));
    }
}
