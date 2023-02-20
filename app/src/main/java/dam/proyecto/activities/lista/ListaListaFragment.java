package dam.proyecto.activities.lista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.ConditionVariable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dam.proyecto.R;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.utilities.Preferencias;

public class ListaListaFragment extends Fragment {

    private Context context;

    // Componentes
    TextView lblNombreDeLaCompra;
    ImageView btnSalir;

    public ListaListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_lista_lista, container, false);
        context = view.getContext();

        // Inicializar componente
        lblNombreDeLaCompra = ( TextView ) view.findViewById( R.id.fla_tv_nombreCompra );
        btnSalir = ( ImageView ) view.findViewById( R.id.fla_img_cerrar );

        // Obtenemos el id de la compra desde las preferencias
        String idCompra = Preferencias.getListaAbiertaId( this.getActivity() );

        // Obtener el objeto Nombre de la compra.
        // En NombreCompraEntity se establece el comercio
        NombreCompraRepository repository = new NombreCompraRepository( context );
        NombreCompraEntity nombreCompra = repository.getById( idCompra );

        // Nombre de la compra
        lblNombreDeLaCompra.setText( nombreCompra.getNombre().toString() );

        // Botón salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent( context, ComprasActivity.class) );
            }
        });


        return view;
    }

}