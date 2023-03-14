package dam.proyecto.activities.almacen;

import static dam.proyecto.Config.CARACTERES_MINIMOS_PARA_BUSCAR;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
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
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.adapters.AdaptadorProductos;
import dam.proyecto.activities.almacen.clases.CaptureActivityPortrait;
import dam.proyecto.activities.almacen.listeners.AlmacenListener;
import dam.proyecto.controllers.MarcaBlancaController;
import dam.proyecto.controllers.NombreCompraController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.controllers.TagController;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.utilities.Preferencias;

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

    // Cámara
    private ActivityResultLauncher<ScanOptions> barcodeLauncher;

    // Campos para la búsqueda
    private AutoCompleteTextView inpTexto;
    private ImageButton btnSearch, btnClear, btnCamara;
    private ArrayList<String> etiquetaList;                                  // Listado de etiquetas

    // RecyclerView que muestra el listado de los productos
    private RecyclerView recyclerView;
    // Adaptador
    private AdaptadorProductos adaptadorProductos;

    // Botón para agregar un nuevo producto
    private FloatingActionButton fabAgregarProducto;

    // Oyente para el almancén
    AlmacenListener listener;

    /* ****************************************************************************************** */
    /* * FUNCIONES ****************************************************************************** */
    /* ****************************************************************************************** */

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

        barcodeLauncher = registerForActivityResult(new ScanContract(),
                result -> {
                    if (result.getContents() == null) {
                        Toast.makeText(view.getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    } else {
                        insertarCodigo( result.getContents() );
                    }
                });


        // Campos de búsqueda
        inpTexto = (AutoCompleteTextView ) view.findViewById( R.id.flp_inp_search );
        btnClear = view.findViewById( R.id.flp_btn_clear );
        btnClear.setOnClickListener(v -> clear());
        btnSearch = view.findViewById( R.id.flp_btn_search );
        btnSearch.setOnClickListener(v -> search());
        btnCamara = view.findViewById(R.id.flp_btn_camara);
        btnCamara.setOnClickListener(v -> scanear() );
        etiquetaList = new TagController( getContext() ).getNombres();

        // Establecemos el adaptador para las etiquetas
        ArrayAdapter<String> adapterEtiquetas = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                etiquetaList
        );
        inpTexto.setAdapter(adapterEtiquetas);

        // Botón para agregar un nuevo producto
        fabAgregarProducto = view.findViewById( R.id.fa_fab_addProductoAlmacen );
        fabAgregarProducto.setOnClickListener( v -> listener.addNuevoProducto());

        // Obtener los datos
        // Pedir al controlador de Producto el listado completo de productos
        // De inicio, la lista va a estar vacía, no se mostrará ningún producto hasta
        // que no se haya indicado en el campo de búsqueda.
//        productoData = ProductoController.getAll( getContext() );
        productoData = new ArrayList<>();

        // Obtenemos el RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);

        // Comprobar que el RecyclerView sea correcto
        if (recyclerView != null) {

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
     * Limpia el campo de búsqueda.
     */
    private void clear(){
        inpTexto.setText("");
    }

    /**
     * Busca productos a traves de un texto dado
     */
    @SuppressLint("NotifyDataSetChanged")
    private void search() {

        // Capturamos el texto que hay que buscar
        String text = inpTexto
                        .getText()
                        .toString()
                        .trim()
                        .toLowerCase();

        // Se admite la cadena vacía para mostrar todos los productos,
        // pues puede darse el caso de que alguno no tenga le etiqueta correcta
        if( text.isEmpty() || ( text.length() >= CARACTERES_MINIMOS_PARA_BUSCAR ) ) {
            // Le pedimos al controlador que nos devuelva la lista de productos

            // Boramos la colección de productos
            productoData.clear();

            // Obtener los comercios que contienen el texto
            ArrayList<ProductoEntity> productosBuscados =
                                        ProductoController.getAll(text, getContext());

            // Controlador de MarcaBlanca
            MarcaBlancaController marcaBlancaController = new MarcaBlancaController( getContext() );

            NombreCompraController nombreCompraController = new NombreCompraController( getContext() );
            productosBuscados = marcaBlancaController
                    .filtrarMarcaBlanca(
                            productosBuscados,
                            nombreCompraController
                                    .getById( Preferencias.getListaAbiertaId( getContext() ) )
                                    .getComercio()
                            );
            productoData.addAll( productosBuscados );

            // Notificamos el cambio al adaptador
            adaptadorProductos.notifyDataSetChanged();

        } else {
            Toast.makeText(getContext(), "Mínimo 3 caracteres", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Busca un producto a través del código de barras y lo muestra.
     * El código de barras es el id de ProductoEntity
     * @param barCode código de barras buscado
     */
    @SuppressLint("NotifyDataSetChanged")
    private void search(String barCode ){
        // Obtenemos el objeto
        ProductoEntity producto = ProductoController.getById( barCode, getContext() );

        // Añadimos el objeto a la lista de productos que se pasa al adaptador
        productoData.clear();
        productoData.add( producto );

        // Notificamos el cambio al adaptador
        adaptadorProductos.notifyDataSetChanged();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if( context instanceof AlmacenListener){
            listener = (AlmacenListener) context;
        } else {
            throw new RuntimeException( context
                    + " debes implementar el oyente." );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * Método que abre la cámara para obtener el código de barras
     */
    private void scanear() {

        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats( ScanOptions.ALL_CODE_TYPES );
        options.setPrompt("ESCANEAR CÓDIGO");
        options.setCameraId(0);
        options.setOrientationLocked( false );
        options.setBeepEnabled( false );
        options.setCaptureActivity( CaptureActivityPortrait.class );
        options.setBarcodeImageEnabled( false );
        barcodeLauncher.launch( options );

    }

    /**
     * Inserta el código devuelto por la librería del lector
     * @param contents el contenido en texto
     */
    private void insertarCodigo(String contents) {
        inpTexto.setText( contents );

        // Buscar el producto y mostarlo si está en la lista
        // o cargarlo en detalle si no lo está
        if( ProductoController.exists( contents, getContext() ) ){
            // Existe y lo cargamos en el listado
            search( contents );
        }else{
            // No está registrado el producto, cargamos la edición
            editarNuevoProducto( contents );
        }
    }

    /**
     * Carga el fragment detalle, en blanco, con el código de barras asociado
     * @param idProducto código de barras
     */
    private void editarNuevoProducto( String idProducto ){

        Fragment fragment = new DetalleProductoFragment();

        Bundle bundle = new Bundle();
        bundle.putString( "idProducto", idProducto );
        fragment.setArguments( bundle );

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.almacenContenedor, fragment )
                .commit();

    }

}