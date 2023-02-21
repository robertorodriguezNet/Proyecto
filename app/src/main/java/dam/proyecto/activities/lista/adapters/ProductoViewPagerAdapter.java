package dam.proyecto.activities.lista.adapters;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dam.proyecto.activities.producto.ProductoComparativaFragment;
import dam.proyecto.activities.producto.ProductoEvolucionFragment;
import dam.proyecto.activities.producto.ProductoInfoFragment;

/**
 * Adaptador para la vista de ProductoActivity.
 * Recibimos el id de la compra por parámtero
 * @since 2023/02/14
 * @author Roberto Rodríguez
 * @version 2023.02.14
 */
public class ProductoViewPagerAdapter extends FragmentStateAdapter {

    private String idCompra;

    /**
     * Constructor
     * @param fragmentActivity
     */
    public ProductoViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String idCompra) {
        super(fragmentActivity);

        this.idCompra = idCompra;

        Log.d( "LDLC", "PageAdapter idCompra: " + this.idCompra );
    }

    /**
     * Devolvemos el fragment correspondiente a postion
     * @param position el índice del fragment seleccionado
     * @return el fragment seleccionado
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        // Hay que crear un Bundle para pasar los datos a cada fragment
        Bundle bundle = new Bundle();
        bundle.putString( "id", idCompra );

        Fragment fragment;

        switch ( position ){
            case 0:
                fragment = new ProductoInfoFragment();
                fragment.setArguments( bundle );
                return fragment;
            case 1:
                return new ProductoComparativaFragment();
            case 2:
                return new ProductoEvolucionFragment();
            default: return new ProductoInfoFragment();
        }
    }

    /**
     * Indicamos que hay 3 tabs
     * @return
     */
    @Override
    public int getItemCount() {
        return 3;
    }
}
