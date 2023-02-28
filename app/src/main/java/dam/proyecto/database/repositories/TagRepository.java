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
     * Devuelve un listado con las etiquetas pedidas
     * @param ids listado de etiquetas pedidas
     * @return
     */
    public ArrayList<String> getNombres( ArrayList<Integer> ids ){

        ArrayList<String> etiquetas = new ArrayList<>();

        for(Integer id : ids) {
            etiquetas.add( db.tagDao().getNameById( id ));
        }

        return etiquetas;
    }

    /**
     * Devuelve true si el tag existe
     * @param tag
     * @return
     */
    public boolean exists( String tag ){
        TagEntity tagEntity = null;
        tagEntity = db.tagDao().findByName( tag );
        return tagEntity != null;
    }
    /**
     * Devuelve el objeto si el tag existe
     * @param tag
     * @return
     */

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

    /**
     * Inserta un nuevo tag y devuelve el id
     * @param tag
     * @return
     */
    public Long insert( String tag ){

        TagEntity newTag = new TagEntity( tag );
        db.tagDao().insert( newTag );

        if( exists( tag ) ){
            newTag = db.tagDao().findByName( tag );
        }

        // Si la etiqueta no existiera, el id = 0
        return newTag.getId();
    }

}
