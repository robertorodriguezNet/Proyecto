package dam.proyecto.controllers;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.NombreCompraRepository;

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
    private NombreCompraRepository repositoryNombreComra;     // Repositorio para NombreCompraEntity

    /**
     * Constructor
     * @param context
     */
    public CompraController(Context context) {
        this.context = context;
        this.repository = new CompraRepository( context );
        this.repositoryNombreComra = new NombreCompraRepository( context );
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

    /**
     * Devuelve todas las compras que se han hecho de un producto
     * @param producto buscado
     * @return
     */
    public ArrayList<CompraEntity> getNombreCompraByProducto( String producto ){
        return repository.getAllByProducto( producto );
    }

    /**
     * Devuelve el id del comercio en el que se ha hecho una compra
     * @param idCompra
     * @return
     */
    public String getNombreComercioByCompra( String idCompra ){
        return repositoryNombreComra.getNombreComercioByCompra( idCompra );
    }

}
