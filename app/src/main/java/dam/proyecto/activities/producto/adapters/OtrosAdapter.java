package dam.proyecto.activities.producto.adapters;

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
public class OtrosAdapter extends ArrayAdapter<String> {

    private Context context;
    private int vistaItem;                                            // Layout que dibuja cada ítem
    private ArrayList<String> data;                                                 // Colección de datos

    /**
     * Constructor
     * @param context
     * @param vistaItem layout que dibuja cada registro
     * @param data los datos
     */
    public OtrosAdapter(@NonNull Context context,
                        int vistaItem,
                        ArrayList<String> data) {

        super(context, vistaItem, data);

        this.data = data;
        this.vistaItem = vistaItem;
        this.context = context;

    }

    /**
     * Método que se ejecuta para elemento del listdao de datos
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(vistaItem, parent, false);

        // Objeto actual
        String actual = data.get(position);

        // Componentes de la interfaz
        TextView denominacion = view.findViewById(R.id.ilo_tv_denominacion);
        TextView precio = view.findViewById(R.id.ilo_tv_precio);
        TextView fecha = view.findViewById(R.id.ilo_tv_fecha);
        TextView comercio = view.findViewById(R.id.ilo_tv_comercio);


        // Escribir los datos
        denominacion.setText( actual );
        comercio.setText("El comercio");
        precio.setText("0.00 €/k");
        fecha.setText("00-mar-2023");

        return view;
    }

    /**
     * Obtener el formato correctode la fecha.
     * @param f
     * @return
     */
    private String getFecha( String f ){

        try{
            String fecha = Fecha.getFecha( f );

            // Eliminamos el día
            String[] data = fecha.split(",");
            fecha = data[1];

            // Eliminamos la hora
            data = fecha.split(" ");
            fecha = data[0] + " " + data[1] + " " + data[2];

            return fecha;
        }catch ( Exception e){
            return f;
        }
    }

}
