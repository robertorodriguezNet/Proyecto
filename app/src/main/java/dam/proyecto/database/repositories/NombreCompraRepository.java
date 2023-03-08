package dam.proyecto.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.NombreCompraDao;
import dam.proyecto.database.entity.NombreCompraEntity;

/**
 *
 * @author Roberto Rodríguez
 * @since 23/01/2023
 * @version 2023.03.07
 */
public class NombreCompraRepository extends Repositorio {

    private final NombreCompraDao DAO;

    public NombreCompraRepository(Context context) {
        super(context);
        DAO = db.nombreCompraDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Elimina la compra de la base de datos
     * @param compra que se debe eliminar
     */
    public void delete( NombreCompraEntity compra ){
        DAO.delete( compra );
    }

    /**
     * Comprueba si existe un nombre de la compra en la base de datos.
     * El nombre de la compra puede estar repetido, pero no el id
     * @param id el id buscado
     * @return true | false si existe o no el nombre de la compra
     */
    public boolean existsIdDeLaCompra(String id) {
        NombreCompraEntity  objeto = db
                .nombreCompraDao()
                .findById(id);
        return objeto != null;
    }

    public boolean existsNombreDeLaCompra(String id) {
        NombreCompraEntity objeto = db
                .nombreCompraDao()
                .findById(id);
        return objeto != null;
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return listado completo de objetos
     */
    public ArrayList<NombreCompraEntity> getAll(){
        return (ArrayList<NombreCompraEntity>) DAO.getAll();
    }

    /**
     * Devuelve un listado con las compras hechas en un comercio dado
     * @param idComercio buscado
     * @return listado de compras en el comercio
     */
    public ArrayList<String> getAllByIdComercio( int idComercio ){
        return (ArrayList<String>) DAO.getAllByComercio( idComercio );
    }

    /**
     * Obtener un nombre de la compra a partir del id
     * @param id el id de la compra, con formato aammddhhmm
     * @return nombre de la compra
     */
    public NombreCompraEntity getById( String id ){
        return DAO.findById( id );
    }

    /**
     * Devuleve el id del comercio asociado a la compra dada
     * @param idCompra compra de la que se quiere obtener el comercio
     * @return int el id de la compra
     */
    public int getIdComercio( String idCompra ){
        return DAO.getIdComercio( idCompra );
    }

    public String getNombreComercioByCompra( String idCompra ){
        return DAO.getNombreComercio( idCompra );
    }

    /**
     * Inserta un objeto en la base de datos
     * @param objeto que se debe insertar
     */
    public void insert( NombreCompraEntity objeto ){
        DAO.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     * @param data colección de objetos
     */
    public void insertAll( List<NombreCompraEntity> data ){
        DAO.insertAll( data );
    }

    public void update(NombreCompraEntity nombreCompra) {
        DAO.update( nombreCompra );
    }




    @Override
    @NonNull
    public String toString() {
        return super.toString();
    }

}
