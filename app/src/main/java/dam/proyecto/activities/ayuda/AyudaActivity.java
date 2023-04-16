package dam.proyecto.activities.ayuda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.ayuda.fragments.AyudaListaFragment;
import dam.proyecto.activities.ayuda.fragments.almacen.AyudaAlmacenAgregarFragment;
import dam.proyecto.activities.ayuda.fragments.almacen.AyudaAlmacenEditarFragment;
import dam.proyecto.activities.ayuda.fragments.almacen.AyudaAlmacenEliminarFragment;
import dam.proyecto.activities.ayuda.fragments.compras.AyudaComprasCrearFragment;
import dam.proyecto.activities.ayuda.fragments.compras.AyudaComprasDuplicarFragment;
import dam.proyecto.activities.ayuda.fragments.compras.AyudaComprasEliminarFragment;
import dam.proyecto.activities.ayuda.fragments.compras.AyudaComprasModificarFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaAbrirFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaAgregarFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaAplicarOfertaFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaCompararOtrosFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaCompartirFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaCompraPreciosFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaCrearFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaEditarPrecioFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaEliminarFragment;
import dam.proyecto.activities.ayuda.fragments.lista.AyudaListaEvolucionPrecioFragment;
import dam.proyecto.activities.ayuda.fragments.otros.AyudaOtrosVacioFragment;
import dam.proyecto.activities.ayuda.listeners.AyudaListener;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.databinding.ActivityAyudaBinding;

public class AyudaActivity extends AppCompatActivity implements AyudaListener, View.OnClickListener {

    ActivityAyudaBinding ayudaBinding;

    AyudaListener oyente;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ayudaBinding = ActivityAyudaBinding.inflate(getLayoutInflater());
        View view = ayudaBinding.getRoot();
        setContentView(view);

        ayudaBinding.navegador.setSelectedItemId(R.id.itInicio);

        // Floating Button para volver
        ayudaBinding.ayudaVolver.setOnClickListener(this);
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

    @Override
    public void cargarAyuda(View view) {

        Log.d("LDLC", "VISTA: " + view.toString());

        Fragment fragment;

        switch (view.getId()) {
            case R.id.ayudaListaCrear:
                fragment = new AyudaListaCrearFragment();
                break;
            case R.id.ayudaListaAbrir:
                fragment = new AyudaListaAbrirFragment();
                break;
            case R.id.ayudaListaAnadir:
                fragment = new AyudaListaAgregarFragment();
                break;
            case R.id.ayudaListaEliminar:
                fragment = new AyudaListaEliminarFragment();
                break;
            case R.id.ayudaListaEditar:
                fragment = new AyudaListaEditarPrecioFragment();
                break;
            case R.id.ayudaListaOfertas:
                fragment = new AyudaListaAplicarOfertaFragment();
                break;
            case R.id.ayudaListaComparaPrecios:
                fragment = new AyudaListaCompraPreciosFragment();
                break;
            case R.id.ayudaListaEvolucionPrecio:
                fragment = new AyudaListaEvolucionPrecioFragment();
                break;
            case R.id.ayudaListaCompararOtros:
                fragment = new AyudaListaCompararOtrosFragment();
                break;
            case R.id.ayudaListaCompartrir:
                fragment = new AyudaListaCompartirFragment();
                break;
            case R.id.ayudaComprasCrear:
                fragment = new AyudaComprasCrearFragment();
                break;
            case R.id.ayudaComprasModificar:
                fragment = new AyudaComprasModificarFragment();
                break;
            case R.id.ayudaComprasDuplicar:
                fragment = new AyudaComprasDuplicarFragment();
                break;
            case R.id.ayudaComprasBorrar:
                fragment = new AyudaComprasEliminarFragment();
                break;
            case R.id.ayudaAlmacenAgregar:
                fragment = new AyudaAlmacenAgregarFragment();
                break;
            case R.id.ayudaAlmacenEditar:
                fragment = new AyudaAlmacenEditarFragment();
                break;
            case R.id.ayudaAlmacenEliminar:
                fragment = new AyudaAlmacenEliminarFragment();
                break;
            default:
                fragment = new AyudaOtrosVacioFragment();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ayudaContenedor, fragment)
                .commit();
    }

    @Override
    public void onClick(View view) {

        if( view.getId() == ayudaBinding.ayudaVolver.getId() ){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ayudaContenedor, new AyudaListaFragment())
                    .commit();
        }

    }
}