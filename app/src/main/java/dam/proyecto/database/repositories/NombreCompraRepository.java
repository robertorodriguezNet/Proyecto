package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.entity.ProductoEntity;

/**
 *
 * @author Roberto Rodríguez
 * @since 23/01/2023
 * @version 2023.02.26
 */
public class NombreCompraRepository extends Repositorio {

    public NombreCompraRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.nombreCompraDao().clear();
    }

    /**
     * Inserta un objeto en la base de datos
     * @param objeto que se debe insertar
     */
    public void insert( NombreCompraEntity objeto ){
        db.nombreCompraDao().insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     * @param data colección de objetos
     */
    public void insertAll( List<NombreCompraEntity> data ){
        db.nombreCompraDao().insertAll( data );
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return
     */
    public ArrayList<NombreCompraEntity> getAll(){
        return (ArrayList<NombreCompraEntity>) db.nombreCompraDao().getAll();
    }

    /**
     * Obtener un nombre de la compra a partir del id
     * @param id el id de la compra, con formato aammddhhmm
     * @return
     */
    public NombreCompraEntity getById( String id ){
        return (NombreCompraEntity) db.nombreCompraDao().findById( id );
    }

    /**
     * Elimina la compra de la base de datos
     * @param compra que se debe eliminar
     */
    public void delete( NombreCompraEntity compra ){
        db.nombreCompraDao().delete( compra );
    }

    /**
     * Comprueba si existe un nombre de la compra en la base de datos.
     * El nombre de la compra puede estar repetido, pero no el id
     * @param id el id buscado
     * @return true | false si existe o no el nombre de la compra
     */
    public boolean existsIdDeLaCompra(String id) {
        NombreCompraEntity objeto = null;
        objeto = db
                .nombreCompraDao()
                .findById(id);
        return objeto != null;
    }

    public void update(NombreCompraEntity nombreCompra) {
        db.nombreCompraDao().update( nombreCompra );
    }

    public boolean existsNombreDeLaCompra(String id) {
        NombreCompraEntity objeto = null;
        objeto = db
                .nombreCompraDao()
                .findById(id);
        return objeto != null;
    }

    /**
     * Devuelve un listado con las compras hechas en un comercio dado
     * @param idComercio
     * @return
     */
    public ArrayList<String> getAllByIdComercio( int idComercio ){
        return (ArrayList<String>) db.nombreCompraDao().getAllByComercio( idComercio );
    }

    /**
     * Devuleve el id del comercio asociado a la compra dada
     * @param idCompra compra de la que se quiere obtener el comercio
     * @return int el id de la compra
     */
    public int getIdComercio( String idCompra ){
        return db.nombreCompraDao().getIdComercio( idCompra );
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
