package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.TagEntity;
import dam.proyecto.database.entity.TagsProductoEntity;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.18
 */
public class TagProductoRepository extends Repositorio {

    public TagProductoRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.tagsProductoDao().clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<TagsProductoEntity> data ){
        db.tagsProductoDao().insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
