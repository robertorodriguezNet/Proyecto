package dam.proyecto.activities.ayuda.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.R;
import dam.proyecto.activities.ayuda.listeners.AyudaListener;

public class AyudaListaFragment extends Fragment {

    AyudaListener oyente;

    private ArrayList<TextView> enlaces;

    public AyudaListaFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ayuda_lista, container, false);

        // Completamos el listado de enlaces
        enlaces = new ArrayList<>();
        enlaces.add(view.findViewById(R.id.ayudaListaCrear));
        enlaces.add(view.findViewById(R.id.ayudaListaAbrir));
        enlaces.add(view.findViewById(R.id.ayudaListaAnadir));
        enlaces.add(view.findViewById(R.id.ayudaListaEliminar));
        enlaces.add(view.findViewById(R.id.ayudaListaEditar));
        enlaces.add(view.findViewById(R.id.ayudaListaOfertas));
        enlaces.add(view.findViewById(R.id.ayudaListaComparaPrecios));
        enlaces.add(view.findViewById(R.id.ayudaListaEvolucionPrecio));
        enlaces.add(view.findViewById(R.id.ayudaListaCompararOtros));
        enlaces.add(view.findViewById(R.id.ayudaListaCompartrir));
        enlaces.add(view.findViewById(R.id.ayudaComprasCrear));
        enlaces.add(view.findViewById(R.id.ayudaComprasModificar));
        enlaces.add(view.findViewById(R.id.ayudaComprasDuplicar));
        enlaces.add(view.findViewById(R.id.ayudaComprasBorrar));
        enlaces.add(view.findViewById(R.id.ayudaAlmacenAgregar));
        enlaces.add(view.findViewById(R.id.ayudaAlmacenEditar));
        enlaces.add(view.findViewById(R.id.ayudaAlmacenEliminar));

        // Recorrer los enlaces y agregar el oyente
        enlaces.forEach(e -> e.setOnClickListener(v -> oyente.cargarAyuda(e)));


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        oyente = (AyudaListener) context;
    }

}