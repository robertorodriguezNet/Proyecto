package dam.proyecto.activities.producto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import dam.proyecto.R;
import dam.proyecto.activities.lista.ListaListaFragment;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.MarcaController;
import dam.proyecto.controllers.MedidaController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.MedidaEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.ProductoRepository;

/**
 * Muestra la información del producto que se está mostrando en la lista.
 * Es editable.
 *
 * @author Roberto Rodríguez
 * @version 1.2023.02.14
 * @since 2023/02/14
 */
public class ProductoInfoFragment extends Fragment {

    // Datos
    private String idCompra;                                         // Id de la compra que se edita
    private CompraEntity compra;                                          // Compra (producto+fecha)
    private ProductoEntity producto;                                            // Producto comprado
    private MedidaEntity medida;                                   // Objeto con la unidad de medida

    // Controladores
    private MarcaController marcaController;
    private ProductoRepository productoRepository;
    private MedidaController medidaController;
    private CompraController compraController;


    private int ofertActiva;                             // Guarda la oferta aplicada, 0 por defecto

    private Context context;                                                             // contexto

    // Componentes de la interfaz
    private TextView lblTitulo,
            inpPrecio,
            inpCantidad,
            inpTotal,
            tvUnidadMedida,
            tvPrecioMedida,
            tvAbbMedida;

    // Botones para las ofertas
    private Button btn2x1,
            btn3x2,
            btn50,
            btn70;
    private Map<Integer, Button> botoneraOferta;


    // Botones para salir
    private Button btnGuardarYSalir,
            btnSalirSinGuardar;

    /* ****************************************************************************************** */
    /*  ** FUNCIONES **************************************************************************** */
    /* ****************************************************************************************** */

    public ProductoInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Obtenemos el id de la compra (CompraEntity) que se ha solicictado
        idCompra = getArguments().getString("id");

        // Inicializar los repositorios de la base de datos
        iniciliazarRepositorios();

        // Cargamos los datos de la compra desde la BD
        cargarDatos();

        View view = inflater.inflate(R.layout.fragment_producto_info,
                container,
                false);

        context = view.getContext();

        // Inicilizar los componentes de la UI
        inicializarComponentes(view);

        // Escribimos los datos en la UI
        escribirLosdatos();

        // Oyentes  para los input editables
        inpPrecio.addTextChangedListener(textWatcher);
        inpCantidad.addTextChangedListener(textWatcher);

        return view;
    }

    /**
     * Inicializamos los componentes de la UI
     */
    private void inicializarComponentes(View view) {

        ofertActiva = compra.getOferta();                              // Obtenemos la oferta activa

        // Inputs editables
        lblTitulo = view.findViewById(R.id.fpi_tv_titulo);
        inpPrecio = view.findViewById(R.id.fpi_inp_precio);
        inpCantidad = view.findViewById(R.id.fpi_inp_cantidad);
        inpTotal = view.findViewById(R.id.fpi_inp_pagado);

        // Componentes para el precio por unidad de medida
        tvUnidadMedida = view.findViewById(R.id.fpi_tv_unidadMedida);
        tvPrecioMedida = view.findViewById(R.id.fpi_tv_precioMedida);
        tvAbbMedida = view.findViewById(R.id.fpi_tv_abbrMedida);

        // Botones de ofertas
        btn2x1 = view.findViewById(R.id.fpi_btn_2x1);
        btn3x2 = view.findViewById(R.id.fpi_btn_3x2);
        btn50 = view.findViewById(R.id.fpi_btn_50);
        btn70 = view.findViewById(R.id.fpi_btn_70);

        // La coleción de botones sirve para poder modificar
        // su apariencia al ser seleccionados
        botoneraOferta = new HashMap<>();
        botoneraOferta.put(1, btn2x1);
        botoneraOferta.put(2, btn3x2);
        botoneraOferta.put(3, btn50);
        botoneraOferta.put(4, btn70);
        setFondoBotones();


        // Botones para salir
        btnGuardarYSalir = view.findViewById(R.id.fpi_btn_guardar);
        btnSalirSinGuardar = view.findViewById(R.id.fpi_btn_salir);

        // Oyentes para los botones
        btnGuardarYSalir.setOnClickListener(view1 -> actualizarCompra());

        btnSalirSinGuardar.setOnClickListener(view12 -> salir());
    }

    private void salir() {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listaContenedor, new ListaListaFragment())
                .commit();
    }

    /**
     * Seteamos los datos en la vista
     */
    @SuppressLint("DefaultLocale")
    private void escribirLosdatos() {

        // Título
        StringBuilder titulo = new StringBuilder();
        titulo.append(producto.getDenominacion())
                .append(" - ")
                .append(marcaController.getNameById(producto.getMarca()))
                .append(" - ")
                .append(producto.getCantidad())
                .append(" ")
                .append(producto.getMedida());

        lblTitulo.setText(titulo);                                           // Establecer el título
        try {

            inpPrecio.setText(String.format("%.02f", compra.getPrecio()));
            inpCantidad.setText(String.format("%.02f", compra.getCantidad()));
            inpTotal.setText(String.format("%.02f", compra.getPagado()));

            // La cantidad se refiere a la cantidad de producto que entra en el
            // artículo, no la cantidad de artículos comprados
            // Si el producto es a granel, la cantidad es 1
            // Para saber si es granel, miramos que la cantidad de producto sea 0
            float precioMedida = compra.getPrecio() / (( producto.getCantidad() == 0)?
                                                    1f : producto.getCantidad());

            tvUnidadMedida.setText(medida.getDescription());
            tvPrecioMedida.setText(String.format("%.02f", precioMedida));
            tvAbbMedida.setText(medida.getId());


        } catch (Exception e) {
            Log.d("LDLC", "ProductoInfoFragment: error al validar precio y/o cantidad." +
                    "\n" + compra.toString() + "\n" + e.getMessage());
        }

    }

    /**
     * Inicializar los repositorios
     */
    private void iniciliazarRepositorios() {
        compraController = new CompraController(context);
        productoRepository = new ProductoRepository(context);
        marcaController = new MarcaController(context);
        medidaController = new MedidaController(context);
    }

    /**
     * Desacargamos los datos de la BD
     */
    private void cargarDatos() {
        compra = compraController.getById(idCompra);
        producto = productoRepository.getById(compra.getProducto());
        medida = medidaController.getById(producto.getMedida());
    }

    /**
     * Actualiza los datos de la compra actual.
     * Las operaciones se realizan en CompraController.
     */
    private void actualizarCompra() {

        // En compra tenemos La CompraEntity que estamos editando
        // Aunque los campos estén modificados, no se han guardado 'compra'

        // Datos que guardaremos:
        // precio
        // cantidad
        // precioMedido
        // oferta
        // pagado

        compra.setPrecio(Float.parseFloat(inpPrecio.getText().toString().replace(",", ".")));
        compra.setCantidad(Float.parseFloat(inpCantidad.getText().toString().replace(",", ".")));
        compra.setPagado(Float.parseFloat(inpTotal.getText().toString().replace(",", ".")));
        compra.setPrecioMedido(Float.parseFloat(tvPrecioMedida.getText().toString().replace(",", ".")));

        //Ahora que tenemos el objeto compra, lo actualizamos en la BD
        compraController.update(compra);

        salir();

    }

    /**
     * Establece el fondo de los botones dependiendo de la oferta seleccionada
     */
    private void setFondoBotones() {

        // Recorremos los botones
        for (int id : botoneraOferta.keySet()) {

            try {
                Button btn = botoneraOferta.get(id);

                int color = (id == compra.getOferta()) ?
                        context.getColor(R.color.fondoBotonVerdeAvtivo) :
                        context.getColor(R.color.fondoBotonVerde);

                btn.setBackgroundColor(color);
            } catch (Exception e) {
                Log.d("LDLC", "Error al recorrer los botones: " + id
                        + "\n" + e.getMessage());
            }

        }

    }

    /* ****************************************************************************************** */
    /* ** INTERFAZ PARA EL OYENTE DE LOS EDIT TEXT ******************************************* ** */
    /* ****************************************************************************************** */

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void afterTextChanged(Editable editable) {

            // Cuando se modifica el precio, recalcula el total y el precio por la medida
            try {

                // Se calcula el total
                float preciof = Float.parseFloat(inpPrecio
                        .getText()
                        .toString()
                        .replace(",", "."));
                float cantidadf = Float.parseFloat(inpCantidad
                        .getText()
                        .toString()
                        .replace(",", "."));

                // Precio por medida
                tvPrecioMedida.setText(String.format("%.03f", preciof / producto.getCantidad()));
                inpTotal.setText(String.format("%.03f", (preciof * cantidadf)));

            } catch (Exception e) {
                Log.d("LDLC", "InfoTextWatcher: " + e.getMessage());
            }

        }
    };

}
