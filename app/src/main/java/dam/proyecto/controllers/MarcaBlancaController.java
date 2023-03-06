package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.entity.MarcaBlancaEntity;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.repositories.MarcaBlancaRepository;
import dam.proyecto.database.repositories.MarcaRepository;

/**
 *
 * @author Roberto Rodríguez Jiménez
 * @since 06/03/2023
 * @version 2023.03.06
 */

public class MarcaBlancaController {

    private Context context;
    private MarcaBlancaRepository repository;

    public MarcaBlancaController(Context context ){
        this.context = context;
        this.repository = new MarcaBlancaRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        repository.clear();
    }

    /**
     * Método que devuelve la colección completa de comecios
     * @return
     */
    public ArrayList<MarcaBlancaEntity> getAll(){
        return repository.getAll();
    }

    /**
     * Devuelve el primer índice válido
     * @return
     */
    public int getIndex(){
        int id = repository.getMaxId();
        id++;
        return id;
    }

    /**
     * Inserta un objeto en tabla
     * @param marca
     * @param comercio
     */
    public void insert(  int marca, int comercio ){
        repository.insert( new MarcaBlancaEntity( getIndex(), marca, comercio ));
    }

    /**
     * Inserta un objeto en la tabla
     * @param id
     * @param marca
     * @param comercio
     */
    public void insert( int id, int marca, int comercio ){
        repository.insert( new MarcaBlancaEntity( id, marca, comercio ));
    }

    /**
     * Inserta un objeto en la tabla
     * @param objeto
     */
    public void insert( MarcaBlancaEntity objeto ){
        repository.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaBlancaEntity> data ){
        repository.insertAll( data );
    }



}
