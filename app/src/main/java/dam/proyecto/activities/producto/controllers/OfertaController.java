package dam.proyecto.activities.producto.controllers;

import android.util.Log;

import java.util.HashMap;

import dam.proyecto.database.entity.ProductoEntity;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.27
 * @since 27/03/2023
 */
public class OfertaController {

    private final ProductoEntity PRODUCTO;

    // Número de productos sobre los que se pueden aplicar las ofertas
    private float productosOfertables;
    //Productos que quedan fuera de la oferta
    private float resto;
    // Importe final
    private float total;

    // mapa clave,valor con el resultado de haber aplicado la oferta
    private HashMap<String, Float> mapa;

    public OfertaController(ProductoEntity producto) {
        this.PRODUCTO = producto;
    }

    /**
     * Oferta 2x1
     * Devuelve el total y el precio por medida
     *
     * @param cantidadf la cantidad que de productos
     * @param preciof   el precio original
     * @return una lista con los valores calculados
     */
    public HashMap<String, Float> get2x1(float cantidadf, float preciof) {

        // Solo puede aplicarse a cantidades par
        // Para obtener el número de productos sobre los cuales aplicar el
        // descuento, nos quedamos con la parte entera del cociente
        productosOfertables = (float) Math.floor(cantidadf / 2);
        resto = (cantidadf % 2);

        total = (preciof * productosOfertables) + (preciof * resto);

        mapa = new HashMap<>();
        mapa.put("precioMedio", (total / cantidadf) / PRODUCTO.getCantidad());
        mapa.put("total", total);

        return mapa;
    }

    /**
     * Oferta 3x2
     * Devuelve el total y el precio por medida
     *
     * @param cantidadf la cantidad que de productos
     * @param preciof   el precio original
     * @return una lista con los valores calculados
     */
    public HashMap<String, Float> get3x2(float cantidadf, float preciof) {

        Log.d("LDLC", "Oferta: 3x2, cantidad: " + cantidadf + " precio: " + preciof);

        // Solo puede aplicarse a cantidades divisibles por 3
        // Para obtener el número de productos sobre los cuales aplicar el
        // descuento, nos quedamos con la parte entera del cociente
        productosOfertables = (float) Math.floor(cantidadf / 3) * 2;
        resto = (cantidadf % 3);

        total = (preciof * productosOfertables) + (preciof * resto);

        mapa = new HashMap<>();
        mapa.put("precioMedio", (total / cantidadf) / PRODUCTO.getCantidad());
        mapa.put("total", total);

        Log.d("LDLC", "Productos ofertables: " + productosOfertables + "\n" +
                "Resto: " + resto);

        return mapa;
    }

    /**
     * Oferta segunda unidad al 50%
     * Devuelve el total y el precio por medida
     *
     * @param cantidadf la cantidad que de productos
     * @param preciof   el precio original
     * @return una lista con los valores calculados
     */
    public HashMap<String, Float> get50(float cantidadf, float preciof) {

        // Solo puede aplicarse a cantidades par
        // Para obtener el número de productos sobre los cuales aplicar el
        // descuento, nos quedamos con la parte entera del cociente
        productosOfertables = (float) Math.floor(cantidadf / 2);
        resto = (cantidadf % 2);

        total = ((preciof + ((preciof * 50) / 100)) * productosOfertables) + (preciof * resto);

        mapa = new HashMap<>();
        mapa.put("precioMedio", (total / cantidadf) / PRODUCTO.getCantidad());
        mapa.put("total", total);

        Log.d("LDLC", "Productos ofertables: " + productosOfertables + "\n" +
                "Resto: " + resto);


        return mapa;
    }

    /**
     * Oferta segunda unidad al 70%
     * Devuelve el total y el precio por medida
     *
     * @param cantidadf la cantidad que de productos
     * @param preciof   el precio original
     * @return una lista con los valores calculados
     */
    public HashMap<String, Float> get70(float cantidadf, float preciof) {

        // Solo puede aplicarse a cantidades par
        // Para obtener el número de productos sobre los cuales aplicar el
        // descuento, nos quedamos con la parte entera del cociente
        productosOfertables = (float) Math.floor(cantidadf / 2);
        resto = (cantidadf % 2);

        total = ((preciof + ((preciof * 70) / 100)) * productosOfertables) + (preciof * resto);

        mapa = new HashMap<>();
        mapa.put("precioMedio", (total / cantidadf) / PRODUCTO.getCantidad());
        mapa.put("total", total);

        Log.d("LDLC", "Productos ofertables: " + productosOfertables + "\n" +
                "Resto: " + resto);


        return mapa;
    }

}
