package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.CompraDao;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.relaciones.VistaCompra;


/**
 * @author Roberto Rodríguez Jiménez
 * @since 23/01/2023
 * @version 2023.03.01
 */
public class CompraRepository extends Repositorio {

    private final CompraDao DAO;

    public CompraRepository(Context context) {
        super(context);
        this.DAO = db.compraDao();
    }


    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Borrar un registro de la compra
     */
    public void delete( CompraEntity compra ){
        DAO.delete( compra );
    }

    public ArrayList<CompraEntity> getAll(){
        return (ArrayList<CompraEntity>) DAO.getAll();
    }

    /**
     * Devuelve todas las compras de un producto dado
     * @param idProducto buscado
     * @return lista de compras
     */
    public ArrayList<CompraEntity> getAllByProducto( String idProducto){
        return (ArrayList<CompraEntity>) DAO.getAllByProducto( idProducto );
    }

    /**
     * Devuelve una compra a partir del id recibido
     * @param id buscado
     * @return compra relacionada con el id
     */
    public CompraEntity getById( String id ){
        return DAO.findById( id );
    }

    /**
     * Devuelve un listado con los comercios en los que se ha comprado un producto
     * @param idProducto buscado
     * @return la colección de comercios
     */
    public ArrayList<Integer> getComerciosByProducto( String idProducto ){
        return (ArrayList<Integer>) DAO.getComerciosByProducto( idProducto );
    }

    // Obtener la compra por una compra dada
    public ArrayList<CompraEntity> getProductosByFecha(String fecha ){
        return (ArrayList<CompraEntity>) DAO.findByFecha( fecha );
    }

    /**
     * Devuelve la última compra de un producto
     * @param idProducto id del producto
     * @return CompraEntity el objeto compra
     */
    public CompraEntity getUltimaCompraByProducto( String idProducto ){
        return DAO.getUltimaCompraByProducto( idProducto );
    }

    /**
     * Devuelve la última compra de un producto en un comercio
     * @param idProducto producto buscado
     * @param idComercio comercio buscado
     * @return la compra relacionada
     */
    public VistaCompra getUltimaCompraDeProductoEnComercio(String idProducto, int idComercio ){
        return DAO.getUltimaCompraDeProductoEnComercio(idProducto,idComercio);
    }

    /**
     * Obtener el último precio de un producto en un comercio dado, descartando
     * la fecha en la que se está editando dicho producto
     * @param idProducto producto buscado
     * @param fecha fecha de edición actual del producto, para ser descartada
     * @return el último precio encontrado
     */
    public float getUltimoPrecio( String idProducto, String fecha ){
        float p = DAO.getUltimoPrecio( idProducto, fecha );
        Log.d("LDLC", "CompraRepository.getUltimoPecio() -- fecha actual " + fecha + " - " + p);
        return p;
    }


    /**
     * Inserta una compra
     * @param compra la compra que se quiere insertar
     */
    public void insert( CompraEntity compra ){

        try{
            DAO.insert( compra );
        }catch ( Exception e ){
            Log.d( "LDLC", "CompraRepository.insert() - Error al guardar la compra: " + compra.toString() );
        }

    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<CompraEntity> data ){
        DAO.insertAll( data );
    }

    @Override
    @NonNull
    public String toString() {
        return super.toString();
    }


    /**
     * Actualiza una compra
     * @param compra que se quiere actualizar
     */
    public void update( CompraEntity compra ){
        DAO.update( compra );
    }

    /**
     * Actualiza la fecha de un objeto CompraEntity
     * @param fechaAnterior la fecha buscada
     * @param fechaNueva la fecha nueva
     */
    public void updateFecha(String fechaAnterior, String fechaNueva) {
        DAO.updateFecha( fechaAnterior, fechaNueva);
    }
}
