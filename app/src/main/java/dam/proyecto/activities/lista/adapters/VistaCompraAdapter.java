package dam.proyecto.activities.lista.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.database.relaciones.VistaCompra;
import dam.proyecto.utilities.Fecha;

public class VistaCompraAdapter extends RecyclerView.Adapter<VistaCompraAdapter.ViewHolder> {

    private ArrayList<VistaCompra> datos;

    public VistaCompraAdapter(ArrayList<VistaCompra> datos) {
        this.datos = datos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.item_vista_compra,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VistaCompra compra = datos.get(position);
        holder.denominacion.setText( compra.denominacion );
        holder.fecha.setText(Fecha.getFechaFormateada( compra.fecha ) );
        holder.precio.setText( compra.precio );

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView denominacion, precio, fecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            denominacion = itemView.findViewById( R.id.ivc_tv_comercio);
            precio = itemView.findViewById( R.id.ivc_tv_precio);
            fecha = itemView.findViewById( R.id.ivc_tv_fecha);

        }

    }

}
