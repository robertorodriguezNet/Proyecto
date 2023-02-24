package dam.proyecto.activities.almacen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.adapters.AdaptadorProductos;
import dam.proyecto.activities.almacen.listeners.AlmacenListener;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.ProductoRepository;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.19
 * @since 19/02/2023
 */
public class ListaProductosFragment extends Fragment {

    // Colección de productos que se muestran en el RecyclerView
    private ArrayList<ProductoEntity> productoData;

    // RecyclerView que muestra el listado de los productos
    private RecyclerView recyclerView;

    // Botón para agregar un nuevo producto
    private FloatingActionButton fabAgregarProducto;

    // Oyente para el almancén
    AlmacenListener listener;

    /** **************************************************************************************** **/
    /** FUNCIONES ****************************************************************************** **/
    /** **************************************************************************************** **/

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

        // Botón para agregar un nuevo producto
        fabAgregarProducto = view.findViewById( R.id.fa_fab_addProductoAlmacen );
        fabAgregarProducto.setOnClickListener( v -> {
            listener.addNuevoProducto();
        });

        // Obtener los datos
        productoData = new ProductoRepository(getContext()).getAll();

        // Obtenemos el RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);

        // Comprobar que el RecyclerView sea correcto
        if (recyclerView instanceof RecyclerView) {

            // Establecemos LayoutManager para el REcycler View
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Adaptador
            AdaptadorProductos adaptadorProductos =
                    new AdaptadorProductos(productoData, getContext(), listener);
            recyclerView.setAdapter(adaptadorProductos);

//            // Implementamos los eventos del adaptados
//            adaptadorProductos.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(
//                            getContext(),
//                            "Agregar a la lista: " +
//                                    productoData.get(recyclerView.getChildAdapterPosition(view)).getDenominacion(),
//                            Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            adaptadorProductos.setOnlongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    Toast.makeText(
//                            getContext(),
//                            "Editar: " +
//                                    productoData.get(recyclerView.getChildAdapterPosition(view)).getDenominacion(),
//                            Toast.LENGTH_SHORT).show();
//                    return false;
//                }
//            });

        }

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if( context instanceof AlmacenListener){
            listener = (AlmacenListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " debes implementar el oyente." );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}