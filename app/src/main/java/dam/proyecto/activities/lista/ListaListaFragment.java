package dam.proyecto.activities.lista;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.adapters.ProductoCompraListAdapter;
import dam.proyecto.activities.lista.listeners.ListaListener;
import dam.proyecto.controllers.ListaController;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.utilities.Preferencias;


/**
 * Controlador para Producto
 * @author Roberto Rodríguez
 * @since 11/02/2023
 * @version 2023.02.24
 */
public class ListaListaFragment extends Fragment {

    private Context context;

    private ListaListener oyente;
    private ListaController listaController;                    // Controlador para la lista abierta

    private Bundle argumentos;                           // Argumentos que puede recibir el fragment

    // Componentes
    TextView lblNombreDeLaCompra;
    Spinner spinner;
    ImageView btnPrecio;
    ImageView btnSalir;
    ImageView btnAgregar;

    // Datos
    // NombreCompraEntity
    NombreCompraEntity nombreCompra;                            // Es la compra que se está editando
    // Repositorios
    ComercioRespository comercioRespository;
    NombreCompraRepository nombreCompraRepository;

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

        // Obtener los argumentos, si es que los hay
        argumentos  = getArguments();

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

            // Obtenemos un controlador para la lista
            listaController = new ListaController( context );

            // Cargar los datos de los comercios
            comercioRespository = new ComercioRespository(context);
            dataComercio = comercioRespository.getAll();

            // Cargar los productos
            dataProductos = listaController.getListaProductos();

            // repositorio para el nombre de la compra
            nombreCompraRepository = new NombreCompraRepository(context);

            // Obtener el objeto Nombre de la compra.
            // En NombreCompraEntity se establece el comercio
            // Inicilizar el NombreDeLaCompra
            nombreCompra = nombreCompraRepository.getById(idCompra);

            // Inicializar componente
            lblNombreDeLaCompra = view.findViewById(R.id.fla_tv_nombreCompra);
            btnPrecio = view.findViewById( R.id.fla_img_precio);
            btnSalir = view.findViewById(R.id.fla_img_cerrar);
            btnAgregar = view.findViewById(R.id.fla_fab_addProduct);
            inicializarSpinner( view );


            // Nombre de la compra
            lblNombreDeLaCompra.setText(nombreCompra.getNombre());

            // Botón para mostrar diferentes precios
            btnPrecio.setOnClickListener(view1 -> abrirCambiarPrecios());

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
                    oyente,
                    ( argumentos == null )? "actual" : argumentos.getString( "opcion"));
            listView.setAdapter(adapterLista);

            // Escribimos el total
            TextView total = view.findViewById(R.id.fla_tv_total);
            total.setText(String.format("%.02f", getTotalCompra()));

        }

        return view;
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
     * Devuelve el id del spinner que se corresponde con el id del comercio.
     * Hay que tener en cuenta que el spinner comienza a contar por 1.
     *
     * @return el id del spinner
     */
    private int getSpinnerId() {
        // Establecer el ítem por defecto
        // Obtener el nombre del comercio
        String nComercio = comercioRespository
                .getNombreComercio(
                        nombreCompra.getComercio()
                );

        int idComercio = 1;
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

    /* ****************************************************************************************** */
    /* ***** ACCIONES DE LOS BOTONES ************************************************************ */
    /* ****************************************************************************************** */

    public void borrar(){

    }

    /**
     * Salimos de la lista de la compra hacia Compras.
     */
    public void salir() {

        actualizarCompra();

        startActivity(new Intent(context, ComprasActivity.class));
    }

    /**
     * Cambia el precio de los productos de la lista
     */
    public void abrirCambiarPrecios(){

        // Botones del diálogo
        Button btnActual, btnGlobal, btnComercio;

        // Mostramos un diálogo con las opciones
        LayoutInflater inflater = this.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder( context );
        View view = inflater.inflate( R.layout.alert_cambiar_precios, null );

        builder.setNegativeButton( "Cancelar", null);
        builder.setView( view );

        // Inicializar los botones del diálogo
        btnActual = view.findViewById( R.id.acp_btn_actual );
        btnGlobal= view.findViewById( R.id.acp_btn_global );
        btnComercio = view.findViewById( R.id.acp_btn_comercio );

        AlertDialog dialogPrecio = builder.create();
        dialogPrecio.show();

        // Oyentes para los eventos
        btnActual.setOnClickListener(v -> btnCambiarPrecioOnClick(v, dialogPrecio ));
        btnGlobal.setOnClickListener(v -> btnCambiarPrecioOnClick(v, dialogPrecio ));
        btnComercio.setOnClickListener(v -> btnCambiarPrecioOnClick(v, dialogPrecio ));

    }

    /* ****************************************************************************************** */
    /* ***** FUNCIONES AUXILIARES *************************************************************** */
    /* ****************************************************************************************** */

    /**
     * Devuelve el importe total de la compra.
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
     * Actualiza la compra.
     * Esta función es llamada desde salir() o al cambiar el spinner del comercio
     */
    private void actualizarCompra(){
        // Obtnemos el comercio directamente del spinner
        nombreCompra.setComercio(((ComercioEntity) spinner.getSelectedItem()).getId());
        nombreCompraRepository.update(nombreCompra);
    }


    /* ****************************************************************************************** */
    /* ***** EVENTOS PARA LOS BOTONES DEL ALERT PRECIOS ***************************************** */
    /* ****************************************************************************************** */

    @SuppressLint("NonConstantResourceId")
    public void btnCambiarPrecioOnClick(View view, AlertDialog dialog ){

        dialog.dismiss();

        Fragment fragment = new ListaListaFragment();
        argumentos = new Bundle();
        String opcion = "actual";

        switch ( view.getId() ){
            case R.id.acp_btn_actual:
                    opcion = "actual";
                break;
            case R.id.acp_btn_global:
                opcion = "global";
                break;
            case R.id.acp_btn_comercio:
                opcion = "comercio";
                break;
            default:
        }

        argumentos.putString( "opcion", opcion);
        fragment.setArguments( argumentos );

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.listaContenedor, fragment)
                .commit();


    }

    /* ****************************************************************************************** */
    /* ***** COMPONENTES ************************************************************************ */
    /* ****************************************************************************************** */

    /**
     * Spinner para seleccionar el comercio
     * @param view vista
     */
    private void inicializarSpinner( View view ){

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
}