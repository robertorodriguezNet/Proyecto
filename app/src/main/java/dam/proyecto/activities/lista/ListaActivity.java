package dam.proyecto.activities.lista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import dam.proyecto.R;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.databinding.ActivityListaBinding;
import dam.proyecto.utilities.Preferencias;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.17
 * @since 17/02/2023
 */
public class ListaActivity extends AppCompatActivity {

    private final String TAG = "ListaActivity";

    ActivityListaBinding bindingLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener la vista mediante ViewBinding
        bindingLista = ActivityListaBinding.inflate(getLayoutInflater());
        View view = bindingLista.getRoot();
        setContentView(view);

        bindingLista.navegador.setSelectedItemId(R.id.itLista);

        // Si en la preferencia de lista abierta no hay nada,
        // cargamos la lista de compras
        String compraId =  Preferencias.getListaAbiertaId(this);
        Log.d("PREF", "Preferencia recuperada: " + compraId );
        if ( compraId  == null ){
            abrirCompras();
        }

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

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                irACompras();
            }
        });
        builder.setView( v );

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void irACompras(){
        startActivity(new Intent( this, ComprasActivity.class));
    }

}