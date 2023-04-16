package dam.proyecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.ayuda.AyudaActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.activities.marcablanca.MarcaBlancaActivity;
import dam.proyecto.controllers.ComercioController;
import dam.proyecto.database.data.Ejemplos;
import dam.proyecto.database.data.ExportDB;
import dam.proyecto.database.data.ImportDB;
import dam.proyecto.databinding.ActivityMainBinding;


/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.17
 * @since 17/02/2023
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bindingMain;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cargamos los datos de ejemplo
        // Hay que pasarle el contexto
        Ejemplos.cargarDatos(this, this);

        // Obtener la vista mediante ViewBinding
        bindingMain = ActivityMainBinding.inflate(getLayoutInflater());
        View view = bindingMain.getRoot();
        setContentView(view);

        bindingMain.navegador.setSelectedItemId(R.id.itInicio);

        // Oyente para el navegador
        bindingMain.navegador.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.itAlamacen:
                            startActivity(new Intent(this, AlmacenActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.itInicio:
                            return true;
                        case R.id.itLista:
                            startActivity(new Intent(this, ListaActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.itCompras:
                            startActivity(new Intent(this, ComprasActivity.class));
                            overridePendingTransition(0, 0);
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
     * @return true si el componenete está cargado
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }


    /**
     * Método que realiza la acción como respuesta
     * al evento sobre un ítem del menú
     *
     * @param item ítem que recibe la acción
     * @return true si no hay error
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mp_it_acercade:
                startActivity( new Intent( this, AcercaDeActivity.class ));
                break;
            case R.id.mp_it_marcaBlanca:
                editarMarcaBlanca();
                break;
            case R.id.mp_it_ayuda:
                startActivity( new Intent( this, AyudaActivity.class ));
                break;
            case R.id.mp_it_comercio:
                abrirComercio();
                break;
            case R.id.mp_it_exportarBD:
                ExportDB.exportDB(this);
                break;
            case R.id.mp_it_impoprtarBD:
                ImportDB.importDB(this);
                break;

        }
        return true;
    }

    /**
     * Abre el diálogo para insertar un nuevo comercio
     */
    private void abrirComercio() {

        LayoutInflater inflater = this.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = inflater.inflate(R.layout.dialog_comercio, null);

        TextView input = view.findViewById(R.id.dc_inp_comercio);

        builder.setTitle("Nuevo comercio");
        builder.setMessage("El nombre debe tener entre 3 y 16 caracteres");
        builder.setView(view);
        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton("Aceptar", (dialogInterface, i) -> {
            ComercioController controller = new ComercioController(getApplicationContext());
            controller.addComercio(input.getText().toString());
        });

        builder.create();
        builder.show();

    }

    /**
     * Carga la actividad para editar las marcas blancas
     */
    private void editarMarcaBlanca(){
        startActivity( new Intent( this,  MarcaBlancaActivity.class ) );
    }

}