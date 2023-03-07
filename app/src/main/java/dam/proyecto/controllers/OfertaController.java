package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.repositories.MarcaRepository;
import dam.proyecto.database.repositories.OfertaRespository;

/**
 * Clase que realiza operaciones
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class OfertaController {

    private Context context;
    private OfertaRespository repository;

    public OfertaController(Context context ){
        this.context = context;
        this.repository = new OfertaRespository( context );
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return
     */
    public ArrayList<OfertaEntity> getAll(){
        return (ArrayList<OfertaEntity>) repository.getAll();
    }


}
