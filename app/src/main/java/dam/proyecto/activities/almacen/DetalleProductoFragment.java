package dam.proyecto.activities.almacen;

import static dam.proyecto.Config.BOTON_DESACTIVADO_ALPHA;
import static dam.proyecto.controllers.ProductoController.validarCodigoDeBarras;
import static dam.proyecto.controllers.ProductoController.validarDenominacion;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.controllers.TagProductoController;
import dam.proyecto.database.entity.MedidaEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.MarcaRepository;
import dam.proyecto.database.repositories.MedidaRepository;
import dam.proyecto.database.repositories.ProductoRepository;
import dam.proyecto.database.repositories.TagProductoRepository;
import dam.proyecto.database.repositories.TagRepository;


/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.22
 * @since 17/02/2023
 */
public class DetalleProductoFragment extends Fragment implements TextWatcher {

    // Instancia del navegador, necesaria para porde
    // habolitarlo o deshabilitarlo
    BottomNavigationView navegador;

    // Elementos que componen el formulario
    private TextView tv_codigoDeBarras,
            tv_denominacion,
            tv_unidades,
            tv_cantidad,
            text_tags;
    private AutoCompleteTextView tv_marca,
            tv_etiqueta;
    private Spinner spn_medida;

    // Botones
    private Button btn_addTag,
            btn_cancelar,
            btn_limpiar,
            btn_eliminar,
            btn_guardar;

    // Array con los botones que son modificables
    private ArrayList<Button> botonera;

    private ArrayList<MedidaEntity> medidaList;                              // Colección de medidas
    private ArrayList<String> marcaList;                                      // Colección de marcas
    private ArrayList<String> etiquetaList;                                  // Listado de etiquetas
    private ArrayList<String> tagProductoList;      // Etiquetas que se corresponden con el producto

    // Repositorios
    MedidaRepository medidaRepository;
    MarcaRepository marcaRepository;
    ProductoRepository productoRepository;
    TagRepository tagRepository;
    TagProductoController tagProductoController;

    private ProductoEntity productoEditando;                         // Producto que está editándose

    private Context context;

    /** **************************************************************************************** **/
    /** ** FUNCIONES *************************************************************************** **/
    /**
     * ***************************************************************************************
     **/
    public DetalleProductoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deshabilitamos el bottom menu
        navegador = getActivity().findViewById(R.id.navegador);
        navegador.setVisibility(View.INVISIBLE);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_producto, container, false);
        context = view.getContext();

        botonera = new ArrayList<>();                          // Cargar los botones en el ArrayList

        // Inicializar los repositorios
        medidaRepository = new MedidaRepository(context);
        marcaRepository = new MarcaRepository(context);
        productoRepository = new ProductoRepository(context);
        tagRepository = new TagRepository(context);
        tagProductoController = new TagProductoController( context );

        // Obtener la colección de medidas, marcas y etiquetas
        // Las etiquetas del producto solo se obtienen si se está editando
        medidaList = medidaRepository.getAll();
        marcaList = marcaRepository.getNombres();
        etiquetaList = tagRepository.getNombres();

        // Inicializamos los componente
        // No se cargan datos
        inicializarComponentes(view);

        // Editar un producto si se ha recibido como argumento
        Bundle argumets = this.getArguments();
        if (argumets != null) {
            productoEditando = (ProductoEntity) getArguments()
                    .getSerializable("producto");
            if (productoEditando != null) {
                cargarProducto(productoEditando);
//            Log.d( "LDLC", "Producto editando: " + productoEditando );
            }
        }
        return view;
    }

    /**
     * Inicilizar los componentes de la interfaz
     */
    private void inicializarComponentes(View view) {

        tv_codigoDeBarras = (TextView) view.findViewById(R.id.aep_inp_codigo);
        tv_denominacion = (TextView) view.findViewById(R.id.aep_inp_denominacion);
        tv_marca = (AutoCompleteTextView) view.findViewById(R.id.aep_inp_marca);
        tv_etiqueta = (AutoCompleteTextView) view.findViewById(R.id.aep_inp_etiqueta);
        tv_unidades = (TextView) view.findViewById(R.id.aep_inp_unidades);
        tv_cantidad = (TextView) view.findViewById(R.id.aep_inp_cantidad);
        spn_medida = (Spinner) view.findViewById(R.id.aep_spn_medida);
        text_tags = (TextView) view.findViewById(R.id.fdp_text_etiquetas);

        btn_addTag = (Button) view.findViewById(R.id.fdp_btn_tagOK);
        btn_addTag.setOnClickListener(v -> {
            addTag();
        });

        // Cargar el spinner
        ArrayAdapter<MedidaEntity> adapterMedidas = new ArrayAdapter<>(
                context,
                androidx
                        .appcompat
                        .R.layout.support_simple_spinner_dropdown_item,
                medidaList
        );
        spn_medida.setAdapter(adapterMedidas);
        spn_medida.setSelection(0);

        // Establecemos el adaptador para las marcas
        ArrayAdapter<String> adapterMarcas = new ArrayAdapter<>(
                context,
                android.R.layout.simple_list_item_1,
                marcaList
        );
        tv_marca.setAdapter(adapterMarcas);

        // Establecemos el adaptador para las etiquetas
        ArrayAdapter<String> adapterEtiquetas = new ArrayAdapter<>(
                context,
                android.R.layout.simple_list_item_1,
                etiquetaList
        );
        tv_etiqueta.setAdapter(adapterEtiquetas);

        // Rellenamos el campo de etiquetas

        // Establecemos los oyentes para los input
        tv_codigoDeBarras.addTextChangedListener(this);
        tv_denominacion.addTextChangedListener(this);
        tv_marca.addTextChangedListener(this);
        tv_unidades.addTextChangedListener(this);
        tv_cantidad.addTextChangedListener(this);
        tv_etiqueta.addTextChangedListener(this);
        text_tags.addTextChangedListener(this);


        // Botonera
        btn_cancelar = view.findViewById(R.id.aep_btn_cancelar);
        btn_limpiar = view.findViewById(R.id.aep_btn_limpiar);
        btn_eliminar = view.findViewById(R.id.aep_btn_eliminar);
        btn_guardar = view.findViewById(R.id.aep_btn_guardar);

        btn_cancelar.setOnClickListener(v -> {
            cancelar();
        });
        btn_limpiar.setOnClickListener(v -> {
            limpiar();
        });
        btn_eliminar.setOnClickListener(v -> {
            eliminar();
        });
        btn_guardar.setOnClickListener(v -> {
            guardar();
        });

        botonera.add(btn_limpiar);
        botonera.add(btn_eliminar);
        botonera.add(btn_guardar);

    }

    /* ****************************************************************************************** */
    /* ** ACCIONES PARA LOS BOTONES ************************************************************* */
    /* ****************************************************************************************** */

    public void cancelar() {
        navegador.setVisibility(View.VISIBLE);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.almacenContenedor, new ListaProductosFragment())
                .commit();
    }

    /**
     * Método que limpia el formulario.
     */
    public void limpiar() {

        tv_codigoDeBarras.setText(R.string.str_cadenaVacia);
        tv_denominacion.setText(R.string.str_cadenaVacia);
        tv_marca.setText(R.string.str_cadenaVacia);
        tv_unidades.setText(R.string.str_cadenaVacia);
        tv_unidades.setHint(R.string.str_cero);
        tv_cantidad.setText(R.string.str_cadenaVacia);
        tv_cantidad.setHint(R.string.str_ceroDecimal);
        spn_medida.setSelection(0);

        // Desactivar botonera
        desactivarBotonera();
    }

    /**
     * Elimina un producto de la base de datos a partir del v id.
     * El producto podría haber sido modificado, por lo que, en lugar
     * de enviar el objeto, enviamos el id y que el controlador lo
     * elimine.
     */
    public void eliminar() {
        String id = tv_codigoDeBarras.getText().toString();
//        Log.d("LDLC", "Borrar: " + id );
        productoRepository.deleteById(id);
        cancelar();
    }

    /**
     * Acción para guardar un producto.
     */
    public void guardar() {

        // Si es un producto nuevo, nos quedamos en el formulario
        guardarProducto();
        if (productoEditando == null) {
            limpiar();
        } else {
            cancelar();
        }


    }

    /* ****************************************************************************************** */
    /* ** INTERFAZ PARA EL OYENTE DE LOS EDIT TEXT ******************************************* ** */
    /* ****************************************************************************************** */
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    /**
     * Después de cada pulsación, evaluamos el formulario.
     * Este método evalúa el código de barras y la denominación, pero no muestra los errores, tan
     * solo los evalúa para poder activar los botones.
     *
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {

//        Log.d("LDlC", "DetalleProductoFragment.afterTextChanged id que cambia: " +
//                editable.toString() );

        // Comprobar el campo de tag, si tiene al menos 3 caracteres, se habilita
        if (tv_etiqueta.getText().toString().length() >= 3) {
            btn_addTag.setEnabled(true);
        }

        // Si hay datos introducidos
        if (hayDatosIntroducidos()) {

//            Log.d("LDLC", "DetalleProductoFragment.afterTextChanged: " +
//                    "Sí hay datos introducidos");

            habilitarBtnLimpiar(true);                               // Habilitar el botón limipar

            // El código de barras y la denominación son válidas
            int errorCB = validarCodigoDeBarras(
                    tv_codigoDeBarras.getText().toString(),
                    productoEditando != null,
                    context);
            int errorD = validarDenominacion(tv_denominacion.getText().toString());

//            Log.d("LDLC", "DetalleProductoFragment.afterTextChanged\n" +
//                    "errorCB: " + errorCB + ", errorD: " + errorD);

            if ((errorCB < 0) && (errorD < 0)) {

                habilitarBtnGuardar(true);

            } else {
                habilitarBtnGuardar(false);
            }

        } else {

//            Log.d("LDLC", "DetalleProductoFragment.afterTextChanged: " +
//                    "No hay datos introducidos");

            // No hay datos escritos
            habilitarBtnLimpiar(false);
        }

    }


    /* ****************************************************************************************** */
    /* ** HABILITAR / DESHABILITAR BOTONERA  ************************************************* ** */
    /* ****************************************************************************************** */

    /**
     * Habilita o deshabilita el botón para limpiar el formulario
     */
    private void habilitarBtnLimpiar(boolean habilitar) {
        btn_limpiar.setClickable(habilitar);
        btn_limpiar.setEnabled(habilitar);
        btn_limpiar
                .setTextColor(getResources()
                        .getColor((habilitar) ? R.color.IconoAzul : R.color.Gris));
        btn_limpiar.setAlpha((habilitar) ? 1 : BOTON_DESACTIVADO_ALPHA);
    }

    /**
     * Habilita el botón para guardar el producto
     */
    private void habilitarBtnGuardar(boolean habilitar) {
        btn_guardar.setClickable(habilitar);
        btn_guardar.setEnabled(habilitar);
        btn_guardar
                .setTextColor(getResources()
                        .getColor((habilitar) ? R.color.green_700 : R.color.Gris));
        btn_guardar.setAlpha((habilitar) ? 1 : BOTON_DESACTIVADO_ALPHA);
    }


    /**
     * Habilita el botón para ELIMINAR el producto
     */
    private void habilitarBtnEliminar(boolean habilitar) {
        btn_eliminar.setClickable(habilitar);
        btn_eliminar.setEnabled(habilitar);
        btn_eliminar
                .setTextColor(getResources()
                        .getColor((habilitar) ? R.color.error : R.color.Gris));
        btn_eliminar.setAlpha((habilitar) ? 1 : BOTON_DESACTIVADO_ALPHA);
    }



    /* ****************************************************************************************** */
    /* ** MÉTODOS AUXILIARES  **************************************************************** ** */
    /* ****************************************************************************************** */

    /**
     * Método que desactiva los botones de la vista.
     */
    private void desactivarBotonera() {

        for (Button boton : botonera) {
            boton.setEnabled(false);
            boton.setClickable(false);
            boton.setTextColor(getResources().getColor(R.color.Gris));
            boton.setAlpha(BOTON_DESACTIVADO_ALPHA);
        }

    }

    /**
     * Método para comprobar si se han introducido datos en el formulario.
     *
     * @return
     */
    private boolean hayDatosIntroducidos() {
        return !tv_codigoDeBarras.getText().toString().isEmpty() ||
                !tv_denominacion.getText().toString().isEmpty() ||
                !tv_marca.getText().toString().isEmpty() ||
                !tv_unidades.getText().toString().isEmpty() ||
                !tv_cantidad.getText().toString().isEmpty();
    }

    /**
     * Este método guarda el producto que se está editando.
     * Los datos para crear el producto se obtienen directamente del formualario.
     */
    private void guardarProducto() {

        try {

            // Si el código de barras está vacío, debemos obtener uno
            String id = (tv_codigoDeBarras.getText().toString().isEmpty()) ?
                    productoRepository.getIdAutomatico() : tv_codigoDeBarras.getText().toString();

            // Obtenemos el id de la marca
            // Si la marca no existe, se guarda y se obtiene el id
            String marca = tv_marca.getText().toString();
            int marcaInt = marcaRepository.getIdByName(marca);

            // Nos aseguramos de que las unidades y la cantidad tengan contenido
            String uStr = tv_unidades.getText().toString();
            int unidades = (uStr.isEmpty()) ? 0 : Integer.parseInt((String) uStr);

            String cStr = tv_cantidad.getText().toString();
            float cantidad = (cStr.isEmpty()) ? 0 : Float.parseFloat((String) cStr);

            // En la medida tenemos el índice que ocupa en el spinner, no el id
            int medidaInt = (int) spn_medida.getSelectedItemId();
            String medida = medidaList.get(medidaInt).getId();


            // Si estamos editando, se actualiza el producto, si no
            // se inserta uno nuevo
            if (productoEditando != null) {
                Log.d("LDLC", "DetalleProductoFragment.guardarProducto: actualizado");

            } else {
                // Es un producto nuevo
                Log.d("LDLC", "DetalleProductoFragment.guardarProducto: guardado" +
                        "\nid: " + id );
                ProductoController.insertProducto(
                        id,
                        tv_denominacion.getText().toString(),
                        marcaInt,
                        unidades,
                        medida,
                        cantidad,
                        context
                );
            }

            // Después de guardar el producto, vamos a sociarle las etiquetas
            asociarTagsAlProducto( id );


        } catch (Exception e) {
            Log.e("LDLC", "DetalleProductoFragment.guardarProducto" +
                    "\nError: " + e.getMessage());
        }

    }

    /**
     * Carga el producto que se está editando.
     *
     * @param producto
     */
    private void cargarProducto(ProductoEntity producto) {

        try {

            // Obtener las etiquetas que le corresponden
            // La lista se inicializa aquí porque tan solo se carga si se
            // está editando un producto
            tagProductoList = tagProductoController.getNombres(producto.getId());
            String tagString = "";
            for (String tag : tagProductoList) {
                tagString += tag + ", ";
            }
            text_tags.setText(tagString);

            tv_codigoDeBarras.setText(producto.getId());
            tv_denominacion.setText(producto.getDenominacion());

            String marca = marcaRepository.getNameById(producto.getMarca());
            tv_marca.setText(marca);

            tv_unidades.setText(String.valueOf(producto.getUnidades()));
            tv_cantidad.setText(String.valueOf(producto.getCantidad()));

            // El spinner debe recibir un entero indicando la posición en la colección
            spn_medida.setSelection(getPosicionMedida());

            habilitarBtnEliminar(true);
            habilitarBtnGuardar(true);

        } catch (Exception e) {
            Log.e("LDLC", "DetalleProductoFragment.cargarProducto id : " +
                    producto.getId() + "\n" + e.getMessage());

        }

    }

    /**
     * Devuelve la posción que ocupa la medida en la colección.
     *
     * @return
     */
    private int getPosicionMedida() {
        int index = 0;
        String m = productoEditando.getMedida();

        // medidaList contiene la colección de medidas que se ha cargado en el spinner
        while (index < medidaList.size()) {
            if (medidaList.get(index).getId().equals(m)) {
                return index;
            }
            index++;
        }

        return index;
    }

    /**
     * Método que guarda una etiqueta en la base de datos
     */
    private void addTag() {

        String texto = text_tags.getText().toString();
        String tag = tv_etiqueta.getText().toString().trim();

        btn_addTag.setEnabled(false);
        tv_etiqueta.setText("");
        tv_etiqueta.setHint("nuevo tag");

        texto += tag + ",";

        text_tags.setText(texto);

        // Guardar el tag, recibimos un id
        int idTag = tagRepository.insert(tag.trim());

        // Insertar el tag en la lista
        etiquetaList.add(tag.trim());

        Toast.makeText(context,
                "Guardado: " + tag + "(" + idTag + ")",
                Toast.LENGTH_SHORT).show();

    }

    /**
     * Asocia las etiquetas al producto.
     * Las etiquetas están registradas en text_tags
     */
    private void asociarTagsAlProducto( String idProducto) {

        // Obtener un array con las etiquetas
        String[] tags = text_tags.getText().toString().split(",");

        // Recorrer las etiquetas y asociarlas al producto
        for (String tag : tags) {
            int idTag = tagRepository.getIdByName(tag.trim());
            Log.d("LDLC", "DetalleProductoFragment.asociarTagsAlProducto\n" +
                    "tag: " + tag + " id: " + tags.length);
            tagProductoController.insert(
                    idProducto,
                    idTag
            );
        }

    }

}