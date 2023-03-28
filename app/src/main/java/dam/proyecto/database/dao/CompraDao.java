package dam.proyecto.database.dao;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.relaciones.VistaCompra;

import java.util.List;

@Dao
public interface CompraDao {

    // CREATE -------------------------------------
    @Insert
    void insert( CompraEntity object);

    @Insert
    void insertAll( List<CompraEntity> objects);

    // READ ---------------------------------------
    @Query( "SELECT * FROM Compra")
    List<CompraEntity> getAll();

    @Query( "SELECT * FROM Compra WHERE producto = :idProducto ORDER BY fecha DESC")
    List<CompraEntity> getAllByProducto( String idProducto );

    @Query( "SELECT * FROM Compra WHERE producto = :idProducto ORDER BY fecha DESC LIMIT 1")
    CompraEntity getUltimaCompraByProducto( String idProducto );

    @Query( "SELECT * FROM Compra WHERE id = :id")
    CompraEntity findById( String id );

    @Query( "SELECT * FROM Compra WHERE fecha LIKE :fecha")
    List<CompraEntity> findByFecha( String fecha );

    @Query( "select p.denominacion, m.name, c.precio, c.precioMedido, p.medida, c.fecha " +
            "from compra as c, Nombrecompra as nc, Productos as p, comercio as m " +
            "where producto = :idProducto and nc.id = c.fecha and c.producto = p.id " +
            "and nc.comercio = :idComercio and m.id = nc.comercio " +
            "order by c.fecha desc limit 1" )
    VistaCompra getUltimaCompraDeProductoEnComercio( String idProducto, int idComercio );

    @Query( "SELECT DISTINCT nc.comercio " +
            "FROM compra AS c, Nombrecompra AS nc, Productos AS p " +
            "WHERE producto = :idProduct AND nc.id = c.fecha AND c.producto = p.id;" )
    List<Integer> getComerciosByProducto( String idProduct );

    @Query( " SELECT precio FROM compra WHERE producto = :producto AND fecha != :fecha ORDER BY fecha DESC LIMIT 1 ")
    float getUltimoPrecio( String producto, String fecha );

    // UPDATE ------------------------------------
    @Update
    void update( CompraEntity object );

    @Query( "UPDATE Compra SET fecha = :fechaNueva WHERE fecha = :fechaAnterior")
    void updateFecha( String fechaAnterior, String fechaNueva);

    // DELETE ------------------------------------
    @Delete
    void delete( CompraEntity object );

    @Query( "DELETE FROM compra" )
    void clear();

    }
