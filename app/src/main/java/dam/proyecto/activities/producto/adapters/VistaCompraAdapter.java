package dam.proyecto.activities.producto.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.R;
import dam.proyecto.database.relaciones.VistaCompra;
import dam.proyecto.utilities.Fecha;


/**
 * Adaptador para mostrar las relaciones de las compras con sus comercios a
 * partir de un producto dado
 *
 * @author Roberto Rodríguez Jiménez
 * @since 02/03/2023
 * @version 2023.03.02
 */
public class VistaCompraAdapter extends ArrayAdapter<VistaCompra> {

    private final Context CONTEXT;
    private final int VISTA_ITEM;                                            // Layout que dibuja cada ítem
    private final List<VistaCompra> DATA;                                            // Colección de datos

    /**
     * Constructor
     * @param context contexto
     * @param vistaItem layout que dibuja cada registro
     * @param data los datos
     */
    public VistaCompraAdapter(@NonNull Context context,
                              int vistaItem,
                              ArrayList<VistaCompra> data) {

        super(context, vistaItem, data);

        this.DATA = data;
        this.VISTA_ITEM = vistaItem;
        this.CONTEXT = context;

    }

    /**
     * Método que se ejecuta para elemento del listdao de datos
     * @param position posición del registro en la lista
     * @param convertView vista
     * @param parent vista padre
     * @return la vista
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder") View view = LayoutInflater.from(CONTEXT).inflate(VISTA_ITEM, parent, false);

        // Objeto actual
        VistaCompra actual = DATA.get(position);

        // Componentes de la interfaz
        TextView comercio = view.findViewById(R.id.ivc_tv_comercio);
        TextView precio = view.findViewById(R.id.ivc_tv_precio);
        TextView fecha = view.findViewById(R.id.ivc_tv_fecha);

        String strComercio = actual.name.toLowerCase();
        String strPrecio = actual.precio.replace(".",",");
        String strFecha = Fecha.getFechaFormateada(actual.fecha );

        // Escribir los datos
        comercio.setText(strComercio);
        precio.setText(strPrecio);
        fecha.setText(strFecha);

        return view;
    }

}
