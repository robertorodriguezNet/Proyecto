package dam.proyecto.activities.lista;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dam.proyecto.R;
import dam.proyecto.activities.lista.adapters.DiferentesComerciosAdapter;
import dam.proyecto.activities.lista.clases.ComercioDiferente;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.database.relaciones.VistaCompra;
import dam.proyecto.utilities.Preferencias;

/**
 * Muestra una comprarativa de una compra (la lista abierta) realizada en
 * diferentes comercios.
 *
 * @author Roberto Rodríguez Jiménez
 * @version 23.03.24
 * @since 24/03/2023
 */
public class ComparativaComerciosFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comparativa_comercios, container, false);
        Context context = view.getContext();

        // Obtener los datos.

        // ¿Qué tengo?: una lista de compras CompraEntity en dataProductos
        //              CompraEntity NO guarda el comercio
        // ¿Qué necesito? la misma lista de compras pero para cada comercio
        // ¿Cómo obtengo los comercios?
        //              Se busca, para cada producto, los comercios en los que ha sido comprado

        CompraController cc = new CompraController(getContext());

        // Obtener un listado de los comercios (Integer) con
        // los productos comprados en él (Array<VistaCompra>)
        // VistaCompra es un POJO de relación para room
        HashMap<Integer, ArrayList<VistaCompra>> compras =
                cc.getComparativaComercios(
                        Preferencias
                                .  getListaAbiertaId( context )
                );


        // Ya tenemos la colección de comercios y sus compras.
        // La colección se agrupa por comercio (por su id)
        // Cada comercio (id) guarda un ArrayList de VistaCompra, con un VistaCompra para
        // cada uno de los productos.

        // Ahora hay que dar forma a los datos que se van a mostra en el layuot:
        // necesitamos una clase que recoja los datos de "compras y crear un array
        // para poder pasarlo al adapter de la lista que se mostrará.
        ArrayList<ComercioDiferente> datos = crearArrayParaElAdapter( compras );

        // Ya tenemos una lista completa con las compras por comercio

        // Obtenemos el ListView en el que cargar los datos
        ListView listView = view.findViewById( R.id.fcc_list_comparativa );
        DiferentesComerciosAdapter adapter = new DiferentesComerciosAdapter(
                context,
                R.layout.item_precio_comercio,
                datos
        );
        listView.setAdapter( adapter );
        return view;
    }

    /**
     * Devuelve un listado de objetos ComercioDiferente, que contienen el resumen
     * de una misma lista de la compra en diferentes comercios.
     *
     * @param compras es el listado de las compras, asociadas a un comercio
     * @return la colección de resúmenes de compras
     */
    private ArrayList<ComercioDiferente> crearArrayParaElAdapter( HashMap<Integer, ArrayList<VistaCompra>> compras ){

        ArrayList<ComercioDiferente> diferentesComercios = new ArrayList<ComercioDiferente>();

        for (Map.Entry<Integer, ArrayList<VistaCompra>> compra : compras.entrySet()) {

            int articulos = 0;
            float total = 0f;
            long desde = 0;
            long hasta = 0;
            String comercio = "";

            // Recorrer los productos comprados en cada comercio
            for (VistaCompra c : compra.getValue()) {

                Long f = Long.parseLong(c.fecha);

                articulos++;
                total += Float.parseFloat(c.precio);

                if (desde == 0) {
                    desde = f;
                } else if (f < desde) {
                    desde = f;
                }

                if (hasta == 0) {
                    hasta = f;
                } else if (f > hasta) {
                    hasta = f;
                }

                comercio = c.name;
            }

            // Guardar el resumen del comercio
            diferentesComercios.add(
                    new ComercioDiferente(
                            comercio,
                            total,
                            articulos,
                            String.valueOf(desde),
                            String.valueOf(hasta)
                    )
            );

        }
        return diferentesComercios;
    }
}