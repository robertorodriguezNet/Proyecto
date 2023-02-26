package dam.proyecto.controllers;


import static dam.proyecto.Config.CODIGO_DE_BARRAS_LENGTH;
import static dam.proyecto.Config.PRODUCTO_DENOMINACION_MIN_LENGTH;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.database.repositories.ProductoRepository;
import dam.proyecto.utilities.Preferencias;

/**
 * Controlador para Producto
 * @since 2023/02/11
 * @author Roberto Rodríguez
 * @version 2023.02.11
 */
public class ProductoController {


    /**
     * Crea un objeto ProductoEntity
     */
    public static ProductoEntity crearProducto (String id,
                                                String denominacion,
                                                int marca,
                                                int unidades,
                                                String medida,
                                                float cantidad) {

        return  new ProductoEntity( id,
                denominacion,
                marca,
                unidades,
                medida,
                cantidad);
    }

    /**
     * Método que inserta un producto en la base de datos
     */
    public static void insertProducto(String id,
                                      String denominacion,
                                      int marca,
                                      int unidades,
                                      String medida,
                                      float cantidad,
                                      Context context){

        ProductoEntity producto = crearProducto(id,denominacion,marca,unidades,medida,cantidad);
        new ProductoRepository( context ).insertProducto( producto );

    }

    /**
     * Inserta un producto
     * @param producto
     */
    public static void insertProducto(ProductoEntity producto, Context context ){
        new ProductoRepository( context ).insertProducto( producto );
    }

    /**
     * Evalúa el código de barras recibido como argumento.
     * El código de barras puede estar en blanco.
     * @param cb el código de barras
     * @return int valor según error, 0 si no hay error
     */
    public static int validarCodigoDeBarras( String cb, Context context ){

        // Si se ha escrito el código de barras, hay que validarlo

        ProductoRepository productoRepository = new ProductoRepository( context );

        // Si está vacío, devolvemos -1
        if( cb.isEmpty() ){
            return -1;
        } else {

            // No está vacío, pero no tiene 13 caracteres
            if( cb.length() < CODIGO_DE_BARRAS_LENGTH ){
                return 1;
            }

            // Si el código de barras tiene la longitud correcta
            if( productoRepository.existsProducto( cb ) ){
                return 0;
            }

        }

        return -1;

    }

    /**
     * Evalúa la denominación
     * @param denominacion
     * @return
     */
    public static int validarDenominacion( String denominacion ){

        // Condiciones
        // El texto no puede estar en blanco
        if( denominacion.isEmpty() ){
            return 3;
        }
        // El texto es demasiado corto
        if ( denominacion.length() < PRODUCTO_DENOMINACION_MIN_LENGTH ){
            return 4;
        }

        return -1;

    }

    /**
     * Obtener el último precio de un producto en un comercio dados
     * @param idProducto
     * @param idComercio
     * @param context
     * @return
     */
    public static float getUltimoPrecio( String idProducto, int idComercio, Context context){

        // Obtnemos todas las compras del producto
        // ordenadas por fecha descendentes
        ArrayList<CompraEntity> compras =
                (ArrayList<CompraEntity>) new CompraRepository( context)
                        .getAllByProducto( idProducto );


        // Obtener todas las compras del comercio,
        // ordenadas descendentes
        ArrayList<String> nombreCompras =
                (ArrayList<String>) new NombreCompraRepository( context )
                        .getAllByIdComercio( idComercio );


        // Recorremos en los productos para buscar si se compró en comercio
        for( CompraEntity compra : compras ){

            if( nombreCompras.contains( compra.getFecha() )){

                // Este es el último producto comprado en el comercio buscado
                return compra.getPrecio();

            }

        }

        return 0.0f;

    }

    /**
     * Devuelve el último precio registrado pero, si devilvemos el último precio, éste será
     * el que se acabe de guardar al crear la lista.
     * Se debe devolver el último, descartando el actual.
     * @param idProducto el producto buscado
     * @param context
     * @return
     */
    public static float getUltimoPrecio( String idProducto, Context context ){
        String idCompraActual = Preferencias.getListaAbiertaId( context );
        return new CompraRepository( context ).getUltimoPrecio( idProducto, idCompraActual );


    }

}