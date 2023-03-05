package dam.proyecto.controllers;

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
     * Borra los datos de la tabla
     */
    public void clearData(){
        repository.clear();
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
     * @param idCompra es la fecha de la compra
     * @return
     */
    public String getNombreComercioByCompra( String idCompra ){
        return repositoryNombreComra.getNombreComercioByCompra( idCompra );
    }

    /**
     * Devuelve la última compra de un producto concreto
     * @param id el producto buscado
     */
    public Map<String, String> getUltimaCompraDe(String id ){

        // Obtener el producto para poder conocer las unidades de medida
        ProductoEntity producto = ProductoController.getById( id, context );

        // Obtener la última compra del producto
        CompraEntity compra = repository.getUltimaCompraByProducto( id );

        // Obtener el comercio en el que se realizó la compra
        String comercio = getNombreComercioByCompra( compra.getFecha() );

        // Ya tenemos todos los datos

        Log.d("LDLC", "Producto: " + producto.getId()
        + "\nFecha de la compra: " + compra.getFecha()
        + "\nComercio: " + comercio );

        // El precio se da por unidad de medida

        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("precio",String.format("%.02f", compra.getPrecio()));
        mapa.put("precioM", String.format("%.02f", compra.getPrecioMedido()) +
                "€/" + producto.getMedida() );
        mapa.put("fecha", Fecha.getFechaFormateada( compra.getFecha() ) );
        mapa.put("comercio", comercio);

        return mapa;
    }

    public void insert( CompraEntity compra ){
        repository.insert( compra );
    }


    /**
     * Inserta una compra a partir de los datos
     * @param producto
     * @param fecha
     * @param cantidad
     * @param pagado
     * @param precio
     * @param precioMedido
     */
    public void insert( String producto,
                        String fecha,
                        float cantidad,
                        float pagado,
                        float precio,
                        float precioMedido){
        insert( new CompraEntity( producto,
                fecha,
                cantidad,
                pagado,
                precio,
                precioMedido)
        );
    }

    /**
     * Método que devuelve un listado con comercio, precio y fecha de
     * cada compra de un producto
     * @param idProducto es el producto buscado
     * @return
     */
    public ArrayList<VistaCompra> loadVistaCompraByProducto(String idProducto ){
        // Dao
        VistaCompraDao vistaCompraDao = repository.getDb().vistaCompraDao();

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

        return (ArrayList<VistaCompra>) data;
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
}
