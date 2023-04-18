package dam.proyecto.activities.ayuda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
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

        // Botón "Volver"
        if( getScreenMode().equals( "movil_portrait")){
            ayudaBinding.ayudaVolver.setOnClickListener(this);
        }

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

        // Fragment que se carga de inicio en el fragment detalle
        Fragment fragment = new AyudaDetalleFragment();
        Bundle bundle = new Bundle();

        String file = "nodisponible";
        String title = "Ayuda no disponible";

        switch (view.getId()) {
            case R.id.ayudaListaCrear:
            case R.id.ayudaComprasCrear:
                file = "ayuda_lista_crear";
                title = "Crear una nueva lista";
                break;
            case R.id.ayudaListaAbrir:
                file = "ayuda_lista_abrir";
                title = "Abrir una lista de la compra";
                break;
            case R.id.ayudaListaAnadir:
                file = "ayuda_lista_agregar";
                title = "Agregar un producto a la lista";
                break;
            case R.id.ayudaListaEliminar:
                file = "ayuda_lista_eliminar";
                title = "Eliminar un producto de la lista";
                break;
            case R.id.ayudaListaEditar:
                file = "ayuda_lista_editar";
                title = "Editar cantidad y precio";
                break;
            case R.id.ayudaListaOfertas:
                file = "ayuda_lista_ofertas";
                title = "Aplicar ofertas";
                break;
            case R.id.ayudaListaComparaPrecios:
                file = "ayuda_lista_comparar_precios";
                title = "Comparar lista en otros comercios";
                break;
            case R.id.ayudaListaEvolucionPrecio:
                file = "ayuda_lista_evolucion";
                title = "Evolución del precio";
                break;
            case R.id.ayudaListaCompararOtros:
                file = "ayuda_lista_comparar_otros";
                title = "Comparar lista en otros comercios";
                break;
            case R.id.ayudaListaCompartrir:
                file = "ayuda_lista_compartir";
                title = "Compartir una lista";
                break;
            case R.id.ayudaComprasModificar:
                file = "ayuda_compras_modificar";
                title = "Cambiar la fecha de una compra";
                break;
            case R.id.ayudaComprasDuplicar:
                file = "ayuda_compras_duplicar";
                title = "Duplicar una compra";
                break;
            case R.id.ayudaComprasBorrar:
                file = "ayuda_compras_eliminar";
                title = "Eliminar una compra";
                break;
            case R.id.ayudaAlmacenAgregar:
                file = "ayuda_almacen_agregar";
                title = "Agregar un producto al almacén";
                break;
            case R.id.ayudaAlmacenEditar:
                file = "ayuda_almacen_editar";
                title = "Editar un producto";
                break;
            case R.id.ayudaAlmacenEliminar:
                file = "ayuda_almacen_eliminar";
                title = "Eliminar un producto del almacén";
                break;
            case R.id.ayudaOtrosCrearComercio:
                file = "ayuda_otros_crear_comercio";
                title = "Crear un comercio";
                break;
            case R.id.ayudaOtrosAsociar:
                file = "ayuda_otros_asociar";
                title = "Asociar un comercio a una marca";
                break;
            default:
        }

        bundle.putString("file",file);
        bundle.putString("title",title);
        fragment.setArguments( bundle );

        if( getScreenMode().equals("movil_landscape")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ayudaContenedorDetalleMovilLandscape, fragment)
                    .commit();
        }else if( getScreenMode().equals("tablet_portrait")){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ayudaContenedorDetalleTabletPortrait, fragment)
                    .commit();
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ayudaContenedor, fragment)
                    .commit();
        }

    }

    /**
     * Devuelve el modo de pantalla
     * @return el modo de mantalla
     */
    private String getScreenMode() {

        String modo = "movil_portrait";

        FragmentContainerView movilLandscape = (FragmentContainerView) findViewById(R.id.ayudaContenedorDetalleMovilLandscape);
        FragmentContainerView tabletPortrait = (FragmentContainerView) findViewById(R.id.ayudaContenedorDetalleTabletPortrait);
        if( movilLandscape != null  ){
            modo = "movil_landscape";
        }else if( tabletPortrait != null ){
            modo = "tablet_portrait";
        }

        return modo;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == ayudaBinding.ayudaVolver.getId()) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ayudaContenedor, new AyudaListaFragment())
                    .commit();
        }

    }
}