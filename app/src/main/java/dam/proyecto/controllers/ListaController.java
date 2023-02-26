package dam.proyecto.controllers;

import android.content.Context;

import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.utilities.Preferencias;


/**
 * Clase que realiza operaciones sobre la lista de la
 * compra que está siendo editada.
 *
 * @author Roberto Rodríguez Jiménez
 * @since 26/02/2023
 * @version 2023.02.26
 */
public class ListaController {

    private Context context;

    private String idLista;                                                // id de la lista abierta
    private int idComercio;                                                    // id del comercio

    /**
     * Constructor
     * @param context
     */
    public ListaController(Context context) {
        this.context = context;

        // Pedir a Preferencias el nombre de la lista que está abierta (o null)
        idLista = Preferencias.getListaAbiertaId( context );

        // Obtener el id del comercio
        idComercio = new NombreCompraRepository( context ).getIdComercio( idLista );

    }

    /**
     * Devuelve true si hay una lista abierta
     * @return
     */
    public boolean isListaAbierta(){
        return idLista != null;
    }

    /**
     * Devuelve el id de la lista abierta o null si no la hay
     * @return [String|null]
     */
    public String getIdLista(){
        return idLista;
    }

    /**
     * Devuleve el nombre del comercio asociado a la lista.
     * Se lo tenemos que pedir al repositorio de Comercio
     * @return
     */
    public String getComercio(){
        return new ComercioRespository(context).getNombreComercio( idComercio );
    }

    /**
     * Devuelve el id del comercio
     * @return
     */
    public int getIdComercio(){
        return this.idComercio;
    }

}
