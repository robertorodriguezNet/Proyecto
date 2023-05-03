package dam.proyecto.activities.almacen.adapters;

import static dam.proyecto.Config.PATH_PRODUCTS_THUMB;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dam.proyecto.R;
import dam.proyecto.activities.almacen.listeners.AlmacenListener;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.MarcaController;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.MarcaRepository;
import dam.proyecto.utilities.Words;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.04
 * @since 19/02/2023
 */
public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder> {

    // Datos
    private final ArrayList<ProductoEntity> DATA_PRODUCTOS;                           // Colección de datos
    private final Context CONTEXT;

    // Oyentes
    private final AlmacenListener LISTENER;

    /**
     * El adaptador debe recibir la lista de los productos.
     * La marca se obtiene desde el adptador.
     */
    public AdaptadorProductos(
            ArrayList<ProductoEntity> dataProductos,
            Context context,
            AlmacenListener listener) {

        this.CONTEXT = context;
        this.LISTENER = listener;

        this.DATA_PRODUCTOS = dataProductos;                               // Datos con los productos

    }

    /**
     * Método que se ejecuta al crear el ViewHolder
     *
     * @param parent   es el componente que recibe el ViewHolder, en este caso un RecyclerView
     * @param viewType viewType
     * @return El ViewHolder que se cargará en el RecyclerView
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Obtenemos la vista del CardView con el diseño del producto
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cardview_producto, parent, false);

        // Devolvemos el componente ViewHolder al cual pasamos la vista (CardView)
        return new ViewHolder(view);
    }

    /**
     * En este método se enlazan los datos del registro con los del componente ViewHolder.
     * Los componentes del ViewHolder se corresponden el el CardView del diseño del producto.
     *
     * @param holder   un holder en blanco
     * @param position la posición del registro que va ha se cargado en el ViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Llamada al método binData y se le pasa el objeto de la lista
        ProductoEntity actual = DATA_PRODUCTOS.get(position);

        // correspondiente a la posición recibida como argumento
        holder.binData(actual);

        // Asociamos el oyente a los diferentes eventos
        holder.itemView.setOnLongClickListener(view -> {
            if (null != LISTENER) {
                LISTENER.editarProducto(actual);
            }
            return true;
        });

        holder.itemView.setOnClickListener(view -> {
            if (null != LISTENER) {
                LISTENER.addProductoALaLista(actual);
            }
        });
    }

    /**
     * Devuelve el número de elementos que forman la colección
     *
     * @return el número de elementos
     */
    @Override
    public int getItemCount() {
        return DATA_PRODUCTOS.size();
    }

    /**
     * Clase interna del adaptador.
     * El ViewHolder es el componente que se pasa al RecyclerView y que obtiene los datos
     * del registro.
     * Los parámetros del ViewHolder son los mismos que el CardView.
     * Cuando el registro deja de verse en la pantalla, el ViewHolder queda libre y puede
     * cargarse con los datos del siguiente registro.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Atributos del ViewHolder, han de ser, como mínimo los mismo de la clase de la
        // colección, solo los que se quieran mostrar
        ImageView imagen;
        TextView denominacion, marca, cantidad, medida;

        // TextView de los últimos precios
        TextView ultimoPrecio, ultimaFecha, ultimoComercio, ultimoPrecioM;

        /**
         * Constructor
         *
         * @param itemView es el elemento de la colección que se va a cargar en el ViewHolder
         */
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            // Inicializamos los atributos para asociarlos a los del CarcView
            imagen = itemView.findViewById(R.id.ala_img_miniatura);
            denominacion = itemView.findViewById(R.id.ala_tv_donominacion);
            marca = itemView.findViewById(R.id.ala_tv_marca);
            cantidad = itemView.findViewById(R.id.ala_tv_cantidad);
            medida = itemView.findViewById(R.id.ala_tv_medida);

            ultimaFecha = itemView.findViewById(R.id.ala_tv_fecha);
            ultimoComercio = itemView.findViewById(R.id.ala_tv_comercio);
            ultimoPrecio = itemView.findViewById(R.id.ala_tv_precio);
            ultimoPrecioM = itemView.findViewById(R.id.ala_tv_precioM);

        }

        /**
         * Método para enlazar los datos del Producto con los del CardView (que son los mismos
         * que los de este ViewHolder)
         *
         * @param producto el producto actual
         */
        public void binData(ProductoEntity producto) {

            // Cargar la imagen con Glide
            String path = PATH_PRODUCTS_THUMB + producto.getId() + ".jpg";
            // Si la imagen no es nula, la cargamos
            Glide.with(CONTEXT)
                    .load(path)
                    .into(imagen);

            // Obtener la denominación y la marca
            denominacion.setText(producto.getDenominacion());
            marca.setText( new MarcaController(CONTEXT).getNameById(producto.getMarca()));
            cantidad.setText(Words.formatearPrecio(producto.getCantidad()));
            medida.setText( producto.getMedida() );

            // Debemos obtener la última compra del producto,
            // OJO, puede que no haya compra del producto
            CompraController controller = new CompraController(CONTEXT);
            HashMap<String, String> mapa =
                    (HashMap<String, String>) controller.getUltimaCompraDe(producto.getId());

            ultimaFecha.setText((mapa == null) ? "" : mapa.get("fecha"));
            ultimoComercio.setText((mapa == null) ? "" : mapa.get("comercio"));
            ultimoPrecio.setText((mapa == null) ? "" : mapa.get("precio"));
            ultimoPrecioM.setText((mapa == null) ? "" : mapa.get("precioM"));

        }

    }

}

