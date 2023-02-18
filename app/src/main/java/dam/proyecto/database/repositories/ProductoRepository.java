package dam.proyecto.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.ProductoEntity;

/**
 * Repositorio para los productos
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.17
 */
public class ProductoRepository extends Repositorio {

    public ProductoRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.productoDao().clear();
    }

    /**
     * Inserta una colección de productos
     */
    public void insertAll( List<ProductoEntity> data ){
        db.productoDao().insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
