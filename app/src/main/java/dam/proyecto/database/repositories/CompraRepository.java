package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.CompraDao;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * @author Roberto Rodríguez Jiménez
 * @since 23/01/2023
 * @version 2023.03.01
 */
public class CompraRepository extends Repositorio {

    private CompraDao dao;

    public CompraRepository(Context context) {
        super(context);
        this.dao = db.compraDao();
    }

    public ArrayList<CompraEntity> getAll(){
        return (ArrayList<CompraEntity>) dao.getAll();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        dao.clear();
    }

    /**
     * Borrar un registro de la compra
     */
    public void delete( CompraEntity compra ){
        dao.delete( compra );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<CompraEntity> data ){
        dao.insertAll( data );
    }

    /**
     * Inserta una compra
     * @param compra
     */
    public void insert( CompraEntity compra ){

        try{
            dao.insert( compra );
            Log.d( "LDLC", "CompraRepository.insert() - Compra guardada: " + compra.toString() );
        }catch ( Exception e ){
            Log.d( "LDLC", "CompraRepository.insert() - Error al guardar la compra: " + compra.toString() );
        }

    }
    // Obtener la compra por una compra dada
    public ArrayList<CompraEntity> getProductosByFecha(String fecha ){
        return (ArrayList<CompraEntity>) dao.findByFecha( fecha );
    }

    /**
     * Devuelve una compra a partir del id recibido
     * @param id
     * @return
     */
    public CompraEntity getCompra( String id ){
        return ( CompraEntity ) dao.findById( id );
    }



    /**
     * Actualiza una compra
     * @param compra
     */
    public void update( CompraEntity compra ){
        dao.update( compra );
    }

    /**
     * Devuelve todas las compras de un producto dado
     * @param idProducto
     * @return
     */
    public ArrayList<CompraEntity> getAllByProducto( String idProducto){
        return (ArrayList<CompraEntity>) dao.getAllByProducto( idProducto );
    }

    /**
     * Devuelve la última compra de un producto
     * @param idProducto id del producto
     * @return CompraEntity el objeto compra
     */
    public CompraEntity getUltimaCompraByProducto( String idProducto ){
        return ( CompraEntity ) dao.getUltimaCompraByProducto( idProducto );
    }

    /**
     * Obtener el último precio de un producto en un comercio dado, descartando
     * la fecha en la que se está editando dicho producto
     * @param idProducto producto buscado
     * @param fecha fecha de edición actual del producto, para ser descartada
     * @return
     */
    public float getUltimoPrecio( String idProducto, String fecha ){
        float p = dao.getUltimoPrecio( idProducto, fecha );
        Log.d("LDLC", "CompraRepository.getUltimoPecio() -- fecha actual " + fecha + " - " + p);
        return p;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
