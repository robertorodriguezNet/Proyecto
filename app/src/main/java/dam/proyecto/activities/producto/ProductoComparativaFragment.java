package dam.proyecto.activities.producto;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import dam.proyecto.R;

/**
 * Muestra una comparativa del producto entre diferentes comercios
 *
 * @author Roberto Rodríguez
 * @version 1.2023.02.14
 * @since 2023/02/14
 */
public class ProductoComparativaFragment extends Fragment {

    private Context context;

    private String idCompra;

    // Componentes
    private TextView tvCompra;

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

        idCompra = getArguments().getString("id");

        View view = inflater.inflate(R.layout.fragment_producto_comparativa, container, false);
        context = view.getContext();

        tvCompra = (TextView) view.findViewById( R.id.fpc_tv_producto );
        tvCompra.setText( idCompra );

        return view;
    }
}