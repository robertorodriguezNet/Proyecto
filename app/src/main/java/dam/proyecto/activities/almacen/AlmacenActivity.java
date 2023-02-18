package dam.proyecto.activities.almacen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.databinding.ActivityAlmacenBinding;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.17
 * @since 17/02/2023
 */
public class AlmacenActivity extends AppCompatActivity {

    private final String TAG = "AlmacenActivity";

    ActivityAlmacenBinding  bindingAlmacen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener la vista mediante ViewBinding
        bindingAlmacen = ActivityAlmacenBinding.inflate(getLayoutInflater());
        View view = bindingAlmacen.getRoot();
        setContentView(view);

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
                    }
                    return false;
                }
        );

    }
}