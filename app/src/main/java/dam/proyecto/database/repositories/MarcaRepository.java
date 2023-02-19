package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.MarcaEntity;

/**
 * Repositorio para las marcas
 * @since 2023/02/07
 * @author Roberto Rodríguez
 * @version 2023.02.17
 */
public class MarcaRepository extends Repositorio{
    public MarcaRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.marcaDao().clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaEntity> data ){
        db.marcaDao().insertAll( data );
    }

    /**
     * Devuelve la colección entera de objetos guardados.
     * @return los datos.
     */
    public ArrayList<MarcaEntity> getAll(){
        return (ArrayList<MarcaEntity>) getDb().marcaDao().getAll();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
