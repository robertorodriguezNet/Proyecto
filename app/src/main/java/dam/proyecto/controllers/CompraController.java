package dam.proyecto.controllers;

import android.content.Context;

import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.CompraRepository;

/**
 * Clase que realiza operaciones sobre la lista de la
 * compra que está siendo editada.
 *
 * @author Roberto Rodríguez Jiménez
 * @since 26/02/2023
 * @version 2023.03.01
 */
public class CompraController {

    private Context context;
    private CompraRepository repository;                              // Repositorio de CompraEntity

    /**
     * Constructor
     * @param context
     */
    public CompraController(Context context) {
        this.context = context;
        repository = new CompraRepository( context );
    }

    /**
     * Constructores para las compras
     * @param producto
     * @param idCompra
     * @param precio
     * @return
     */
    public CompraEntity newCompra(ProductoEntity producto, String idCompra, float precio ){
        return new CompraEntity(producto.getId(),
                idCompra,
                1f,
                0.0f,
                precio,
                0.0f);
    }

    /**
     * Devuelve una compra a partir del id recibido
     * @param id
     * @return
     */
    public CompraEntity getById( String id ){
        return repository.getCompra( id );
    }

}
