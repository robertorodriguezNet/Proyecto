package dam.proyecto.activities.lista;

import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import dam.proyecto.R;
import dam.proyecto.activities.lista.adapters.ProductoViewPagerAdapter;

public class DetalleListaFragment extends Fragment {

    // Componentes UI
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    public DetalleListaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Deshabilitamos el bottom menu
        BottomNavigationView navegador = getActivity().findViewById(R.id.navegador);
        navegador.setVisibility(View.INVISIBLE);
        // Hay que redimensionar el contenedor
        FragmentContainerView contenedor = getActivity().findViewById(R.id.listaContenedor);
        ViewGroup.LayoutParams params = contenedor.getLayoutParams();
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        contenedor.setLayoutParams(params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_lista, container, false);

        String idCompra = getArguments().getString("id");

        // Inicializar componentes
        tabLayout = view.findViewById(R.id.ap_tly_tabLayout);
        viewPager2 = view.findViewById(R.id.ap_vp_viewPager);

        viewPager2.setAdapter(
                new ProductoViewPagerAdapter(
                        (FragmentActivity) view.getContext(),
                        idCompra)
        );

        // Oyente para el TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            /**
             * Al seleccionar un tab, establecemos el ítem actual obteniendo la posición
             * @param tab pestaña seleccionada
             */
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Cuando cambie el fragment, cambiamos la pestaña seleccionada
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        return view;
    }
}