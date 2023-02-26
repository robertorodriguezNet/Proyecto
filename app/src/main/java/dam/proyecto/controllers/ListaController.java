package dam.proyecto.controllers;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.CompraRepository;
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

    /**
     * Agrega un producto a la lista que está siendo editada
     * @param producto que se quiere agregar
     * @param precio el precio que se ha seleccionado
     */
    public void addProducto(ProductoEntity producto, float precio ){

        CompraController compraController = new CompraController( context );
        CompraRepository compraRepository = new CompraRepository( context );

        // Hay que crear un nuevo registro CompraEntity en la tabla Compra
        // Campos: idDelProducto, idDeLaCompra, precio
        // En lugar de crear el objeto CompraEntity pidiéndoselo a CompraEntity,
        // se lo pedimos al controlador de la compra, que puede sobrecargar el constructor
        CompraEntity compra = compraController.newCompra( producto, idLista, precio);

        compraRepository.insert( compra );

    }

    /**
     * Devuelve los productos asociados a la fecha de la lista abierta
     * @return
     */
    public ArrayList<CompraEntity> getListaProductos(){
        return new CompraRepository( context ).getProductosByFecha( idLista );
    }

}
