package dam.proyecto.activities.marcablanca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.controllers.ComercioController;
import dam.proyecto.controllers.MarcaBlancaController;
import dam.proyecto.controllers.MarcaController;

public class MarcaBlancaActivity extends AppCompatActivity {

    // Campos de búsqueda
    private AutoCompleteTextView inpComercio;     // Campo de texto en el que introducir el comercio
    private AutoCompleteTextView inpMarca;           // Campo de texto en el que introducir la marca
    private ImageButton btnLimpiar;
    private ImageButton btnAsociar;

    private Button btnSalir;                                     // Botón para salir de la actividad

    // Listas
    private ListView listaAsociadas;                // Lista de marcas asociadas al comercio elegido

    // Controladores
    private ComercioController comercioController;
    private MarcaController marcaController;
    private MarcaBlancaController marcaBlancaController;

    // ---------------------------------------------------------------------------------------------
    // -- FUNCIONES --------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca_blanca);

        inicializarControladores();                           // Controladores para la base de datos
        inicializarComponentes();                                   // Inicializamos los componentes

        establecerOyente();           // Establece los oyentes para los componentes que lo necesiten

        // Necesitamos un adaptador para que el campo de búsqueda muestre el listado de comercios
        // El adaptador necesita el listado de comercios
        ArrayAdapter<String> adapterComercios = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                comercioController.getAllNames()
        );
        inpComercio.setAdapter(adapterComercios);

        ArrayAdapter<String> adapterMarcas = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                marcaController.getNombres()
        );
        inpMarca.setAdapter(adapterMarcas);


    }

    /**
     * Establece los oyentes para cada componente que lo requiera
     */
    private void establecerOyente() {

        btnLimpiar.setOnClickListener(v -> limpiarCampoBusqueda());   // Limpiar campo de búsqueda
        btnAsociar.setOnClickListener(v -> iniciarProceso());  // Iniciar el proceso de asociación
        btnSalir.setOnClickListener(v -> salir());                        // Salir de la actividad

    }

    /**
     * Inicializar los componentes de la UI
     */
    private void inicializarComponentes() {

        // Campos de búsqueda
        inpComercio = this.findViewById(R.id.amb_inp_comercio);
        inpMarca = this.findViewById(R.id.amb_inp_marca);
        btnLimpiar = this.findViewById(R.id.amb_btn_clear);
        btnAsociar = this.findViewById(R.id.amb_btn_start);

        listaAsociadas = this.findViewById( R.id.amb_lv_listaComercios );

        btnSalir = this.findViewById(R.id.amb_btn_salir);

    }

    /**
     * Inicializa los controladores que permiten operaciones sobre
     * las entidades de la base de datos.
     */
    private void inicializarControladores() {
        comercioController = new ComercioController(this);
        marcaController = new MarcaController(this);
        marcaBlancaController = new MarcaBlancaController( this );
    }

    /**
     * Inicia el proceso de asociación de marcas.
     * El evento es lanzado por el botón de búsqueda y debe cargar en el listado de
     * marcas todas las marcas.
     * En ellistado de asociadas se muestran las marcas asociadas al comercio seleccionado.
     */
    private void iniciarProceso() {

        try {

            // Obtener el id del comercio y de la marca
            int idComercio = comercioController.getIdByName(inpComercio.getText().toString());
            int idMarca = marcaController.getIdByName(inpMarca.getText().toString());

            if ((idComercio > 1) && (idMarca > 1)) {
                // Los datos introducidos son correctos

                // Obtener las marcas asociadas al comercio
                ArrayList<Integer> idsMarcas = marcaBlancaController.getMarcasByComercio( idComercio );

                ArrayList<String> nombresMarca = new ArrayList<>();
                idsMarcas.forEach( id -> nombresMarca.add(marcaController.getNameById( id )));

                // Comprobar que la asociación no exista
                if( marcaBlancaController.exists( idComercio, idMarca )){
                    Toast.makeText(this, "la asociación existe", Toast.LENGTH_SHORT).show();
                }else{

                    // La asociación no existe, sequimos adelante
                    marcaBlancaController.insert( idMarca, idComercio );
                    nombresMarca.add( inpMarca.getText().toString() );
                }

                // Tanto si se hace algo como si no, cargamos las asociaciones
                ArrayAdapter<String> adapterAsociados = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        nombresMarca
                );
                listaAsociadas.setAdapter( adapterAsociados );

            } else {
                Toast.makeText(this, "nada que hacer", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Toast.makeText(this, "Algún dato es incorrecto", Toast.LENGTH_SHORT).show();
            Log.e("LDLC","MarcaBlancaActivity: " + e.getMessage() );
        }

    }

    private void limpiarCampoBusqueda() {
        inpComercio.setText("");
    }


    /**
     * Vuelve a la actividad principal
     */
    private void salir() {
        startActivity(new Intent(this, MainActivity.class));
    }
}