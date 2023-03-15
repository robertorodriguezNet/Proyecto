package dam.proyecto.activities.producto;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import dam.proyecto.R;
import dam.proyecto.activities.producto.adapters.OtrosAdapter;
import dam.proyecto.activities.producto.adapters.VistaCompraAdapter;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.controllers.TagProductoController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.relaciones.VistaCompra;

/**
 * Muestra una comparativa del producto entre diferentes comercios
 *
 * @author Roberto Rodríguez
 * @since 14/02/2023
 * @version 2023.03.01
 */
public class ProductoComparativaFragment extends Fragment {

    private Context context;

    // Controladores
    private CompraController compraController;                           // Controlador de la compra
    private TagProductoController tagProductoController;

    // Datos referentes al producto
    private String idCompra;         // id de la compra que se está editando (el producto comprrado)
    private CompraEntity compra;                       // Compra realizada y la que se está editando
    private ProductoEntity producto;                                            // Producto comprado
    private ArrayList<VistaCompra> relacionDeCompras;   // Listado de todas las compras del producto

    // Componentes de la UI
    private TextView tvCompra;
    private ListView listaComparativa;              // Contenedor para el listdado de la comparativa
    private ListView listaOtros;                  // Lista para mostrar otros productos relacionados

    // -- FUNCIONES --------------------------------------------------------------------------------

    public ProductoComparativaFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDatos();                                                   // Cargar los datos necesarios

        View view = inflater.inflate(R.layout.fragment_producto_comparativa, container, false);
        context = view.getContext();

        tvCompra = (TextView) view.findViewById( R.id.fpc_tv_producto );
        tvCompra.setText( producto.getDenominacion() );

        // Listado de la comparativa
        listaComparativa = (ListView) view.findViewById( R.id.fpc_lv_comparativa );
        VistaCompraAdapter adapter = new VistaCompraAdapter( context,
                R.layout.item_vista_compra,
                relacionDeCompras);
        listaComparativa.setAdapter( adapter );

        // Listado de la comparativa de productos relacionados
        listaOtros = view.findViewById( R.id.fpc_lv_otros );
        tagProductoController = new TagProductoController( getContext() );
        // Necesitamos un listado que contenga: denominación, precioMedida, comercio y fecha
        // 1.- Listado de productos que contengan el primer tag del elemento comparado
        int tagMasImportante = tagProductoController.getTagMasImportante( producto.getId() );

        // 2.- Ahora necesitamos los productos cuyo primer tag sea el mismo
        ArrayList<String> productosRelacionados =
                tagProductoController.getProcutosByTag( tagMasImportante );

        // Ya tenemos el listado de productos
        OtrosAdapter adapterOtros = new OtrosAdapter( context,
                R.layout.item_lista_otros,
                productosRelacionados);
        listaOtros.setAdapter( adapterOtros );

        return view;
    }

    /**
     * Obtenemos los datos
     */
    private void getDatos(){

        compraController = new CompraController( getContext() );
        idCompra = getArguments().getString("id");

        // A partir de la compra podemos obtener los datos del producto
        compra = compraController.getById( idCompra );
        producto = ProductoController.getById( compra.getProducto(), context );

        // Listado de todas las compras de un producto
        // Este listado es el que se muestra en el ListView
        relacionDeCompras = getProductoEnComercios( producto.getId() );

    }

    /**
     * Buscar el mismo producto en diferentes comencios.
     * @param idProducto el producto buscado
     * @return la relación de compras del producto
     */
    private ArrayList<VistaCompra> getProductoEnComercios( String idProducto ){
        String log = "PoductoComparativaFragment.getProductoEnComercio";

        // Para realizar la consulta tan solo necesitamos el id del producto
        // Vamos a buscar el producto en las diferentes compras y, a partir
        // de esa compra, obtener el comercio

        // Le pedimos al controlador de compra que nos devuelva el listado
        ArrayList<VistaCompra> vistas = compraController.loadVistaCompraByProducto( idProducto );
        Iterator<VistaCompra> it = vistas.iterator();
        while( it.hasNext() ){
            VistaCompra vista = it.next();
            log += "\n" + vista.name + " " + vista.precio + " " + vista.fecha;
        }

        Log.d("LDLC", log);


        /*
        Consulta
        Con esta consulta obtenemos los datos de la compra

        SELECT m.name, c.precio, c.fecha
        FROM Compra as c, Nombrecompra as n, Comercio as m, Productos as p
        WHERE c.producto = '8717163889169'
			AND c.fecha = n.id
			AND m.id = n.comercio
			AND p.id = c.producto
		ORDER BY c.fecha DESC

        name    	precio          	fecha
        mercadona	1.52999997138977	2302101928
        lupa     	2.28999996185303	2301171451
        lupa    	1.52999997138977	2211050000

        */

        return vistas;
    }
}