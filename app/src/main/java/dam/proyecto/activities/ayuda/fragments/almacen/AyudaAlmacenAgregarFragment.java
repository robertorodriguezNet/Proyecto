package dam.proyecto.activities.ayuda.fragments.almacen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dam.proyecto.R;

public class AyudaAlmacenAgregarFragment extends Fragment {


    public AyudaAlmacenAgregarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ayuda_almacen_agregar, container, false);

        WebView texto = (WebView) view.findViewById(R.id.fala_texto);
        texto.loadUrl("file:///android_asset/ayuda_almacen_agregar.htm");
        texto.setWebViewClient(new WebViewClient());

        return view;
    }
}