package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;

/**
 *
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.17
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
     * Inserta una colección de objetos
     */
    public void insertAll( List<NombreCompraEntity> data ){
        db.nombreCompraDao().insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
