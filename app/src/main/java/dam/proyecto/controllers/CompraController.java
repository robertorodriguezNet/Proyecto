package dam.proyecto.controllers;

import android.content.Context;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.CompraRepository;

import dam.proyecto.utilities.Preferencias;

/**
 * @author Roberto Rodríguez
 * @version 1.2023.01.23
 * @since 2023/02/13
 */
public class CompraController {

    /**
     * Añade un producto a la lista de la compra activa
     * @param producto
     */
    public static void addProductoALaCompra( ProductoEntity producto, Context context ){

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
     * Actualiza el comercio de la compra abierta
     * @param comercio
     */
//    public static void actualizarComercio( ComercioEntity comercio ){
//
//        // Tenemos el comercio.
//        // Obtenemos la compra que está abierta
//        String idCompra = Preferencias.getListaAbiertaId();
//        NombreCompraEntity compra = NombreCompraRepository.getNombreDeLaCompra( idCompra );
//
//        // Ya tenemos la compra, seteamos el comercio
//        compra.setComercio( comercio.getId() );
//
//        // Actualizamos el objeto NombreCompra en la base de datos
//        NombreCompraRepository.updateNombreDeLaCompra( compra );
//
//        // Setamos el nombre del comercio en las preferencias
//        Preferencias.setListaAbiertaComercio( comercio.getId() );
//    }

}
