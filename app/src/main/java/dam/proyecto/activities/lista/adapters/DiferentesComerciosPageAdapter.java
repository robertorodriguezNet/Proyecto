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
import dam.proyecto.activities.lista.clases.ComercioDiferente;
import dam.proyecto.utilities.Fecha;

public class DiferentesComerciosPageAdapter
        extends RecyclerView.Adapter<DiferentesComerciosPageAdapter.ViewHolder> {

    ArrayList<ComercioDiferente> datos;

    public DiferentesComerciosPageAdapter(ArrayList<ComercioDiferente> datos) {
        this.datos = datos;
        Log.d("LDLC", "Adaptador datos recibidos: " + datos.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.item_precio_comercio,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ComercioDiferente comercioDiferente = datos.get(position);

        holder.comercio.setText(comercioDiferente.getComercio());
        holder.desde.setText(Fecha.getFechaFormateada(
                comercioDiferente.getDesde()
        ));
        holder.hasta.setText(Fecha.getFechaFormateada(
                comercioDiferente.getHasta()
        ));
        holder.total.setText(String.format("%.02f", comercioDiferente.getTotal()));
        holder.articulos.setText(String.valueOf(comercioDiferente.getArticulos()));

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView comercio;
        TextView desde;
        TextView hasta;
        TextView total;
        TextView articulos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            comercio = itemView.findViewById(R.id.ipc_tv_comercio);
            desde = itemView.findViewById(R.id.ipc_tv_fechaDesde);
            hasta = itemView.findViewById(R.id.ipc_tv_fechaHasta);
            total = itemView.findViewById(R.id.ipc_tv_importe);
            articulos = itemView.findViewById(R.id.ipc_tv_numArticulos);

        }
    }

}
