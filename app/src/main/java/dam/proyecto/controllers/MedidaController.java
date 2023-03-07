package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.MedidaEntity;
import dam.proyecto.database.repositories.MedidaRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class MedidaController {

    private Context context;
    private MedidaRepository repository;

    public MedidaController(Context context ){
        this.context = context;
        this.repository = new MedidaRepository( context );
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return
     */
    public ArrayList<MedidaEntity> getAll(){
        return (ArrayList<MedidaEntity>) repository.getAll();
    }


}
