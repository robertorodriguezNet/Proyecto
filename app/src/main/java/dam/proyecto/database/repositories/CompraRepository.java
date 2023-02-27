package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.25
 */
public class CompraRepository extends Repositorio {

    public CompraRepository(Context context) {
        super(context);
    }

    public ArrayList<CompraEntity> getAll(){
        return (ArrayList<CompraEntity>) db.compraDao().getAll();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.compraDao().clear();
    }

    /**
     * Borrar un registro de la compra
     */
    public void delete( CompraEntity compra ){
        db.compraDao().delete( compra );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<CompraEntity> data ){
        db.compraDao().insertAll( data );
    }

    // Obtener la compra por una compra dada
    public ArrayList<CompraEntity> getProductosByFecha(String fecha ){
        return (ArrayList<CompraEntity>) db.compraDao().findByFecha( fecha );
    }

    public CompraEntity getCompra( String id ){
        return ( CompraEntity ) db.compraDao().findById( id );
    }

    /**
     * Inserta una compra
     * @param compra
     */
    public void insert( CompraEntity compra ){

        try{

            db.compraDao().insert( compra );

            Log.d( "LDLC", "CompraRepository.insert() - Compra guardada: " + compra.toString() );

        }catch ( Exception e ){
            Log.d( "LDLC", "CompraRepository.insert() - Error al guardar la compra: " + compra.toString() );
        }

    }

    /**
     * Actualiza una compra
     * @param compra
     */
    public void update( CompraEntity compra ){
        db.compraDao().update( compra );
    }

    /**
     * Devuelve todas las compras de un producto dado
     * @param idProducto
     * @return
     */
    public ArrayList<CompraEntity> getAllByProducto( String idProducto){
        return (ArrayList<CompraEntity>) db.compraDao().getAllByProducto( idProducto );
    }

    /**
     * Obtener el último precio de un producto en un comercio dado, descartando
     * la fecha en la que se está editando dicho producto
     * @param idProducto producto buscado
     * @param fecha fecha de edición actual del producto, para ser descartada
     * @return
     */
    public float getUltimoPrecio( String idProducto, String fecha ){
        float p = db.compraDao().getUltimoPrecio( idProducto, fecha );
        Log.d("LDLC", "CompraRepository.getUltimoPecio() -- fecha actual " + fecha + " - " + p);
        return p;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
