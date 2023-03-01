package dam.proyecto.activities.producto;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import dam.proyecto.R;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.ProductoEntity;

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

    // Datos referentes al producto
    private String idCompra;         // id de la compra que se está editando (el producto comprrado)
    private CompraEntity compra;                       // Compra realizada y la que se está editando
    private ProductoEntity producto;                                            // Producto comprado

    // Componentes de la UI
    private TextView tvCompra;

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
        getProductoEnComercios();


        View view = inflater.inflate(R.layout.fragment_producto_comparativa, container, false);
        context = view.getContext();

        tvCompra = (TextView) view.findViewById( R.id.fpc_tv_producto );
        tvCompra.setText( producto.getDenominacion() );

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

    }

    /**
     * Buscar el mismo producto en diferentes comencios.
     */
    private void getProductoEnComercios(){
        String log = "PoductoComparativaFragment.getProductoEnComercio";

        // Colección de compras del producto
        // Le pedimos a Compra un listado del las compras del producto
        ArrayList<CompraEntity> comprasDelProducto =
                                compraController.getNombreCompraByProducto( producto.getId() );

        // Ya tenemos un listado con las compras

        // Ahora debemos obtener los comercios en los que se han hecho esas compras

        Iterator<CompraEntity> it = comprasDelProducto.iterator();
        while ( it.hasNext() ){
            CompraEntity compra = it.next();
            log += "\n" + compra.getFecha() + " " +
                    compraController.getNombreComercioByCompra( compra.getFecha() );
        }

        Log.d("LDLC", log);


        /*
        Consulta
        Con esta consulta obtenemos los datos de la compra

        select c.producto, m.name
        from Compra as c, Nombrecompra as n, Comercio as m
        where producto = '8717163889169' and c.fecha = n.id and m.id = n.comercio

        8717163889169 lupa
        8717163889169 mercadona
        8717163889169 lupa
        */
    }
}