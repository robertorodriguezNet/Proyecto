package dam.proyecto.activities.producto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dam.proyecto.activities.producto.classes.Grafico;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.ProductoController;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return new Grafico(getDatos(), getContext());
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