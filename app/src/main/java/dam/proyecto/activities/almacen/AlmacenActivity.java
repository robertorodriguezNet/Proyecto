package dam.proyecto.activities.almacen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.DetalleListaFragment;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.databinding.ActivityAlmacenBinding;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.17
 * @since 17/02/2023
 */
public class AlmacenActivity extends AppCompatActivity implements AlmacenListener {

    private final String TAG = "AlmacenActivity";

    ActivityAlmacenBinding  bindingAlmacen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
     * @param producto
     */
    public void editarProducto(ProductoEntity producto){


        Log.d("LDLC", "AlmacenActivity editando un producto" );

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
     * @param producto producto que queremos añadir
     */
    @Override
    public void addProductoALaLista(ProductoEntity producto) {
        // El controlador de la compra se encarga de hacerlo
        CompraController.addProductoALaCompra( producto, this );
    }

}