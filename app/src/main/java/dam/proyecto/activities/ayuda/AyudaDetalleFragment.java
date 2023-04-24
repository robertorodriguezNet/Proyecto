package dam.proyecto.activities.ayuda;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

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
        } else {
            file = "blanco";
            title = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int lauyout = (title.contains("(Vídeo)")) ?
                R.layout.fragment_ayuda_video : R.layout.fragment_ayuda_detalle;

        View view = inflater.inflate(lauyout, container, false);

        TextView titulo = view.findViewById(R.id.ayuda_detalle_titulo);
        titulo.setText(title);

        if (lauyout == R.layout.fragment_ayuda_video) {
            Uri uri = Uri.parse("http://robertorodriguez.net/ldlc/videotutoriales/" + file + ".mp4");
            VideoView contenido = (VideoView) view.findViewById(R.id.ayuda_detalle_contenido);
            contenido.setMediaController(new MediaController(getContext()));
            contenido.setVideoURI(uri);
            contenido.requestFocus();
            contenido.start();
        } else {
            WebView contenido = (WebView) view.findViewById(R.id.ayuda_detalle_contenido);
            contenido.loadUrl("file:///android_asset/" + file + ".htm");
            contenido.setWebViewClient(new WebViewClient());
        }

        return view;
    }
}