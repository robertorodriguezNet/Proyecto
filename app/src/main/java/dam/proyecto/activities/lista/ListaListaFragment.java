package dam.proyecto.activities.lista;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.adapters.DiferentesComerciosAdapter;
import dam.proyecto.activities.lista.adapters.ProductoCompraListAdapter;
import dam.proyecto.activities.lista.clases.ComercioDiferente;
import dam.proyecto.activities.lista.listeners.ListaListener;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.ListaController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.relaciones.VistaCompra;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.utilities.Preferencias;


/**
 * Controlador para Producto
 *
 * @author Roberto Rodríguez
 * @since 11/02/2023
 * @version 2023.03.23
 */
public class ListaListaFragment extends Fragment {

    private Context context;

    private ListaListener oyente;
    private ListaController listaController;                    // Controlador para la lista abierta

    private Bundle argumentos;                           // Argumentos que puede recibir el fragment

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
    ComercioRespository comercioRespository;
    NombreCompraRepository nombreCompraRepository;

    ArrayList<ComercioEntity> dataComercio;                // Colección de comercios para el spinner
    ArrayList<CompraEntity> dataProductos;                    // Colección de productos de la compra

    float totalTicket;                       // Declaramos esta variable para poder usarla en lambda

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deshabilitamos el bottom menu
        BottomNavigationView navegador = getActivity().findViewById(R.id.navegador);
        if (navegador != null) {
            navegador.setVisibility(View.VISIBLE);
        }

        // Obtener los argumentos, si es que los hay
        argumentos = getArguments();

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
            listaController = new ListaController(context);

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
            btnShare = view.findViewById(R.id.fla_img_share);
            btnPrecio = view.findViewById(R.id.fla_img_precio);
            btnSalir = view.findViewById(R.id.fla_img_cerrar);
            btnAgregar = view.findViewById(R.id.fla_fab_addProduct);
            inicializarSpinner(view);


            // Nombre de la compra
            lblNombreDeLaCompra.setText(nombreCompra.getNombre());

            // Botón para compartir la lista
            btnShare.setOnClickListener(view1 -> compartir());

            // Botón para mostrar diferentes precios
            btnPrecio.setOnClickListener(view1 -> abrirPreciosComercio());

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
                    (argumentos == null) ? "actual" : argumentos.getString("opcion"));
            listView.setAdapter(adapterLista);

            // Escribimos el total
            TextView total = view.findViewById(R.id.fla_tv_total);
            total.setText(String.format("%.02f", getTotalCompra()));

        }

        return view;
    }

    /**
     * Muestra un listado con los productos de la lista abierta
     * pero con los precios de otros comercios.
     */
    public void abrirPreciosComercio() {

        // Obtener los datos.

        // ¿Qué tengo?: una lista de compras CompraEntity en dataProductos
        //              CompraEntity NO guarda el comercio
        // ¿Qué necesito? la misma lista de compras pero para cada comercio
        // ¿Cómo obtengo los comercios?
        //              Se busca, para cada producto, los comercios en los que ha sido comprado
        //              Para cada producto debe existir una colección de comercios (Integer)
        CompraController cc = new CompraController(getContext());
        HashMap<Integer, ArrayList<VistaCompra>> compras =
                cc.getComparativaComercios(
                        Preferencias
                                .getListaAbiertaId(context)
                );

        // Ya tenemos la colección de comercios y sus compras.
        // La colección se agrupa por comercio (por su id)
        // Cada comercio (id) guarda un ArrayList de VistaCompra, con un VistaCompra para
        // cada uno de los productos.
        // Ahora hay que dar forma a los datos que se van a mostra en el layot:
        // necesitamos una clase que recoja los datos y crear un array para pasarlo al
        // adapter de la lista que se mostrará.
        ArrayList<ComercioDiferente> diferentesComercios = new ArrayList<ComercioDiferente>();

        // De los datos del array, no necesitamos la clave, pues en VistaCompra ya
        // aparece el comercio
        for( Map.Entry<Integer, ArrayList<VistaCompra>> compra : compras.entrySet() ){

            int articulos = 0;
            float total = 0f;
            long desde = 0;
            long hasta = 0;
            String comercio = "";

            // Recorrer los productos comprados en cada comercio
            for ( VistaCompra c : compra.getValue() ) {

                Long f = Long.parseLong( c.fecha );

                articulos++;
                total += Float.parseFloat( c.precio );

                if( desde == 0 ){
                    desde = f;
                } else if ( f < desde ){
                    desde = f;
                }

                if( hasta == 0 ){
                    hasta = f;
                }else if( f > hasta ){
                    hasta = f;
                }

                comercio = c.name;
            }

            // Guardar el resumen del comercio
            diferentesComercios.add(
                    new ComercioDiferente(
                            comercio,
                            total,
                            articulos,
                            String.valueOf(desde),
                            String.valueOf(hasta)
                    )
            );

        }

        // Ya tenemos una lista completa con las compras por comercio

        // Mostramos un diálogo con las opciones

        // Vista del dialog
        ListView listView = new ListView( context );

        ArrayAdapter adapter = new DiferentesComerciosAdapter(
                context,
                R.layout.item_precio_comercio,
                diferentesComercios
        );
        listView.setAdapter( adapter );


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("En otros comercios");


        builder.setNegativeButton("Cancelar", null);
        builder.setView(listView);

        AlertDialog dialogPrecio = builder.create();
        dialogPrecio.show();

    }

    /**
     * Actualiza la compra.
     * Esta función es llamada desde salir() o al cambiar el spinner del comercio
     */
    private void actualizarCompra() {
        // Obtnemos el comercio directamente del spinner
        nombreCompra.setComercio(((ComercioEntity) spinner.getSelectedItem()).getId());
        nombreCompraRepository.update(nombreCompra);
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
        startActivity(sendIntent);

    }

    /**
     * Devuelve el precio con una longitud de 3 enteros y 2 decimales
     *
     * @param precio el precio
     * @return el precio formateado
     */
    @SuppressLint("DefaultLocale")
    private String formatearPrecio(float precio) {
        return String.format("%.02f", precio);
    }

    /**
     * Devuelve la lista para compartir por Whatsapp.
     * Leemos los datos de la lista dataProductos que contiene objetos CompraEntity
     *
     * @return una cedna de texto con la información.
     */
    private String getListaParaCompratir() {

        StringBuilder lista = new StringBuilder();
        totalTicket = 0f;

        dataProductos.forEach(p -> {
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
                }
        );

        lista
                .append("TOTAL: ......... ")
                .append(totalTicket);

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
        String nComercio = comercioRespository
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
     * Devuelve la cadena de texto recortada
     *
     * @param texto que se quiere recortar
     * @return el texto recortado
     */
    public String recortarTexto(String texto, int longitud) {
        return (texto.length() <= longitud) ? texto : texto.substring(0, longitud);
    }

    /**
     * Salimos de la lista de la compra hacia Compras.
     */
    public void salir() {

        actualizarCompra();

        startActivity(new Intent(context, ComprasActivity.class));
    }

}