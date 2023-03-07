package dam.proyecto.controllers;

import static dam.proyecto.Config.ERROR_CREAR_COMERCIO;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.NombreCompraRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class NombreCompraController {

    private Context context;
    private NombreCompraRepository repository;

    public NombreCompraController(Context context ){
        this.context = context;
        this.repository = new NombreCompraRepository( context );
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return
     */
    public ArrayList<NombreCompraEntity> getAll(){
        return (ArrayList<NombreCompraEntity>) repository.getAll();
    }


}
