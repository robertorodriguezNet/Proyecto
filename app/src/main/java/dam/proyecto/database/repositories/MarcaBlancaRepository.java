package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.MarcaBlancaEntity;

public class MarcaBlancaRepository extends Repositorio {
    public MarcaBlancaRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.marcaBlancaDao().clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaBlancaEntity> data ){
        db.marcaBlancaDao().insertAll( data );
    }

    /**
     * Devuelve la colección entera de objetos guardados.
     * @return los datos.
     */
    public ArrayList<MarcaBlancaEntity> getAll(){
        return (ArrayList<MarcaBlancaEntity>) getDb().marcaBlancaDao().getAll();
    }

}
