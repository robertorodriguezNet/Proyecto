package dam.proyecto.activities.compras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.databinding.ActivityAlmacenBinding;
import dam.proyecto.databinding.ActivityComprasBinding;

public class ComprasActivity extends AppCompatActivity {

    private final String TAG = "ComprasActivity";

    ActivityComprasBinding bindingCompras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener la vista mediante ViewBinding
        bindingCompras = ActivityComprasBinding.inflate(getLayoutInflater());
        View view = bindingCompras.getRoot();
        setContentView(view);

        bindingCompras.navegador.setSelectedItemId(R.id.itAlamacen);

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
}