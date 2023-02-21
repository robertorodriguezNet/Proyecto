package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.18
 */
public class CompraRepository extends Repositorio {

    public CompraRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.compraDao().clear();
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

            Log.d( "LDLC", "Compra guardada: " + compra.toString() );

        }catch ( Exception e ){
            Log.d( "LDLC", "Error al guardar la compra: " + compra.toString() );
        }

    }

    /**
     * Actualiza una compra
     * @param compra
     */
    public void update( CompraEntity compra ){
        db.compraDao().update( compra );
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
