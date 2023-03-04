package dam.proyecto.activities.almacen;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.adapters.AdaptadorProductos;
import dam.proyecto.activities.almacen.listeners.AlmacenListener;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.ProductoRepository;
import dam.proyecto.database.repositories.TagRepository;

/**
 * Carga la lista de productos almacenados en la base de datos.
 *
 * @author Roberto Rodríguez Jiménez
 * @since 19/02/2023
 * @version 2023.03.04
 */
public class ListaProductosFragment extends Fragment {

    // Colección de productos que se muestran en el RecyclerView
    private ArrayList<ProductoEntity> productoData;

    // Campos para la búsqueda
    private AutoCompleteTextView inpTexto;
    private ImageButton btnSearch;
    private ArrayList<String> etiquetaList;                                  // Listado de etiquetas

    // RecyclerView que muestra el listado de los productos
    private RecyclerView recyclerView;
    // Adaptador
    private AdaptadorProductos adaptadorProductos;

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

        // Campos de búsqueda
        inpTexto = (AutoCompleteTextView ) view.findViewById( R.id.flp_inp_search );
        btnSearch = view.findViewById( R.id.flp_btn_search );
        btnSearch.setOnClickListener(v -> {
            search();
        });
        etiquetaList = new TagRepository( getContext() ).getNombres();

        // Establecemos el adaptador para las etiquetas
        ArrayAdapter<String> adapterEtiquetas = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                etiquetaList
        );
        inpTexto.setAdapter(adapterEtiquetas);

        // Botón para agregar un nuevo producto
        fabAgregarProducto = view.findViewById( R.id.fa_fab_addProductoAlmacen );
        fabAgregarProducto.setOnClickListener( v -> {
            listener.addNuevoProducto();
        });

        // Obtener los datos
        // Pedir al controlador de Producto el listado completo de productos
        // De inicio, la lista va a estar vacía, no se mostrará ningún producto hasta
        // que no se haya indicado en el campo de búsqueda.
//        productoData = ProductoController.getAll( getContext() );
        productoData = new ArrayList<>();

        // Obtenemos el RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);

        // Comprobar que el RecyclerView sea correcto
        if (recyclerView instanceof RecyclerView) {

            // Establecemos LayoutManager para el REcycler View
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            // Adaptador
            adaptadorProductos =
                    new AdaptadorProductos(productoData, getContext(), listener);
            recyclerView.setAdapter(adaptadorProductos);

        }

        return view;
    }

    /**
     * Busca productos a traves de un texto dado
     */
    private void search() {

        // Capturamos el texto que hay que buscar
        String text = inpTexto
                        .getText()
                        .toString()
                        .trim()
                        .toLowerCase();

        if( text.length() >= 3) {
            // Le pedimos al controlador que nos devuelva la lista de productos

            // Boramos la colección de productos
            productoData.clear();

            ArrayList<ProductoEntity> productosBuscados =
                                        ProductoController.getAll(text, getContext());

            for ( ProductoEntity producto: productosBuscados ) {
                productoData.add( producto );
            }

            // Notificamos el cambio al adaptador
            adaptadorProductos.notifyDataSetChanged();

        } else {
            Toast.makeText(getContext(), "Mínimo 3 caracteres", Toast.LENGTH_SHORT).show();
        }
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