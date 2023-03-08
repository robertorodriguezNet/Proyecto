package dam.proyecto.database.repositories;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import androidx.room.Dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.TagDao;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.TagEntity;

/**
 * @author Roberto Rodríguez
 * @since 2023/01/23
 * @version 2023.03.01
 */
public class TagRepository extends Repositorio {

    private TagDao dao;

    public TagRepository(Context context) {
        super(context);
        this.dao = db.tagDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        dao.clear();
    }

    /**
     * Devuelve un TagEntity a partir de un nombre
     * @param name es el nombre buscado
     * @return TagEntity relacionado con el nombre
     */
    public TagEntity findByName( String name ){
        return dao.findByName( name );
    }

    public ArrayList<TagEntity> getAll(){
        return (ArrayList<TagEntity>) db.tagDao().getAll();
    }

    /**
     * Devuelve una colección de tags contienen el texto
     * @param texto
     * @return
     */
    public ArrayList<TagEntity> getAll( String texto ){
        return (ArrayList<TagEntity>) dao.getAll( texto );
    }
    /**
     * Devuelve una colección de id de las etiquetas que contienen el texto
     * @param texto
     * @return
     */
    public ArrayList<Integer> getAllId( String texto ){
        return (ArrayList<Integer>) dao.getAllId( texto );
    }

    /**
     * Devuelve el id de una etiqueta por su nombre
     * @param name el tag buscado
     * @return
     */
    public int getIdByName( String name ){
        return dao.getIdByName( name );
    }

    /**
     * Devuelve el valor máximo de los id de la tabla
     * @return int id
     */
    public int getMaxId(){
        return dao.getMaxId();
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
            etiquetas.add( dao.getNameById( id ));
        }
        return etiquetas;
    }

    /**
     * Devuelve una colección con los id de los productos que contienen el texto
     * en la etiqueta
     * @param texto
     * @return
     */
    public ArrayList<String> getProductosByTag( String texto ){
        ArrayList<String> list = (ArrayList<String>) dao.getProductosByTag( texto );
        ArrayList<String> idList = new ArrayList<>();

        Iterator it = list.iterator();
        while ( it.hasNext() ){
            String producto = (String) it.next();
            idList.add( producto );
        }

        return idList;
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<TagEntity> data ){
        dao.insertAll( data );
    }

    /**
     * Inserta un objeto
     */
    public void insert( TagEntity tag ){
        dao.insert( tag );
    }



    @Override
    public String toString() {
        return super.toString();
    }

}
