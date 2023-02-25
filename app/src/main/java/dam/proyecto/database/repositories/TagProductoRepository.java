package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.TagEntity;
import dam.proyecto.database.entity.TagsProductoEntity;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.25
 */
public class TagProductoRepository extends Repositorio {

    public TagProductoRepository(Context context) {
        super(context);
    }

    public ArrayList<TagsProductoEntity> getAll(){
        return (ArrayList<TagsProductoEntity>) db.tagsProductoDao().getAll();
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
