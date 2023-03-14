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
 *
 * @author Roberto Rodríguez
 * @version 2023.03.08
 * @since 11/02/2023
 */
public class ProductoController {


    /**
     * Borra los datos de la tabla
     */
    public static void clear(Context context) {
        new ProductoRepository(context).clear();
    }

    /**
     * Crea un objeto ProductoEntity
     */
    public static ProductoEntity crearProducto(String id,
                                               String denominacion,
                                               int marca,
                                               int unidades,
                                               String medida,
                                               float cantidad) {

        return new ProductoEntity(id,
                denominacion,
                marca,
                unidades,
                medida,
                cantidad);
    }

    /**
     * Comprueba si existe un producto a partir de su id
     *
     * @param id      buscado
     * @param context contexto
     * @return true si el producto está registrado
     */
    public static boolean exists(String id, Context context) {
        ProductoEntity producto = new ProductoRepository(context).getById(id);
        return (producto != null);
    }


    /**
     * Obtener el último precio de un producto en un comercio dados
     *
     * @param idProducto producto buscado
     * @param idComercio en que el que se busca
     * @param context    contexto
     * @return precio del producto buscado
     */
    public static float getUltimoPrecio(String idProducto, int idComercio, Context context) {

        // Obtnemos todas las compras del producto
        // ordenadas por fecha descendentes
        ArrayList<CompraEntity> compras = new CompraRepository(context)
                .getAllByProducto(idProducto);


        // Obtener todas las compras del comercio,
        // ordenadas descendentes
        ArrayList<String> nombreCompras = new NombreCompraRepository(context)
                .getAllByIdComercio(idComercio);

        // Recorremos en los productos para buscar si se compró en comercio
        for (CompraEntity compra : compras) {
            if (nombreCompras.contains(compra.getFecha())) {
                // Este es el último producto comprado en el comercio buscado
                return compra.getPrecio();
            }
        }

        return 0.0f;

    }

    /**
     * Devuelve la colección completa de productos
     *
     * @return colección de productos
     */
    public static ArrayList<ProductoEntity> getAll(Context context) {
        return new ProductoRepository(context).getAll();
    }

    /**
     * Devuelve la colección completa de productos que contienen el texto
     *
     * @return colección de productos
     */
    public static ArrayList<ProductoEntity> getAll(String texto, Context context) {

        TagController tagControler = new TagController(context);

        // Pedimos al controlador de etiquetas un listado con los productos que
        // contienen el texto buscado
        ArrayList<String> idProductos = tagControler.getProductosByTag(texto);

        // Pedimos el listado completo de productos
        ArrayList<ProductoEntity> listado = ProductoController.getAll(context);

        // Eliminamos los productos que no estén en la colección de idProductos
        listado.removeIf(producto -> !idProductos.contains(producto.getId()));

        return listado;
    }

    /**
     * Devuelve el ProductoEntity correspondiente con el id
     *
     * @param id      buscado
     * @param context contexto
     * @return el producto buscado
     */
    public static ProductoEntity getById(String id, Context context) {
        return new ProductoRepository(context).getById(id);
    }


    /**
     * Devuelve el último precio registrado pero, si devilvemos el último precio, éste será
     * el que se acabe de guardar al crear la lista.
     * Se debe devolver el último, descartando el actual.
     *
     * @param idProducto el producto buscado
     * @param context    contexto
     * @return último precio
     */
    public static float getUltimoPrecio(String idProducto, Context context) {
        String idCompraActual = Preferencias.getListaAbiertaId(context);
        return new CompraRepository(context).getUltimoPrecio(idProducto, idCompraActual);
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
                                      Context context) {

        ProductoEntity producto = crearProducto(id, denominacion, marca, unidades, medida, cantidad);
        new ProductoRepository(context).insertProducto(producto);

    }

    /**
     * Inserta un producto
     *
     * @param producto que se quiere insertar
     */
    public static void insertProducto(ProductoEntity producto, Context context) {
        new ProductoRepository(context).insertProducto(producto);
    }

    /**
     * Evalúa el código de barras recibido como argumento.
     * El código de barras puede estar en blanco.
     * El producto puede estar editándose y querer cambiar el código de barras.
     *
     * @param cb el código de barras
     * @return int valor según error, 0 si no hay error
     */
    public static int validarCodigoDeBarras(String cb, boolean editando, Context context) {

        // Si se ha escrito el código de barras, hay que validarlo

//        Log.d("LDLC", "ProductoController.validarCodigoDeBarras: " + cb );


        ProductoRepository productoRepository = new ProductoRepository(context);

        // Si está vacío, devolvemos -1
        if (cb.isEmpty()) {
            return -1;
        } else {

            // No está vacío, pero no tiene los caracteres epecificados
            boolean err = false;
            for (int ean : CODIGO_DE_BARRAS_LENGTH) {
                if (err || (cb.length() == ean)) {
                    err = true;
                }
            }
            if (!err) {
                return 1;
            }

            // Si el código de barras tiene la longitud correcta, se comprueba
            // que no exista, en caso de que editando = false
            if (!editando && productoRepository.existsProducto(cb)) {
                return 0;
            }

        }

        return -1;

    }

    /**
     * Evalúa la denominación
     *
     * @param denominacion que se quiere validar
     * @return el valor del error que pudiera dar
     */
    public static int validarDenominacion(String denominacion) {

//        Log.d("LDLC", "ProductoController.validarDenominacion: " + denominacion );

        // Condiciones
        // El texto no puede estar en blanco
        if (denominacion.isEmpty()) {
            return 3;
        }
        // El texto es demasiado corto
        if (denominacion.length() < PRODUCTO_DENOMINACION_MIN_LENGTH) {
            return 4;
        }

        return -1;

    }


    /**
     * Actualiza un producto
     *
     * @param objeto producto que se actualiza
     * @param context contexto
     */
    public static void update(ProductoEntity objeto, Context context) {
        ProductoRepository repository = new ProductoRepository(context);
        repository.update(objeto);
    }

}