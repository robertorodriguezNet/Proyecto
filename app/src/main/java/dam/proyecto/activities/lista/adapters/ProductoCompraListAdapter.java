package dam.proyecto.activities.lista.adapters;

import static dam.proyecto.Config.PATH_PRODUCTS_THUMB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

import dam.proyecto.R;
import dam.proyecto.activities.lista.listeners.ListaListener;
import dam.proyecto.controllers.ProductoController;
import dam.proyecto.database.entity.CompraEntity;

/**
 * Adaptador para cargar los producto de una compra en su lista de productos.
 * NO SE USA PARA LA TABLA, solo para ListView
 *
 * @author Roberto Rodríguez Jiménez
 * @version 2023.01.25
 * @since 25/01/2023
 */
public class ProductoCompraListAdapter extends ArrayAdapter<CompraEntity> {

    private final Context CONTEXT;
    private final int VISTA_ITEM;                                     // Layout que dibuja cada ítem
    private final List<CompraEntity> data;                 // Colección de productos de NombreCompra

    private final ListaListener oyente;

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
                                     ListaListener oyente
    ) {

        super(context, vistaItem, data);

        this.data = data;
        this.VISTA_ITEM = vistaItem;
        this.CONTEXT = context;

        this.oyente = oyente;

    }

    /**
     * Se ejecuta cada vez que se lee un elemento de la colección
     *
     * @param position    posición del elemento en la colección
     * @param convertView vista
     * @param parent      contenedor padre
     * @return la vista generada
     */
    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder") View view = LayoutInflater
                .from(CONTEXT)
                .inflate(VISTA_ITEM, parent, false);

        // Es el registro de una compra, es decir, el producto comprado
        // con sus datos
        CompraEntity actual = data.get(position);

        // Obtener la denominación del producto
        String denominacion = ProductoController
                .getDenominacionProducto(actual.getProducto(), CONTEXT);

        // Obtener los componentes de la interfaz
        ImageView miniatura = view.findViewById(R.id.pci_img_miniatura);
        TextView producto = view.findViewById(R.id.pci_tv_producto);
        TextView ud = view.findViewById(R.id.pci_tv_unidadesCompradas);
        TextView precio = view.findViewById(R.id.pci_tv_precioUniodad);
        TextView pagado = view.findViewById(R.id.pci_tv_totalProducto);


//        Log.d("LDLC", "ProductoCompraListAdapter.getView() - opción de precio: " + opcionDePrecio );
        // Para obtener el precio necesitamos
        // .- actual: el precio del producto
        // .- global: el id del producto
        // .- comercio: el id del producto y el id del comercio

        // Seteamos los datos

        // Cargar la imagen con Glide
        String path = PATH_PRODUCTS_THUMB + actual.getProducto() + ".jpg";
        // Si la imagen no es nula, la cargamos
        Glide.with(CONTEXT)
                .load(path)
                .into(miniatura);

        producto.setText(denominacion);
        float cantidadF = actual.getCantidad();
        float precioF = actual.getPrecio();

        float total = cantidadF * precioF;

        ud.setText(String.format("%.02f", cantidadF));
        precio.setText(String.format("%.02f", precioF));
        pagado.setText(String.format("%.02f", total));

        view.setOnClickListener(v -> oyente.onProductoCompradoClick(actual));

        view.setOnLongClickListener(v -> {
            oyente.onProductoCompradoLongClick(actual);
            return true;
        });

        return view;
    }

}