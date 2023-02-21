package dam.proyecto.activities.producto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dam.proyecto.R;

/**
 * Muestra una comparativa del producto entre diferentes comercios
 *
 * @author Roberto Rodríguez
 * @version 1.2023.02.14
 * @since 2023/02/14
 */
public class ProductoComparativaFragment extends Fragment {

    public ProductoComparativaFragment() {
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
        return inflater.inflate(R.layout.fragment_producto_comparativa, container, false);
    }
}