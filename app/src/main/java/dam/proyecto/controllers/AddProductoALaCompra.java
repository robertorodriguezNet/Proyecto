package dam.proyecto.controllers;

import android.content.Context;

import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.ProductoRepository;
import dam.proyecto.utilities.Preferencias;

/**
 * Añade un producto a una compra.
 * Necesitamos el id del producto y el id de la compra.
 *
 * @author Roberto Rodríguez
 * @since 2023/02/13
 * @version 2023.02.23
 */
public class AddProductoALaCompra {

    private static CompraRepository compraRepository;

    /**
     *
     * @param producto producto que se quiere agregar
     * @param idCompra compra a la que se quiere agregar
     * @param context contexto
     */
    public static void add(ProductoEntity producto, String idCompra, Context context ){

        CompraRepository compraRepository = new CompraRepository( context );

        // Obtener la compra
        String fecha = Preferencias.getListaAbiertaId( context );

        // Crear el objeto compra
        // No hay que pasarle el id, se crea en el constructor
        // La cantidad por defecto es 1
        // Los datos de los precios son 0 porque pueden variar, aunque pueden mostrarse los
        // últimos registrados
        CompraEntity compra = new CompraEntity(
                producto.getId(),
                fecha,
                1f,
                0.0f,
                0.0f,
                0.0f
        );

        // Pedir al repositorio que guarde el producto e la compra
        compraRepository.insert( compra );
    }

    /**
     * @param idProducto id del producto que se quiere agregar
     * @param idCompra compra a la que se quiere agregar
     * @param context contexto
     */
    public static void add( String idProducto, String idCompra, Context context ){
        // Obtenemos el producto asociado al id
        add( new ProductoRepository( context ).getById( idProducto ), idCompra, context);
    }

}
