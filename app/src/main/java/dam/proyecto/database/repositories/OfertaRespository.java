package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.OfertaEntity;
import dam.proyecto.database.entity.TagsProductoEntity;

/**
 * Repositorio para OfertaEntity
 * @since 2023/02/16
 * @author Roberto Rodríguez
 * @version 2023.02.18
 */
public class OfertaRespository extends Repositorio {
    public OfertaRespository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.ofertaDao().clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<OfertaEntity> data ){
        db.ofertaDao().insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
