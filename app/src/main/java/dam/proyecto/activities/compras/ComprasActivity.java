package dam.proyecto.activities.compras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

    private final String TAG = "ComprasActivity";
    private ListenerCompras listenerCompras;

    ActivityComprasBinding bindingCompras;

    // Datos de las compras (NombreCompraEntity)
    private ArrayList<NombreCompraEntity> dataNombreCompra;

    // Componentes
    private EditText inputNombre;                       // Input para indicar el nombre de la compra
    private ImageView checkAceptar;                                  // Check para aceptar el nombre

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
        AdaptadorCompras adaptadorCompras = new AdaptadorCompras(
                this,
                R.layout.item_compra,
                dataNombreCompra,
                new ListenerCompras() {
                    @Override
                    public void cli_img_deleteOnClik(NombreCompraEntity compra) {
                        borrarCompra( compra );
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
    public void borrarCompra(NombreCompraEntity compra) {
        Toast.makeText(this, "Eliminar una compra", Toast.LENGTH_SHORT).show();
    }

    /**
     * Edita una compra
     * @param compra
     */
    public void editarCompra(NombreCompraEntity compra) {
        Toast.makeText(this, "Editar una compra", Toast.LENGTH_SHORT).show();
    }

    /**
     * Crear una compra
     */
    private void crearCompra( View view ){
        Toast.makeText(ComprasActivity.this, "Crear la compra", Toast.LENGTH_SHORT).show();
    }
}