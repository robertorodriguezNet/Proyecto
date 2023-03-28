package dam.proyecto.database.relaciones;

/**
 * Devuelve
 * ----------------------------------------------
 * name    	precio          	fecha
 * mercadona	1.52999997138977	2302101928
 * lupa     	2.28999996185303	2301171451
 * lupa    	1.52999997138977	2211050000 *
 * ----------------------------------------------
 *
 * @author Roberto Rodríguez Jiménez
 * @since 02/03/2023
 * @version 2023.03.02
 *
 * Se declaran dos datos que se necesitan mostrar.
 * Las claves no se muestran porque se entiende que ya se tienen,
 * pues son necesarias para obetener los registros.
 */
public class VistaCompra {

     public String denominacion;
     public String name;                                                      // Nombre del comercio
     public String precio;
     public String precioMedido;
     public String medida;
     public String fecha;

     @Override
     public String toString() {
          return "VistaCompra{" +
                  "denominacion='" + denominacion + '\'' +
                  ", name='" + name + '\'' +
                  ", precio='" + precio + '\'' +
                  ", precioMedido='" + precioMedido + ' ' + medida + '\'' +
                  ", fecha='" + fecha + '\'' +
                  '}';
     }
}
