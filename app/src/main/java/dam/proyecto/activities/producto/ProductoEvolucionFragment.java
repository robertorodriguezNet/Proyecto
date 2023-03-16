package dam.proyecto.activities.producto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dam.proyecto.R;

/**
 * Muestra la evolución del precio del producto y de su etiqueta principal.
 *
 *  Hay que obtener todas las compras de un producto y todas las compras de una etiqueta.
 *
 * @author Roberto Rodríguez
 * @since 14/02/2023
 * @version 2023.03.16
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
        return view;
    }


    private void getDatos(){

    }
}