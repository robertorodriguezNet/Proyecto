package dam.proyecto.activities.ayuda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.ayuda.fragments.AyudaAgregarProductoFragment;
import dam.proyecto.activities.ayuda.fragments.AyudaCrearListaFragment;
import dam.proyecto.activities.ayuda.fragments.AyudaEliminarListaFragment;
import dam.proyecto.activities.ayuda.fragments.AyudaEliminarProductoFragment;
import dam.proyecto.activities.ayuda.fragments.AyudaListaFragment;
import dam.proyecto.activities.ayuda.fragments.AyudaVacioFragment;
import dam.proyecto.activities.ayuda.listeners.AyudaListener;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.databinding.ActivityAyudaBinding;

public class AyudaActivity extends AppCompatActivity implements AyudaListener {

    ActivityAyudaBinding ayudaBinding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ayudaBinding = ActivityAyudaBinding.inflate(getLayoutInflater());
        View view = ayudaBinding.getRoot();
        setContentView(view);

        ayudaBinding.navegador.setSelectedItemId(R.id.itInicio);

        // Floating Button para volver
        ayudaBinding.ayudaVolver.setOnClickListener(this::cargarFragment);

        // Oyente para el navegador
        ayudaBinding.navegador.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.itAlamacen:
                            startActivity(new Intent(this, AlmacenActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.itInicio:
                            startActivity(new Intent(this, MainActivity.class));
                            overridePendingTransition(0, 0);
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void cargarFragment(View view) {

//        Fragment fragment;
//
//        switch (view.getId()) {
//            case R.id.fc_tv_crearLista:
//                fragment = new AyudaCrearListaFragment();
//                break;
//            case R.id.fc_tv_eliminarLista:
//                fragment = new AyudaEliminarListaFragment();
//                break;
//            case R.id.fc_tv_agregarProducto:
//                fragment = new AyudaAgregarProductoFragment();
//                break;
//            case R.id.fc_tv_eliminarProducto:
//                fragment = new AyudaEliminarProductoFragment();
//                break;
//            case R.id.fa_fab_addProductoAlmacen:
//                fragment = new AyudaListaFragment();
//                break;
//            default:
//                fragment = new AyudaVacioFragment();
//        }
//
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.ayudaContenedor, fragment)
//                .commit();
    }
}