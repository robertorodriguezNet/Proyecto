package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.MedidaEntity;

/**
 * Repositorio para las medidas
 * @since 2023/02/06
 * @author Roberto Rodríguez
 * @version 2023.02.18
 */
public class MedidaRepository extends Repositorio{
    public MedidaRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.medidaDao().clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MedidaEntity> data ){
        db.medidaDao().insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
