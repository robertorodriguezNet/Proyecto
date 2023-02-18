package dam.proyecto.database.repositories;

import android.content.Context;

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

    @Override
    public String toString() {
        return super.toString();
    }

}
