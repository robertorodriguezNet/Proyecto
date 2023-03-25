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

public class DiferentesComerciosPageAdapter
        extends RecyclerView.Adapter<DiferentesComerciosPageAdapter.ViewHolder> {

    ArrayList<ComercioDiferente> datos;

    public DiferentesComerciosPageAdapter(ArrayList<ComercioDiferente> datos) {
        this.datos = datos;
        Log.d("LDLC","Adaptador datos recibidos: " + datos.size() );
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
        holder.comercio.setText( comercioDiferente.getComercio() );

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView comercio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            comercio = itemView.findViewById(R.id.ipc_tv_comercio);
        }
    }

}
