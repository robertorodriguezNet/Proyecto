package dam.proyecto.activities.ayuda;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import dam.proyecto.R;

public class AyudaDetalleFragment extends Fragment {

    private String file;
    private String title;

    public AyudaDetalleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            file = getArguments().getString("file");
            title = getArguments().getString("title");
        }else{
            file = "blanco";
            title = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ayuda_detalle, container, false);

        TextView titulo = view.findViewById(R.id.ayuda_detalle_titulo);
        titulo.setText(title);

        // file:///android_asset/ayuda_almacen_agregar.htm
        WebView contenido = (WebView) view.findViewById(R.id.ayuda_detalle_contenido);
        contenido.loadUrl("file:///android_asset/" + file + ".htm");
        contenido.setWebViewClient( new WebViewClient() );

        return view;
    }
}