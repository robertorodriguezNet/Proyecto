package dam.proyecto.activities.lista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.lista.adapters.DiferentesComerciosPageAdapter;
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
        ArrayList<ComercioDiferente> compras =
                cc.getComparativaComercios(
                        Preferencias
                                .getListaAbiertaId(getContext())
                );

        // Ahora hay que dar forma a los datos que se van a mostra en el layuot:
        // necesitamos una clase que recoja los datos de "compras y crear un array
        // para poder pasarlo al adapter de la lista que se mostrará.
        ArrayList<ComercioDiferente> datos = crearArrayParaElAdapter(compras);

        View view = inflater.inflate(R.layout.fragment_comparativa_comercios, container, false);
        ViewPager2 viewPager = view.findViewById(R.id.fcc_list_comparativa);

        DiferentesComerciosPageAdapter adapter = new DiferentesComerciosPageAdapter(datos, getContext() );
        viewPager.setAdapter(adapter);
//        viewPager.setClipToPadding(false);
//        viewPager.setClipChildren(false);
//        viewPager.setOffscreenPageLimit(2);
//        viewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        // Botón para volver
        Button btnSalir = view.findViewById(R.id.fcc_btn_salir);
        btnSalir.setOnClickListener(v -> getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.listaContenedor, new ListaListaFragment())
                .commit()
        );

        return view;
    }

    /**
     * Devuelve un listado de objetos ComercioDiferente, que contienen el resumen
     * de una misma lista de la compra en diferentes comercios y un listado
     * de VistaCompra de cada producto.
     * VistaCompra contiene los datos esenciales de una compra: denominación del producto,
     * precio, precio medio, fecha y nombre del comercio.
     * Es una clase de relación.
     *
     * @param compras es el listado de los diferentes comercios en los
     *                que se han comprado productos.
     * @return la colección de resúmenes de compras
     */
    private ArrayList<ComercioDiferente> crearArrayParaElAdapter(ArrayList<ComercioDiferente> compras) {

        ArrayList<ComercioDiferente> diferentesComercios = new ArrayList<>();

        // Recorrer cada uno de los objetos ComercioDiferente de la colección que
        // recibimos como parámetro.
        // Para cada objeto debemos obtener los valores
        for( ComercioDiferente comercio : compras ){

            int articulos = 0;
            float total = 0f;
            long desde = 0;
            long hasta = 0;
            String nombreComercio = "";

            // Recorrer los productos comprados en el comercio
            // Comercio tiene una lista de los productos comprados
            for( VistaCompra vistaCompra : comercio.getListaDeProductos() ){

                long f = Long.parseLong(vistaCompra.fecha);

                articulos++;
                total += Float.parseFloat(vistaCompra.precio);

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

                nombreComercio = vistaCompra.name;

            } // fin de recorrer el listado de productos de un comercio

            // Guardar el resumen
            comercio.setComercio( nombreComercio );
            comercio.setArticulos(articulos);
            comercio.setTotal(total);
            comercio.setDesde(String.valueOf(desde));
            comercio.setHasta(String.valueOf(hasta));

            diferentesComercios.add( comercio );
        }

        return diferentesComercios;
    }
}