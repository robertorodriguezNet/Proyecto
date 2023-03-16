package dam.proyecto.activities.producto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.controllers.TagProductoController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * Muestra la evolución del precio del producto en el comercio seleccionado
 * y en todos los comercios.
 *
 * @author Roberto Rodríguez
 * @version 2023.03.16
 * @since 14/02/2023
 */
public class ProductoEvolucionFragment extends Fragment {


    public ProductoEvolucionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDatos();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_producto_evolucion, container, false);


        return view;
    }


    /**
     * Método que obtiene los datos necesarios para mostrar los gráficos
     */
    private void getDatos() {

        CompraController compraController = new CompraController(getContext());
        TagProductoController tagProductoController = new TagProductoController(getContext());

        // La compra la obtenemos a partir de los argumentos
        // que recibe el fragment
        CompraEntity compra = compraController
                .getById(
                        getArguments()
                                .getString("id")
                );


        // Obtener el producto
        ProductoEntity producto = ProductoController
                .getById(
                        compra.getProducto(),
                        getContext()
                );

        // Obtener la etiqueta del producto
        int idTag = tagProductoController.getTagMasImportante(producto.getId());

        // Tenemos que obtener todas las compras de un producto.
        // Sólo necesitamos el precio.
        // Da igual el comercio.
        ArrayList<CompraEntity> todasLasComprasDelProducto = compraController
                .getCompraByProducto(
                        producto.getId()
                );

        StringBuilder datos = new StringBuilder();
        todasLasComprasDelProducto.forEach(c -> datos
                .append(c.getPrecio())
                .append("\n")
        );

        Log.d("LDLC", "Precios de " + producto.getDenominacion());
        Log.d("LDLC", "Tag principal " + idTag );
        Log.d("LDLC", datos.toString());

    }
}