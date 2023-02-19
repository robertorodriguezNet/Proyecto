package dam.proyecto.activities.compras.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.util.List;

import dam.proyecto.R;
import dam.proyecto.activities.compras.listener.ListenerCompras;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.utilities.Fecha;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.19
 * @since 19/02/2023
 */
public class AdaptadorCompras extends ArrayAdapter<NombreCompraEntity> {

    private Context context;
    private int compras_list_item;                                    // Layout que dibuja cada ítem
    private List<NombreCompraEntity> data;                   // Colección de objetos de NombreCompra

    private ListenerCompras oyente;                                   // Oyente para los componentes

    /**
     * Cosntructor
     * @param context   contexto
     * @param compras_list_item es el layout que dibuja cada ítem
     * @param data la colección de datos
     */
    public AdaptadorCompras(Context context,
                              int compras_list_item,
                              List<NombreCompraEntity> data,
                              ListenerCompras oyente) {

        super( context, compras_list_item, data);

        this.oyente = oyente;                                                              // Oyente

        this.data = data;
        this.compras_list_item = compras_list_item;
        this.context = context;

    }

    /**
     * Se ejecuta cada vez que se lee un elemento de la colección
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater
                .from( context )
                .inflate( compras_list_item, parent, false );

        NombreCompraEntity actual = data.get( position );

        // Obtener los componentes de la interfaz
        TextView fecha = ( TextView ) view.findViewById( R.id.cli_tv_fecha);
        TextView nombre = ( TextView ) view.findViewById( R.id.cli_tv_nombre);
        TextView comercio = ( TextView ) view.findViewById( R.id.cli_tv_comercio );
        ImageView deleteIcon = ( ImageView ) view.findViewById( R.id.cli_img_delete);

        // Formatear la fecha
        String formatFecha = null;
        try {
            formatFecha = Fecha.getFecha( actual.getId() );
        } catch (ParseException e) {
            formatFecha = "";
        }

        // Obtener el comercio a partir del id
        String strComercio = new ComercioRespository( context )
                                .getNombreComercio( actual.getComercio() );

        // Seteamos los datos
        fecha.setText( formatFecha );
        nombre.setText( actual.getNombre() );
        comercio.setText( strComercio );

        // Asignamos un oyente al layout ¿?
        view.findViewById( R.id.cli_lly_datosCompra).setOnClickListener( v -> {
            if( null != oyente ){
                oyente.cli_lly_datosCompraOnClick( actual );
            }
        });

        // Asignamos un oyente al icono de borrar
        deleteIcon.setOnClickListener( v -> {
            if( null != oyente ){
                oyente.cli_img_deleteOnClik( actual );
            }

        });

        return view;
    }



}
