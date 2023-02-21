package dam.proyecto.activities.producto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dam.proyecto.R;

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
        return inflater.inflate(R.layout.fragment_producto_evolucion, container, false);
    }
}