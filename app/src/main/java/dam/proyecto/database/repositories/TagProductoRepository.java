package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.TagsProductoDao;
import dam.proyecto.database.entity.TagsProductoEntity;

/**
 * @author Roberto Rodríguez
 * @since 23/01/2023
 * @version 2023.02.28
 */
public class TagProductoRepository extends Repositorio {
    
    private final Context CONTEXT;
    private final TagsProductoDao DAO;
    
    public TagProductoRepository(Context context) {
        super(context);
        this.CONTEXT = context;
        DAO = db.tagsProductoDao();
    }


    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Devuelve una lista completa de los objetos
     * @return la lista de objetos
     */
    public ArrayList<TagsProductoEntity> getAll(){
        return (ArrayList<TagsProductoEntity>) DAO.getAll();
    }

    /**
     * Devuelve el valor máximo de los id's de la tabla
     * @return el id
     */
    public int getMaxId(){
        return DAO.getMaxId();
    }

    /**
     * Devuelve los tags de un producto
     * @param args producto
     * @return lista de id's asociados al producto
     */
    public ArrayList<Integer> getTagByProducto(String args){
        return (ArrayList<Integer>) DAO.getTagByProducto( args );
    }

    /**
     * Inserta un objeto en la table
     * @param objeto que se inserta
     */
    public void insert( TagsProductoEntity objeto ){
        DAO.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<TagsProductoEntity> data ){
        DAO.insertAll( data );
    }


    public TagsProductoEntity getAsociacion( String producto, int tag ){
        return DAO.getAsociacion( producto, tag );
    }

    @Override
    @NonNull
    public String toString() {
        return super.toString();
    }
}
