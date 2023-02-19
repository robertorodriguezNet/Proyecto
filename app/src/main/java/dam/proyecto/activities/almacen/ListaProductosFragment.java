package dam.proyecto.activities.almacen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.adapters.AdaptadorProductos;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.ProductoRepository;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.19
 * @since 19/02/2023
 */
public class ListaProductosFragment extends Fragment {

    private ArrayList<ProductoEntity> productoData;

    RecyclerView recyclerView;

    public ListaProductosFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Obtener la vista
        View view = inflater.inflate(R.layout.fragment_lista_productos, container, false);

        // Obtener los datos
        productoData = new ProductoRepository(getContext()).getAll();

        // Obtenemos el RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);

        // Comprobar que el RecyclerView sea correcto
        if (recyclerView instanceof RecyclerView) {

            // Establecemos LayoutManager para el REcycler View
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Adaptador
            AdaptadorProductos adaptadorProductos = new AdaptadorProductos(productoData, getContext());
            recyclerView.setAdapter(adaptadorProductos);

            // Implementamos los eventos del adaptados
            adaptadorProductos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(
                            getContext(),
                            "Agregar a la lista: " +
                                    productoData.get(recyclerView.getChildAdapterPosition(view)).getDenominacion(),
                            Toast.LENGTH_SHORT).show();
                }
            });

            adaptadorProductos.setOnlongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(
                            getContext(),
                            "Editar: " +
                                    productoData.get(recyclerView.getChildAdapterPosition(view)).getDenominacion(),
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

        }

        return view;
    }
}