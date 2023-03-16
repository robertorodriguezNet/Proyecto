package dam.proyecto.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.TagDao;
import dam.proyecto.database.entity.TagEntity;

/**
 * @author Roberto Rodríguez
 * @since 2023/01/23
 * @version 2023.03.01
 */
public class TagRepository extends Repositorio {

    private final TagDao DAO;

    public TagRepository(Context context) {
        super(context);
        this.DAO = db.tagDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Devuelve un TagEntity a partir de un nombre
     * @param name es el nombre buscado
     * @return TagEntity relacionado con el nombre
     */
    public TagEntity findByName( String name ){
        return DAO.findByName( name );
    }

    /**
     * Devuelve un TagEntity a partir de un id
     * @param id es el id buscado
     * @return TagEntity relacionado con el id
     */
    public TagEntity findById( int id ){
        return DAO.findById( id );
    }

    public ArrayList<TagEntity> getAll(){
        return (ArrayList<TagEntity>) db.tagDao().getAll();
    }

    /**
     * Devuelve una colección de tags contienen el texto
     * @param texto buscado
     * @return colección de objetos Tag que contienen el texto
     */
    public ArrayList<TagEntity> getAll( String texto ){
        return (ArrayList<TagEntity>) DAO.getAll( texto );
    }
    /**
     * Devuelve una colección de id de las etiquetas que contienen el texto
     * @param texto buscado
     * @return colección de id's de las etiquetas que contienen el texto
     */
    public ArrayList<Integer> getAllId( String texto ){
        return (ArrayList<Integer>) DAO.getAllId( texto );
    }

    /**
     * Devuelve el id de una etiqueta por su nombre
     * @param name el tag buscado
     * @return el id de la etiqueta pedida
     */
    public int getIdByName( String name ){
        return DAO.getIdByName( name );
    }

    /**
     * Devuelve el valor máximo de los id de la tabla
     * @return int id
     */
    public int getMaxId(){
        return DAO.getMaxId();
    }

    /**
     * Devuelve el nombre de una etiqueta por su id
     * @param id de la etiqueta
     * @return el nombre
     */
    public String getNameById( int id ){
        return DAO.getNameById( id );
    }


    /**
     * Devuelve una colección con los id de los productos que contienen el texto
     * en la etiqueta
     * @param texto el texto buscado
     * @return colección de id's de productos que contienen el id
     */
    public ArrayList<String> getProductosByTag( String texto ){
        return (ArrayList<String>) DAO.getProductosByTag( texto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<TagEntity> data ){
        DAO.insertAll( data );
    }

    /**
     * Inserta un objeto
     */
    public void insert( TagEntity tag ){
        DAO.insert( tag );
    }


    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

}
