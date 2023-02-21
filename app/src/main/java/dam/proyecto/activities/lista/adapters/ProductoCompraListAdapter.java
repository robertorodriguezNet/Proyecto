package dam.proyecto.activities.lista.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dam.proyecto.R;
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

    /**
     * Cosntructor
     *
     * @param context   contexto
     * @param vistaItem es el layout que dibuja cada ítem
     * @param data      la colección de datos
     */
    public ProductoCompraListAdapter(Context context,
                                     int vistaItem,
                                     List<CompraEntity> data) {

        super(context, vistaItem, data);

        this.data = data;
        this.vistaItem = vistaItem;
        this.context = context;

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


        // Seteamos los datos
        producto.setText(denominacion);
        ud.setText(String.format("%.03f", actual.getCantidad()));
        precio.setText("0");
        pagado.setText(String.format("%.02f", actual.getPagado()));

//        view.setOnClickListener( v -> {
//            oyente.editarProductoListaAbierta( actual );
//        });

        return view;
    }

}