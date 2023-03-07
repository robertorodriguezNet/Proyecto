package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.MarcaBlancaEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.MarcaBlancaRepository;
import dam.proyecto.database.repositories.NombreCompraRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class MarcaBlancaController {

    private Context context;
    private MarcaBlancaRepository repository;

    public MarcaBlancaController(Context context ){
        this.context = context;
        this.repository = new MarcaBlancaRepository( context );
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return
     */
    public ArrayList<MarcaBlancaEntity> getAll(){
        return (ArrayList<MarcaBlancaEntity>) repository.getAll();
    }


}
