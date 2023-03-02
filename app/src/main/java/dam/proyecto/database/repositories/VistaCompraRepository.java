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

    private VistaCompraDao dao;

    public VistaCompraRepository(Context context) {
        super(context);
        this.dao = getDb().vistaCompraDao();
    }

    public ArrayList<VistaCompra> loadVistaCompraByProducto( String idProducto ){
        return (ArrayList<VistaCompra>) dao.loadVistaCompraByProducto( idProducto );
    }

}
