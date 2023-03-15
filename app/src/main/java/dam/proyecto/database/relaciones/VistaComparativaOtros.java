package dam.proyecto.database.relaciones;


/**
 * select c.producto, p.denominacion, c.fecha, c.precio, cm.name
 * from  Compra as c, Productos as p, Nombrecompra as nc, comercio as cm
 * where c.producto in ( select producto from TagsProducto where tag = 35)
 * 		and c.producto = p.id
 * 		and nc.id = c.fecha
 * 		and cm.id = nc.comercio
 *
 * Devuelve
 * ----------------------------------------------
 * denominacion   comercio    	precio          	fecha
 *              mercadona  	1.52999997138977	2302101928
 *              lupa     	2.28999996185303	2301171451
 *              lupa    	1.52999997138977	2211050000 *
 * ----------------------------------------------
 *
 * @author Roberto Rodríguez Jiménez
 * @since 02/03/2023
 * @version 2023.03.02
 */
public class VistaComparativaOtros {

    private String denominacion;
    private String comercio;
    private String precio;
    private String fecha;

    public VistaComparativaOtros(String denominacion, String comercio, String precio, String fecha) {
        this.denominacion = denominacion;
        this.comercio = comercio;
        this.precio = precio;
        this.fecha = fecha;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
