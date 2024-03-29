package dam.proyecto.activities.producto;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.producto.adapters.OtrosAdapter;
import dam.proyecto.activities.producto.adapters.VistaCompraAdapter;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.controllers.TagController;
import dam.proyecto.controllers.TagProductoController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.relaciones.VistaCompra;

/**
 * Muestra una comparativa del producto entre diferentes comercios
 *
 * @author Roberto Rodríguez
 * @version 2023.03.01
 * @since 14/02/2023
 */
public class ProductoComparativaFragment extends Fragment {

    // Controladores
    private TagProductoController tagProductoController;

    // Datos referentes al producto
    private ProductoEntity producto;                                            // Producto comprado
    private ArrayList<VistaCompra> relacionDeCompras;   // Listado de todas las compras del producto
    private ArrayList<VistaCompra> relacionDeRelacionados;     // Listado los productos relacionados


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

        getDatos();                  // Cargar los datos necesarios para poder cargar la información

        // Componentes de la UI
        TextView tvCompra;
        TextView tituloRelacionados;
        ListView listaComparativa;              // Contenedor para el listdado de la comparativa
        ListView listaOtros;                  // Lista para mostrar otros productos relacionados

        View view = inflater.inflate(R.layout.fragment_producto_comparativa, container, false);
        Context context = view.getContext();

        tvCompra = (TextView) view.findViewById(R.id.fpc_tv_producto);
        tvCompra.setText(producto.getDenominacion());

        tituloRelacionados = (TextView) view.findViewById( R.id.fpc_tv_otros );

        // Listado de la comparativa
        listaComparativa = (ListView) view.findViewById(R.id.fpc_lv_comparativa);
        VistaCompraAdapter adapter = new VistaCompraAdapter( view.getContext(),
                R.layout.item_vista_compra,
                relacionDeCompras);
        listaComparativa.setAdapter(adapter);

        // Listado de productos relacionados
        listaOtros = (ListView) view.findViewById(R.id.fpc_lv_otros);
        OtrosAdapter adapterOtros = new OtrosAdapter(context,
                R.layout.item_lista_otros,
                relacionDeRelacionados);
        listaOtros.setAdapter(adapterOtros);

        // Al título de los productos relacionados le añadimos el tag
        addTagAlTitulo( tituloRelacionados );

        return view;
    }

    /**
     * Obtenemos los datos
     */
    private void getDatos() {

        CompraController compraController = new CompraController(getContext());

        if (getArguments() != null) {

            // A partir de la compra podemos obtener los datos del producto
            CompraEntity compra = compraController.getById(getArguments().getString("id"));
            producto = ProductoController.getById(compra.getProducto(), getContext());

            // Pedimos al controlador de las compras un listado de cada una de las compras
            // del producto pasado como argumento.
            relacionDeCompras = compraController.getVistaCompraByProducto(producto.getId());

            // Listado de la comparativa de productos relacionados
            // Pedimos el primer tag del producto
            tagProductoController = new TagProductoController(getContext());
            relacionDeRelacionados = compraController.getVistaCompraByTag(
                    tagProductoController.getTagMasImportante(producto.getId())
            );
        }

    }

    /**
     * Añadir al título de los productos relacionados el tag de ralación
     */
    private void addTagAlTitulo( TextView tituloRelacionados){

        int idTag = tagProductoController.getTagMasImportante( producto.getId() );

        TagController tc = new TagController( getContext() );

        String titulo = tituloRelacionados.getText() + " " + tc.getNombre( idTag );

        tituloRelacionados.setText( titulo );
    }

}