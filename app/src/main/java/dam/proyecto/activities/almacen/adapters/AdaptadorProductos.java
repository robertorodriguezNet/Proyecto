package dam.proyecto.activities.almacen.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.widget.ImageView;
import android.widget.TextView;


import dam.proyecto.R;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.MarcaRepository;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.19
 * @since 19/02/2023
 */
public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder>
                implements View.OnClickListener, View.OnLongClickListener {

    private ArrayList<ProductoEntity> dataProductos;                           // Colección de datos

    private Context context;

    // Oyentes
    private View.OnClickListener clickListener;
    private View.OnLongClickListener onLongClickListener;

    /**
     * El adaptador debe recibir la lista de los productos.
     * La marca se obtiene desde el adptador.
     */
    public AdaptadorProductos(ArrayList<ProductoEntity> dataProductos, Context context ) {

        this.dataProductos = dataProductos;                               // Datos con los productos
        this.context = context;

    }

    /**
     * Método que se ejecuta al crear el ViewHolder
     *
     * @param parent   es el componente que recibe el ViewHolder, en este caso un RecyclerView
     * @param viewType
     * @return El ViewHolder que se cargará en el RecyclerView
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Obtenemos la vista del CardView con el diseño del producto
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.cardview_producto, null);

        // Establecer los oyentes
        view.setOnClickListener( this );
        view.setOnLongClickListener( this );

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
        // correspondiente a la posición recibida como argumento
        holder.binData(dataProductos.get(position));

    }

    /**
     * Devuelve el número de elementos que forman la colección
     *
     * @return el número de elementos
     */
    @Override
    public int getItemCount() {
        return dataProductos.size();
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
        ImageView imagen;                                          // Muestra la imagen del producto
        TextView denominacion, marca;                          // Muestra la denominación y la marca

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
        }

        /**
         * Método para enlazar los datos del Producto con los del CardView (que son los mismos
         * que los de este ViewHolder)
         *
         * @param producto
         */
        public void binData(ProductoEntity producto) {

            // Si la imagen no es nula, la cargamos
/*            if( !producto.getImagen().isEmpty() ){
                Glide.with( context )
                        .load( producto.getImagen() )
                        .into( imagen );
            }*/

            // Obtener la denominación y la marca
            denominacion.setText(producto.getDenominacion());
            marca.setText( String.valueOf( producto.getMarca() ) );
//            marca.setText(marcaData.get(producto.getMarca() - 1).getName());

        }

    }
    /**********************************************************************************************/
    /******* OYENTES  *****************************************************************************/
    /**********************************************************************************************/

    /**
     * Inicializar los oyentes
     */
    public void setOnClickListener( View.OnClickListener listener ){
        this.clickListener = listener;
    }

    public void setOnlongClickListener( View.OnLongClickListener listener ){
        this.onLongClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if( clickListener != null ){
            clickListener.onClick(view);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if ( onLongClickListener != null ){
            onLongClickListener.onLongClick(view);
        }
        return false;
    }
}

