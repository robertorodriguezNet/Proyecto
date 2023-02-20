package dam.proyecto.activities.compras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.adapters.AdaptadorCompras;
import dam.proyecto.activities.compras.listener.ListenerCompras;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.databinding.ActivityAlmacenBinding;
import dam.proyecto.databinding.ActivityComprasBinding;
import dam.proyecto.utilities.Fecha;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.19
 * @since 19/02/2023
 */
public class ComprasActivity extends AppCompatActivity {

    private final String TAG = "CA";
    private ListenerCompras listenerCompras;

    ActivityComprasBinding bindingCompras;

    // Datos de las compras (NombreCompraEntity)
    private ArrayList<NombreCompraEntity> dataNombreCompra;

    // Componentes
    private EditText inputNombre;                       // Input para indicar el nombre de la compra
    private ImageView checkAceptar;                                  // Check para aceptar el nombre

    AdaptadorCompras adaptadorCompras;                  // Adapatador para el listado de las compras

    /*********************************************************************************************/
    /***** FUNCIONES *****************************************************************************/
    /*********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener la vista mediante ViewBinding
        bindingCompras = ActivityComprasBinding.inflate(getLayoutInflater());
        View view = bindingCompras.getRoot();
        setContentView(view);
        
        bindingCompras.navegador.setSelectedItemId(R.id.itCompras);


        // == Zona del campo de texto ==============================================================
        // Establecemos el hint para para el input del nuevoNombre        
        bindingCompras.acInpNuevoNombre.setHint( Fecha.getNuevaFecha() + " o escribe un nombre");

        // Oyente para el check
        bindingCompras.acImgCrearCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearCompra( view );
            }
        });

        // == Zona de la lista =====================================================================
        // Obtener el listado de las compras
        dataNombreCompra = new NombreCompraRepository( this).getAll();

        // Crear el adaptador que vamos a pasar al ListView
        adaptadorCompras = new AdaptadorCompras(
                this,
                R.layout.item_compra,
                dataNombreCompra,
                new ListenerCompras() {
                    @Override
                    public void cli_img_deleteOnClik(NombreCompraEntity compra, int posicion) {
                        borrarCompra( compra, posicion );
                    }

                    @Override
                    public void cli_lly_datosCompraOnClick(NombreCompraEntity compra) {
                        editarCompra( compra );
                    }
                }
        );
        bindingCompras.listaCompras.setAdapter( adaptadorCompras );


        // Oyente para el navegador
        bindingCompras.navegador.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.itAlamacen:
                            startActivity(new Intent(this, AlmacenActivity.class ));
                            overridePendingTransition(0,0);
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
                            return true;

                    }
                    return false;
                }
        );

    }

    /**********************************************************************************************/
    /***** MÉTODO OYENTES *************************************************************************/
    /**********************************************************************************************/
    /**
     * Borrar una compra de la lista.
     *
     * @param compra la compra que se quiere borrar
     */
    public void borrarCompra(NombreCompraEntity compra, int posicion) {

        // 1.- Borrar la compra de dataNombreCompra
        dataNombreCompra.remove( compra );

        // 2.- Borrar la compra de la base de datos
        NombreCompraRepository repository = new NombreCompraRepository( this );
        repository.delete( compra );

        // 3.- Informar al adptador del cambio
        adaptadorCompras.notifyDataSetChanged();

        Toast.makeText(this,
                "Compra " + compra.getNombre() + " eliminada",
                Toast.LENGTH_SHORT).show();

        Log.d( TAG, "Borrar la poscion: " + posicion
            + "\nque se corresponde con : " + compra.toString()
            + "\nLa posición es ocupada por: " + dataNombreCompra.get( posicion ));
    }

    /**
     * Edita una compra
     * @param compra
     */
    public void editarCompra(NombreCompraEntity compra) {
        Toast.makeText(this, "Editar una compra", Toast.LENGTH_SHORT).show();
    }

    /**
     * Crear una compra.
     * En la vista recibimos el nombre que se le quiere dar a la nueva compra.
     * Este nombre puede estar en blanco o puede estar repetido.
     * Al crear la compra, se genera un id único con formato: aammddhhmm
     * Si el nombre de la compra se ha dejado en blanco, se le asigna el id.
     *
     * @param view la vista del formualrio
     */
    private void crearCompra( View view ){

        // Crear una instancia del repositorio de NombreCompra
        NombreCompraRepository repository = new NombreCompraRepository( this );

        // Obtenemos el id para la nueva lista
        String id = Fecha.getNuevaFecha();

        // Nombre de la lista del formualrio
        String nombre = bindingCompras.acInpNuevoNombre.getText().toString();

        // 1.- Comprobamos si el id ya existe
        // Comprobamos si el nombre ya existe
        if (!repository.existsIdDeLaCompra(id)) {

            // El id no existe

            // Si no hay nombre o tiene menos de 3 caracteres, se toma el id
            String nombreDeLaLista = (nombre.length() >= 3) ? nombre : id;


            // Creamos el objeto NombreCompra
            // Por defecto se crea el comercio 1 -> ""
            NombreCompraEntity compra = new NombreCompraEntity(id, nombreDeLaLista, 1);

            // 2.- Lo agregamos al listado
            dataNombreCompra.add( 0, compra );

            // 3.- Lo guardamos en la base de datos en la
            // posición 0 para que se muestre al inicio
            repository.insert( compra );

            // 4.- Notificamos el cambio al adaptador
            adaptadorCompras.notifyDataSetChanged();

            Toast.makeText(ComprasActivity.this,
                    "Creada la compra " + nombreDeLaLista,
                    Toast.LENGTH_SHORT).show();

            Log.d( TAG, "Creando una compra: " + nombreDeLaLista );

        }else{

            int minuto = Integer.parseInt( id.substring(8,10) );
            minuto++;
            String hora = id.substring(6,8) + ":" + minuto;

            // El nombre ya existe
            LayoutInflater inflater = this.getLayoutInflater();
            AlertDialog.Builder builder = new AlertDialog.Builder( this );
            View v = inflater.inflate( R.layout.alert_nueva_compra, null );

            TextView solucion = v.findViewById(R.id.anc_tv_solucion);
            solucion.setText( solucion.getText() + " " + hora);

            builder.setPositiveButton( "Aceptar", null );
            builder.setView( v );

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}