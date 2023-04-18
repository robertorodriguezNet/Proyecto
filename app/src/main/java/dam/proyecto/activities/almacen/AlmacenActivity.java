package dam.proyecto.activities.almacen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.listeners.AlmacenListener;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.controllers.ListaController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.databinding.ActivityAlmacenBinding;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.17
 * @since 17/02/2023
 */
public class AlmacenActivity extends AppCompatActivity implements AlmacenListener {

    ActivityAlmacenBinding  bindingAlmacen;

    // Usado en addProductoALaLista()
    // Índice de las opciones de selección del precio
    // Es necesario para poder comunicar el tipo de precio entre los listeners de diálogo
    // que se abre al intentar agregar un producto a la lista abierta
    private int posicion = 0;



    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Obtener la vista mediante ViewBinding
        bindingAlmacen = ActivityAlmacenBinding.inflate(getLayoutInflater());
        View view = bindingAlmacen.getRoot();
        setContentView(view);

        bindingAlmacen.navegador.setVisibility( View.VISIBLE );
        bindingAlmacen.navegador.setSelectedItemId(R.id.itAlamacen);

        // Oyente para el navegador
        bindingAlmacen.navegador.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.itAlamacen:
                            return true;
                        case R.id.itInicio:
                            startActivity(new Intent( this, MainActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.itLista:
                            startActivity(new Intent( this, ListaActivity.class));
                            overridePendingTransition(0, 0);
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

    /* ****************************************************************************************** */
    /* ***** IMPLEMENTACION DE LOS LISTENERS **************************************************** */
    /* ****************************************************************************************** */
    /**
     * Añade un nuevo producto al almacén.
     * Para ello cargamos el detalle de un producto, que estará en blanco
     */
    @Override
    public void addNuevoProducto() {

        Log.d("LDLC", "AlmacenActivity llamando a Detalle" );

        Fragment fragment = new DetalleProductoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.almacenContenedor, fragment )
                .commit();

    }

    /**
     * Edita el producto seleccionado de la lista del almancén.
     * Cargamos DetalleProductoFragment, pero le pasamos el id
     * para que lo recargue.
     * @param producto que se quiere editar
     */
    public void editarProducto(ProductoEntity producto){

        Fragment fragment = new DetalleProductoFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable( "producto", producto );
        fragment.setArguments( bundle );

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.almacenContenedor, fragment )
                .commit();

    }

    /**
     * Añadimos el producto a la lista de la compra que esté activa.
     * Antes de agregar el producto, debemos saber qué precio va a mostrar:
     * [actual(0)|global|comercio]
     *
     * @param producto producto que queremos añadir
     */
    @Override
    public void addProductoALaLista(ProductoEntity producto) {

        // Controlador de la lista
        ListaController listaController = new ListaController( this);

        // Comprobamos que haya una lista abierta
        if( listaController.isListaAbierta() ){

            // Los precios los pedimos al controlador del producto
            // El precio global obtiene el último precio conocido, pero
            // ni indica el comercio en que se compró
            // [0] -> actual
            // [1] -> global
            // [2] -> comercio
            float [] precios = {
                    0.0f,
                    ProductoController.getUltimoPrecio( producto.getId(),this ),
                    ProductoController
                    .getUltimoPrecio( producto.getId(),
                            listaController.getIdComercio(),
                            this)
            };

            // Mostrar un listado con las tres opciones
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            String [] opciones = {
                    "Sin precio: 0.00 €",
                    "Último conocido: " + precios[1] + " €",
                    "Último de:\n " + listaController.getComercio().toUpperCase()
                            + ": " + precios[2] + " €",
            };

            builder.setTitle( "Selecciona una opción")
                    .setSingleChoiceItems(opciones, posicion, (dialogInterface, i) -> posicion = i)
                    .setPositiveButton("Aceptar", (dialogInterface, i) -> listaController.addProducto( producto, precios[posicion] ))
                    .setNegativeButton("Cancelar", null);


            builder.create();
            builder.show();

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle( "No hay una lista abierta")
                    .setNegativeButton( "Cerrar", null)
                    .setMessage( "No se puede añadir el producto a una lista porque no " +
                            "hay ninguna lista abierta" );
            builder.create();
            builder.show();
        }

    }


}