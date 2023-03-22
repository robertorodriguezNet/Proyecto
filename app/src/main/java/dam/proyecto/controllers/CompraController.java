package dam.proyecto.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.relaciones.VistaCompra;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.database.repositories.VistaCompraRepository;
import dam.proyecto.utilities.Fecha;

/**
 * Clase que realiza operaciones sobre la lista de la
 * compra que está siendo editada.
 *
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.17
 * @since 26/02/2023
 */
public class CompraController {

    private final Context CONTEXT;
    private final CompraRepository REPOSITORY;                        // Repositorio de CompraEntity

    /**
     * Constructor
     *
     * @param context contexto
     */
    public CompraController(Context context) {
        this.CONTEXT = context;
        this.REPOSITORY = new CompraRepository(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        REPOSITORY.clear();
    }

    /**
     * Devuelve la colección de objetos completa
     *
     * @return la colección de objetos
     */
    public ArrayList<CompraEntity> getAll() {
        return REPOSITORY.getAll();
    }

    /**
     * Devuelve una compra a partir del id recibido
     *
     * @param id id de la compra
     * @return objeto relacionado con el id
     */
    public CompraEntity getById(String id) {
        return REPOSITORY.getById(id);
    }

    /**
     * Devuelve una colección con los id's de los comercios en los que
     * se han comprado los productos de una lista
     *
     * @param compras la colección de compras de la lista
     * @return la colección de comercios
     */
    public HashSet<Integer> getColeccionDeComerciosByLista(ArrayList<CompraEntity> compras) {

        // Colección final de comercios
        HashSet<Integer> listadoFinal = new HashSet<>();

        // Recorrer los productos y pedir el listado de comercios en los que se ha comprado
        for (CompraEntity producto : compras) {
            // Obtener la colección de comercios de un producto
            ArrayList<Integer> comercios = getComerciosByProducto(producto.getProducto());
            // Añadir los elementos
            listadoFinal.addAll(comercios);
        }

        return listadoFinal;
    }

    /**
     * Devuelve una colección con los id's de los comercios en los que
     * se han comprado los productos de una lista
     *
     * @param idCompra el id de la compra
     * @return la colección de comercios
     */
    public HashSet<Integer> getColeccionDeComerciosByLista(String idCompra) {
        return getColeccionDeComerciosByLista(
                getProductosByFecha(idCompra)
        );
    }

    /**
     * Devuelve un listado con los comercios en los que se ha comprado un producto
     *
     * @param idProducto buscado
     * @return la colección de comercios
     */
    public ArrayList<Integer> getComerciosByProducto(String idProducto) {
        return REPOSITORY.getComerciosByProducto(idProducto);
    }

    /**
     * Obtenemos un array de enteros que representan los comercios en los que hay compras
     *
     * @param productos que pertenecen a la lista abierta
     */
    public HashMap<Integer, ArrayList<VistaCompra>> getComparativaComercios(ArrayList<CompraEntity> productos) {

        // Colección de listas para cada comercio
        // El valor Integer K se refiere al id del comercio
        // El ArrayList V es la colección de productos
        HashMap<Integer, ArrayList<VistaCompra>> compras = new HashMap<>();

        // Comercios en los que se ha comprado el producto
        HashSet<Integer> comercios = getColeccionDeComerciosByLista(productos);

        // Hay que crear una lista de la compra para cada comercio
        comercios.forEach(c -> {

            // ArrayList nueva para cada comercio
            ArrayList<VistaCompra> enUnComercio = new ArrayList<>();

            // Para cada comercio
            // Recorremos la colección de productos y guardamos en un array
            // la última compra de ese producto en ese comercio
            productos.forEach(p -> {

                // Obtener la última comprara de un producto en un comercio
                VistaCompra compra = getUltimaCompraDeProductoEnComercio(p.getProducto(), c);

                // Si hay compra del producto
                if (compra != null) {
                    enUnComercio.add(compra);
                }

            });

            // Insertamos el comercio y el ArrayList en el HashMap
            compras.put(c, enUnComercio);
        });

        // Ya tenemos la colección de compras en cada comercio
        return compras;
    }

    /**
     * Obtenemos un array de enteros que representan los comercios en los que hay compras
     *
     * @param idCompra id de la lista abierta (la fecha)
     */
    public HashMap<Integer, ArrayList<VistaCompra>> getComparativaComercios(String idCompra) {
        return getComparativaComercios(REPOSITORY.getProductosByFecha(idCompra));
    }

    /**
     * Devuelve todas las compras que se han hecho de un producto
     *
     * @param idProducto buscado
     * @return listado de las compras en las que se ha comprado el producto
     */
    public ArrayList<CompraEntity> getCompraByProducto(String idProducto) {
        return REPOSITORY.getAllByProducto(idProducto);
    }

    /**
     * Devuelve el id del comercio en el que se ha hecho una compra
     *
     * @param idCompra es la fecha de la compra
     * @return id del comercio en el que se ha hecho la compra
     */
    public String getNombreComercioByCompra(String idCompra) {
        NombreCompraRepository nombreCompraRepository =
                new NombreCompraRepository(CONTEXT);
        return nombreCompraRepository.getNombreComercioByCompra(idCompra);
    }


    /**
     * Obtener una compra por una fecha dada
     *
     * @param fecha la compra
     * @return colección de compras
     */
    public ArrayList<CompraEntity> getProductosByFecha(String fecha) {
        return (ArrayList<CompraEntity>) REPOSITORY.getProductosByFecha(fecha);
    }

    /**
     * Devuelve la última compra de un producto en un comercio
     *
     * @param idProducto producto buscado
     * @param idComercio comercio buscado
     * @return la compra relacionada
     */
    public VistaCompra getUltimaCompraDeProductoEnComercio(String idProducto, int idComercio) {
        return REPOSITORY.getUltimaCompraDeProductoEnComercio(idProducto, idComercio);
    }


    /**
     * Devuelve la última compra de un producto concreto
     *
     * @param id el producto buscado
     * @return [Map|null] mapa con los datos de la última compra o null si no hay compra aún
     */
    @SuppressLint("DefaultLocale")
    public Map<String, String> getUltimaCompraDe(String id) {

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
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método que devuelve un listado con comercio, precio y fecha de
     * cada compra de un producto
     *
     * @param idProducto es el producto buscado
     * @return listado del VistaCompra
     */
    public ArrayList<VistaCompra> getVistaCompraByProducto(String idProducto) {

        // Repositorio de VistaCompra
        VistaCompraRepository vcr = new VistaCompraRepository(CONTEXT);

        // Colección completa
        ArrayList<VistaCompra> completa = (ArrayList<VistaCompra>) vcr
                .getVistaCompraByProducto(idProducto);

        // Colección completa
        ArrayList<VistaCompra> data = new ArrayList<>();

        // Lista temporal de comercios
        ArrayList<String> comercios = new ArrayList<>();

        // Colección limpia
        // Debemos eliminar los comercios repetidos
        // La lista completa está ordenada por fecha descedente, por lo que solo
        // la primera aparición es válida
        for (VistaCompra compra : completa) {
            // Buscamos el comercio en la lista dats
            if (!comercios.contains(compra.name)) {
                data.add(compra);
                comercios.add(compra.name);
            }
        }

        return data;
    }

    /**
     * Método que devuelve un listado con la denominación,  comercio, precio medio y fecha de
     * cada compra de una etiqueta concreta.
     *
     * @param idTag es la etiqueta buscada
     * @return listado del VistaCompra
     */
    public ArrayList<VistaCompra> getVistaCompraByTag(int idTag) {
        return new VistaCompraRepository(CONTEXT).getVistaCompraByTag(idTag);
    }

    /**
     * Inserta un nuevo objeto
     *
     * @param producto     id del producto (código de barras)
     * @param fecha        fecha de la compra
     * @param cantidad     número de artículos comprados
     * @param pagado       pagado por todos los artículos
     * @param precio       de cada artículo
     * @param precioMedido precio por unidad de medida
     * @param oferta       tipo de oferta (0 si no hay)
     */
    public void insert(String producto,
                       String fecha,
                       float cantidad,
                       float pagado,
                       float precio,
                       float precioMedido,
                       int oferta) {
        REPOSITORY.insert(new CompraEntity(producto,
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
     *
     * @param compra que se quiere insertar
     */
    public void insert(CompraEntity compra) {
        REPOSITORY.insert(compra);
    }

    /**
     * Constructores para las compras
     *
     * @param producto producto comprado
     * @param idCompra id de la compra
     * @param precio   precio del producto
     * @return objeto compra
     */
    public CompraEntity newCompra(ProductoEntity producto, String idCompra, float precio) {
        return new CompraEntity(producto.getId(),
                idCompra,
                1f,
                0.0f,
                precio,
                0.0f,
                0);
    }

    /**
     * Actualiza una compra
     *
     * @param compra que se quiere actualizar
     */
    public void update(CompraEntity compra) {
        REPOSITORY.update(compra);
    }

    /**
     * Actualiza la fecha de compra de los objetos CompraEntity
     * que tengan una fecha determinada.
     * Los formatos de fecha, tanto la anterior como la nueva, es aammddhhmm
     *
     * @param fechaAnterior fecha que hay que actualizar
     * @param fechaNueva    fecha nueva
     */
    public void updateFecha(String fechaAnterior, String fechaNueva) {
        REPOSITORY.updateFecha(fechaAnterior, fechaNueva);
    }

}
