package dam.proyecto.database.entity;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Compra")
public class CompraEntity {

    @PrimaryKey( autoGenerate = false )
    @NonNull
    private String id;                                                        // Es producto + fecha
    @ColumnInfo( name = "producto" ) private String producto;           // Código de barras en texto
    @ColumnInfo( name = "fecha" ) private String fecha;               // Formato aammddhhmm en texto
    @ColumnInfo( name = "precio" ) private float precio;     // Precio del pproducto en ese momento
    @ColumnInfo( name = "cantidad" ) private float cantidad; // float porque hay cantidades a granel
    @ColumnInfo( name = "pagado" ) private float pagado;   // Es lo que se ha pagado por el producto
    @ColumnInfo( name = "precioMedido" ) private float precioMedido;  // Precio por unidad de medida
    @ColumnInfo( name = "oferta" ) private int oferta;              // Precio por unidad de medida


    public CompraEntity(String producto,
                  String fecha,
                  float cantidad,
                  float pagado,
                        float precio,
                        float precioMedido) {

        this.id = producto + fecha;
        this.producto = producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.pagado = pagado;
        this.precio = precio;
        this.precioMedido = precioMedido;
        this.oferta = 0;                                               // No hay ofertas por defecto

    }

    public float getPrecioMedido() {
        return precioMedido;
    }

    public void setPrecioMedido(float precioMedido) {
        this.precioMedido = precioMedido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPagado() {
        return pagado;
    }

    public void setPagado(float pagado) {
        this.pagado = pagado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getOferta() {
        return oferta;
    }

    public void setOferta(int oferta) {
        this.oferta = oferta;
    }

    @Override
    public String toString() {
        return "CompraEntity{" +
                "id='" + id + '\'' +
                ", producto='" + producto + '\'' +
                ", fecha='" + fecha + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", pagado=" + pagado +
                ", precioMedido=" + precioMedido +
                ", oferta=" + oferta +
                '}';
    }
}