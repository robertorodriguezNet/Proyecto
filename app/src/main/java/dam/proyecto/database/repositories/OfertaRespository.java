package dam.proyecto.database.repositories;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.OfertaDao;
import dam.proyecto.database.entity.OfertaEntity;

/**
 * Repositorio para OfertaEntity
 * @author Roberto Rodríguez
 * @since 16/02/2023
 * @version 2023.03.08
 */
public class OfertaRespository extends Repositorio {

    public final OfertaDao DAO;

    public OfertaRespository(Context context) {
        super(context);
        DAO = db.ofertaDao();
    }

    /**
     * Devuelve toda la colección de objetos
     * @return la colección de objetos
     */
    public ArrayList<OfertaEntity> getAll(){
        return (ArrayList<OfertaEntity>)DAO.getAll();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        DAO.clear();
    }

    /**
     * Inserta un objeto en la tabla
     * @param objeto que se inserta
     */
    public void insert( OfertaEntity objeto ){
        DAO.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<OfertaEntity> data ){
        DAO.insertAll( data );
    }

    @Override
    @NonNull
    public String toString() {
        return super.toString();
    }
}
