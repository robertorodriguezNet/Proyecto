package dam.proyecto.controllers;


import static dam.proyecto.Config.CODIGO_DE_BARRAS_LENGTH;
import static dam.proyecto.Config.PRODUCTO_DENOMINACION_MIN_LENGTH;

import android.content.Context;

import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.ProductoRepository;

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

}