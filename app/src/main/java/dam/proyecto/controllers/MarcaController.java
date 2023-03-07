package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.repositories.MarcaRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class MarcaController {

    private Context context;
    private MarcaRepository repository;

    public MarcaController(Context context ){
        this.context = context;
        this.repository = new MarcaRepository( context );
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return
     */
    public ArrayList<MarcaEntity> getAll(){
        return (ArrayList<MarcaEntity>) repository.getAll();
    }


}
