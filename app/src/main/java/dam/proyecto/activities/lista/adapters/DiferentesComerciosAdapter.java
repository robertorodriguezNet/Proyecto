package dam.proyecto.activities.lista.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dam.proyecto.R;
import dam.proyecto.activities.lista.clases.ComercioDiferente;
/**
 *
 * @author Roberto Rodríguez Jiménez
 * @since 23/03/2023
 * @version 2023.03.23
 */
public class DiferentesComerciosAdapter extends ArrayAdapter<ComercioDiferente> {

    private final int VISTA_ITEM;                                     // Layout que dibuja cada ítem
    private final List<ComercioDiferente> DATA;

    /**
     * Cosntructor
     *
     * @param context   contexto
     * @param itemLayout es el layout que dibuja cada ítem
     * @param datos      la colección de datos
     */
    public DiferentesComerciosAdapter(Context context, int itemLayout, List<ComercioDiferente> datos) {
        super(context, itemLayout, datos);

        this.VISTA_ITEM = itemLayout;
        this.DATA = datos;

    }


    /**
     * Se ejecuta cada vez que se lee un elemento de la colección
     *
     * @param position posición del elemento en la colección
     * @param convertView vista
     * @param parent contenedor padre
     * @return la vista generada
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

//        Log.d("LDLC","un comercio leído");

        ComercioDiferente actual = DATA.get(position);

        Log.d("LDLC","un comercio leído: " + actual.getComercio() );


        @SuppressLint("ViewHolder") View view = LayoutInflater
                .from( getContext() )
                .inflate( VISTA_ITEM, parent, false);

        // Componentes de la interfaz
        TextView comercio = view.findViewById(R.id.ipc_tv_comercio);
        TextView importe = view.findViewById(R.id.ipc_tv_importe);
        TextView articulos = view.findViewById(R.id.ipc_tv_numArticulos);
        TextView desde = view.findViewById(R.id.ipc_tv_fechaDesde);
        TextView hasta = view.findViewById(R.id.ipc_tv_fechaHasta);

        comercio.setText( actual.getComercio().toUpperCase() );
        articulos.setText( String.valueOf( actual.getArticulos() ) );
        desde.setText( actual.getDesde() );
        hasta.setText( actual.getHasta() );

        return view;
    }
}
