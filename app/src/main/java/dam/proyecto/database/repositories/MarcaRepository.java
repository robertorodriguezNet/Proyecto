package dam.proyecto.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.MarcaDao;
import dam.proyecto.database.entity.MarcaEntity;

/**
 * Repositorio para las marcas
 * @author Roberto Rodríguez
 * @since 06/02/2023
 * @version 2023.02.21
 */
public class MarcaRepository extends Repositorio{

    private final MarcaDao DAO;

    public MarcaRepository(Context context) {
        super(context);
        DAO = db.marcaDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Devuelve el objeto a partir del nombre
     * @param name el nombre buscado
     * @return el objeto
     */
    public MarcaEntity findByName( String name ){
        return DAO.findByName( name );
    }

    /**
     * Devuelve el objeto a partir del id
     * @param id de la marca buscada
     * @return el objeto
     */
    public MarcaEntity findById( int id ){
        return DAO.findById( id );
    }

    /**
     * Inserta el objeto en la tabla
     * @param objeto que se quiere insertar
     */
    public void insert( MarcaEntity objeto ){
        DAO.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaEntity> data ){
        DAO.insertAll( data );
    }

    /**
     * Devuelve la colección entera de objetos guardados.
     * @return los datos.
     */
    public ArrayList<MarcaEntity> getAll(){
        return (ArrayList<MarcaEntity>) DAO.getAll();
    }

    /**
     * Devuelve el id más alto registrado
     * @return el id más alto
     */
    public int getMaxId(){
        return DAO.getMaxId();
    }


    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

}
