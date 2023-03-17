package dam.proyecto.activities.producto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.producto.classes.Grafico;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * Muestra la evolución del precio del producto en el comercio dado
 *
 * @author Roberto Rodríguez
 * @version 1.2023.02.14
 * @since 2023/02/14
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_producto_evolucion, container, false);

        Grafico grafico = view.findViewById( R.id.grafico );
        grafico.setDatos( getDatos() );

        return view;
    }

    /**
     * Método que obtiene los datos necesarios para mostrar los gráficos.
     * Debe mostrar todas las compras del producto que se está editando.
     */
    private ArrayList<CompraEntity> getDatos() {

        CompraController compraController = new CompraController(getContext());

        // La compra la obtenemos a partir de los argumentos
        // que recibe el fragment
        assert getArguments() != null;
        CompraEntity compra = compraController
                .getById(
                        getArguments()
                                .getString("id")
                );


        // Obtener el producto de la compra
        ProductoEntity producto = ProductoController
                .getById(
                        compra.getProducto(),
                        getContext()
                );

        // Tenemos que obtener todas las compras de un producto.
        // Sólo necesitamos el precio.
        // Da igual el comercio.
        return compraController
                .getCompraByProducto(
                        producto.getId()
                );

    }
}