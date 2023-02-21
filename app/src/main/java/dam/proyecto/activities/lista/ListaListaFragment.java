package dam.proyecto.activities.lista;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.ComprasActivity;
import dam.proyecto.activities.lista.adapters.ProductoCompraListAdapter;
import dam.proyecto.activities.lista.listeners.ListaListener;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.utilities.Preferencias;

public class ListaListaFragment extends Fragment {

    private static final String TAG = "LL";
    private Context context;

    private ListaListener oyente;

    // Componentes
    TextView lblNombreDeLaCompra;
    ImageView btnSalir;
    Spinner spinner;
    ImageView btnAgregar;

    // Datos
    // NombreCompraEntity
    NombreCompraEntity nombreCompra;                      // Es la compra que se está editando
    // Repositorios
    ComercioRespository comercioRespository;
    NombreCompraRepository nombreCompraRepository;
    CompraRepository compraRepository;

    ArrayList<ComercioEntity> dataComercio;                // Colección de comercios para el spinner
    ArrayList<CompraEntity> dataProductos;                    // Colección de productos de la compra


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_lista_lista, container, false);
        context = view.getContext();

        // Lo primero es comprobar que tengamos una compra editada
        // Obtenemos el id de la compra desde las preferencias
        String idCompra = Preferencias.getListaAbiertaId(this.getActivity());



        // Solo si la compra no es nula
        if (idCompra != null) {

            // Cargar los datos de los comercios
            comercioRespository = new ComercioRespository( context );
            dataComercio = comercioRespository.getAll();

            // Cargar los productos
            compraRepository = new CompraRepository( context );
            dataProductos =
                    (ArrayList<CompraEntity>) compraRepository
                            .getProductosByFecha( Preferencias.getListaAbiertaId( context ) );

            // repositorio para el nombre de la compra
            nombreCompraRepository = new NombreCompraRepository( context );

            // Obtener el objeto Nombre de la compra.
            // En NombreCompraEntity se establece el comercio
            // Inicilizar el NombreDeLaCompra
            nombreCompra = nombreCompraRepository.getById( idCompra );

            // Inicializar componente
            lblNombreDeLaCompra = (TextView) view.findViewById(R.id.fla_tv_nombreCompra);
            btnSalir = (ImageView) view.findViewById(R.id.fla_img_cerrar);
            spinner =  (Spinner) view.findViewById(R.id.fla_spn_seleccionarComercio);
            btnAgregar = ( ImageView ) view.findViewById( R.id.fla_fab_addProduct );

            // Spinner
            ArrayAdapter<ComercioEntity> adapter = new ArrayAdapter<>(
                    context,
                    androidx
                            .appcompat
                            .R.layout.support_simple_spinner_dropdown_item,
                    dataComercio
            );
            spinner.setAdapter(adapter);
            spinner.setSelection( getSpinnerId() );

            // Nombre de la compra
            lblNombreDeLaCompra.setText(nombreCompra.getNombre().toString());

            // Botón para añadir productos
            btnAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity( new Intent( context, AlmacenActivity.class ) );
                }
            });

            // Botón salir
            btnSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    salir();
                }
            });

            // Cargar la lista
            // Obtener el ListView
            ListView listView = view.findViewById( R.id.fla_lv_listaDeLaCompra );

            // Adaptador
            ProductoCompraListAdapter adapterLista = new ProductoCompraListAdapter( view.getContext(),
                    R.layout.producto_compra_item,
                    dataProductos,
                    oyente);
            listView.setAdapter( adapterLista );

//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    oyente.onProductoCompradoClick( view );
//                }
//            });

            // Escribimos el total
            TextView total = view.findViewById( R.id.fla_tv_total );
            total.setText( String.format( "%.02f", getTotalCompra()));

        }

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if( context instanceof ListaListener){
            oyente = (ListaListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " debes implementar el oyente." );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        oyente = null;
    }

    /**
     * Devuelve el id del spinner que se corresponde con el id del comercio.
     * Hay que tener en cuenta que el spinner comienza a contar por 1.
     * @return el id del spinner
     */
    private int getSpinnerId(){
        // Establecer el ítem por defecto
        // Obtener el nombre del comercio
        String nComercio = comercioRespository
                .getNombreComercio(
                        nombreCompra.getComercio()
                );

        int idComercio = 1;
        //Buscamos el comercio en la dataComercio
        boolean nombre = false;
        ComercioEntity c = null;
        int count = 0;
        while( !nombre && (count < dataComercio.size() ) ){
            c = dataComercio.get( count );
            nombre = (c.getName().equals(nComercio));
            count++;
        }
        return( ( c == null ) ? 1 : --count );
    }


    /**
     * Salimos de la lista de la compra hcia Compras.
     * Al salir, debemos actualizar el comercio de NombreDeLaCompra
     */
    public void salir(){

        // Obtnemos el comercio directamente del spinner
        nombreCompra.setComercio( ((ComercioEntity) spinner.getSelectedItem()).getId() );
        nombreCompraRepository.update( nombreCompra );

        startActivity(new Intent(context, ComprasActivity.class));
    }

    /**
     * Devuelve el importe total de la compra.
     * @return el importe total de la compra
     */
    private float getTotalCompra(){

        float total = 0.0f;

        for ( CompraEntity producto : dataProductos ) {
            total += producto.getPagado();
        }

        return total;

    }



}