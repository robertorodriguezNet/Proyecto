package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
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

    /**
     * Devuelve la colección completa de medidas.
     * @return
     */
    public ArrayList<MedidaEntity> getAll(){
        return (ArrayList<MedidaEntity>) db.medidaDao().getAll();
    }

    public MedidaEntity getById( String id ){
        return ( MedidaEntity ) db.medidaDao().findById( id );
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
