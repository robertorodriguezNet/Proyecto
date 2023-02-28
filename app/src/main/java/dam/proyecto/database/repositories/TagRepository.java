package dam.proyecto.database.repositories;

import android.content.Context;
import android.nfc.Tag;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.TagEntity;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.18
 */
public class TagRepository extends Repositorio {

    public TagRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.tagDao().clear();
    }

    public ArrayList<TagEntity> getAll(){
        return (ArrayList<TagEntity>) db.tagDao().getAll();
    }

    /**
     * Devuelve un listado con tan sólo los nombres
     * @return
     */
    public ArrayList<String> getNombres(){
        ArrayList<TagEntity> objetos = getAll();
        ArrayList<String> nombres = new ArrayList<>();

        for ( TagEntity objeto: objetos ) {
            nombres.add( objeto.getName() );
        }

        return nombres;
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<TagEntity> data ){
        db.tagDao().insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
