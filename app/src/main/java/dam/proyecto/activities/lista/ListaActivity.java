package dam.proyecto.activities.lista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import dam.proyecto.R;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.listeners.ListaListener;
import dam.proyecto.controllers.ListaController;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.databinding.ActivityListaBinding;
import dam.proyecto.utilities.Preferencias;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.17
 * @since 17/02/2023
 */
public class  ListaActivity extends AppCompatActivity implements ListaListener {

    ActivityListaBinding bindingLista;
    ListaController listaController;                                      // Controlador de la lista

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener la vista mediante ViewBinding
        bindingLista = ActivityListaBinding.inflate(getLayoutInflater());
        View view = bindingLista.getRoot();

        listaController = new ListaController( this );

        // -- Navegador ----------------------------------------------------------------------------
        bindingLista.navegador.setSelectedItemId(R.id.itLista);

        // Oyente para el navegador
        bindingLista.navegador.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.itAlamacen:
                            startActivity(new Intent(  this, AlmacenActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.itInicio:
                            startActivity(new Intent( this, MainActivity.class));
                            overridePendingTransition(0,0);
                            return true;
                        case R.id.itLista:
                            return true;
                        case R.id.itCompras:
                            startActivity(new Intent( this, ComprasActivity.class));
                            overridePendingTransition(0,0);
                            return true;

                    }
                    return false;
                }
        );
        // -- Fin del navegador --------------------------------------------------------------------

        // Tenemos dos opciones al llegar a la actividad:
        // .- Existe una preferencia con la lista abierta
        // .- No existe una preferencia con la lista abierta

        // Obtenemos la preferencia
        String compraId =  Preferencias.getListaAbiertaId(this);

        // La preferencia existe: cargamos la vista
        if ( compraId  != null ){
            setContentView(view);
        } else {
            abrirCompras();
        }

    }


    /**
     * Método que abre la actividad Compras en caso de que no se
     * esté editando ninguna lista.
     */
    private void abrirCompras() {

        // Primero mostramos un mensaje
        LayoutInflater inflater = this.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        View v = inflater.inflate( R.layout.alert_lista_vacia, null );

        builder.setPositiveButton("Aceptar", (dialogInterface, i) -> irACompras());
        builder.setView( v );

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    /**
     * Cargar la actividad Compras
     */
    private void irACompras(){
        startActivity(new Intent( this, ComprasActivity.class));
    }

    // -- LISTENERS --------------------------------------------------------------------------------

    /**
     * Cargamos el detalle de una compra (producto en la lista, no NombreCompra)
     * Hemos de pasarle el id de la compra a vista detalle.
     * Necesitamos el id de CompraEntity para poder acceder a los datos del
     * producto en la compra.
     * @param compra es la compra (producto comprado) sobre la la que se clicado
     */
    @Override
    public void onProductoCompradoClick( CompraEntity compra ) {

        Fragment fragment = new DetalleListaFragment();
        Bundle bundle = new Bundle();
        bundle.putString( "id", compra.getId() );
        fragment.setArguments( bundle );

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listaContenedor, fragment )
                .commit();

    }

    /**
     * Un producto comprado recibe un LongClick para ser borrado.
     * @param compra que se quiere borrar
     */
    @Override
    public void onProductoCompradoLongClick(CompraEntity compra) {

        ProductoEntity producto = ProductoController
                    .getById(
                            compra.getProducto(),
                            this
                );

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( "Borrar producto");
        builder.setMessage( "¿Borrar " + producto.getDenominacion() + "?" );
        builder.setPositiveButton("Aceptar", (dialogInterface, i) -> elimiarProductoDeLaCompra( compra ));
        builder.setNegativeButton( "Cancelar", null );
        builder.create();
        builder.show();

    }

    /**
     * Elimina un producto de la lista de la compra que se está editanco
     * @param compra producto que se quiere borrar
     */
    private void elimiarProductoDeLaCompra( CompraEntity compra){

        // Pedimos al controlador de la lista que elimine la compra del producto
        listaController.delete( compra );

        // Recargamos el fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.listaContenedor, new ListaListaFragment() )
                .commit();

    }

    @Override
    public void compararComercios(){

        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.listaContenedor, new ComparativaComerciosFragment() )
                .commit();

    }
}