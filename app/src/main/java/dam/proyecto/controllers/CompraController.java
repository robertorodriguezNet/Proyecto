package dam.proyecto.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dam.proyecto.database.dao.VistaCompraDao;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.relaciones.VistaCompra;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.utilities.Fecha;

/**
 * Clase que realiza operaciones sobre la lista de la
 * compra que está siendo editada.
 *
 * @author Roberto Rodríguez Jiménez
 * @since 26/02/2023
 * @version 2023.03.04
 */
public class CompraController {

    private final Context CONTEXT;
    private final CompraRepository REPOSITORY;                        // Repositorio de CompraEntity

    /**
     * Constructor
     * @param context contexto
     */
    public CompraController(Context context) {
        this.CONTEXT = context;
        this.REPOSITORY = new CompraRepository( context );
    }


    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }



    /**
     * Devuelve la colección de objetos completa
     * @return la colección de objetos
     */
    public ArrayList<CompraEntity> getAll(){
        return REPOSITORY.getAll();
    }

    /**
     * Devuelve una compra a partir del id recibido
     * @param id id de la compra
     * @return objeto relacionado con el id
     */
    public CompraEntity getById( String id ){
        return REPOSITORY.getCompra( id );
    }

    /**
     * Devuelve todas las compras que se han hecho de un producto
     * @param producto buscado
     * @return listado de las compras en las que se ha comprado el producto
     */
    public ArrayList<CompraEntity> getNombreCompraByProducto( String producto ){
        return REPOSITORY.getAllByProducto( producto );
    }

    /**
     * Devuelve el id del comercio en el que se ha hecho una compra
     * @param idCompra es la fecha de la compra
     * @return id del comercio en el que se ha hecho la compra
     */
    public String getNombreComercioByCompra( String idCompra ){
        NombreCompraRepository nombreCompraRepository =
                new NombreCompraRepository(CONTEXT);
        return nombreCompraRepository.getNombreComercioByCompra( idCompra );
    }

    // Obtener la compra por una compra dada
    public ArrayList<CompraEntity> getProductosByFecha(String fecha ){
        return (ArrayList<CompraEntity>) REPOSITORY.getProductosByFecha( fecha );
    }

    /**
     * Devuelve la última compra de un producto concreto
     * @param id el producto buscado
     * @return [Map|null] mapa con los datos de la última compra o null si no hay compra aún
     */
    @SuppressLint("DefaultLocale")
    public Map<String, String> getUltimaCompraDe(String id ){

        try {
            // Obtener el producto para poder conocer las unidades de medida
            ProductoEntity producto = ProductoController.getById(id, CONTEXT);

            // Obtener la última compra del producto
            CompraEntity compra = REPOSITORY.getUltimaCompraByProducto(id);

            // Obtener el comercio en el que se realizó la compra
            String comercio = getNombreComercioByCompra(compra.getFecha());

            // Ya tenemos todos los datos

            Log.d("LDLC", "Producto: " + producto.getId()
                    + "\nFecha de la compra: " + compra.getFecha()
                    + "\nComercio: " + comercio);

            // El precio se da por unidad de medida

            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("precio", String.format("%.02f", compra.getPrecio()));
            mapa.put("precioM", String.format("%.02f", compra.getPrecioMedido()) +
                    "€/" + producto.getMedida());
            mapa.put("fecha", Fecha.getFechaFormateada(compra.getFecha()));
            mapa.put("comercio", comercio);

            return mapa;
        }catch ( Exception e ){
            return null;
        }
    }

    /**
     * Inserta un nuevo objeto
     * @param producto id del producto (código de barras)
     * @param fecha fecha de la compra
     * @param cantidad número de artículos comprados
     * @param pagado pagado por todos los artículos
     * @param precio de cada artículo
     * @param precioMedido precio por unidad de medida
     * @param oferta tipo de oferta (0 si no hay)
     */
    public void insert( String producto,
                        String fecha,
                        float cantidad,
                        float pagado,
                        float precio,
                        float precioMedido,
                        int oferta){
        REPOSITORY.insert( new CompraEntity( producto,
                fecha,
                cantidad,
                pagado,
                precio,
                precioMedido,
                oferta)
        );
    }

    /**
     * Inserta la compra recibida
     * @param compra que se quiere insertar
     */
    public void insert( CompraEntity compra ){
        REPOSITORY.insert( compra );
    }

    /**
     * Método que devuelve un listado con comercio, precio y fecha de
     * cada compra de un producto
     * @param idProducto es el producto buscado
     * @return listado del VistaCompra
     */
    public ArrayList<VistaCompra> loadVistaCompraByProducto(String idProducto ){
        // Dao
        VistaCompraDao vistaCompraDao = REPOSITORY.getDb().vistaCompraDao();

        // Colección completa
        ArrayList<VistaCompra> completa = (ArrayList<VistaCompra>) vistaCompraDao
                            .loadVistaCompraByProducto( idProducto );

        // Colección completa
        ArrayList<VistaCompra> data = new ArrayList<>();

        // Lista temporal de comercios
        ArrayList<String> comercios = new ArrayList<>();

        // Colección limpia
        // Debemos eliminar los comercios repetidos
        // La lista completa está ordenada por fecha descedente, por lo que solo
        // la primera aparición es válida
        Iterator<VistaCompra> it = completa.iterator();
        while( it.hasNext() ){
            VistaCompra compra = it.next();

            // Buscamos el comercio en la lista dats
            if( !comercios.contains( compra.name )){
                data.add( compra );
                comercios.add( compra.name );
            }
        }

        return data;
    }




    /**
     * Constructores para las compras
     * @param producto producto comprado
     * @param idCompra id de la compra
     * @param precio precio del producto
     * @return objeto compra
     */
    public CompraEntity newCompra(ProductoEntity producto, String idCompra, float precio ){
        return new CompraEntity(producto.getId(),
                idCompra,
                1f,
                0.0f,
                precio,
                0.0f,
                0);
    }

}
