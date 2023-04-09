package dam.proyecto.activities.lista.adapters;

import android.os.Bundle;

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
 *
 * @author Roberto Rodríguez
 * @version 2023.02.14
 * @since 2023/02/14
 */
public class ProductoViewPagerAdapter extends FragmentStateAdapter {

    private final String ID_COMPRA;

    /**
     * Constructor
     *
     * @param fragmentActivity fragment
     */
    public ProductoViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String idCompra) {
        super(fragmentActivity);
        this.ID_COMPRA = idCompra;
    }

    /**
     * Devolvemos el fragment correspondiente a postion
     *
     * @param position el índice del fragment seleccionado
     * @return el fragment seleccionado
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        // Hay que crear un Bundle para pasar los datos a cada fragment
        Bundle bundle = new Bundle();
        bundle.putString("id", ID_COMPRA);

        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new ProductoInfoFragment();
                fragment.setArguments(bundle);
                return fragment;
            case 1:
                fragment = new ProductoComparativaFragment();
                fragment.setArguments(bundle);
                return fragment;
            case 2:
                fragment = new ProductoEvolucionFragment();
                fragment.setArguments(bundle);
                return fragment;
            default:
                return new ProductoInfoFragment();
        }
    }

    /**
     * Indicamos que hay 3 tabs
     *
     * @return número de tabs que se cargan
     */
    @Override
    public int getItemCount() {
        return 3;
    }
}
