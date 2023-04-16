package dam.proyecto.activities.ayuda.fragments.lista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dam.proyecto.R;


public class AyudaListaAgregarFragment extends Fragment {


    public AyudaListaAgregarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ayuda_lista_agregar, container, false);

        WebView webView = (WebView) view.findViewById(R.id.fala_texto);
        webView.loadUrl("file:///android_asset/ayuda_lista_agregar.htm");
        webView.setWebViewClient( new WebViewClient() );

        return view;
    }
}