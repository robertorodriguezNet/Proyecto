/**
 * En esta interfaz definimos las consultas que queramos hacer a l base de datos.
 */
package dam.proyecto.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import dam.proyecto.database.relaciones.VistaCompra;


/**
 * Clase que modela una compra con todos sus datos
 *
 * @author Roberto Rodríguez Jiménez
 * @since 02/03/2023
 * @version 2023.03.02
 */
@Dao
public interface VistaCompraDao {

    @Query( "select p.denominacion, m.name, c.precio, c.preciomedido, c.fecha " +
            "from Compra as c, Nombrecompra as n, Comercio as m, Productos as p " +
            "where c.producto = :idProducto " +
            "and c.fecha = n.id " +
            "and m.id = n.comercio " +
            "and p.id = c.producto " +
            "order by c.fecha DESC")
    List<VistaCompra> getVistaCompraByProducto(String idProducto );

    @Query( "select p.denominacion, m.name, c.precio, c.preciomedido, c.fecha " +
            "from Compra as c, Nombrecompra as n, Comercio as m, Productos as p " +
            "where c.producto in (SELECT producto FROM TagsProducto WHERE tag = :idTag) " +
            "and c.fecha = n.id " +
            "and m.id = n.comercio " +
            "and p.id = c.producto " +
            "order by c.fecha DESC")

    List<VistaCompra> getVistaCompraByTag( int idTag );

}
