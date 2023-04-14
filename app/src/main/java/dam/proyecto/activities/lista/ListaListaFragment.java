package dam.proyecto.activities.lista;

import static dam.proyecto.utilities.Words.formatearPrecio;
import static dam.proyecto.utilities.Words.recortarTexto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.adapters.ProductoCompraListAdapter;
import dam.proyecto.activities.lista.listeners.ListaListener;
import dam.proyecto.controllers.ComercioController;
import dam.proyecto.controllers.ListaController;
import dam.proyecto.controllers.NombreCompraController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.utilities.Preferencias;


/**
 * @author Roberto Rodríguez
 * @version 2023.03.23
 * @since 11/02/2023
 */
public class ListaListaFragment extends Fragment {

    private Context context;

    private ListaListener oyente;

    // Componentes
    TextView lblNombreDeLaCompra;
    Spinner spinner;

    ImageView btnShare;
    ImageView btnPrecio;
    ImageView btnSalir;
    ImageView btnAgregar;

    // Datos
    // NombreCompraEntity
    NombreCompraEntity nombreCompra;                            // Es la compra que se está editando
    // Repositorios
    ComercioController comercioController;
    //    NombreCompraRepository nombreCompraRepository;
    NombreCompraController nombreCompraController;

    ArrayList<ComercioEntity> dataComercio;                // Colección de comercios para el spinner
    ArrayList<CompraEntity> dataProductos;                    // Colección de productos de la compra

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deshabilitamos el bottom menu
        BottomNavigationView navegador = getActivity().findViewById(R.id.navegador);
        if (navegador != null) {
            navegador.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_lista_lista, container, false);
        context = view.getContext();

        // Lo primero es comprobar que tengamos una compra editada
        // Obtenemos el id de la compra desde las preferencias
        String idCompra = Preferencias.getListaAbiertaId(this.getActivity());


        // Solo si la compra no es nula
        if (idCompra != null) {

            // Cargar los datos de los comercios
            comercioController = new ComercioController(context);
            dataComercio = comercioController.getAll();

            // Cargar los productos
            dataProductos = new ListaController(context).getListaProductos();

            // controlador para el nombre de la compra
            nombreCompraController = new NombreCompraController(context);

            // Obtener el objeto Nombre de la compra.
            // En NombreCompraEntity se establece el comercio
            // Inicilizar el NombreDeLaCompra
            nombreCompra = nombreCompraController.getById(idCompra);

            // Inicializar componente
            lblNombreDeLaCompra = view.findViewById(R.id.fla_tv_nombreCompra);
            btnShare = view.findViewById(R.id.fla_img_share);
            btnPrecio = view.findViewById(R.id.fla_img_precio);
            btnSalir = view.findViewById(R.id.fla_img_cerrar);
            btnAgregar = view.findViewById(R.id.fla_fab_addProduct);
            inicializarSpinner(view);


            // Nombre de la compra
            String nombre = nombreCompra.getNombre();
            if (nombre.length() >= 13) {
                lblNombreDeLaCompra.setTextSize(14);
            }
            lblNombreDeLaCompra.setText(nombre);

            // Botón para compartir la lista
            btnShare.setOnClickListener(view1 -> compartir());

            // Botón para mostrar diferentes precios
            btnPrecio.setOnClickListener(v -> oyente.compararComercios());

            // Botón salir
            btnSalir.setOnClickListener(view12 -> salir());

            // Botón para añadir productos
            btnAgregar.setOnClickListener(view13 -> startActivity(new Intent(context, AlmacenActivity.class)));

            // Cargar la lista
            // Obtener el ListView
            ListView listView = view.findViewById(R.id.fla_lv_listaDeLaCompra);

            // Adaptador
            ProductoCompraListAdapter adapterLista = new ProductoCompraListAdapter(view.getContext(),
                    R.layout.producto_compra_item,
                    dataProductos,
                    oyente
            );
            listView.setAdapter(adapterLista);

            // Escribimos el total
            TextView total = view.findViewById(R.id.fla_tv_total);
            total.setText(String.format("%.02f", getTotalCompra()));

        }
        
        return view;
    }

    /**
     * Actualiza la compra.
     * Esta función es llamada desde salir() o al cambiar el spinner del comercio
     */
    private void actualizarCompra() {
        // Obtnemos el comercio directamente del spinner
        nombreCompra.setComercio(((ComercioEntity) spinner.getSelectedItem()).getId());
        nombreCompraController.update(nombreCompra);
    }

    /**
     * Compartir la lista de la compra
     */
    public void compartir() {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getListaParaCompratir());
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");

        try{
            startActivity(sendIntent);
        }catch ( android.content.ActivityNotFoundException ex){
            ex.printStackTrace();
            Toast.makeText(context, "No se ha encontrado WathsApp", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Devuelve la lista para compartir por Whatsapp.
     * Leemos los datos de la lista dataProductos que contiene objetos CompraEntity
     *
     * @return una cedna de texto con la información.
     */
    private String getListaParaCompratir() {

        StringBuilder lista = new StringBuilder();
        float totalTicket = 0f;

        // Recorremos la lista de productos y los añadimos al mensaje
        for( CompraEntity p : dataProductos ){

            // Si el producto no ha sido borrado
            if(ProductoController.exists(p.getProducto(),getContext())){
            lista.append(
                            recortarTexto(
                                    ProductoController
                                            .getById(p.getProducto(), getContext())
                                            .getDenominacion(),
                                    18
                            )
                    )
                    .append("\n")
                    .append(formatearPrecio(p.getCantidad()))
                    .append(" x ")
                    .append(formatearPrecio(p.getPrecio()))
                    .append(" = ")
                    .append(formatearPrecio(p.getPagado()))
                    .append("\n-----------------------------\n");

            totalTicket += p.getPagado();
            }else{
                // El producto ha sido borrado
                lista.append("(Producto borrado)")
                        .append("\n-----------------------------\n");
            }
        }

        lista
                .append("TOTAL: ......... ")
                .append( formatearPrecio( totalTicket ) );

        return lista.toString();

    }

    /**
     * Devuelve el id del spinner que se corresponde con el id del comercio.
     * Hay que tener en cuenta que el spinner comienza a contar por 1.
     *
     * @return el id del spinner
     */
    private int getSpinnerId() {
        // Establecer el ítem por defecto
        // Obtener el nombre del comercio
        String nComercio = comercioController
                .getNombreComercio(
                        nombreCompra.getComercio()
                );

        //Buscamos el comercio en la dataComercio
        boolean nombre = false;
        ComercioEntity c = null;
        int count = 0;
        while (!nombre && (count < dataComercio.size())) {
            c = dataComercio.get(count);
            nombre = (c.getName().equals(nComercio));
            count++;
        }
        return ((c == null) ? 1 : --count);
    }

    /**
     * Devuelve el importe total de la compra.
     *
     * @return el importe total de la compra
     */
    private float getTotalCompra() {
        float total = 0.0f;
        for (CompraEntity producto : dataProductos) {
            total += producto.getPrecio() * producto.getCantidad();
        }
        return total;
    }

    /**
     * Spinner para seleccionar el comercio
     *
     * @param view vista
     */
    private void inicializarSpinner(View view) {

        spinner = view.findViewById(R.id.fla_spn_seleccionarComercio);
        ArrayAdapter<ComercioEntity> adapter = new ArrayAdapter<>(
                context,
                androidx
                        .appcompat
                        .R.layout.support_simple_spinner_dropdown_item,
                dataComercio
        );
        spinner.setAdapter(adapter);
        spinner.setSelection(getSpinnerId());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                actualizarCompra();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ListaListener) {
            oyente = (ListaListener) context;
        } else {
            throw new RuntimeException(context
                    + " debes implementar el oyente.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        oyente = null;
    }


    /**
     * Salimos de la lista de la compra hacia Compras.
     */
    public void salir() {

        actualizarCompra();

        startActivity(new Intent(context, ComprasActivity.class));
    }

}