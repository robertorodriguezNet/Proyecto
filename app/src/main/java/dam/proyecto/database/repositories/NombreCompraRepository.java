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
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.20
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
        return (ArrayList<NombreCompraEntity>) getDb().nombreCompraDao().getAll();
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

    @Override
    public String toString() {
        return super.toString();
    }
}
