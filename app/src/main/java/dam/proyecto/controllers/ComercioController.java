package dam.proyecto.controllers;

import static dam.proyecto.Config.ERROR_CREAR_COMERCIO;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.repositories.ComercioRespository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.14
 * @since 04/03/2023
 */

public class ComercioController {

    private final Context CONTEXT;
    private final ComercioRespository REPOSITORY;

    public ComercioController(Context context) {
        this.CONTEXT = context;
        this.REPOSITORY = new ComercioRespository(context);
    }

    /**
     * Añade un nuevo comercio a la lista
     *
     * @param name nombre del comercio que se añade
     */
    public void addComercio(String name) {

        // Comprobar el nombre del comercio
        int error = -1;
        if ((name.length() < 3) || (name.length() > 16)) {
            error = 0;
        }
        if ((error == -1) && (existsComercio(name))) {
            error = 1;
        }

//        Log.d( "LDLC", "Añadir comercio: " + name );

        if (error >= 0) {
            Toast.makeText(CONTEXT,
                    name + "\n" + ERROR_CREAR_COMERCIO[error],
                    Toast.LENGTH_SHORT).show();
        } else {
            insert(name);
        }
    }


    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        REPOSITORY.clear();
    }


    /**
     * Comprueba si un comercio ya está registrado a partir de su nombre
     *
     * @param name nombre del comercio buscado
     * @return true si el comercio existe
     */
    public boolean existsComercio(String name) {
        ComercioEntity comercio = REPOSITORY.findByName(name);
        return (comercio != null);
    }

    /**
     * Comprueba si el comercio id existe
     *
     * @param id el comercio buscado
     * @return true si el comercio existe
     */
    public boolean existsComercio(int id) {
        ComercioEntity comercio = REPOSITORY.findById(id);
        return (comercio != null);

    }

    /**
     * Método que devuelve la colección completa de comecios
     *
     * @return colección de objetos
     */
    public ArrayList<ComercioEntity> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Devuelve un listado completo de los nombres de los registros.
     *
     * @return la colección de nombres
     */
    public ArrayList<String> getAllNames() {
        return (ArrayList<String>) REPOSITORY.getAllNames();
    }

    /**
     * Devuelve el objeto comercio a partir de su id
     *
     * @param idComercio id del comercio
     * @return ComercioEntity relacionado con el idComercio
     */
    public ComercioEntity getById(int idComercio) {
        return REPOSITORY.findById(idComercio);
    }

    /**
     * Devuelve el objeto comercio a partir de su nombre
     *
     * @param nombre del comercio
     * @return ComercioEntity relacionado con el nombre
     */
    public ComercioEntity getByName(String nombre) {
        return REPOSITORY.findByName(nombre);
    }

    /**
     * Devuelve el id de un comercio a partir de su nombre
     *
     * @param nombre del comercio
     * @return id del comercio buscado
     */
    public int getIdByName(String nombre) {
        return getByName(nombre).getId();
    }


    /**
     * Devuelve un nuevo id válido
     *
     * @return id válido
     */
    public int getNewId() {
        int id = REPOSITORY.getMaxId();
        id++;
        return id;
    }


    /**
     * Devuelve el nombre del comercio con el id recibido
     *
     * @param id del comercio buscado
     * @return String el nombre del comercio o 1 si es nulo
     */
    public String getNombreComercio(int id) {
        try {
            return REPOSITORY.findById(id).getName();
        }catch(Exception e){
            return REPOSITORY.findById(1).getName();
        }
    }

    /**
     * Inserta un comercio a partir del nombre
     *
     * @param name nombre del comercio
     */
    public void insert(String name) {
        // Comprobar si el comercio existe
        if (!existsComercio(name)) {
            insert(new ComercioEntity(getNewId(), name));
        }
    }

    /**
     * Inserta un comercio a partir del nombre y del id
     *
     * @param name nombre del comercio
     */
    public void insert(int id, String name) {
        // Comprobar si el comercio existe
        if (!existsComercio(name)) {
            insert(new ComercioEntity(id, name));
        }
    }

    /**
     * Inserta un comercio a partir de un objeto comercio
     *
     * @param comercio el comercio que se quiere insertar
     */
    public void insert(ComercioEntity comercio) {
        REPOSITORY.insert(comercio);
    }


    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<ComercioEntity> data) {
        REPOSITORY.insertAll(data);
    }
}
