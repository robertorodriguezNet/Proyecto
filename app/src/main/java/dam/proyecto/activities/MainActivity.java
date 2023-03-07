package dam.proyecto.activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.controllers.ComercioController;
import dam.proyecto.database.data.Ejemplos;
import dam.proyecto.database.data.ExportDB;
import dam.proyecto.database.data.ImportDB;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.databinding.ActivityMainBinding;


/**
 *
 * @author  Roberto Rodríguez Jiménez
 * @since 17/02/2023
 * @version 2023.02.17
 */
public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    ActivityMainBinding bindingMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargamos los datos de ejemplo
        // Hay que pasarle el contexto
        Log.d("BD", "MainActivity.class: antes de cargar datos" );
//        Ejemplos.cargarDatos( this, this );

        // Obtener la vista mediante ViewBinding
        bindingMain = ActivityMainBinding.inflate( getLayoutInflater() );
        View view = bindingMain.getRoot();
        setContentView( view );

        bindingMain.navegador.setSelectedItemId( R.id.itInicio );

        // Oyente para el navegador
        bindingMain.navegador.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.itAlamacen:
                            startActivity(new Intent(this, AlmacenActivity.class ));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.itInicio:
                            return true;
                        case R.id.itLista:
                            startActivity(new Intent( this, ListaActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.itCompras:
                            startActivity(new Intent( this, ComprasActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                    }
                    return false;
                }
        );

    }

    /**
     * Método para crear el menú en el ToolBar
     *
     * @param menu el menú que será cargado
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_principal, menu);
        return true;
    }

    /**
     * Método que realiza la acción como respuesta
     * al evento sobre un ítem del menú
     * @param item ítem que recibe la acción
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId() ){
            case R.id.mp_it_acercade:
                Toast.makeText(this, "acerca de...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mp_it_ayuda:
                Toast.makeText(this, "ayuda", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mp_it_comercio:
                    abrirComercio();
                break;
            case R.id.mp_it_marca:
                Toast.makeText(this, "añadir marca", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mp_it_exportarBD:
                    ExportDB.exportDB( this );
                break;
            case R.id.mp_it_impoprtarBD:BD:
                    ImportDB.importDB( this );
                break;

        }
        return true;
    }

    /**
     * Abre el diálogo para insertar un nuevo comercio
     */
    private void abrirComercio(){

        LayoutInflater inflater = this.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        View view = inflater.inflate( R.layout.dialog_comercio, null );

        TextView input = (TextView) view.findViewById( R.id.dc_inp_comercio );

        builder.setTitle("Nuevo comercio");
        builder.setMessage("El nombre debe tener entre 3 y 16 caracteres");
        builder.setView( view );
        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ComercioController controller = new ComercioController(getApplicationContext());
                controller.addComercio( input.getText().toString() );
            }
        });

        builder.create();
        builder.show();

    }

}