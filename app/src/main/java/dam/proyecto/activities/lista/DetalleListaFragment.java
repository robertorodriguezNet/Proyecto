package dam.proyecto.activities.lista;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import dam.proyecto.R;
import dam.proyecto.activities.lista.adapters.ProductoViewPagerAdapter;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.utilities.Preferencias;

public class DetalleListaFragment extends Fragment {

    // Componentes UI
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ProductoViewPagerAdapter adapter;

    private Context context;

    public DetalleListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_lista, container, false);
        context = view.getContext();

        return view;
    }
}