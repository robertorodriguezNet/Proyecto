package dam.proyecto.activities.lista.adapters;

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
import dam.proyecto.activities.lista.listeners.ListaListener;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.repositories.ProductoRepository;

/**
 * Adaptador para cargar los producto de una compra en su lista de productos.
 * NO SE USA PARA LA TABLA, solo para ListView
 *
 * @author Roberto Rodríguez
 * @version 1.2023.01.25
 * @since 2023/01/25
 */
public class ProductoCompraListAdapter extends ArrayAdapter<CompraEntity> {

    private Context context;
    private int vistaItem;                                    // Layout que dibuja cada ítem
    private List<CompraEntity> data;                       // Colección de productos de NombreCompra

    private ListaListener oyente;

    private String opcionDePrecio;                             // Tipo de precio que se va a mostrar
    /**
     * Cosntructor
     *
     * @param context   contexto
     * @param vistaItem es el layout que dibuja cada ítem
     * @param data      la colección de datos
     */
    public ProductoCompraListAdapter(Context context,
                                     int vistaItem,
                                     List<CompraEntity> data,
                                     ListaListener oyente,
                                     String opcionDePrecio) {

        super(context, vistaItem, data);

        this.data = data;
        this.vistaItem = vistaItem;
        this.context = context;

        this.oyente = oyente;

        this.opcionDePrecio = opcionDePrecio;

    }

    /**
     * Se ejecuta cada vez que se lee un elemento de la colección
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater
                .from(context)
                .inflate(vistaItem, parent, false);

        // Es el registro de una compra, es decir, el producto comprado
        // con sus datos
        CompraEntity actual = data.get(position);

        // Obtener la denominación del producto
        String denominacion = new ProductoRepository( context )
                                            .getDenominacionProducto(actual.getProducto());

        // Obtener los componentes de la interfaz
        TextView producto = (TextView) view.findViewById(R.id.pci_tv_producto);
        TextView ud = (TextView) view.findViewById(R.id.pci_tv_unidadesCompradas);
        TextView precio = (TextView) view.findViewById(R.id.pci_tv_precioUniodad);
        TextView pagado = (TextView) view.findViewById(R.id.pci_tv_totalProducto);


//        Log.d("LDLC", "ProductoCompraListAdapter.getView() - opción de precio: " + opcionDePrecio );
        // Para obtener el precio necesitamos
        // .- actual: el precio del producto
        // .- global: el id del producto
        // .- comercio: el id del producto y el id del comercio

        // Seteamos los datos
        producto.setText(denominacion);
        float cantidadF = actual.getCantidad();
        float precioF = actual.getPrecio();
        if( opcionDePrecio.equals("global")){
            precioF = ProductoController.getUltimoPrecio( actual.getProducto(), context);
            Log.d("LDLC", "Obteniendo precio global de " + actual.getProducto()
            + " precio: " + precioF);

        }
        float total = cantidadF * precioF;

        ud.setText(String.format("%.02f", cantidadF));
        precio.setText(String.format("%.02f", precioF));
        pagado.setText(String.format("%.02f", total));

        view.setOnClickListener( v -> {
            oyente.onProductoCompradoClick( actual );
        });

        return view;
    }

}