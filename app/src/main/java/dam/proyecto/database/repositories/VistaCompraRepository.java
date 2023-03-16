package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.VistaCompraDao;
import dam.proyecto.database.relaciones.VistaCompra;

/**
 * Repositorio para la vista de la compra
 * @author Roberto Rodríguez
 * @since 02/03/2023
 * @version 2023.03.02
 */
public class VistaCompraRepository extends Repositorio{

    private final VistaCompraDao DAO;

    public VistaCompraRepository(Context context) {
        super(context);
        this.DAO = getDb().vistaCompraDao();
    }

    /**
     * Devuelve un listados con las compras de un producto
     * @param idProducto buscado
     * @return la colección de compras
     */
    public ArrayList<VistaCompra> getVistaCompraByProducto( String idProducto ){
        return (ArrayList<VistaCompra>) DAO.getVistaCompraByProducto( idProducto );
    }

    /**
     * Devuelve un listados con las compras de una etiqueta
     * @param idTag buscada
     * @return la colección de compras
     */
    public ArrayList<VistaCompra> getVistaCompraByTag( int idTag ){
        return (ArrayList<VistaCompra>) DAO.getVistaCompraByTag( idTag );
    }

}
