package dam.proyecto.activities.lista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dam.proyecto.R;

import android.content.Intent;
import android.view.View;

import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.databinding.ActivityListaBinding;

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
                    }
                    return false;
                }
        );

    }


}